class NakedPairsSolver {

    List<String> report
    BasicSolver basicSolver = new BasicSolver()

    boolean solveForBoard(Board board) {
        basicSolver.solveForBoard(board)
        if (!solveUnits(board.getRows())) {
            if (!solveUnits(board.getColumns())) {
                if (!solveUnits(board.regions)) {
                    return false
                }
            }
        }
        return true
    }

    boolean solveUnits(Set<Unit> units) {
        units.any {unit ->
            solveUnit unit
        }
    }

    boolean solveUnit(Unit unit) {
        if (unit.unsolvedCells.inject(0) {count, cell ->
            count + (cell.values.size() == 2 ? 1 : 0)
        } == 2) {
            List<Cell> pairs = new ArrayList<Cell>(unit.unsolvedCells.findAll {cell ->
                cell.values.size() == 2
            })
            if (pairs[0].values.intersect(pairs[1].values).size() == 2) {
                unit.unsolvedCells.findAll {cell ->
                    !pairs.contains(cell)
                }.each {cell ->
                    cell.values.removeAll(pairs[0].values.intersect(pairs[1].values))
                    if (cell.values.size() == 1) println "solved $cell!!"
                }
            }
        }
    }
}
