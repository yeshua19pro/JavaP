public class Pieces {
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

    public static boolean validKnight(char[] move, char[] moveTo){
        int col = move[0];
        int row = move[1];
        if (moveTo[0] == col - 2 && moveTo[1] == row - 1) return true;
        else if (moveTo[0] == col - 2 && moveTo[1] == row + 1) return true;
        else if (moveTo[0] == col - 1 && moveTo[1] == row - 2) return true;
        else if (moveTo[0] == col + 1 && moveTo[1] == row - 2) return true;
        else if (moveTo[0] == col - 1 && moveTo[1] == row + 2) return true;
        else if (moveTo[0] == col + 1 && moveTo[1] == row + 2) return true;
        else if (moveTo[0] == col + 2 && moveTo[1] == row - 1) return true;
        else if (moveTo[0] == col + 2 && moveTo[1] == row + 1) return true;
        return false;
    }
    public static boolean validRook(char[][] board, char[] move, char[] moveTo) {
        boolean x = false;
        if (move[0] == moveTo[0]) x = true;
        else if (move[1] == moveTo[1]) x = true;
        if (!x) return false;
        x = ChessUtils.clean(board, move, moveTo);
        return x;
    }

    public static boolean validBishop(char[][] board, char[] move, char[] moveTo) {
        boolean x = false;
        if (move[0] - moveTo[0] == move[1] - moveTo[1]) x = true;
        else if (move[1] > moveTo[1] && move[0] < moveTo[0]) {
            int col = move[0];
            int row = move[1];
            while (col < moveTo[0] && row > moveTo[1]) {
                col++;
                row--;
                if (col == moveTo[0] && row == moveTo[1]) {
                    x = true;
                }
            }
        }
        else if (move[1] < moveTo[1] && move[0] > moveTo[0]) {
            int col = move[0];
            int row = move[1];
            while (col > moveTo[0] && row < moveTo[1]) {
                col--;
                row++;

                if (col == moveTo[0] && row == moveTo[1]) {
                    x = true;
                }
            }
        }
        if (!x) return false;
        x = ChessUtils.clean(board, move, moveTo);
        return x;
    }

    public static boolean validQueen(char[][] board, char[] move, char[] moveTo) {
        if (validRook(board, move, moveTo)) return true;

        if (validBishop(board, move, moveTo)) return true;

        return false;
    }
}
