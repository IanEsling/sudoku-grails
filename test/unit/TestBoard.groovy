import com.google.common.collect.Lists
import com.google.common.collect.TreeBasedTable
import org.junit.Ignore
import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class TestBoard {

    String boardString = """123.56789
234.67891
345.78912
456.89123
567.91234
678.12345
789.23456
891.34567
912.45678"""

    @Test
    void createBoardFromInput() {
        TreeBasedTable<Integer, Integer, List<Integer>> board = newBoard(boardString)
        assertNotNull(board)
        assertEquals("wrong size of board", 9, board.rowMap().size())
        board.rowMap().each {row ->
            row.value.each {column->
            if (column.key == 3) {assertEquals("wrong numbers in column 3: $column", 9, column.value.size())}
            else {
                assertEquals("wrong number not in column 3: $column", 1, column.value.size())
            }
            }

        }
    }

    TreeBasedTable<Integer, Integer, List<Integer>> newBoard(String board) {
        TreeBasedTable<Integer, Integer, List<Integer>> newBoard = TreeBasedTable.create()
        (0..8).each {row ->
            (0..8).each {column ->
                newBoard.put(row, column, Lists.newArrayList(board.charAt(row * 10 + column) == "." ? (1..9) : board.charAt(row + column)))
            }
        }
        return newBoard
    }

    @Ignore
    @Test
    void boardHasAllPossibleNumbersFilledIn() {

    }
}
