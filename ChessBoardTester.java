/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nancy
 */

public class ChessBoardTester {

    public static void main(String[] args) {
        testConvert();
        testPrintBoard();
        testIntegration();
        fullCoverageTest();
    }

    // testing convert() method
    public static void testConvert() {
        System.out.println("=== Testing convert() ===");

        // Normal case
        char[] move1 = {'e','2'};
        Board.convert(move1);
        System.out.println("e2 -> [" + (int)move1[0] + "," + (int)move1[1] + "] (expected [4,6])");

        // Boundaries
        char[] move2 = {'a','1'};
        Board.convert(move2);
        System.out.println("a1 -> [" + (int)move2[0] + "," + (int)move2[1] + "] (expected [0,7])");

        char[] move3 = {'h','8'};
        Board.convert(move3);
        System.out.println("h8 -> [" + (int)move3[0] + "," + (int)move3[1] + "] (expected [7,0])");

        // Invalid input (just to see what happens)
        char[] move4 = {'z','9'};
        try {
            Board.convert(move4);
            System.out.println("z9 -> [" + (int)move4[0] + "," + (int)move4[1] + "]");
        } catch(Exception e) {
            System.out.println("z9 -> Exception caught: " + e.getMessage());
        }
    }

    // testing PrintBoard
    public static void testPrintBoard() {
        System.out.println("\n=== Testing printBoard() ===");
        char[][] board = new char[8][8];

        // Empty board
        System.out.println("Empty board:");
        Board.printBoard(board);

        // Board with some pieces
        board[0][0] = 'R';
        board[0][7] = 'R';
        board[7][0] = 'r';
        board[7][7] = 'r';
        System.out.println("\nBoard with pieces:");
        Board.printBoard(board);
    }

    // integration test
    public static void testIntegration() {
        System.out.println("\n=== Integration Test ===");
        char[][] board = new char[8][8];

        char[] move1 = {'e','2'};
        Board.convert(move1);
        board[move1[1]][move1[0]] = 'Q'; // Queen

        char[] move2 = {'g','1'};
        Board.convert(move2);
        board[move2[1]][move2[0]] = 'N'; // Knight

        Board.printBoard(board);
    }

    // loop through all 64 positions
    public static void fullCoverageTest() {
        System.out.println("\n=== Full Coverage Test ===");
        char[][] board = new char[8][8];

        for (char col = 'a'; col <= 'h'; col++) {
            for (char row = '1'; row <= '8'; row++) {
                char[] move = {col, row};
                Board.convert(move);
                board[move[1]][move[0]] = 'P'; // Place a dummy pawn
            }
        }

        Board.printBoard(board);
        System.out.println("\nFull coverage test complete. Check board for correct placement and pattern.");
    }
}
