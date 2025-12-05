import java.util.Scanner;

public class ChessUtils {
    static boolean whiteTurn = true;


    public static boolean validateInput(String s) {
        if (s.length() != 2) return false;
        char col = s.charAt(0);
        char row = s.charAt(1);
        if (col < 'a' || col > 'h') return false;
        if (row < '1' || row > '8') return false;
        return true;
    }



    public static boolean clean(char[][] board, char[] move, char[] moveTo) {

        int col1 = move[0];
        int row1 = move[1];
        int col2 = moveTo[0];
        int row2 = moveTo[1];

        int dCol = Integer.compare(col2, col1);
        int dRow = Integer.compare(row2, row1);
        if (dCol != 0 && dRow != 0 && Math.abs(col2 - col1) != Math.abs(row2 - row1)) {
            return false;
        }

        int col = col1 + dCol;
        int row = row1 + dRow;

        while (col != col2 || row != row2) {
            if (board[row][col] != '\0') {
                System.out.println("There is a piece blocking the way.");
                return false;
            }
            col += dCol;
            row += dRow;
        }
        return true;
    }
    public static boolean isWPiece(char p) {
        char [] wPieces = {'♟', '♞', '♝', '♜', '♛', '♚'};
        for (int i = 0; i < wPieces.length; i++) {
            if (p == wPieces[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBPiece(char p) {
        char [] bPieces = {'♙', '♘', '♗', '♖', '♕', '♔'};
        for (int i = 0; i < bPieces.length; i++) {
            if (p == bPieces[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPiece(char p) {
        return isWPiece(p) || isBPiece(p);
    }


}
