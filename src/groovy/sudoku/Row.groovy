package sudoku

import com.google.common.base.Predicate
import com.google.common.collect.Collections2

class Row extends AbstractUnit implements Comparable<Row> {

    Integer row
    Set<Cell> cells
    UnitType type = UnitType.Row

    Row(Integer row, Collection<Cell> cells) {
        this.row = row
        this.cells = new TreeSet<Cell>(Collections2.filter(cells, new Predicate<Cell>(){
            boolean apply(Cell cell) {
                return cell.row == row
            }
        }))
    }

    @Override
    String toString() {
        return "Row " + Character.toChars(96 + row)
    }

    int compareTo(Row t) {
        return this.row.compareTo(t.row)
    }
}
