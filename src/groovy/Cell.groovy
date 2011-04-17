
class Cell implements Comparable<Cell> {

    final Integer row, column
    final List<Integer> values

    Cell(Integer row, Integer column, List<Integer> values){
        this.row = row
        this.column = column
        this.values = values
    }

    @Override
    String toString() {
        return "Cell($row,$column)"
    }

    int compareTo(Cell cell) {
        return this.row == cell.row ? this.column - cell.column : this.row - cell.row
    }


}
