import java.util.Scanner;

public class ChessUtils {
    static boolean whiteTurn = true;
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

    public static boolean validMove(char [][] board, char[] move, char[] moveTo){
        char piece = board[move[1]][move[0]];
        char target = board[moveTo[1]][moveTo[0]];
        if (move[0] < 0 || move[0] > 7 || move[1] < 0 || move[1] > 7) {
            System.out.println("Origin box outside the board.");
            return false;
        } else if (moveTo[0] < 0 || moveTo[0] > 7 || moveTo[1] < 0 || moveTo[1] > 7) {
            System.out.println("Destination square outside the board.");
            return false;
        } else if (!isPiece(piece)) {
            System.out.println("There are no pieces on the home board.");
            return false;
        } else if (whiteTurn && !isWPiece(piece)) {
            System.out.println("You must move a white piece.");
            return false;
        } else if(!whiteTurn && !isBPiece(piece)) {
            System.out.println("You must move a black piece.");
            return false;
        } else if (isWPiece(piece) && isWPiece(target)) {
            System.out.println("You can't capture your own piece.");
            return false;
        } else if (isBPiece(piece) && isBPiece(target)) {
            System.out.println("You can't capture your own piece.");
            return false;
        }else {
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
            convert(move);
            System.out.println("Ingresa la casilla a la que la vas a mover");
            String to = sc.nextLine().trim();

            if (!validateInput(to)) {
                System.out.println("Formato inválido. Usa letra+a–h y número 1–8. Ejemplo: e4");
                continue;
            }

            moveTo = to.toCharArray();
            convert(moveTo);

            validM = validMove(board, move, moveTo);
        }
        board[moveTo[1]][moveTo[0]] = board[move[1]][move[0]];
        board[move[1]][move[0]] = '\0';
        return board;
    }
}
