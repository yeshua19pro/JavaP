public class Pieces {
    /**
     *  @author Sophia Narvaez
     * @param  move position of the knight
     * @param  moveTo position to move the knight to
     *  @return true if the move is valid, false otherwise
     *  Validate the knight move based on the fact that one square moves in one direction and two in another
     */
    public static boolean validKnight(char[] move, char[] moveTo) {
        int x = Math.abs(move[0] - moveTo[0]);
        int y = Math.abs(move[1] - moveTo[1]);
        return (x == 2 && y == 1) || (x == 1 && y == 2);
    }

    /**
     *  @author Sophia Narvaez
     * @param  move position of the Rook
     * @param  moveTo position to move the Rook to
     * @param board current state of the chess board
     *  @return true if the move is valid, false otherwise
     *  Validate the Rook move based on the fact that it moves in the same column or the same row, as long as there are no pieces in between
     */
    public static boolean validRook(char[][] board, char[] move, char[] moveTo) {
        boolean x = false;
        if (move[0] == moveTo[0]) x = true;
        else if (move[1] == moveTo[1]) x = true;
        if (!x) return false;
        x = ChessUtils.clean(board, move, moveTo);
        return x;
    }

    /**
     * @author Sophia Narvaez
     * @param  move position of the Bishop
     * @param  moveTo position to move the Bishop to
     * @param board current state of the chess board
     *  @return true if the move is valid, false otherwise
     *  Validate the Bishop move based on the fact that it moves diagonally, as long as there are no pieces in between
     */
    public static boolean validBishop(char[][] board, char[] move, char[] moveTo) {
        int col = Math.abs(moveTo[0] - move[0]);
        int row = Math.abs(moveTo[1] - move[1]);
        if (col != row) return false;

        return ChessUtils.clean(board, move, moveTo);
    }

    /**
     *  @author Sophia Narvaez
     * @param  move position of the Queen
     * @param  moveTo position to move the Queen to
     * @param board current state of the chess board
     *  @return true if the move is valid, false otherwise
     *  Validate the Queen move based on the fact that it moves like a bishop and a rook
     */
    public static boolean validQueen(char[][] board, char[] move, char[] moveTo) {
        if (validRook(board, move, moveTo)) return true;

        if (validBishop(board, move, moveTo)) return true;

        return false;
    }
    /**
     *  @author Sophia Narvaez
     * @param  move position of the King
     * @param  moveTo position to move the King to
     * @param board current state of the chess board
     *  @return true if the move is valid, false otherwise
     *  Validate the Kings move based on moving only one square
     */
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

        if (dxEnemy <= 1 && dyEnemy <= 1) {
            System.out.println("The king cannot move next to the enemy king.");
            return false;
        }
        return true;
    }

    /**
     *  @author Sophia Narvaez
     * @param  move position of the Rook
     * @param  moveTo position to move the Rook to
     * @param board current state of the chess board
     *  @return true if the move is valid, false otherwise
     *  Validate the pawn move based on the fact that from its starting square it can move up to two squares, otherwise only one square, and it eats diagonally.
     */

    public static boolean validPawn(char[][] board, char[] move, char[] moveTo) {
        if (board[move[1]][move[0]] == '♟'){
            if (moveTo[1] >= move[1]) {
                return false;
            } else if (move [1] == 6 && moveTo[1] == 4 && moveTo[0] == move[0] && board[5][move[0]] == '\0' && board[4][move[0]] == '\0'){
                return true;
            } else if (moveTo[1] == move[1] - 1 && moveTo[0] == move[0] && board[moveTo[1]][moveTo[0]] == '\0'){
                return true;
            } else if (Math.abs(moveTo[0] - move[0]) == 1 && moveTo[1] == move[1] - 1 && ChessUtils.isBPiece(board[moveTo[1]][moveTo[0]])){
                return true;
            } else {
                return false;
            }
        }
        else if (board[move[1]][move[0]] == '♙'){
            if (moveTo[1] <= move[1]) {
                return false;
            } else if (move [1] == 1 && moveTo[1] == 3 && moveTo[0] == move[0] && board[2][move[0]] == '\0' && board[3][move[0]] == '\0'){
                return true;
            } else if (moveTo[1] == move[1] + 1 && moveTo[0] == move[0] && board[moveTo[1]][moveTo[0]] == '\0'){
                return true;
            } else if (Math.abs(moveTo[0] - move[0]) == 1 && moveTo[1] == move[1] + 1 && ChessUtils.isWPiece(board[moveTo[1]][moveTo[0]])){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
