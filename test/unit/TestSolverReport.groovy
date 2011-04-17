import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNull

class TestSolverReport {

    Board board
    OnlyOneChoiceSolver solver = new OnlyOneChoiceSolver()

    @Test
    void reportOnlyOneChoiceInColumn() {
        board = new Board("123456...456......78.12....2........5.................9..........................")
        assertEquals("solved region before solving", 0, UnitTestUtils.solvedUnits(board.regions))
        solver.solveUnits board.regions
        assertEquals("only 1 region not solved correctly:\n${board.toString()}", 1, UnitTestUtils.solvedUnits(board.regions))
        assertEquals("solver report for region incorrect",
                "3,3 must be a 9 because it's the only choice for this region",
                solver.report.toString())
        board = new Board("..3.1.4...876543211..9...5.......................................................")
        solver.solveUnits board.rows
        assertEquals("solver report for row incorrect",
                "2,1 must be a 9 because it's the only choice for this row",
                solver.report.toString())
        solver.solveUnits board.columns
        assertNull("solver report should be null when not solving anything", solver.report)
        board = new Board("1........2........3........4........5........6........7........8.................")
        solver.solveUnits board.columns
        assertEquals("solver report for columns incorrect",
                "9,1 must be a 9 because it's the only choice for this column",
                solver.report.toString())
    }
}
