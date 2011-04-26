package sudoku

import org.junit.Test
import static org.junit.Assert.fail

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("3.128...7...1.7...7.693451..7.4.918.9.8.217.4.1.873.5...9.4.371...7.28.5..73.8..6")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            fail "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
