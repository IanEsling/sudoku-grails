package sudoku

import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class TestBoard {

    String boardString = "123.56789" +
            "456.89123" +
            "789.23456" +
            "231.45978" +
            "564.78312" +
            "897.12645" +
            "312.64897" +
            "645.97231" +
            "978.31564"

    @Test
    void seeBoard() {
        println new Board(boardString).toString()
    }

    @Test(expected = BoardCreationException.class)
    void invalidBoardInputLength() {
        newBoard("748923748923")
    }

    @Test(expected = BoardCreationException.class)
    void invalidBoardInputCharacter1() {
        newBoard("1..2..3...4..%..6...7..8..91..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..9")
    }

    @Test(expected = BoardCreationException.class)
    void invalidBoardInputCharacter2() {
        newBoard("1..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..g1..2..3...4..5..6...7..8..9")
    }

    @Test(expected = BoardCreationException.class)
    void invalidBoardInputCharacter3() {
        newBoard("1..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..91..2..3...4..5..&...7..8..9")
    }

    @Test(expected = BoardCreationException.class)
    void invalidBoardInputCharacter4() {
        newBoard("l..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..91..2..3...4..5..6...7..8..9")
    }

    @Test
    void createBoardFromInput() {
        Board board = newBoard(boardString)
        assertNotNull(board)
        assertEquals("wrong size of board", 81, board.cells.size())
    }

    @Test(expected = InvalidBoardException.class)
    void invalidRow() {
        newBoard("123456788........................................................................")
    }

    @Test(expected = InvalidBoardException.class)
    void invalidColumn() {
        newBoard("1........2........3........4........5........6........7........8........8........")
    }

    @Test(expected = InvalidBoardException.class)
    void invalidRegion() {
        newBoard("123456789456789123791............................................................")
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    Board newBoard(String board) {
        return new Board(board)
    }
}
