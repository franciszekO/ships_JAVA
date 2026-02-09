import java.util.Random;
public class GameLogic {
        private char[][] board;
        private int shipsSunk = 0;
        private final int SIZE = 5;
        private final int SHIPS_COUNT = 3;

        public GameLogic() {
            board = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) board[i][j] = '~';
            }
            placeShips();
        }

        private void placeShips() {
            Random rand = new Random();
            int placed = 0;
            while (placed < SHIPS_COUNT) {
                int r = rand.nextInt(SIZE), c = rand.nextInt(SIZE);
                if (board[r][c] != 'S') {
                    board[r][c] = 'S';
                    placed++;
                }
            }
        }

        public String shoot(int r, int c) {
            if (board[r][c] == 'S') {
                board[r][c] = 'X';
                shipsSunk++;
                return "HIT";
            } else {
                board[r][c] = 'O';
                return "MISS";
            }
        }

        public boolean isGameOver() {
            return shipsSunk >= SHIPS_COUNT;
        }
    }

