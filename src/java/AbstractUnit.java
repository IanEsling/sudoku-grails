import com.google.common.collect.Sets;

import java.util.Set;

public abstract class AbstractUnit implements Unit {

    String type;

    public String getType() {
        return type;
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
