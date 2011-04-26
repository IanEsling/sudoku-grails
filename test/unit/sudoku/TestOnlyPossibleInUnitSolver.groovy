package sudoku

import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class TestOnlyPossibleInUnitSolver {

    OnlyPossibleInUnitSolver testee = new OnlyPossibleInUnitSolver()

    @Test
    void onlyPossibleInUnit() {
        Board board = new Board(".53...79...97534..1.......2.9..8..1....9.7....8..3..7.5.......3..76412...61...94.")
        BasicSolver solver = new BasicSolver()
        assertEquals(4, board.getColumn(2).solvedNumbers().size())
        while (solver.solveForBoard(board)) {
            assertNotNull("no report for only possible in unit solver", solver.report)
            solver.report.each {
                println it
            }
            println board.asString()
            println board.toString()
        }
        println board.toString()
        assertEquals(9, board.getColumn(2).solvedNumbers().size())
        while (testee.solveForBoard(board)) {
            assertNotNull("no report for only possible in unit solver", testee.report)
            testee.report.each {
                println it
            }
            println board.asString()
            println board.toString()
        }
        (1..9).each {
            assertEquals("column $it not solved: ${board.asString()}", 9, board.getColumn(it).solvedNumbers().size())
            assertEquals("row $it not solved: ${board.asString()}", 9, board.getRow(it).solvedNumbers().size())
        }
    }
}
