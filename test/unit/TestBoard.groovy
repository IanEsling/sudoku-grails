import com.google.common.collect.Lists
import com.google.common.collect.TreeBasedTable
import org.junit.Ignore
import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertFalse
import com.google.common.collect.Multimap
import com.google.common.collect.ArrayListMultimap

class TestBoard {

    String boardString =    "123.56789" +
                            "234.67891" +
                            "345.78912" +
                            "456.89123" +
                            "567.91234" +
                            "678.12345" +
                            "789.23456" +
                            "891.34567" +
                            "912.45678"

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

    @Test
    void boardIsValidSudokuPuzzle() {
        assertTrue("board rows should be valid", allRowsAreValid(newBoard(boardString)))
        assertFalse("board rows should be invalid", allRowsAreValid(newBoard("123456788........................................................................")))
        assertTrue("board columns should be valid", allColumnsAreValid(newBoard(boardString)))
        assertFalse("board columns should be invalid", allColumnsAreValid(newBoard("1........2........3........4........5........6........7........8........8........")))
    }

    boolean allColumnsAreValid(TreeBasedTable<Integer,Integer,List<Integer>> board) {
        true
    }

    boolean allRowsAreValid(board){
        def valid = true
        board.rowMap().each {row->
            Multimap<Integer,Integer> rows = ArrayListMultimap.create()
            row.value.each {column->
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
    TreeBasedTable<Integer, Integer, List<Integer>> newBoard(String board) {
        TreeBasedTable<Integer, Integer, List<Integer>> newBoard = TreeBasedTable.create()
        (0..8).each {row ->
            (0..8).each {column ->
                newBoard.put(row, column, Lists.newArrayList(board.charAt((row * 9) + column) == "." ?
                    (1..9) :
                    board.charAt((row * 9) + column)))
            }
        }
        return newBoard
    }
}
