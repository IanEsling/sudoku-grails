class ReportingSolver {

    OnlyPossibleInUnitSolver onlyPossibleInUnitSolver = new OnlyPossibleInUnitSolver()

    def solve = {Board board ->
        while (onlyPossibleInUnitSolver.solveForBoard(board)) {
            onlyPossibleInUnitSolver.report.each {
                println it
            }
            println board.asString()
            println board.toString()
            solve(board)
        }
    }
}
