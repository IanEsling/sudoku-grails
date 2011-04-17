import org.junit.Test
import static org.junit.Assert.assertEquals

@SuppressWarnings("GroovyAssignabilityCheck") class TestOnlyOnePossibleChoice {

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

    @Test
    void onlySolveFirstProblem() {
        board = new Board("123456...456789...78.12....2........5.................9..........................")
        assertEquals("solved region before solving", 0, UnitTestUtils.solvedUnits(board.regions))
        solver.solveUnits board.regions
        assertEquals("only 1 region not solved correctly:\n${board.toString()}", 1, UnitTestUtils.solvedUnits(board.regions))
        board = new Board("1.......22.......33.......44.......55.......66.......77.......88.......9.........")
        assertEquals("solved column before solving", 0, UnitTestUtils.solvedUnits(board.columns))
        solver.solveUnits board.columns
        assertEquals("only 1 column not solved correctly:\n${board.toString()}", 1, UnitTestUtils.solvedUnits(board.columns))
        board = new Board("12345678.78912345................................................................")
        assertEquals("solved row before solving", 0, UnitTestUtils.solvedUnits(board.rows))
        solver.solveUnits board.rows
        assertEquals("only 1 row not solved correctly:\n${board.toString()}", 1, UnitTestUtils.solvedUnits(board.rows))
    }
}
