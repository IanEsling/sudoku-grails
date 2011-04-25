import org.junit.Test
import static org.junit.Assert.fail
import sudoku.Board

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("2...8.3...6..7..84.3.5..2.9...1.54.8.........4.27.6...3.1..7.4.72..4..6...4.1...3")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            fail "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
