
import java.util.Random;

public class Game {
    public int[][] board = new int[8][8];
    public char[] moves = { 'r', 'd', 'b' };
    Random rand = new Random();

    public Game(Random rand) {
        this.rand = rand;
    }

    public int check(char move, int row, int col) {
        if (move == 'd') {
            if (row == 7) {
                return 0;
            }
            return 1;
        }

        else if (move == 'r') {
            if (col == 7) {
                return 0;
            }
            return 1;
        }

        else if (move == 'b') {
            if (row == 7 || col == 7) {
                return 0;
            }
            return 1;
        } else {
            System.out.println("Char invalid");
            return -1;
        }
    }

    public int goodMove(char move, int row, int col) {
        int ans = check(move, row, col);
        if (ans != 1) {
            return 0;
        }

        int newRow = row;
        int newCol = col;
        if (move == 'r') {
            newCol++;
        }
        if (move == 'd') {
            newRow++;
        }
        if (move == 'b') {
            newRow++;
            newCol++;
        }

        // You do not want to move into these
        if (newCol % 2 == 0 && newRow == 7) {
            return 0;
        }
        if (newRow % 2 == 0 && newCol == 7) {
            return 0;
        }

        // You do not want to put yourself in a position that the bot could possibly
        // move you into the above index's
        int res;

        // Check r
        if (newCol != 7) {
            res = possibleMove(move, newRow, newCol + 1);
            if (res == 0) {
                return 0;
            }
        }
        // Check d
        if (newRow != 7) {
            res = possibleMove(move, newRow + 1, newCol);
            if (res == 0) {
                return 0;
            }
        }
        // Check b
        if (newRow != 7 && newCol != 7) {
            res = possibleMove(move, newRow + 1, newCol + 1);
            if (res == 0) {
                return 0;
            }
        }

        return 1;

    }

    public int possibleMove(char move, int row, int col) {
        if (col % 2 == 0 && row == 7) {
            return 0;
        }
        if (col % 2 == 0 && row == 7) {
            return 0;
        }
        return 1;
    }

    public char selectPlayerTwoMove(int row, int col) {
        int move = rand.nextInt(3);
        int y = check(moves[move], row, col);
        while (y != 1) {
            move = rand.nextInt(3);
            y = check(moves[move], row, col);
        }

        return moves[move];
    }

    public int selectPlayerOneMove(int row, int col) {
        int l = 0;
        char move = moves[l];
        int ans = goodMove(move, row, col);
        // Making a move not outside the board
        while (ans != 1) {
            l++;
            if (l > 3) {
                System.out.println("Went out of bounds of L");
                return -1;
            }
            move = moves[l];
            ans = goodMove(move, row, col);
        }

        return l; // L
    }

    public int play() {
        int row = 0;
        int col = 0;
        boolean result = false;
        while (result == false) {
            int x = selectPlayerOneMove(row, col);
            // if (x == -1)
            // {
            // System.out.println("Surrender");
            // return -1;
            // }
            char move = moves[x];
            if (move == 'r') {
                col++;
            }
            if (move == 'd') {
                row++;
            }
            if (move == 'b') {
                row++;
                col++;
            }
            if (row == 7 && col == 7) {
                return 1;
            }

            move = selectPlayerTwoMove(row, col);
            if (move == 'r') {
                col++;
            }
            if (move == 'd') {
                row++;
            }
            if (move == 'b') {
                row++;
                col++;
            }
            if (row == 7 && col == 7) {
                return 0;
            }
        }
        return 5;
    }
}
