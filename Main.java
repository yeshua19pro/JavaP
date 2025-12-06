import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean whiteTurn = true;
        char[][] board = new char[8][8];
        board[7][0] = '♜';
        board[7][1] = '♞';
        board[7][2] = '♝';
        board[7][3] = '♛';
        board[7][4] = '♚';
        board[7][5] = '♝';
        board[7][6] = '♞';
        board[7][7] = '♜';

        for (int col = 0; col < 8; col++) {
            board[6][col] = '♟';
        }
        board[0][0] = '♖';
        board[0][1] = '♘';
        board[0][2] = '♗';
        board[0][3] = '♕';
        board[0][4] = '♔';
        board[0][5] = '♗';
        board[0][6] = '♘';
        board[0][7] = '♖';
        for (int col = 0; col < 8; col++) {
            board[1][col] = '♙';
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Checkers and Chess program!");
        System.out.println("Please choose a game to play:");
        System.out.println("1. Checkers \n 2. Chess");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                break;
            case "2":
                System.out.println("Starting Chess game...");
                while (true){
                    while (whiteTurn) {
                        if (!GameMaster.isCheck(board, whiteTurn)) {
                            System.out.println("White is in check!");
                            System.out.println("Its Checkmate? 1. Yes 2. No");
                            String checkmate = sc.nextLine();
                            switch (checkmate){
                                case "1":
                                    System.out.println("Checkmate! Black wins!");
                                    sc.close();
                                    return;
                                case "2":
                                    break;
                                default:
                                    System.out.println("Invalid choice. Continuing game.");
                                    break;
                            }
                        }
                        ChessBoard.printBoard(board);
                        board = GameMaster.moveP(board, whiteTurn);
                        whiteTurn = false;
                    }
                    while (!whiteTurn) {
                        if (!GameMaster.isCheck(board, whiteTurn)) {
                            System.out.println("Black is in check!");
                            System.out.println("Its Checkmate? 1. Yes 2. No");
                            String checkmate = sc.nextLine();
                            switch (checkmate){
                                case "1":
                                    System.out.println("Checkmate! White wins!");
                                    sc.close();
                                    return;
                                case "2":
                                    break;
                                default:
                                    System.out.println("Invalid choice. Continuing game.");
                                    break;
                            }
                        }
                        ChessBoard.printBoard(board);
                        board = GameMaster.moveP(board, whiteTurn);
                        whiteTurn = true;
                    }
                }
            default:
                System.out.println("Invalid choice. Please restart the program and choose either 1 or 2.");
                break;
        }
    }
}
