import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            println "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
