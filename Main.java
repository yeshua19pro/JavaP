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
        char[][] ckBoard = new char[8][8];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 1) ckBoard[r][c] = 'O';
            }
        }
        for (int r = 5; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((r + c) % 2 == 1) ckBoard[r][c] = '◉';
            }
        }


        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Checkers and Chess program!");
        System.out.println("Please choose a game to play:");
        System.out.println("1. Checkers \n2. Chess");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Starting Checkers game...");
                while (true) {
                    char[] move = null;
                    char[] moveTo = null;
                    if (whiteTurn) {
                        System.out.println("White turn");
                        GameMaster.draw(sc);
                        if (!CheckersGM.hasPieces(ckBoard, whiteTurn)) {
                            System.out.println("Black wins! White has no pieces left.");
                            break;
                        }
                    } else {
                        System.out.println("Black turn");
                        GameMaster.draw(sc);
                        if (!CheckersGM.hasPieces(ckBoard, whiteTurn)) {
                            System.out.println("White wins! Black has no pieces left.");
                            break;
                        }
                    }
                    Board.printCKBoard(ckBoard);
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

                    if (!CheckersGM.validMove(ckBoard, move, moveTo, whiteTurn)){
                        System.out.println("Do you have any valid movement? 1. Yes 2. No");
                        String validMove = sc.nextLine();
                        switch (validMove){
                            case "1":
                                break;
                            case "2":
                                if (whiteTurn){
                                    System.out.println("Black wins! White has no valid moves left.");
                                    sc.close();
                                    System.exit(0);
                                } else {
                                    System.out.println("White wins! Black has no valid moves left.");
                                    sc.close();
                                    System.exit(0);
                                }
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                        continue;
                    }


                    CheckersGM.applyMove(ckBoard, move, moveTo);

                    CheckersGM.crown(ckBoard);

                    whiteTurn = !whiteTurn;
                }
            case "2":
                System.out.println("Starting Chess game...");
                while (true){
                    while (whiteTurn) {
                        System.out.println("White's turn");
                        GameMaster.draw(sc);
                        Board.printBoard(board);
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
                                    System.exit(0);
                                    return;
                                case "2":
                                    break;
                                default:
                                    System.out.println("Invalid choice. Continuing game.");
                                    break;
                            }
                        }
                        System.out.println("White's turn");
                        GameMaster.draw(sc );
                        Board.printBoard(board);
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
