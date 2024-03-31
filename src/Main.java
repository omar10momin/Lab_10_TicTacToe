import java.util.Scanner;

public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        boolean playAgain = true;
        while (playAgain) {
            clearBoard();
            display();

            while (true) {
                int rowMove = SafeInput.getRangedInt(scanner, "Enter row (1 - 3) for your move", 1, 3);
                int colMove = SafeInput.getRangedInt(scanner, "Enter column (1 - 3) for your move", 1, 3);

                if (isValidMove(rowMove, colMove)) {
                    int rowIndex = rowMove - 1;
                    int colIndex = colMove - 1;
                    board[rowIndex][colIndex] = currentPlayer;
                    display();

                    if (isWin(currentPlayer)) {
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    } else if (isTie()) {
                        System.out.println("It's a tie!");
                        break;
                    }

                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                } else {
                    System.out.println("Invalid move! Please choose an empty cell.");
                }
            }

            playAgain = SafeInput.getYNConfirm(scanner, "Do you want to play again?");
            if (playAgain) {
                currentPlayer = "X"; // Reset player to X for new game
            }
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("-------------");
        for (int i = 0; i < ROW; i++) {
            System.out.print("| ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;
        return board[rowIndex][colIndex].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false; // If there is any empty cell, game can continue
                }
            }
        }
        return true; // If no empty cells found, it's a tie
    }
}
