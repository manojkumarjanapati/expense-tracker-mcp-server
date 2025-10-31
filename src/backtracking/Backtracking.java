package backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class Backtracking {

    public static boolean isSubarray(char[] str, char[] subStr, int stop){
        int i = 0, j=0;
        while(i<=stop){
            if(str[i] == subStr[j]){
                i++;j++;
                if(j == subStr.length) return true;
            }else{
                // resetting i back. think of (str:aaab,subStr:aab), it fails for i = 0, so we should come back to 0 and set it to next index 1, we should not skip 1, it will pass now
                i = i - j + 1;
                j = 0;
            }
        }
        return false;
    }

    public static void permutationsExcludingSubstring(char[] str, char[] subStr, int l){
        if(l == str.length) System.out.println(new String(str));
        else{
            for(int i = l; i<str.length; i++){
                // swap i and l
                char tmp = str[i];
                str[i] = str[l];
                str[l] = tmp;

                if(!isSubarray(str, subStr, l)){
//                if(!Arrays.toString(str).contains(Arrays.toString(subStr))){
                    permutationsExcludingSubstring(str,subStr,l+1);
                }

                // swap i and l back again
                tmp = str[i];
                str[i] = str[l];
                str[l] = tmp;
            }
        }
    }

    public static void printPermutationsWithoutSubstring(String str, String subStr){
        permutationsExcludingSubstring(str.toCharArray(),subStr.toCharArray(),0);
    }

    public static void testPrintPermutationsWithoutSubstring(){
        String str = "ABC";
        String subStr = "AB";
        printPermutationsWithoutSubstring(str,subStr);
    }

    public static boolean ratInMazeDFS(int[][] maze,int x, int y, int tx, int ty){
        // maze rules :- 0 is wall, 1 is allowed path
        // my markings:- -1 as RAT successful Path to target
        if(x == tx && y == ty){
            maze[x][y] = -1; // RAT path marking
            return true;
        }

        if(x <= tx && y <= ty && maze[x][y] == 1){ // checking isSafe cell
            maze[x][y] = -1; // marking as successful RAT path blindly
            if(ratInMazeDFS(maze,x,y+1,tx,ty) || ratInMazeDFS(maze,x+1,y,tx,ty)) return true;
            maze[x][y] = 0; // paths through x,y doesn't have a solution, so making it 0 [unsuccessful,already visited and now blocked]
        }

        return false;
    }
    public static void ratInMaze(int[][] maze){
        int x = 0,y = 0; // source cell
        int tx = maze.length-1, ty = maze[0].length-1; // target cell
        boolean isRatPath = ratInMazeDFS(maze,x,y,tx,ty);
        for(int r = 0; r<=tx; r++){
            for(int c = 0; c<=ty; c++){
                if(maze[r][c] != -1) maze[r][c] = 0; // making all 0,1 as 0 so that only success path will be in maze
                else maze[r][c] = 1;
            }
        }
        System.out.println("Is Rat Path Possible: "+isRatPath);
        if(isRatPath){
            for(int[] row : maze){
                System.out.println(Arrays.toString(row));
            }
        }
    }

    public static void testRanInMaze(){
        int[][] maze = {{1,0,0,0},{1,1,0,1},{0,1,0,0},{1,1,1,1}};
        ratInMaze(maze);
    }

    public static boolean isSafe(int[][] board, int row, int col){
        // queens placed so far will be above this row
        // so we check with previously placed queens so far
        // checking in column(col) till this row
        for(int r = 0; r<row; r++) if(board[r][col] == 1) return false;
        // checking diagonals till this row
        for(int r=row,c=col; r>=0 && c>=0; r--,c--){
            if(board[r][c] == 1) return false;
        }
        for(int r=row,c=col; r>=0 && c < board.length; r--,c++){
            if(board[r][c] == 1) return false;
        }
        return true;
    }

    public static boolean nQueenRecursion(int[][] board, int row){
        if(row == board.length) return true; // all N-Queens are placed [one for each row]

        for(int col = 0; col < board[row].length; col++){
            if(isSafe(board,row,col)){
                board[row][col] = 1; // placing queen as it is safe as of now
                if(nQueenRecursion(board,row+1)) return true;
                board[row][col] = 0; // removing queen as it is not possible to place N-queens
            }
        }
        return false;
    }
    public static void nQueenProblem(int n){
        int[][] board = new int[n][n];
        boolean nQueensPossible = nQueenRecursion(board,0);
        System.out.println("N-Queens possible: "+nQueensPossible);
        if(nQueensPossible){
            for(int[] row: board) System.out.println(Arrays.toString(row));
        }
    }

    public static void testNQueenProblem(){
        int n = 6;
        nQueenProblem(n);
    }

    public static boolean isSafeSudokuNumber(int[][] grid, int r, int c, int num){
        // checking in row and column
        for(int k = 0; k<grid.length; k++){
            if(grid[r][k] == num || grid[k][c] == num) return false;
        }

        // checking in subGrid
        int subGridLen = (int) Math.sqrt(grid.length);
        int rFloor = subGridLen * (r/subGridLen);
        int cFloor = subGridLen * (c/subGridLen);
        for(int i = rFloor; i<rFloor+subGridLen; i++){
            for(int j = cFloor; j<cFloor+subGridLen; j++){
                if(grid[i][j] == num) return false;
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] grid, Stack<List<Integer>> emptyCells){
        if(emptyCells.isEmpty()) return true; // sudoku solved

        List<Integer> currEmptyCell = emptyCells.pop();
        int r = currEmptyCell.get(0);
        int c = currEmptyCell.get(1);

        for(int num = 1; num <= grid.length; num++){
            if(isSafeSudokuNumber(grid,r,c,num)){
                grid[r][c] = num; // placing num as it is safe for now
                if(solveSudoku(grid,emptyCells)) return true;
                grid[r][c] = 0; // removing num as it is not correct num
            }
        }

        emptyCells.push(currEmptyCell); // putting it back as it is again empty now

        return false;
    }

    public static void sudoku(int[][] grid){
        Stack<List<Integer>> emptyCells = new Stack<>();
        int n = grid.length;
        for(int i = n-1; i>=0; i--){
            for(int j = n-1; j>=0; j--){
                if(grid[i][j] == 0) emptyCells.push(List.of(i,j));
            }
        }

        boolean sudokuSolved = solveSudoku(grid,emptyCells);
        System.out.println("Sudoku solved: "+sudokuSolved);
        if(sudokuSolved){
            for(int[] row: grid) System.out.println(Arrays.toString(row));
        }
    }

    public static void testSudoku(){
        int[][] grid =  {{1,0,3,4},{0,0,2,1},{0,1,4,2},{2,4,1,0}};
        sudoku(grid);
    }
    public static void main(String[] args) {
        testPrintPermutationsWithoutSubstring();
        testRanInMaze();
        testNQueenProblem();
        testSudoku();
    }
}
