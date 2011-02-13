package net.tanesha.sudoku;

import java.util.HashSet;
import java.util.Set;

public class Cell {

    String value = null;
    Set solutions = new HashSet();
    boolean changes = false;
    
    public Cell(String value) {
        if (value == null || value.trim().length() == 0)
            value = null;

        this.value = value;
    }
    public Cell() {
        this(null);
    }
    public boolean hasValue() {
        return value != null;
    }
    public void removeSolution(String s) {
        boolean t = solutions.remove(s);

        if (!t)
            return;

        changes = true;

        if (solutions.size() == 1) {
            this.value = (String) solutions.iterator().next();
            solutions.clear();
        }
    }
    public void solve(String value) {
        this.value = value;
        changes = true;
        solutions.clear();
    }
    public void addSolution(String value) {
        changes = true;
        solutions.add(value);
    }
    public String[] getSolutions() {
        return (String[]) solutions.toArray(new String[solutions.size()]);
    }
    public boolean hasSolution(String value) {
        return solutions.contains(value);
    }
    public String toString() {
        return value;
    }
    public boolean hasChanges() {
        return changes;
    }
    public void resetChanges() {
        changes = false;
    }
}
