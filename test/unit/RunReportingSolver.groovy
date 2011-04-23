import org.junit.Test

class RunReportingSolver {

    @Test
    void runReportingSolver() {
        Board board = new Board("3..2........1.7...7.6.3.5...7...9.8.9...2...4.1.8...5...9.4.3.1...7.2........8..6")
        ReportingSolver reportingSolver = new ReportingSolver()
        println board.toString()
        if (!reportingSolver.solve(board)) {
            println "failed to solve board: \n${board.toStringForFailedBoard()}"
        }
    }
}
