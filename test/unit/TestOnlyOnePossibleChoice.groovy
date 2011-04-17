import org.junit.Test
import static org.junit.Assert.assertEquals

class TestOnlyOnePossibleChoice {

    Board board
    OnlyOneChoiceSolver solver = new OnlyOneChoiceSolver()

    @Test
    void oneChoiceInRow() {
        board = new Board("..3.1.4...876543211..9...5.......................................................")
        solver.solveUnits(board.rows)
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "..3.1.4..9876543211..9...5.......................................................",
                board.asString())
        board = new Board("............................84756293.............................................")
        solver.solveUnits(board.rows)
        assertEquals("next number 1 not solved correctly:\n${board.toString()}",
                "...........................184756293.............................................",
                board.asString())
    }

    @Test
    void oneChoiceInColumn() {
        board = new Board("1........2........3........4........5........6........7........8.................")
        solver.solveUnits(board.columns)
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "1........2........3........4........5........6........7........8........9........",
                board.asString())
        board = new Board("........1........2........3........4........5........6........7........8.........")
        solver.solveUnits(board.columns)
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "........1........2........3........4........5........6........7........8........9",
                board.asString())
    }

    @Test
    void oneChoiceInRegion() {
        board = new Board("123......456......78.......2........5.................9..........................")
        solver.solveUnits board.regions
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "123......456......789......2........5.................9..........................",
                board.asString())
        board = new Board("12345678945678912378912345.2........5.................9..........................")
        solver.solveUnits board.regions
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "1234567894567891237891234562........5.................9..........................",
                board.asString())
    }
}
