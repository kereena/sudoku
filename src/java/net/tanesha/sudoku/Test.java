package net.tanesha.sudoku;

public class Test {

    public static void main(String[] args) {
        
        Matrix matrix = new Matrix(4, 2, new String[][] {
            {"1", "2", "3", "4" }, 
            {"2", null, "4", null }, 
            {"3", "4", "1", "2" }, 
            {"4", "1", null, "3" }
        });
        
        System.out.println(matrix.toString());
        Solver solver = new Solver(matrix, new String[]{"1", "2", "3", "4"});
        
        solver.solve();

        System.out.println(matrix.toString());
        
        Matrix sud = new Matrix(9, 3, new String[][] {
            {"7", " ", " ", " ", "3", "2", " ", "8", " " }, 
            {"9", " ", " ", "4", " ", " ", " ", "5", "1" }, 
            {" ", " ", "6", "1", "9", "8", " ", " ", " " }, 
            {"3", " ", "2", " ", " ", "5", "1", "9", " " }, 
            {" ", "4", " ", "7", "8", " ", " ", " ", " " }, 
            {"6", " ", " ", " ", "1", " ", " ", " ", "5" }, 
            {" ", " ", " ", "9", " ", "1", " ", "3", "2" }, 
            {" ", "5", " ", " ", " ", "4", "7", " ", "9" }, 
            {" ", " ", "9", "8", " ", "6", " ", " ", "4" }, 
        });

        System.out.println("Example: \n" + sud.toString());

        Solver solver2 = new Solver(sud, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
        
        solver2.solve();

        System.out.println("Solution: \n" + sud.toString());
        
        Matrix sud2 = new Matrix(9, 3, new String[][] {
            {"8", "4", "3",/**/ "9", " ", " ",/**/ " ", " ", " " }, 
            {"9", " ", " ",/**/ "4", "1", " ",/**/ " ", " ", "5" }, 
            {" ", " ", " ",/**/ " ", " ", " ",/**/ "8", " ", " " }, 
            // ---------------------------------------------------
            {"7", " ", " ",/**/ "5", " ", " ",/**/ " ", " ", " " }, 
            {" ", " ", " ",/**/ "3", "2", " ",/**/ " ", " ", "1" }, 
            {" ", "8", " ",/**/ " ", " ", " ",/**/ " ", "4", " " }, 
            // ---------------------------------------------------
            {" ", " ", "6",/**/ " ", " ", " ",/**/ "4", " ", " " }, 
            {" ", " ", "1",/**/ " ", " ", "9",/**/ " ", " ", " " }, 
            {" ", " ", " ",/**/ " ", " ", "6",/**/ " ", "1", "2" }, 
        });

        System.out.println("Example: \n" + sud2.toString());

        Solver solver3 = new Solver(sud2, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
        
        solver3.solve();

        System.out.println("Solution: \n" + sud2.toString());

    }
}
