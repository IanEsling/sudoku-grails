package sudoku

import org.junit.Test
import static org.junit.Assert.assertEquals

class TestSolverReport {

    Board board
    BasicSolver solver = new BasicSolver()

    @Test
    void reportIsNullIfNothingSolved() {
        board = new Board("..3.1.4...876543211..9...5.......................................................")
        solver.solveUnits board.rows
        assertEquals("solver report for row incorrect",
                "2,1 [1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2, 3, 4, 5, 6, 7 or 8 because they are in Row 2",
                solver.report[0].toString())
        solver.solveUnits board.columns
        assertEquals("solver report should be null when not solving anything", 0, solver.report.size())
    }

    @Test
    void reportOnlyOneChoiceInUnit() {
        board = new Board("123456...456......78.12....2........5.................9..........................")
        assertEquals("solved region before solving", 0, UnitTestUtils.solvedUnits(board.regions))
        solver.solveUnits board.regions
        assertEquals("only 1 region not solved correctly:\n${board.toString()}", 1, UnitTestUtils.solvedUnits(board.regions))
        assertEquals("solver report for region incorrect",
                "3,3 [1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2, 3, 4, 5, 6, 7 or 8 because they are in Region 1",
                solver.report[0].toString())
        board = new Board("..3.1.4...876543211..9...5.......................................................")
        solver.solveUnits board.rows
        assertEquals("solver report for row incorrect",
                "2,1 [1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2, 3, 4, 5, 6, 7 or 8 because they are in Row 2",
                solver.report[0].toString())
        solver.solveUnits board.columns
        assertEquals("solver report should be empty when not solving anything: ${solver.report}", 0, solver.report.size())
        board = new Board("1........2........3........4........5........6........7........8.................")
        solver.solveUnits board.columns
        assertEquals("solver report for columns incorrect",
                "9,1 [1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2, 3, 4, 5, 6, 7 or 8 because they are in Column 1",
                solver.report[0].toString())
    }
}
