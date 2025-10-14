import os
from fastmcp import FastMCP
import sqlite3
import datetime
import re
from typing import Optional

DB_PATH = os.path.join(os.path.dirname(__file__), 'expenses.db')

CATEGORIES_PATH = os.path.join(os.path.dirname(__file__), 'categories.json')

mcp = FastMCP(name="Expense Tracker")


def get_conn():
    """Centralized connection helper."""
    return sqlite3.connect(DB_PATH)


def is_valid_date(s: str) -> bool:
    if not s or not isinstance(s, str):
        return False
    # Simple YYYY-MM-DD validation
    return bool(re.match(r"^\d{4}-\d{2}-\d{2}$", s))


def init_db():
    with get_conn() as conn:
        conn.execute('''
            CREATE TABLE IF NOT EXISTS expenses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                amount REAL NOT NULL,
                category TEXT NOT NULL,
                subcategory TEXT DEFAULT '',
                date TEXT NOT NULL,
                description TEXT DEFAULT ''
            )
        ''')
        # Add an index for faster queries on date/category
        conn.execute("CREATE INDEX IF NOT EXISTS idx_expenses_date ON expenses(date)")
        conn.execute("CREATE INDEX IF NOT EXISTS idx_expenses_category_date ON expenses(category, date)")


init_db()

@mcp.tool()
def add_expense(amount: float, category: str, subcategory: str = '', date: str = '', description: str = '') -> dict:
    """Add a new expense to the database."""
    # default date to today if not provided
    if not date:
        date = datetime.date.today().isoformat()
    elif not is_valid_date(date):
        return {"status": "error", "message": "date must be YYYY-MM-DD"}

    with get_conn() as conn:
        cursor = conn.cursor()
        cursor.execute('''
            INSERT INTO expenses (amount, category, subcategory, date, description)
            VALUES (?, ?, ?, ?, ?)
        ''', (amount, category, subcategory, date, description))
        conn.commit()
    return {"status": "ok", "id": cursor.lastrowid}

@mcp.tool()
def list_expenses(category: str = '', start_date: Optional[str] = None, end_date: Optional[str] = None) -> dict:
    """List expenses filtered by category and an optional date range.

    If start_date or end_date are missing, the function will return results across the full DB range.
    """
    # default to full-range when dates are missing
    if not start_date:
        start_date = '0001-01-01'
    if not end_date:
        end_date = '9999-12-31'

    if not (is_valid_date(start_date) and is_valid_date(end_date)):
        return {"status": "error", "message": "start_date and end_date must be YYYY-MM-DD if provided"}

    with get_conn() as conn:
        cursor = conn.execute('''
            SELECT id, amount, category, subcategory, date, description
            FROM expenses
            WHERE date BETWEEN ? AND ? AND (category = ? OR ? = '')
        ''', (start_date, end_date, category, category))
        expenses = cursor.fetchall()

    return {"status": "ok", "expenses": [{"id": row[0], "amount": row[1], "category": row[2], "subcategory": row[3], "date": row[4], "description": row[5]} for row in expenses]}

@mcp.tool()
def summarize(start_date, end_date, category=None):
    """Get total expenses by category within a date range.
    """
    query = """
        SELECT category, SUM(amount) as total
        FROM expenses
        WHERE date BETWEEN ? AND ?
    """
    params = [start_date, end_date]
    
    if category:  # Only add category filter if provided
        query += " AND category = ?"
        params.append(category)
    
    query += " GROUP BY category ORDER BY category ASC"
    
    with get_conn() as conn:
        cursor = conn.cursor()
        cursor.execute(query, params)
        results = cursor.fetchall()
    
    return {"status": "ok", "results": [{"category": row[0], "total": row[1]} for row in results]}

@mcp.tool()
def update_expense(expense_id: int, amount: float = None, category: str = None, subcategory: str = None, date: str = None, description: str = None) -> dict:
    """Update an existing expense."""
    fields = []
    params = []
    
    if amount is not None:
        fields.append("amount = ?")
        params.append(amount)
    if category is not None:
        fields.append("category = ?")
        params.append(category)
    if subcategory is not None:
        fields.append("subcategory = ?")
        params.append(subcategory)
    if date is not None:
        if not is_valid_date(date):
            return {"status": "error", "message": "date must be YYYY-MM-DD"}
        fields.append("date = ?")
        params.append(date)
    if description is not None:
        fields.append("description = ?")
        params.append(description)
    
    if not fields:
        return {"status": "error", "message": "no fields to update"}

    params.append(expense_id)

    with get_conn() as conn:
        conn.execute(f'''
            UPDATE expenses
            SET {', '.join(fields)}
            WHERE id = ?
        ''', params)
        conn.commit()

    return {"status": "ok"}

@mcp.tool()
def delete_expense(expense_id: int) -> dict:
    """Delete an expense by ID."""
    with get_conn() as conn:
        conn.execute('DELETE FROM expenses WHERE id = ?', (expense_id,))
        conn.commit()
    return {"status": "ok"}

@mcp.resource("expense://categories", mime_type="application/json")
def categories():
    try:
        with open(CATEGORIES_PATH, 'r', encoding="utf-8") as f:
            return f.read()
    except FileNotFoundError:
        return '[]'

if __name__ == "__main__":
    mcp.run()