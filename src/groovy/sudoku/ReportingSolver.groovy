package sudoku

class ReportingSolver {

    NakedPairsSolver nakedPairsSolver = new NakedPairsSolver()

    boolean solve(Board board) {
        while (nakedPairsSolver.solveForBoard(board)) {
            nakedPairsSolver.report.each {
                println it
            }
            println board.asString()
            println board.toString()
            solve(board)
        }
        if (!board.isValid()) {
            throw new RuntimeException("invalid board: ${board.toStringForFailedBoard()}")
        }
        return !board.toString().contains(".")
    }
}
