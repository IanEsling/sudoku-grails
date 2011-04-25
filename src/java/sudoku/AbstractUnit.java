package sudoku;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

import java.util.Set;

public abstract class AbstractUnit implements Unit {

    String type;

    public String getType() {
        return type;
    }

    public Set<Cell> getUnsolvedCells(){
        return Sets.newHashSet(Collections2.filter(getCells(), new Predicate<Cell>() {
            public boolean apply(Cell cell) {
                return cell.getValues().size() > 1;
            }
        }));
    }

    public Set<Integer> possibleNumbers(){
        Set<Integer> possibleNumbers = Sets.newHashSet();

        for (Cell cell : getCells()) {
            if (cell.getValues().size() > 1) {
                possibleNumbers.addAll(cell.getValues());
            }
        }
        return possibleNumbers;
    }

    public Set<Integer> solvedNumbers() {
        Set<Integer> definiteNumbers = Sets.newHashSet();

        for (Cell cell : getCells()) {
            if (cell.getValues().size() == 1) {
                definiteNumbers.add(cell.getValues().get(0));
            }
        }
        return definiteNumbers;
    }
}
