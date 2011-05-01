package sudoku

import org.junit.Test
import static org.junit.Assert.fail

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("38..........4..785..9.2.3...6..9....8..3.2..9....4..7...1.7.5..495..6..........92")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            fail "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
