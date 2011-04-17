import com.google.common.base.Predicate
import com.google.common.collect.Collections2

class Region extends AbstractUnit {

    Set<Cell> cells
    String type = "region"

    Region(Collection<Integer> columns, Collection<Integer> rows, Collection<Cell> cells) {
        this.cells = Collections2.filter(cells, new Predicate<Cell>() {
            boolean apply(Cell cell) {
                return columns.contains(cell.column) && rows.contains(cell.row)
            }
        })
    }
}