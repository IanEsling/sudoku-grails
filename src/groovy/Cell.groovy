import com.google.common.collect.Lists

class Cell implements Comparable<Cell> {

    final Integer row, column
    final List<Integer> values
    List<String> report = Lists.newArrayList()

    Cell(Integer row, Integer column, List<Integer> values) {
        this.row = row
        this.column = column
        this.values = new ArrayList<Integer>(values)
    }

    void remove(Unit unit) {
        if (unit.solvedNumbers().intersect(values).size() > 0) {
            report << "$row,$column ${values.toListString()} cannot be a ${makePresentable(unit.solvedNumbers().intersect(values))}" +
                    "in ${unit.toString()}"
            values.removeAll unit.solvedNumbers()
            if (values.size() == 1) {
                report << "so it must be a ${values[0]}"
            }
        }
    }

    String makePresentable(Collection<Integer> presentMe) {
        StringBuilder builder = new StringBuilder()
        presentMe.eachWithIndex {number, index ->
            builder.append(number)
            builder.append(index == presentMe.size() - 1 ? "" :
                (index == presentMe.size() - 2 ? " or " : ", "))
        }
        builder.append " because "
        builder.append presentMe.size() == 1 ? "it is " : "they are "
        return builder.toString()
    }

    @Override
    String toString() {
        return "Cell($row,$column)"
    }

    int compareTo(Cell cell) {
        return this.row == cell.row ? this.column - cell.column : this.row - cell.row
    }


}
