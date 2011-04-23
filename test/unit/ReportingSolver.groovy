class ReportingSolver {

    BasicSolver basicSolver = new BasicSolver()
    OnlyPossibleInUnitSolver onlyPossibleInUnitSolver = new OnlyPossibleInUnitSolver()
    NakedPairsSolver nakedPairsSolver = new NakedPairsSolver()

    boolean solve (Board board) {
        while (nakedPairsSolver.solveForBoard(board)) {
            nakedPairsSolver.report.each {
                println it
            }
            println board.asString()
            println board.toString()
            solve(board)
        }
        return false
    }
}
