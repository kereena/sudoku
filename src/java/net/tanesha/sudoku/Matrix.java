package net.tanesha.sudoku;

public class Matrix {

    Cell[][] matrix = null;
    int boxSize;
    
    public Matrix(int size, int boxSize, String[][] initValues) {
        matrix = new Cell[size][size];
        this.boxSize = boxSize;
        
        for (int row = 0; row < size; row++) {
            
            for (int column = 0; column < size; column++) {
                matrix[row][column] = new Cell(initValues[row][column]);
            }
        }
    }
    
    public boolean isComplete() {
        for (int row = 0; row < matrix.length; row++)
            for (int column = 0; column < matrix[row].length; column++)
                if (!matrix[row][column].hasValue())
                    return false;
        return true;
    }

    public boolean hasChanges() {
        for (int row = 0; row < matrix.length; row++)
            for (int column = 0; column < matrix[row].length; column++)
                if (matrix[row][column].hasChanges())
                    return true;
        return false;
    }

    public void resetChanges() {
        for (int row = 0; row < matrix.length; row++)
            for (int column = 0; column < matrix[row].length; column++)
                matrix[row][column].resetChanges();
    }

    public void iterateRows(LineHandler handler) {
        for (int i = 0; i < matrix.length; i++)
            handler.handleLine(i);
    }
    public void iterateColumns(LineHandler handler) {
        for (int i = 0; i < matrix[0].length; i++)
            handler.handleLine(i);
    }
    
    public void iterateRow(int row, CellHandler handler) {
        for (int i = 0; i < matrix[row].length; i++)
            handler.handleCell(matrix[row][i]);
    }
    
    public void iterateColumn(int column, CellHandler handler) {
        for (int i = 0; i < matrix.length; i++)
            handler.handleCell(matrix[i][column]);
    }

 

    /**
     * Find x/y coordinates for each box in the matrix
     * @param handler
     */
    public void iterateBoxes(BoxHandler handler) {
        int xrows = matrix.length / boxSize;
        int yrows = matrix[0].length / boxSize;
        
        for (int row = 0; row < xrows; row++)
            for (int column = 0; column < yrows; column++)
                handler.handleBox(row * boxSize, column * boxSize);
    }

    /**
     * Iterate through cells of a box in a matrix. Use iterateBoxes to find offsets.
     * @param boxX x-offset of box
     * @param boxY y-offset of box
     * @param handler
     */
    public void iterateBox(int boxX, int boxY, CellHandler handler) {

        for (int row = boxX; row < (boxX + boxSize); row++)
            for (int column = boxY; column < (boxY + boxSize); column++)
                handler.handleCell(matrix[row][column]);
    }
    
    public String toString() {
        
        StringBuffer buf = new StringBuffer();
        
        for (int i = 0; i < matrix.length; i++) {

            buf.append("+");
            for (int j = 0; j < matrix[i].length; j++)
                buf.append("-----+");
            buf.append("\n");
            
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0)
                    buf.append("| ");
                buf.append(pad(matrix[i][j].toString())).append(" | ");
            }
            buf.append("\n");
        }
        
        buf.append("+");
        for (int i = 0; i < matrix.length; i++)
            buf.append("-----+");
        buf.append("\n");
        
        return buf.toString();
    }
    private String pad(String s) {
        if (s == null)
            return "   ";
        while (s.length() < 3)
            s = " " + s;
        return s;
    }
}
