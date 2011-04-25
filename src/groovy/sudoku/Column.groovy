package sudoku

import com.google.common.base.Predicate
import com.google.common.collect.Collections2

class Column extends AbstractUnit implements Comparable<Column> {

    Set<Cell> cells
    String type = "column"
    Integer column

    Column(Integer column, Collection<Cell> cells) {
        this.column = column
        this.cells = Collections2.filter(cells, new Predicate<Cell>() {
            boolean apply(Cell cell) {
                return cell.column == column
            }
        })
    }

    @Override
    String toString() {
        return "sudoku.Column $column"
    }

    int compareTo(Column t) {
        return this.column.compareTo(t.column)
    }


}
