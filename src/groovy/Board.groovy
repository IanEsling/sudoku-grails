import com.google.common.collect.*

class Board {

    final TreeBasedTable<Integer, Integer, List<Integer>> grid

    final TreeSet<Cell> cells
    final Set<Column> columns
    final Set<Row> rows
    final Set<Region> regions

    Board(String board) {
        if (!(board ==~ "[/.0-9]{81}")) {
            throw new RuntimeException("trying to create board with invalid string: $board")
        }
        cells = Sets.newTreeSet()
        (1..9).each {row ->
            (1..9).each {column ->
                cells.add new Cell(row, column,
                        Lists.newArrayList(board.charAt(((row - 1) * 9) + (column - 1)) == "." ?
                            (1..9) :
                            Integer.valueOf(board.charAt(((row - 1) * 9) + (column - 1)).toString())))
            }
        }
        rows = Sets.newHashSet()
        columns = Sets.newHashSet()
        (1..9).each {
            rows << new Row(it, cells)
            columns << new Column(it, cells)
        }
        regions = Sets.newHashSet()
        [(1..3), (4..6), (7..9)].each {rowList ->
            [(1..3), (4..6), (7..9)].each {columnList ->
                regions << new Region(rowList, columnList, cells)
            }
        }

        if (!allColumnsAreValid()
                || !allRowsAreValid()
                || rows.size() != 9
                || columns.size() != 9
                || regions.size() != 9
                || !allRegionsAreValid()
        ) {
            throw new RuntimeException("""tried to create invalid board:\n
            rows:${rows}\n
            columns:${columns}\n
            ${toString()}""")
        }
    }

    def get = {row, column ->
        grid.get(row, column)
    }
    def rowMap = {
        grid.rowMap()
    }

    def columnMap = {
        grid.columnMap()
    }

    boolean allRegionsAreValid() {
        regions.every {region->
            unitCellsAreValid region
        }
    }

    boolean allRowsAreValid() {
        rows.every {row ->
            unitCellsAreValid row
        }
    }

    boolean allColumnsAreValid() {
        columns.every {column ->
            unitCellsAreValid column
        }
    }

    private boolean unitCellsAreValid(Unit unit) {
        boolean valid = true
        Set<Integer> values = Sets.newHashSet()
        unit.cells.each {cell ->
            if (cell.values.size() == 1) {
                if (values.any {it == cell.values[0]}) {valid = false}
                else {
                    values.add cell.values[0]
                }
            }
        }
        return valid
    }

    @Override
    String toString() {
        StringBuffer buffer = new StringBuffer()
        cells.each {cell ->
            buffer.append cell.values.size() == 1 ?
                " " + cell.values[0] + " " :
                " . "
            if (cell.column == 3 || cell.column == 6) buffer.append " | "
            if (cell.column == 9) buffer.append "\n"
            if (cell.column == 9 && [3, 6].contains(cell.row)) {
                buffer.append "--------- + --------- + ---------\n"
            }
        }
        return buffer.toString()
    }

    String asString() {
        StringBuffer buffer = new StringBuffer()
        cells.each {cell ->
            buffer.append cell.values.size() == 1 ? cell.values[0] : "."
        }

        return buffer.toString()
    }

}
