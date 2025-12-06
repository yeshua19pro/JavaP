import java.util.Scanner;
public class CheckersGM {

    public static boolean validMove(char[][] board, char[] from, char[] to, boolean whiteTurn) {
        char p = board[from[1]][from[0]];
        char t = board[to[1]][to[0]];
        if (p == '\0') {
            System.out.println("No piece there.");
            return false;
        }
        if (t != '\0') {
            System.out.println("Destination must be empty.");
            return false;
        }

        boolean isWhite = (p == '◉' || p == '●');
        boolean isQueen = (p == '●' || p == '◎');

        if (whiteTurn != isWhite) {
            System.out.println("Not your piece.");
            return false;
        }

        int dx = to[0] - from[0];
        int dy = to[1] - from[1];

        int dir = 0;
        if (isWhite) dir = -1;
        else dir = 1;

        if (Math.abs(dx) == 1 && (dy == dir || (isQueen && Math.abs(dy) == 1))) {
            return true;
        }

        if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
            int midX = (from[0] + to[0]) / 2;
            int midY = (from[1] + to[1]) / 2;
            char mid = board[midY][midX];

            if (mid == '\0') {
                System.out.println("No piece to capture.");
                return false;
            }

            boolean midWhite = (mid == '◉' || mid == '●');
            if (midWhite == isWhite) {
                System.out.println("You can't capture your own piece.");
                return false;
            }

            return true;
        }

        System.out.println("Invalid checkers move.");
        return false;
    }


    public static void applyMove(char[][] board, char[] from, char[] to) {
        int dx = Math.abs(to[0] - from[0]);

        if (dx == 1) {
            board[to[1]][to[0]] = board[from[1]][from[0]];
            board[from[1]][from[0]] = '\0';
            return;
        }

        int midX = (from[0] + to[0]) / 2;
        int midY = (from[1] + to[1]) / 2;
        board[midY][midX] = '\0';

        board[to[1]][to[0]] = board[from[1]][from[0]];
        board[from[1]][from[0]] = '\0';
    }

    public static void crown(char[][] board) {
        for (int c = 0; c < 8; c++) {
            if (board[0][c] == '◉') board[0][c] = '●';
        }
        for (int c = 0; c < 8; c++) {
            if (board[7][c] == 'O') board[7][c] = '◎';
        }
    }

    public static boolean hasPieces(char[][] board, boolean whiteTurn) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                char p = board[r][c];
                if (whiteTurn && (p == '◉' || p == '●')) {
                    return true;
                } else if (!whiteTurn && (p == 'O' || p == '◎')) {
                    return true;
                }
            }
        }
        return false;
    }

}
