/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nancy
 */

public class Main {
    public static void main(String[] args) {
        char[][] board = new char[8][8]; // create an empty board
        
        // Example: place a piece
        board[0][0] = 'R'; // Rook at top-left
        
        // Print the board
        ChessBoard.printBoard(board);
        
        // Example: convert a move
        char[] move = {'e', '2'};
        ChessBoard.convert(move);
        System.out.println("Converted move indices: [" + (int)move[0] + "," + (int)move[1] + "]");
    }
}
