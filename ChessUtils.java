import java.util.Scanner;

public class ChessUtils {
    static boolean whiteTurn = true;

    public static boolean validMove(char [][] board, char[] move, char[] moveTo){
        char piece = board[move[1]][move[0]];
        char target = board[moveTo[1]][moveTo[0]];
        if (move[0] < 0 || move[0] > 7 || move[1] < 0 || move[1] > 7) {
            System.out.println("Origin box outside the board.");
            return false;
        } else if (moveTo[0] < 0 || moveTo[0] > 7 || moveTo[1] < 0 || moveTo[1] > 7) {
            System.out.println("Destination square outside the board.");
            return false;
        } else if (!Pieces.isPiece(piece)) {
            System.out.println("There are no pieces on the home board.");
            return false;
        } else if (whiteTurn && !Pieces.isWPiece(piece)) {
            System.out.println("You must move a white piece.");
            return false;
        } else if(!whiteTurn && !Pieces.isBPiece(piece)) {
            System.out.println("You must move a black piece.");
            return false;
        } else if (Pieces.isWPiece(piece) && Pieces.isWPiece(target)) {
            System.out.println("You can't capture your own piece.");
            return false;
        } else if (Pieces.isBPiece(piece) && Pieces.isBPiece(target)) {
            System.out.println("You can't capture your own piece.");
            return false;
        } else if (move [0] == moveTo[0] && move[1] == moveTo[1]) {
            System.out.println("You must move to a different square.");
            return false;
        }else if (piece == '♞' || piece == '♘') {
            if (!validKnight(move, moveTo)) {
                System.out.println("invalid move for the knight");
                return false;
            }
            return true;
        } else if (piece == '♜' || piece == '♖'){
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
        } else {
            return true;
        }
    }

    public static boolean validateInput(String s) {
        if (s.length() != 2) return false;
        char col = s.charAt(0);
        char row = s.charAt(1);
        if (col < 'a' || col > 'h') return false;
        if (row < '1' || row > '8') return false;
        return true;
    }

    public static char[][] moveP(char [][] board){
        Scanner sc = new Scanner(System.in);
        char[] move = null;
        char[] moveTo = null;
        boolean validM = false;

        while (!validM) {
            System.out.println("Enter the cell number (example e2):");
            String mo = sc.nextLine().trim();

            if (!validateInput(mo)) {
                System.out.println("Invalid format. Use letters a – h and numbers 1 – 8. Example: e2");
                continue;
            }

            move = mo.toCharArray();
            ChessBoard.convert(move);
            System.out.println("Ingresa la casilla a la que la vas a mover");
            String to = sc.nextLine().trim();

            if (!validateInput(to)) {
                System.out.println("Formato inválido. Usa letra+a–h y número 1–8. Ejemplo: e4");
                continue;
            }

            moveTo = to.toCharArray();
            ChessBoard.convert(moveTo);

            validM = validMove(board, move, moveTo);
        }
        board[moveTo[1]][moveTo[0]] = board[move[1]][move[0]];
        board[move[1]][move[0]] = '\0';
        return board;
    }

    public static boolean clean(char board[][], char move[], char moveTo[]) {
        if (move[1] == moveTo[1]) { //same row
            if (move[0] < moveTo[0]) {
                for (int i = move[0] + 1; i < moveTo[0]; i++) {
                    if (board[move[1]][i] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = moveTo[0] + 1; i < move[0]; i++) {
                    if (board[move[1]][i] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            }
        } else if (move[0] == moveTo[0]) { //same column
            if (move[1] < moveTo[1]) {
                for (int i = move[1] + 1; i < moveTo[1]; i++) {
                    if (board[i][move[0]] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = moveTo[1] + 1; i < move[1]; i++) {
                    if (board[i][move[0]] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            }
        } else {//Diagonal
            if (move[1] < moveTo[1] && move[0] < moveTo[0]) {//bottom right diagonal
                int diff = moveTo[0] - move[0];
                for (int i = 1; i < diff; i++) {
                    if (board[move[1] + i][move[0] + i] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            } else if (move[1] < moveTo[1] && move[0] > moveTo[0]) { //upper right diagonal
                int diff = move[0] - moveTo[0];
                for (int i = 1; i < diff; i++) {
                    if (board[move[1] + i][move[0] - i] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            } else if (move[1] > moveTo[1] && move[0] < moveTo[0]) { //bottom left diagonal
                int diff = moveTo[0] - move[0];
                for (int i = 1; i < diff; i++) {
                    if (board[move[1] - i][move[0] + i] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            } else { //top left diagonal
                int diff = move[0] - moveTo[0];
                for (int i = 1; i < diff; i++) {
                    if (board[move[1] - i][move[0] - i] != '\0') {
                        System.out.println("There is a piece blocking the way.");
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
