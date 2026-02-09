//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Random;
import java.util.Scanner;

public class Main {

    // Konfiguracja gry: plansza 5x5 i 3 statki
    private static final int BOARD_SIZE = 5;
    private static final int SHIPS_COUNT = 3;

    // Symbole używane na mapie
    private static final char WATER = '~';
    private static final char SHIP = 'S'; // Ten symbol jest ukryty dla gracza
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    public static void main(String[] args) {
        // 1. Inicjalizacja planszy
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard(board);

        // 2. Rozstawienie statków przez komputer
        placeShips(board);

        // 3. Rozgrywka
        int shipsSunk = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Battleship! Try to sink " + SHIPS_COUNT + " hidden ships.");

        // Pętla gry działa dopóki nie zatopimy wszystkich statków
        while (shipsSunk < SHIPS_COUNT) {
            printBoard(board, false); // false = nie pokazuj ukrytych statków

            int row = -1;
            int col = -1;

            // Walidacja wejścia (czy użytkownik wpisał poprawne liczby)
            while (true) {
                System.out.print("Enter row (0-" + (BOARD_SIZE - 1) + ") and column (0-" + (BOARD_SIZE - 1) + ") separated by space: ");
                if (scanner.hasNextInt()) {
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
                        break; // Współrzędne poprawne, wychodzimy z pętli walidacji
                    } else {
                        System.out.println("Coordinates out of bounds!");
                    }
                } else {
                    System.out.println("Invalid input. Please enter numbers.");
                    scanner.next(); // Czyści błędne wejście
                }
            }

            // Logika strzału
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
        printBoard(board, true); // Pokaż planszę końcową
        scanner.close();
    }

    // Metoda wypełniająca planszę wodą
    private static void initializeBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = WATER;
            }
        }
    }

    // Metoda losująca pozycje statków
    private static void placeShips(char[][] board) {
        Random random = new Random();
        int shipsPlaced = 0;

        while (shipsPlaced < SHIPS_COUNT) {
            int row = random.nextInt(BOARD_SIZE);
            int col = random.nextInt(BOARD_SIZE);

            // Sprawdzamy, czy w tym miejscu nie ma już statku
            if (board[row][col] != SHIP) {
                board[row][col] = SHIP;
                shipsPlaced++;
            }
        }
    }

    // Metoda rysująca planszę
    private static void printBoard(char[][] board, boolean revealShips) {
        System.out.print("  ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " "); // Numery kolumn
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " "); // Numery wierszy
            for (int j = 0; j < BOARD_SIZE; j++) {
                char cell = board[i][j];
                // Jeśli komórka to statek, a my nie chcemy go ujawniać, drukujemy wodę
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