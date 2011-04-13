import org.junit.Before
import org.junit.Test
import com.google.common.collect.*
import static org.junit.Assert.*

class TestBoard {

    Multimap<List<Integer>, List<Integer>> regions

    @Before
    public void regions() {
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

    String boardString = "123.56789" +
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
        Board board = newBoard(boardString)
        assertNotNull(board)
        assertEquals("wrong size of board", 9, board.rowMap().size())
        board.rowMap().each {row ->
            row.value.each {column ->
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
        assertFalse("board rows should be invalid", allRowsAreValid(
                newBoard("123456788........................................................................")))
        assertTrue("board columns should be valid", allColumnsAreValid(newBoard(boardString)))
        assertFalse("board columns should be invalid", allColumnsAreValid(
                newBoard("1........2........3........4........5........6........7........8........8........")))
        assertFalse("board regions should be invalid", allRegionsAreValid(newBoard(boardString)))
        assertFalse("board region 1 should be invalid", allRegionsAreValid(
                newBoard("123......456......799............................................................")))
        assertFalse("board region 2 should be invalid", allRegionsAreValid(
                newBoard("...123......456......788.........................................................")))
        assertFalse("board region 3 should be invalid", allRegionsAreValid(
                newBoard("......123......456......778......................................................")))
        assertFalse("board region 4 should be invalid", allRegionsAreValid(
                newBoard("...........................123......456......688.................................")))
        assertFalse("board region 5 should be invalid", allRegionsAreValid(
                newBoard("..............................123......455......788..............................")))
        assertFalse("board region 6 should be invalid", allRegionsAreValid(
                newBoard(".................................123......446......788...........................")))
        assertFalse("board region 7 should be invalid", allRegionsAreValid(
                newBoard("......................................................123......356......788......")))
        assertFalse("board region 8 should be invalid", allRegionsAreValid(
                newBoard(".........................................................122......456......788...")))
        assertFalse("board region 9 should be invalid", allRegionsAreValid(
                newBoard("............................................................113......456......788")))
        assertTrue("board region 1 should be valid", allRegionsAreValid(
                newBoard("123......456......789............................................................")))
        assertTrue("board region 2 should be valid", allRegionsAreValid(
                newBoard("...123......456......789.........................................................")))
        assertTrue("board region 3 should be valid", allRegionsAreValid(
                newBoard("......123......456......789......................................................")))
        assertTrue("board region 4 should be valid", allRegionsAreValid(
                newBoard("...........................123......456......789.................................")))
        assertTrue("board region 5 should be valid", allRegionsAreValid(
                newBoard("..............................123......456......789..............................")))
        assertTrue("board region 6 should be valid", allRegionsAreValid(
                newBoard(".................................123......456......789...........................")))
        assertTrue("board region 7 should be valid", allRegionsAreValid(
                newBoard("......................................................123......456......789......")))
        assertTrue("board region 8 should be valid", allRegionsAreValid(
                newBoard(".........................................................123......456......789...")))
        assertTrue("board region 9 should be valid", allRegionsAreValid(
                newBoard("............................................................123......456......789")))
    }

    boolean allRegionsAreValid(Board board) {

        boolean valid = true
        regions.asMap().each {entry ->
            List<Integer> squares = []
            entry.key.each {row ->
                entry.value.each {columnList ->
                    columnList.each {column ->
                        if (board.get(row, column).size() == 1) {
                            if (squares.contains(board.get(row, column)[0])) {
                                valid = false
                            }
                            else {
                                squares.add(board.get(row, column)[0])
                            }
                        }
                    }
                }
            }
        }
        return valid
    }

    boolean allColumnsAreValid(Board board) {
        return validateMap(board.columnMap())
    }

    boolean allRowsAreValid(Board board) {
        return validateMap(board.rowMap())
    }

    boolean validateMap(Map<Integer, Map<Integer, List<Integer>>> rowOrColumn) {
        boolean valid = true
        rowOrColumn.each {row ->
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
    Board newBoard(String board) {
        return new Board(board)
    }

}
