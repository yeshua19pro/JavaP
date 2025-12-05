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

    public static boolean validKnight(char[] move, char[] moveTo) {
        int x = Math.abs(move[0] - moveTo[0]);
        int y = Math.abs(move[1] - moveTo[1]);
        return (x == 2 && y == 1) || (x == 1 && y == 2);
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
        int col = Math.abs(moveTo[0] - move[0]);
        int row = Math.abs(moveTo[1] - move[1]);
        if (col != row) return false;

        return ChessUtils.clean(board, move, moveTo);
    }


    public static boolean validQueen(char[][] board, char[] move, char[] moveTo) {
        if (validRook(board, move, moveTo)) return true;

        if (validBishop(board, move, moveTo)) return true;

        return false;
    }
    public static boolean validKing (char[][] board, char[] move, char[] moveTo) {
        int col = Math.abs(moveTo[0] - move[0]);
        int row = Math.abs(moveTo[1] - move[1]);
        if (col > 1 || row > 1) return false;
        char enemyKing;
        char thisKing = board[move[1]][move[0]];
        if (thisKing == '♚'){
            enemyKing = '♔';
        } else if (thisKing == '♔') {
            enemyKing = '♚';
        }else {
            return false;
        }
        int enemyCol = -1;
        int enemyRow = -1;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == enemyKing) {
                    enemyCol = c;
                    enemyRow = r;
                    break;
                }
            }
        }
        int dxEnemy = Math.abs(moveTo[0] - enemyCol);
        int dyEnemy = Math.abs(moveTo[1] - enemyRow);

        if (dx <= 1 && dy <= 1) {
            System.out.println("The king cannot move next to the enemy king.");
            return false;
        }
        return true;
    }
}
