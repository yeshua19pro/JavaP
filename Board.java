public class Board {
    public static void convert(char[] move) {
        //Converts the chess notation to array indices
        switch (move[0]){
            case 'a': // each letter corresponds to a column index
                move[0] = 0;
                break;
            case 'b':
                move[0] = 1;
                break;
            case 'c':
                move[0] = 2;
                break;
            case 'd':
                move[0] = 3;
                break;
            case 'e':
                move[0] = 4;
                break;
            case 'f':
                move[0] = 5;
                break;
            case 'g':
                move[0] = 6;
                break;
            case 'h':
                move[0] = 7;
                break;
        }
        switch (move[1]) { // each number corresponds to a row index
            case '1':
                move[1] = 7;
                break;
            case '2':
                move[1] = 6;
                break;
            case '3':
                move[1] = 5;
                break;
            case '4':
                move[1] = 4;
                break;
            case '5':
                move[1] = 3;
                break;
            case '6':
                move[1] = 2;
                break;
            case '7':
                move[1] = 1;
                break;
            case '8':
                move[1] = 0;
                break;
        }
    }
    public static void printBoard(char[][] board) {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                char p = board[fila][col];
                if (p == '\0') {
                    if ((fila + col) % 2 == 0) p = '▣';
                    else p = '▢';
                }
                if (col == 2) {
                    if (p == '▣' || p == '▢') {
                        System.out.print("| " + p + " ");
                    } else {
                        System.out.print("|" + p);
                    }
                } else {
                    System.out.print("| " + p);
                }
            }
            System.out.println("|");
        }
    }
}
