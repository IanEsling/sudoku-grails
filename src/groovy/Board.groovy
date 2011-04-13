import com.google.common.collect.Lists
import com.google.common.collect.TreeBasedTable

class Board {

    final TreeBasedTable<Integer, Integer, List<Integer>> grid

    @SuppressWarnings("GroovyAssignabilityCheck") Board(String board) {
        if (!(board ==~ "[/.0-9]{81}" )) {
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
}
