import java.util.Scanner;

public class GameMaster {
    /**
     *  @author Sophia Narvaez
     * @param  move position of the piece
     * @param  moveTo position to move the piece
     *  @return true if the move is valid, false otherwise
     *   Validate the move based on the piece type and the rules of chess
     *   Check that there are no errors such as positions outside the board, eating your own piece, or trying to move the piece to the same square it came from.
     *
     */
    public static boolean validMove(char[][] board, char[] move, char[] moveTo, boolean whiteTurn) {
        char piece = board[move[1]][move[0]];
        char target = board[moveTo[1]][moveTo[0]];
        if (move[0] < 0 || move[0] > 7 || move[1] < 0 || move[1] > 7) {
            System.out.println("Origin box outside the board.");
            return false;
        } else if (moveTo[0] < 0 || moveTo[0] > 7 || moveTo[1] < 0 || moveTo[1] > 7) {
            System.out.println("Destination square outside the board.");
            return false;
        } else if (!ChessUtils.isPiece(piece)) {
            System.out.println("There are no pieces on the home board.");
            return false;
        } else if (whiteTurn && !ChessUtils.isWPiece(piece)) {
            System.out.println("You must move a white piece.");
            return false;
        } else if (!whiteTurn && !ChessUtils.isBPiece(piece)) {
            System.out.println("You must move a black piece.");
            return false;
        } else if (ChessUtils.isWPiece(piece) && ChessUtils.isWPiece(target)) {
            System.out.println("You can't capture your own piece.");
            return false;
        } else if (ChessUtils.isBPiece(piece) && ChessUtils.isBPiece(target)) {
            System.out.println("You can't capture your own piece.");
            return false;
        } else if (move[0] == moveTo[0] && move[1] == moveTo[1]) {
            System.out.println("You must move to a different square.");
            return false;
        } else if (piece == '♞' || piece == '♘') {
            if (!Pieces.validKnight(move, moveTo)) {
                System.out.println("invalid move for the knight");
                return false;
            }
            return true;
        } else if (piece == '♜' || piece == '♖') {
            if (!Pieces.validRook(board, move, moveTo)) {
                System.out.println("invalid move for the rook");
                return false;
            }
            return true;
        } else if (piece == '♝' || piece == '♗') {
            if (!Pieces.validBishop(board, move, moveTo)) {
                System.out.println("invalid move for the bishop");
                return false;
            }
            return true;
        } else if (piece == '♛' || piece == '♕') {
            if (!Pieces.validQueen(board, move, moveTo)) {
                System.out.println("invalid move for the queen");
                return false;
            }
            return true;
        } else if (piece == '♚' || piece == '♔') {
            if (!Pieces.validKing(board, move, moveTo)) {
                System.out.println("invalid move for the king");
                return false;
            }
            return true;
        } else {
            return true;
        }
    }
    /**
     *  @author Sophia Narvaez
     * @param  board current state of the chess board
     *  @return true if the move is valid, false otherwise
     *   Handles player input for moving a piece, validates the move, and updates the board state accordingly
     *
     */
    public static char[][] moveP(char[][] board, boolean whiteTurn) {
        Scanner sc = new Scanner(System.in);
        char[] move = null;
        char[] moveTo = null;
        boolean validM = false;

        while (!validM) {
            System.out.println("Enter the cell number (example e2):");
            String mo = sc.nextLine().trim();

            if (!ChessUtils.validateInput(mo)) {
                System.out.println("Invalid format. Use letters a – h and numbers 1 – 8. Example: e2");
                continue;
            }

            move = mo.toCharArray();
            Board.convert(move);
            System.out.println("Enter the cell number (example e2):");
            String to = sc.nextLine().trim();

            if (!ChessUtils.validateInput(to)) {
                System.out.println("Invalid format. Use letters a – h and numbers 1 – 8. Example: e2");
                continue;
            }

            moveTo = to.toCharArray();
            Board.convert(moveTo);

            validM = validMove(board, move, moveTo, whiteTurn);
            if (validM) {
                validM = moveLeavesKingInCheck(board, move, moveTo, whiteTurn);
                if (!validM) {
                    System.out.println("Move puts or leaves king in check. Choose another move.");
                    System.out.println("Its Checkmate? 1. Yes 2. No");
                    String checkmate = sc.nextLine();
                    switch (checkmate) {
                        case "1":
                            System.out.println("Checkmate!");
                            sc.close();
                        case "2":
                            break;
                        default:
                            System.out.println("Invalid choice. Continuing game.");
                            break;
                    }
                }
            }
        }
        board[moveTo[1]][moveTo[0]] = board[move[1]][move[0]];
        board[move[1]][move[0]] = '\0';
        return board;
    }
    /**
     *  @author Sophia Narvaez
     * @param  board current state of the chess board
     *  @return true if its safe, false otherwise
     *  Check if the current player's king is in check
     */
    public static boolean isCheck(char[][] board, boolean whiteTurn) {
        char king = '\0';
        if (whiteTurn) {
            king = '♚';
        } else {
            king = '♔';
        }
        int kRow = -1, kCol = -1;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == king) {
                    kRow = r;
                    kCol = c;
                    break;
                }
            }
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {

                char piece = board[r][c];

                if (piece == '\0') continue;

                if (whiteTurn && ChessUtils.isWPiece(piece)) continue;
                if (!whiteTurn && ChessUtils.isBPiece(piece)) continue;
                char[] from = {(char) c, (char) r};
                char[] to = {(char) kCol, (char) kRow};
                if (piece == '♞' || piece == '♘') {
                    if (Pieces.validKnight(from, to)) return false;
                } else if (piece == '♜' || piece == '♖') {
                    if (Pieces.validRook(board, from, to)) return false;
                } else if (piece == '♝' || piece == '♗') {
                    if (Pieces.validBishop(board, from, to)) return false;
                } else if (piece == '♛' || piece == '♕') {
                    if (Pieces.validQueen(board, from, to)) return false;
                } else if (piece == '♚' || piece == '♔') {
                    if (Pieces.validKing(board, from, to)) return false;
                } else if (piece == '♟' || piece == '♙') {
                    if (Pieces.validPawn(board, from, to)) return false;
                }
            }
        }
        return true;
    }

    /**
     *  @author Sophia Narvaez
     * @param  move position of the piece
     * @param  moveTo position to move the piece
     * @param  board current state of the chess board
     *  @return true if the move is valid, false otherwise
     *  Check if moving a piece leaves the player's king in check
     *
     */
    public static boolean moveLeavesKingInCheck(char[][] board, char[] move, char[] moveTo, boolean whiteTurn) {
        char[][] copy = ChessUtils.copyBoard(board);
        copy[moveTo[1]][moveTo[0]] = copy[move[1]][move[0]];
        copy[move[1]][move[0]] = '\0';
        return isCheck(copy, whiteTurn);
    }

    /**
     *  @author Sophia Narvaez
     * @param  sc Scanner for user input
     *  Handles draw offers between players
     */
    public static void draw(Scanner sc) {
        System.out.println("Do you want to offer draws? 1. yes 2. no");
        String drawOffer = sc.nextLine();
        switch (drawOffer) {
            case "1":
                System.out.println("Did your opponent agree to a draw?? \n1. yes \n2. no");
                String drawResponse = sc.nextLine();
                switch (drawResponse) {
                    case "1":
                        System.out.println("The game is a draw!");
                        sc.close();
                        System.exit(0);
                        return;
                    case "2":
                        System.out.println("Draw offer declined. Continuing game.");
                        break;
                    default:
                        System.out.println("Invalid choice. Continuing game.");
                        break;
                }
                break;
            case "2":
                break;
            default:
                System.out.println("Invalid choice. Continuing game.");
                break;
        }
    }
}
