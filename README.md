# Expense Tracker MCP Server

A Model Context Protocol (MCP) server that enables AI assistants like Claude to manage your personal expenses through natural conversation. Built with Python using `fastmcp` and `uv`.

## Features

- **Add Expenses**: Record expenses with amount, category, date, and description
- **List & Filter**: View expenses by category and date range
- **Summarize**: Get spending summaries grouped by category
- **Update Expenses**: Modify existing expense records
- **Delete Expenses**: Remove unwanted expense entries
- **Natural Language Interface**: Interact with your expense data conversationally through Claude

## Prerequisites

- Python 3.10 or higher
- [uv](https://github.com/astral-sh/uv) package manager

## Installation

```bash
# Clone the repository
git clone https://github.com/Khushi-c-sharma/expense-tracker-mcp-server.git
cd expense-tracker-mcp-server

# Install uv if you haven't already
curl -LsSf https://astral.sh/uv/install.sh | sh

# Install dependencies
uv sync
```

## Configuration

Add the server to your Claude Desktop configuration file:

**MacOS**: `~/Library/Application Support/Claude/claude_desktop_config.json`

**Windows**: `%APPDATA%/Claude/claude_desktop_config.json`

```json
{
  "mcpServers": {
    "expense-tracker": {
      "command": "uv",
      "args": [
        "--directory",
        "/path/to/expense-tracker-mcp",
        "run",
        "expense-tracker"
      ]
    }
  }
}
```

## Usage Examples

Once configured, you can interact with your expenses through Claude naturally:

```
You: "Add expense 2632 spent yesterday for shopping dress for my convocation"
Claude: *Adds the expense with proper categorization*

You: "Show me all my expenses this month"
Claude: *Lists and summarizes your monthly expenses*

You: "How much did I spend on food in September?"
Claude: *Provides category-specific spending summary*
```

## Available Tools

### `add_expense`
Add a new expense to the database.
- **Parameters**: amount (required), category (required), date, description, subcategory

### `list_expenses`
List expenses filtered by category and optional date range.
- **Parameters**: category, start_date, end_date

### `summarize`
Get total expenses by category within a date range.
- **Parameters**: start_date (required), end_date (required), category

### `update_expense`
Update an existing expense.
- **Parameters**: expense_id (required), amount, category, date, description, subcategory

### `delete_expense`
Delete an expense by ID.
- **Parameters**: expense_id (required)

## Database

Expenses are stored in a local SQLite database (`expenses.db`) with the following schema:

```sql
CREATE TABLE expenses (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  amount REAL NOT NULL,
  category TEXT NOT NULL,
  subcategory TEXT,
  date TEXT NOT NULL,
  description TEXT
);
```

## Development

```bash
# Run the server directly
uv run expense-tracker

# Run in development mode with auto-reload
uv run python src/expense_tracker/server.py

# Install new dependencies
uv add package-name

# Update dependencies
uv sync
```

## Project Structure

```
expense-tracker-mcp/
├── main.py                     # Main MCP server code
├── pyproject.toml              # Project configuration
├── uv.lock                     # Locked dependencies
├── expenses.db                 # SQLite database (created on first run)
└── README.md
```

## Use Cases

- **Personal Finance Tracking**: Monitor daily spending habits
- **Budget Management**: Track expenses by category to stay within budget
- **Expense Reports**: Generate summaries for tax purposes or reimbursements
- **Shopping Tracking**: Keep records of purchases and major expenses
- **Financial Analysis**: Analyze spending patterns over time

## Built With

- [fastmcp](https://github.com/jlowin/fastmcp) - Fast, Pythonic MCP server framework
- [uv](https://github.com/astral-sh/uv) - Fast Python package installer and resolver
- SQLite - Lightweight database for expense storage
- [Model Context Protocol](https://modelcontextprotocol.io/) - Protocol for AI-application integration

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Troubleshooting

**Server not appearing in Claude Desktop?**
- Ensure the path in `claude_desktop_config.json` is absolute and correct
- Restart Claude Desktop after configuration changes
- Check Claude Desktop logs for error messages

**Database errors?**
- Ensure the directory is writable
- Delete `expenses.db` to recreate the database from scratch

## Support

If you encounter any issues or have questions, please [open an issue](https://github.com/yourusername/expense-tracker-mcp/issues) on GitHub.

---

**Made with ❤️ for better expense tracking through AI**

