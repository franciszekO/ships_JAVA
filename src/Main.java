//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Random;
import java.util.Scanner;

public class Main {

    // mapa wymiary
    private static final int BOARD_SIZE = 5;
    private static final int SHIPS_COUNT = 3;

    // mapa symbole
    private static final char WATER = '~';
    private static final char SHIP = 'S'; 
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    public static void main(String[] args) {
        // plansza
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard(board);

        placeShips(board);

        int shipsSunk = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Battleship! Try to sink " + SHIPS_COUNT + " hidden ships.");

        // pętla gry
        while (shipsSunk < SHIPS_COUNT) {
            printBoard(board, false); 

            int row = -1;
            int col = -1;

          
            while (true) {
                System.out.print("Enter row (0-" + (BOARD_SIZE - 1) + ") and column (0-" + (BOARD_SIZE - 1) + ") separated by space: ");
                if (scanner.hasNextInt()) {
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
                        break;
                    } else {
                        System.out.println("Coordinates out of bounds!");
                    }
                } else {
                    System.out.println("Invalid input. Please enter numbers.");
                    scanner.next(); //błędne wejście
                }
            }

            // strzały
            if (board[row][col] == SHIP) {
                System.out.println("HIT! You sank a ship!");
                board[row][col] = HIT;
                shipsSunk++;
            } else if (board[row][col] == WATER) {
                System.out.println("MISS!");
                board[row][col] = MISS;
            } else {
                System.out.println("You already shot here!");
            }
        }

        System.out.println("CONGRATULATIONS! You won!");
        printBoard(board, true); // koniec
        scanner.close();
    }

    // miss
    private static void initializeBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = WATER;
            }
        }
    }

    // statki losowanie
    private static void placeShips(char[][] board) {
        Random random = new Random();
        int shipsPlaced = 0;

        while (shipsPlaced < SHIPS_COUNT) {
            int row = random.nextInt(BOARD_SIZE);
            int col = random.nextInt(BOARD_SIZE);

            
            if (board[row][col] != SHIP) {
                board[row][col] = SHIP;
                shipsPlaced++;
            }
        }
    }

  
    private static void printBoard(char[][] board, boolean revealShips) {
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " "); 
            for (int j = 0; j < BOARD_SIZE; j++) {
                char cell = board[i][j];
                
                if (cell == SHIP && !revealShips) {
                    System.out.print(WATER + " ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }
}
