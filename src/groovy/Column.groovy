import com.google.common.collect.Collections2
import com.google.common.base.Predicate

class Column implements Unit {

    Set<Cell> cells

    Column(Integer column, Collection<Cell> cells) {
        this.cells = Collections2.filter(cells, new Predicate<Cell>(){
            boolean apply(Cell cell) {
                return cell.column == column
            }
        })
    }
}
