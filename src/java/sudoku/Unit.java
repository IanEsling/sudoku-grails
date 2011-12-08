package sudoku;

import java.util.Collection;
import java.util.Set;

public interface Unit {

    Set<Cell> getCells();
    
    Set<Cell> getCellsSolvedFor(Collection<Integer> values);

    Set<Cell> getUnsolvedCells();

    Set<Integer> solvedNumbers();

    Set<Integer> possibleNumbers();

    UnitType getType();
}
