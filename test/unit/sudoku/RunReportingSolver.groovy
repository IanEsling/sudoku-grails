package sudoku

import org.junit.Test
import static org.junit.Assert.fail

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("5.7...8.3....1....2.......9...........64.52.....186...7.32.45.86..7.8..2..2.6.7..")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            fail "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
