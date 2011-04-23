import com.google.common.base.Predicate
import com.google.common.collect.Collections2

class Region extends AbstractUnit implements Comparable<Region> {

    Set<Cell> cells
    String type = "region"
    int regionNumber

    Region(Collection<Integer> columns, Collection<Integer> rows, Collection<Cell> cells, int regionNumber) {
        this.cells = Collections2.filter(cells, new Predicate<Cell>() {
            boolean apply(Cell cell) {
                return columns.contains(cell.column) && rows.contains(cell.row)
            }
        })
        this.regionNumber = regionNumber
    }

    @Override
    String toString() {
        return "Region $regionNumber"
    }

    int compareTo(Region t) {
        return this.regionNumber.compareTo(t.regionNumber)
    }


}