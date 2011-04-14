import org.junit.Test
import static org.junit.Assert.*

class TestBoard {

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
    void seeBoard(){
        println new Board(boardString).toString()
    }

    @Test(expected = RuntimeException.class)
    void invalidBoardInputLength() {
        newBoard("748923748923")
    }

    @Test(expected = RuntimeException.class)
    void invalidBoardInputCharacter1() {
        newBoard("1..2..3...4..%..6...7..8..91..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..9")
    }

    @Test(expected = RuntimeException.class)
    void invalidBoardInputCharacter2() {
        newBoard("1..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..g1..2..3...4..5..6...7..8..9")
    }

    @Test(expected = RuntimeException.class)
    void invalidBoardInputCharacter3() {
        newBoard("1..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..91..2..3...4..5..&...7..8..9")
    }

    @Test(expected = RuntimeException.class)
    void invalidBoardInputCharacter4() {
        newBoard("l..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..9")
    }

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
        return board.allRegionsAreValid()
    }

    boolean allColumnsAreValid(Board board) {
        return board.allColumnsAreValid()
    }

    boolean allRowsAreValid(Board board) {
        return board.allRowsAreValid()
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    Board newBoard(String board) {
        return new Board(board)
    }
}
