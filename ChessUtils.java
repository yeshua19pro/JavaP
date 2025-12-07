import java.util.Scanner;

public class ChessUtils {

    /**
     *  @author Sophia Narvaez
     * @param  s string input representing a position on the chess board (e.g., "e4")
     *  @return true if the move is valid, false otherwise
     *   Validate the input string to ensure it represents a valid chess board position
     */
    public static boolean validateInput(String s) {
        if (s.length() != 2) return false;
        char col = s.charAt(0);
        char row = s.charAt(1);
        if (col < 'a' || col > 'h') return false;
        if (row < '1' || row > '8') return false;
        return true;
    }


    /**
     *  @author Sophia Narvaez
     * @param  move position of the piece
     * @param  moveTo position to move the piece
     *  @return true if the move is valid, false otherwise
     *  validate that the path that the piece will travel is empty (there are no pieces blocking the way)
     */
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
    /**
     *  @author Sophia Narvaez
     * @param  p character representing a chess piece
     *  @return true if the piece is a white piece, false otherwise
     */
    public static boolean isWPiece(char p) {
        char [] wPieces = {'♟', '♞', '♝', '♜', '♛', '♚'};
        for (int i = 0; i < wPieces.length; i++) {
            if (p == wPieces[i]) {
                return true;
            }
        }
        return false;
    }
    /**
     *  @author Sophia Narvaez
     * @param  p character representing a chess piece
     *  @return true if the piece is a black piece, false otherwise
     */
    public static boolean isBPiece(char p) {
        char [] bPieces = {'♙', '♘', '♗', '♖', '♕', '♔'};
        for (int i = 0; i < bPieces.length; i++) {
            if (p == bPieces[i]) {
                return true;
            }
        }
        return false;
    }
    /**
     *  @author Sophia Narvaez
     * @param  p character representing a chess piece
     *  @return true if the piece is a valid chess piece, false otherwise
     *  valid based on the methods if it is a white piece/if it is a black piece
     */
    public static boolean isPiece(char p) {
        return isWPiece(p) || isBPiece(p);
    }
    /**
     *  @author Sophia Narvaez
     * @param  board current state of the chess board
     *  @return a copy of the chess board
     *  Create a deep copy of the chess board
     */
    public static char[][] copyBoard(char[][] board) {
        char[][] copy = new char[8][8];
        for (int r = 0; r < 8; r++) {
            System.arraycopy(board[r], 0, copy[r], 0, 8);
        }
        return copy;
    }
}
