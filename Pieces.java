public class Pieces {
    public static boolean validKnight(char[] move, char[] moveTo){
        int col = move[0];
        int row = move[1];
        if (moveTo[0] == col - 2 && moveTo[1] == row - 1) return true;
        else if (moveTo[0] == col - 2 && moveTo[1] == row + 1) return true;
        else if (moveTo[0] == col - 1 && moveTo[1] == row - 2) return true;
        else if (moveTo[0] == col + 1 && moveTo[1] == row - 2) return true;
        else if (moveTo[0] == col - 1 && moveTo[1] == row + 2) return true;
        else if (moveTo[0] == col + 1 && moveTo[1] == row + 2) return true;
        else if (moveTo[0] == col + 2 && moveTo[1] == row - 1) return true;
        else if (moveTo[0] == col + 2 && moveTo[1] == row + 1) return true;
        return false;
    }

}
