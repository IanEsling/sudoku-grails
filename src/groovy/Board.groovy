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

    @SuppressWarnings("GroovyAssignabilityCheck")
    Board(String board) {
        if (!(board ==~ "[/.0-9]{81}")) {
            throw new RuntimeException("trying to create board with invalid string: $board")
        }
        grid = TreeBasedTable.create()
        (0..8).each {row ->
            (0..8).each {column ->
                grid.put row, column,
                        Lists.newArrayList(board.charAt((row * 9) + column) == "." ?
                            (1..9) :
                            board.charAt((row * 9) + column))
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
}
