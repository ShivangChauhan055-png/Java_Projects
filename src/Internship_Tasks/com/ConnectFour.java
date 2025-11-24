package Internship_Tasks.com;

import java.util.Scanner;

public class ConnectFour {
    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 7;
    private static final char EMPTY_SLOT = '.';
    private static final char RED_PLAYER = 'R';
    private static final char YELLOW_PLAYER = 'Y';

    private char[][] gameBoard;
    private char currentPlayerDisc;
    private Scanner userInput;

    public ConnectFour() {
        gameBoard = new char[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        currentPlayerDisc = RED_PLAYER;
        userInput = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++) {
                gameBoard[rowIndex][columnIndex] = EMPTY_SLOT;
            }
        }
    }

    private void printBoard() {
        System.out.println("\n Connect Four Game");
        System.out.println("===================");

        for (int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++) {
            System.out.print("| ");
            for (int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++) {
                System.out.print(gameBoard[rowIndex][columnIndex] + " ");
            }
            System.out.println("|");
        }

        System.out.println("===================");
        System.out.print("  ");
        for (int columnNumber = 1; columnNumber <= NUMBER_OF_COLUMNS; columnNumber++) {
            System.out.print(columnNumber + " ");
        }
        System.out.println("\n");
    }

    private boolean isValidMove(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= NUMBER_OF_COLUMNS) {
            return false;
        }
        return gameBoard[0][columnIndex] == EMPTY_SLOT;
    }

    private int dropDisc(int columnIndex) {
        for (int rowIndex = NUMBER_OF_ROWS - 1; rowIndex >= 0; rowIndex--) {
            if (gameBoard[rowIndex][columnIndex] == EMPTY_SLOT) {
                gameBoard[rowIndex][columnIndex] = currentPlayerDisc;
                return rowIndex;
            }
        }
        return -1;
    }

    private boolean checkWin(int lastMoveRow, int lastMoveColumn) {
        return checkDirection(lastMoveRow, lastMoveColumn, 0, 1) ||  // Horizontal
                checkDirection(lastMoveRow, lastMoveColumn, 1, 0) ||  // Vertical
                checkDirection(lastMoveRow, lastMoveColumn, 1, 1) ||  // Diagonal /
                checkDirection(lastMoveRow, lastMoveColumn, 1, -1);   // Diagonal \
    }

    private boolean checkDirection(int startRow, int startColumn, int rowDirection, int columnDirection) {
        int consecutiveDiscs = 1;

        // Check forward direction
        consecutiveDiscs += countInDirection(startRow, startColumn, rowDirection, columnDirection);

        // Check backward direction
        consecutiveDiscs += countInDirection(startRow, startColumn, -rowDirection, -columnDirection);

        return consecutiveDiscs >= 4;
    }

    private int countInDirection(int startRow, int startColumn, int rowDirection, int columnDirection) {
        int discCount = 0;
        int currentRow = startRow + rowDirection;
        int currentColumn = startColumn + columnDirection;

        while (currentRow >= 0 && currentRow < NUMBER_OF_ROWS &&
                currentColumn >= 0 && currentColumn < NUMBER_OF_COLUMNS &&
                gameBoard[currentRow][currentColumn] == currentPlayerDisc) {
            discCount++;
            currentRow += rowDirection;
            currentColumn += columnDirection;
        }

        return discCount;
    }

    private boolean isBoardFull() {
        for (int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++) {
            if (gameBoard[0][columnIndex] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayerDisc = (currentPlayerDisc == RED_PLAYER) ? YELLOW_PLAYER : RED_PLAYER;
    }

    public void play() {
        System.out.println("Welcome to Connect Four!");
        System.out.println("Player 1: R (Red)");
        System.out.println("Player 2: Y (Yellow)");
        System.out.println("Enter column number (1-7) to drop your disc.");

        boolean isGameOver = false;

        while (!isGameOver) {
            printBoard();

            String currentPlayerName = (currentPlayerDisc == RED_PLAYER) ? "Player 1 (R)" : "Player 2 (Y)";
            System.out.print(currentPlayerName + ", enter column (1-7): ");

            try {
                int selectedColumn = userInput.nextInt() - 1;

                if (!isValidMove(selectedColumn)) {
                    System.out.println("Invalid move! Column is full or out of range. Try again.");
                    continue;
                }

                int discLandedRow = dropDisc(selectedColumn);

                if (checkWin(discLandedRow, selectedColumn)) {
                    printBoard();
                    System.out.println(" " + currentPlayerName + " WINS! ");
                    isGameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a DRAW! The board is full.");
                    isGameOver = true;
                } else {
                    switchPlayer();
                }

            } catch (Exception error) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                userInput.nextLine(); // Clear the invalid input
            }
        }

        System.out.print("\nPlay again? (y/n): ");
        userInput.nextLine(); // Clear buffer
        String playerResponse = userInput.nextLine();

        if (playerResponse.toLowerCase().startsWith("y")) {
            initializeBoard();
            currentPlayerDisc = RED_PLAYER;
            play();
        } else {
            System.out.println("Thanks for playing!");
            userInput.close();
        }
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.play();
    }
}
