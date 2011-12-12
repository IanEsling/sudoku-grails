package sudoku

import com.google.common.collect.Lists

class Cell implements Comparable<Cell> {

    boolean lastOneSolved, originalCell
    final Integer row, column
    final List<Integer> values
    Report report = new Report()

    Cell(Integer row, Integer column, List<Integer> values) {
        this.row = row
        this.column = column
        this.values = new ArrayList<Integer>(values)
        this.originalCell = values.size() == 1
    }

    void solve(Integer number) {
        values.each {
            it == number ? null : remove(it)
        }
    }

    void remove(Integer number) {
        values.removeAll(Lists.newArrayList(number))
        if (values.size() == 1) {
            report.add("so it must be a ${values[0]}")
            lastOneSolved = true
        }
        if (values.size() == 0) {
            throw new RuntimeException("reduced values for cell ${row},${column} to zero: ${report}")
        }
    }

    void remove(Unit unit) {
        if (unit.solvedNumbers().intersect(values).size() > 0) {
            report.add(toString() + "${values.toListString()} cannot be a ${makePresentable(unit.solvedNumbers().intersect(values))}" +
                    "in ${unit.toString()}", unit.getCellsSolvedFor(values))
            unit.solvedNumbers().intersect(values).each {
                remove it
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
        return "(" + Character.toChars(96 + row) + ",$column)"
    }

    String getPageId() {
        return "$row" + "$column"
    }

    int compareTo(Cell cell) {
        return this.row == cell.row ? this.column - cell.column : this.row - cell.row
    }
}
