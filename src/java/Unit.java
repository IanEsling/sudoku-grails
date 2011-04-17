import java.util.Set;

public interface Unit {

    Set<Cell> getCells();

    Set<Cell> getUnsolvedCells();

    Set<Integer> solvedNumbers();

    Set<Integer> possibleNumbers();

    String getType();
}
