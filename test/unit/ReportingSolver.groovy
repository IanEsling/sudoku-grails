import sudoku.HiddenPairsSolver
import sudoku.BasicSolver
import sudoku.NakedPairsSolver
import sudoku.OnlyPossibleInUnitSolver
import sudoku.Board

class ReportingSolver {

    BasicSolver basicSolver = new BasicSolver()
    OnlyPossibleInUnitSolver onlyPossibleInUnitSolver = new OnlyPossibleInUnitSolver()
    NakedPairsSolver nakedPairsSolver = new NakedPairsSolver()
    HiddenPairsSolver hiddenPairsSolver = new HiddenPairsSolver()

    boolean solve (Board board) {
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
