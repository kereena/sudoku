package net.tanesha.sudoku;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solver {

    Matrix matrix;
    String[] valueSet;
    
    public Solver(Matrix matrix, String [] valueSet) {
        this.matrix = matrix;
        this.valueSet = valueSet;
    }
    
    public void solve() {
        
        initSolutions(matrix);

        int iterations = 0;
        
        while (true) {
            System.out.println("solveGI.iteration: " + iterations++);

            // attempt to solve using SISO algo.
            solveSISO(matrix);
            
            solveSC(matrix);

            if (!matrix.hasChanges())
                break;

            matrix.resetChanges();
            
            System.out.println(matrix);
        }
    }

    private void updateCount(Map m, String s, int count) {
        Integer i = (Integer) m.get(s);
        if (i == null)
            i = new Integer(0);

        m.put(s, new Integer(i.intValue() + count));
    }
    private int findCount(Map m, String s) {
        Integer i = (Integer) m.get(s);
        if (i == null)
            return 0;
        return i.intValue();
    }
    
    private void solveSC(final Matrix m) {

        // rows
        m.iterateRows(new LineHandler() {

            public void handleLine(int line) {

                final Map inUse = new HashMap();
                
                // find in use for a row.
                m.iterateRow(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        if (cell.hasValue())
                            updateCount(inUse, cell.toString(), 1);
                        String[] s = cell.getSolutions();
                        for (int i = 0; i < s.length; i++) {
                            updateCount(inUse, s[i], 1);
                        }
                    }
                });

                final List solutions = new LinkedList();
                
                for (Iterator i = inUse.keySet().iterator(); i.hasNext(); ) {
                    String k = (String) i.next();
                    int c = findCount(inUse, k);
                    if (c == 1)
                        solutions.add(k);
                }

                System.out.println("Found solutions: " + solutions);

                // remove in use
                m.iterateRow(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        for (Iterator i = solutions.iterator(); i.hasNext(); ) {
                            String s = (String) i.next();
                            if (cell.hasSolution(s))
                                cell.solve(s);
                        }
                    } 
                });
                
            }
        });

        // rows
        m.iterateColumns(new LineHandler() {

            public void handleLine(int line) {

                final Map inUse = new HashMap();
                
                // find in use for a row.
                m.iterateColumn(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        if (cell.hasValue())
                            updateCount(inUse, cell.toString(), 1);
                        String[] s = cell.getSolutions();
                        for (int i = 0; i < s.length; i++) {
                            updateCount(inUse, s[i], 1);
                        }
                    }
                });

                final List solutions = new LinkedList();
                
                for (Iterator i = inUse.keySet().iterator(); i.hasNext(); ) {
                    String k = (String) i.next();
                    int c = findCount(inUse, k);
                    if (c == 1)
                        solutions.add(k);
                }

                System.out.println("Found solutions: " + solutions);

                // remove in use
                m.iterateColumn(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        for (Iterator i = solutions.iterator(); i.hasNext(); ) {
                            String s = (String) i.next();
                            if (cell.hasSolution(s))
                                cell.solve(s);
                        }
                    } 
                });
                
            }
        });

        // rows
        m.iterateBoxes(new BoxHandler() {

            public void handleBox(int boxX, int boxY) {

                final Map inUse = new HashMap();
                
                // find in use for a row.
                m.iterateBox(boxX, boxY, new CellHandler() {
                    public void handleCell(Cell cell) {
                        if (cell.hasValue())
                            updateCount(inUse, cell.toString(), 1);
                        String[] s = cell.getSolutions();
                        for (int i = 0; i < s.length; i++) {
                            updateCount(inUse, s[i], 1);
                        }
                    }
                });

                final List solutions = new LinkedList();
                
                for (Iterator i = inUse.keySet().iterator(); i.hasNext(); ) {
                    String k = (String) i.next();
                    int c = findCount(inUse, k);
                    if (c == 1)
                        solutions.add(k);
                }

                System.out.println("Found solutions: " + solutions);

                // remove in use
                m.iterateBox(boxX, boxY, new CellHandler() {
                    public void handleCell(Cell cell) {
                        for (Iterator i = solutions.iterator(); i.hasNext(); ) {
                            String s = (String) i.next();
                            if (cell.hasSolution(s))
                                cell.solve(s);
                        }
                    } 
                });
                
            }
        });

    }
    
    private void solveSISO(final Matrix m) {

        // rows
        m.iterateRows(new LineHandler() {

            public void handleLine(int line) {

                final List inUse = new LinkedList();
                
                // find in use for a row.
                m.iterateRow(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        if (cell.hasValue())
                            inUse.add(cell.toString());
                    } 
                });

                // remove in use
                m.iterateRow(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        for (Iterator i = inUse.iterator(); i.hasNext(); ) {
                            String s = (String) i.next();
                            cell.removeSolution(s);
                        }
                    } 
                });
                
            }
        });

        // columns
        m.iterateColumns(new LineHandler() {

            public void handleLine(int line) {

                final List inUse = new LinkedList();
                
                // find in use for a row.
                m.iterateColumn(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        if (cell.hasValue())
                            inUse.add(cell.toString());
                    } 
                });

                // remove in use
                m.iterateColumn(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        for (Iterator i = inUse.iterator(); i.hasNext(); ) {
                            String s = (String) i.next();
                            cell.removeSolution(s);
                        }
                    } 
                });
                
            }
        });

        // boxes
        m.iterateBoxes(new BoxHandler() {

            public void handleBox(int boxX, int boxY) {

                final List inUse = new LinkedList();
                
                // find in use for a row.
                m.iterateBox(boxX, boxY, new CellHandler() {
                    public void handleCell(Cell cell) {
                        if (cell.hasValue())
                            inUse.add(cell.toString());
                    } 
                });

                // remove in use
                m.iterateBox(boxX, boxY, new CellHandler() {
                    public void handleCell(Cell cell) {
                        for (Iterator i = inUse.iterator(); i.hasNext(); ) {
                            String s = (String) i.next();
                            cell.removeSolution(s);
                        }
                    } 
                });
                
            }
        });


    }
    
    
    private void initSolutions(final Matrix m) {

        // add all possible solutions to all fields. 
        m.iterateRows(new LineHandler() {
            public void handleLine(int line) {
                m.iterateRow(line, new CellHandler() {
                    public void handleCell(Cell cell) {
                        if (cell.hasValue())
                            return;
                        for (int i = 0; i < valueSet.length; i++)
                            cell.addSolution(valueSet[i]);
                    }
                });
            }
        });
    }
}
