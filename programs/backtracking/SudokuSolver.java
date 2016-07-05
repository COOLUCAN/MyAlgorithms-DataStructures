package GitHub.trunk.programs.backtracking;

/**
 * Created by chch0316 on 7/5/2016.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        int [][] in = {
                {0,3,0,1},
                {1,0,3,2},
                {3,0,1,0},
                {0,1,0,3}
        };
        puzzleSolver1(in);
    }

    public static void puzzleSolver1(int[][] in) {
        auxPuzzleSolver1(in,0,0);
    }

    public static void auxPuzzleSolver1(int[][] in, int row, int col) {
        if (row == in.length) {
            if(isValid1(in))
                display(in);
            return;
        }
            //System.out.println(row+" col "+col);
            if (in[row][col] != 0) {
                auxPuzzleSolver1(in, (col == in.length-1) ? row + 1 : row, col%in.length);
            } else {
                for (int d = 1; d <=in.length; d++) {
                    in[row][col] = d;
                    auxPuzzleSolver1(in, (col == in.length - 1) ? row + 1 : row, col%in.length);
                }
                in[row][col]=0;
            }
    }

    public static boolean isValid1(int[][] in) {

        //check for each row can contains all (ex: 1-9) numbers
        //check for each column contains all (ex:1-9) numbers
        for (int r = 0; r < in.length; r++) {
            for (int d = 1; d <= in.length; d++) {
                boolean isDigitExistInRow = false,isDigitExistInCol = false;
                for (int c = 0; c < in.length; c++) {
                    if (in[r][c] == d) {
                        isDigitExistInRow = true;
                    }
                    if(in[c][r]==d){
                        isDigitExistInCol = true;
                    }
                }
                if(isDigitExistInRow==false||isDigitExistInCol==false)
                    return false;
            }
        }

        //Check for each cell in a grid
        int fact = (int) Math.sqrt(in.length);

        for (int i = 0; i < fact; i++) {
            int sr = i * fact;
            for (int j = 0; j < fact; j++) {
                int sc = j * fact;
                digit:
                for (int d = 1; d <= in.length; d++) {
                    boolean isDigitExistInGrid = false;
                    for (int r = 0; r < in.length; r++) {
                        for (int c = 0; c < in.length; c++) {
                            if (in[sr + r][sc + c] == d) {
                                isDigitExistInGrid = true;
                                continue digit;
                            }
                        }
                    }
                    if (isDigitExistInGrid == false) return false;
                }
            }
        }
        return true;
    }

    public static void display(int[][] in){
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in.length ; j++) {
                System.out.print(in[i][j]+" ");
            }
            System.out.println();
        }
    }
}
