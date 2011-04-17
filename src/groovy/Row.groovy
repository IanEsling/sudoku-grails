import com.google.common.base.Predicate
import com.google.common.collect.Collections2

class Row implements Unit {

    Set<Cell> cells

    Row(Integer row, Collection<Cell> cells) {
        this.cells = Collections2.filter(cells, new Predicate<Cell>(){
            boolean apply(Cell cell) {
                return cell.row == row
            }
        })
    }
}
