package projects.project04;

import java.util.Scanner;

/**
 * Game of tic-tac-toe
 */
public class Project04 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int move = 1;

        char[][] board = new char[][] {
                {'.','.','.'},
                {'.','.','.'},
                {'.','.','.'}};

        while (true) {
            displayBoard(board);
            getPlayerInput(board, move);

            if (isGameOver(board, move)) break;
            move++;
        }
    }

    public static void displayBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%c ", board[i][j]);
            }
            System.out.println();
        }
    }

    public static void getPlayerInput(char[][] board, int move) {

        int row, col;
        int player = ((move % 2) != 0) ? 1 : 2;
        char symbol = ((move % 2) != 0) ? 'X' : 'O';

        while (true) {
            System.out.printf("Player %d enter row and column (two numbers from 1-3)\n", player );
            row = input.nextInt() - 1;
            col = input.nextInt() - 1;

            if (isValid(board, row, col)) break;
        }

        board[row][col] = symbol;
    }

    public static boolean isValid(char[][] board, int row, int col) {

        if (row > 3 || col > 3) {
            displayBoard(board);
            System.out.println("Invalid input. Row and column numbers must be from 1-3");
            return false;
        }

        if (board[row][col] != '.') {
            displayBoard(board);
            System.out.println("Invalid input. The position is filled in.");
            return false;
        }

        return true;
    }

    public static boolean isGameOver(char[][] board, int move) {

        int player = ((move % 2) != 0) ? 1 : 2;
        char symbol = ((move % 2) != 0) ? 'X' : 'O';
        boolean isWin = false, isDraw = false;

        //Check for win
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                isWin = true;
            }
            for (int j = 0; j < board[i].length; j++) {
                if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                    isWin = true;
                }
            }
        }

        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            isWin = true;
        }

        // Check for draw
        if (isWin == false && move >= 9) {
            isDraw = true;
        }

        if (isWin) {
            displayBoard(board);
            System.out.printf("GAME OVER. PLAYER %d WINS!\n", player);
            return true;
        }
        if (isDraw) {
            displayBoard(board);
            System.out.printf("GAME OVER. Draw.");
            return true;
        }
        return false;
    }

}
