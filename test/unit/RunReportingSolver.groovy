import org.junit.Test
import static org.junit.Assert.fail

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("....7..6..5.4.6...2.8...4....31....5.4..9..3.6....31....4...7.8...8.4.9..3..5....")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            fail "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
