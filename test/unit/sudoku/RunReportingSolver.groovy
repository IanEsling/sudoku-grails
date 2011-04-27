package sudoku

import org.junit.Test
import static org.junit.Assert.fail

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("..1..7.9.59..8...1.3.....8......58...5..6..2...41......8.....3.1...2..79.2.7..4..")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            fail "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
