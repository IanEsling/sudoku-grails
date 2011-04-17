import java.util.Set;

public interface Unit {

    Set<Cell> getCells();

    Set<Integer> solvedNumbers();

    String getType();
}
