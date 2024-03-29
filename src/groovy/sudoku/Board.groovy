package sudoku

import com.google.common.collect.Lists
import com.google.common.collect.Sets

class Board implements Serializable {

    final TreeSet<Cell> cells
    final Set<Column> columns
    final Set<Row> rows
    final Set<Region> regions

    Board(String board) {
        if (board.length() != 81) {
            throw new BoardCreationException("Need exactly 81 characters to create a new board, only ${board.length()} provided: ${board}")
        }
        //TODO: accept things other than .s as empty squares
        if (!(board ==~ "[/.1-9]{81}")) {
            throw new BoardCreationException("Only 1-9 and . (for empty square) allowed to create a new board: $board")
        }
        cells = Sets.newTreeSet()
        (1..9).each {row ->
            (1..9).each {column ->
                cells.add new Cell(row, column,
                        board.charAt(((row - 1) * 9) + (column - 1)) == "." ?
                            Lists.newArrayList(1..9) :
                            Lists.newArrayList(Integer.valueOf(board.charAt(((row - 1) * 9) + (column - 1)).toString())))
            }
        }
        rows = Sets.newTreeSet()
        columns = Sets.newTreeSet()
        (1..9).each {
            rows << new Row(it, cells)
            columns << new Column(it, cells)
        }
        regions = Sets.newTreeSet()
        int i = 1
        [(1..3), (4..6), (7..9)].each {rowList ->
            [(1..3), (4..6), (7..9)].each {columnList ->
                regions << new Region(rowList, columnList, cells, i)
                i++
            }
        }

        validateBoard()
    }

    private def validateBoard() {
        if (!cellsForUnitsAreValid(columns)
                || !cellsForUnitsAreValid(rows)
                || !cellsForUnitsAreValid(regions)
        ) {
            throw new InvalidBoardException("tried to create invalid board:\n" +
                    "rows:${rows}\n" +
                    "columns:${columns}\n" +
                    "${toString()}")
        }
    }

    def originalCells = {
        cells.findAll {
            it.originalCell
        }
    }

    boolean cellsForUnitsAreValid(Set<? extends Unit> units) {
        units.every {unit ->
            unitCellsAreValid unit
        }
    }

    private boolean unitCellsAreValid(Unit unit) {
        boolean valid = true
        Set<Integer> values = Sets.newHashSet()
        unit.cells.each {cell ->
            if (cell.values.size() == 1) {
                if (values.any {it == cell.values[0]}) {
                    valid = false
                }
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

    boolean isSolved() {
        return !asString().contains(".")
    }

    Cell getCell(Integer row, Integer column) {
        cells.find {cell ->
            cell.row == row && cell.column == column
        }
    }

    Row getRow(Integer rowIndex) {
        rows.find {boardRow ->
            boardRow.row == rowIndex
        }
    }

    Column getColumn(Integer columnIndex) {
        columns.find {boardColumn ->
            boardColumn.column == columnIndex
        }
    }

    String toStringForFailedBoard() {
        StringBuffer buffer = new StringBuffer()
        Map<Cell, List<Integer>> cellValues = new HashMap<Cell, List<Integer>>()
        cells.each {cell ->
            List<Integer> values = new ArrayList<Integer>([0, 0, 0, 0, 0, 0, 0, 0, 0])
            cell.values.each {
                values.remove(it - 1)
                values.add it - 1, it
            }
            cellValues.put cell, values
        }
        rows.each {row ->
            if (row.row == 4 || row.row == 7) {
                buffer.append "---------------------------------- + ----------------------------------- + ------------------------------------\n"
            } else {
                buffer.append "---------   ---------   ---------  |  ---------   ---------   ---------  |  ---------   ---------   ---------\n"
            }

            row.cells.each {cell ->
                (0..2).each {
                    buffer.append cellValues[cell][it] == 0 ? " . " : " " + cellValues[cell][it] + " "
                }
                if (cell.column == 3 || cell.column == 6) {
                    buffer.append "| | |"
                } else {
                    buffer.append "| |"
                }

            }
            buffer.append "\n"
            row.cells.each {cell ->
                (3..5).each {
                    buffer.append cellValues[cell][it] == 0 ? " . " : " " + cellValues[cell][it] + " "
                }
                if (cell.column == 3 || cell.column == 6) {
                    buffer.append "| | |"
                } else {
                    buffer.append "| |"
                }
            }
            buffer.append "\n"
            row.cells.each {cell ->
                (6..8).each {
                    buffer.append cellValues[cell][it] == 0 ? " . " : " " + cellValues[cell][it] + " "
                }
                if (cell.column == 3 || cell.column == 6) {
                    buffer.append "| | |"
                } else {
                    buffer.append "| |"
                }
            }
            buffer.append "\n"
        }
        return buffer.toString()
    }

    boolean isValid() {
        return cellsForUnitsAreValid(columns) && cellsForUnitsAreValid(regions) && cellsForUnitsAreValid(rows)
    }
}
