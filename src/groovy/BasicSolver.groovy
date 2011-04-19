import com.google.common.collect.Lists

class BasicSolver {

    List<String> report

    boolean solveForBoard(Board board) {
        if (!solveUnits(board.regions)) {
            if (!solveUnits(board.columns)) {
                return solveUnits(board.rows)
            }
        }
        return true
    }

    boolean solveUnits(Set<Unit> units) {
        units.any {unit ->
            solveUnit unit
        }
    }

    private boolean solveUnit(Unit unit) {
        report = Lists.newArrayList()
        boolean solved = false
        unit.cells.each {cell ->
            if (!solved && cell.values.size() > 1) {
                cell.remove unit
                if (cell.values.size() == 1) {
                    solved = true
                    report = cell.report
                }
            }
        }
        return solved
    }
}