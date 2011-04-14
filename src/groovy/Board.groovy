import com.google.common.collect.*

class Board {

    final TreeBasedTable<Integer, Integer, List<Integer>> grid
    Multimap<List<Integer>, List<Integer>> regions

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

        boolean valid = true
        regions.asMap().each {entry ->
            List<Integer> squares = []
            entry.key.each {row ->
                entry.value.each {columnList ->
                    columnList.each {column ->
                        if (grid.get(row, column).size() == 1) {
                            if (squares.contains(grid.get(row, column)[0])) {
                                valid = false
                            }
                            else {
                                squares.add(grid.get(row, column)[0])
                            }
                        }
                    }
                }
            }
        }
        return valid
    }

    boolean allRowsAreValid() {
        rowOrColumnIsValid rowMap()
    }

    boolean allColumnsAreValid() {
        rowOrColumnIsValid columnMap()
    }

    private boolean rowOrColumnIsValid(Map<Integer, Map<Integer, List<Integer>>> map) {
        boolean valid = true
        map.each {row ->
            Multimap<Integer, Integer> rows = ArrayListMultimap.create()
            row.value.each {column ->
                if (column.value.size() == 1)
                    if (rows.get(row.key).contains(column.value[0])) {valid = false}
                    else {
                        rows.put(row.key, column.value[0])
                    }
            }
        }
        return valid
    }

    @SuppressWarnings("GroovyAssignabilityCheck") Board(String board) {
        if (!(board ==~ "[/.0-9]{81}")) {
            throw new RuntimeException("trying to create board with invalid string: $board")
        }
        grid = TreeBasedTable.create()
        (0..8).each {row ->
            (0..8).each {column ->
                grid.put row, column,
                        Lists.newArrayList(board.charAt((row * 9) + column) == "." ?
                            (1..9) :
                            Integer.valueOf(board.charAt((row * 9) + column).toString()))
            }
        }
        createRegions()
    }

    void createRegions() {
        regions = new HashMultimap<List<Integer>, List<Integer>>()
        regions.put((0..2), (0..2))
        regions.put((3..5), (0..2))
        regions.put((6..8), (0..2))
        regions.put((0..2), (3..5))
        regions.put((3..5), (3..5))
        regions.put((6..8), (3..5))
        regions.put((0..2), (6..8))
        regions.put((3..5), (6..8))
        regions.put((6..8), (6..8))
    }

    @Override
    String toString() {
        StringBuffer buffer = new StringBuffer()
        grid.rowMap().each {entry ->
            entry.value.each {column ->
                buffer.append column.value.size() == 1 ? " " + column.value[0] + " " : " . "
                if (column.key == 2 || column.key == 5) buffer.append " | "
                if (column.key == 8) {
                    buffer.append "\n"
                }
            }
            if (entry.key == 2 || entry.key == 5) {
                buffer.append "--------- + --------- + ---------\n"
            }
        }
        return buffer.toString()
    }

    String asString() {
        StringBuffer buffer = new StringBuffer()
        grid.rowMap().each {entry ->
            entry.value.each {column ->
                buffer.append column.value.size() == 1 ? column.value[0] : "."
            }
        }
        return buffer.toString()
    }
}
