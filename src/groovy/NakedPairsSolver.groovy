import com.google.common.collect.Lists

class NakedPairsSolver {

    List<String> report
    BasicSolver basicSolver = new BasicSolver()
    OnlyPossibleInUnitSolver onlyPossibleInUnitSolver = new OnlyPossibleInUnitSolver()

    boolean solveForBoard(Board board) {
        if (!onlyPossibleInUnitSolver.solveForBoard(board))
            if (!solveUnits(board.getRows())) {
                if (!solveUnits(board.getColumns())) {
                    if (!solveUnits(board.regions)) {
                        return false
                    }
                }
            }
    else {
        report = basicSolver.report
    }
    return true
}

boolean solveUnits(Set<Unit> units) {
    units.any {unit ->
        solveUnit unit
    }
}

boolean solveUnit(Unit unit) {
    report = Lists.newArrayList()
    boolean solved = false
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
                if (!solved) {
                    def tempValues = cell.values.clone()
                    cell.values.removeAll(pairs[0].values.intersect(pairs[1].values))
                    if (cell.values.size() == 1) {
                        report = cell.report
                        report << "${cell.row},${cell.column} ${tempValues} cannot be a ${pairs[0].values[0]} or a ${pairs[0].values[1]} " +
                                "because they are both the only possible values in (${pairs[0].row},${pairs[0].column})" +
                                " and (${pairs[1].row},${pairs[1].column})"
                        report << "so it must be a ${cell.values[0]}"
                        solved = true
                    }                 \

                }
            }
        }
    }

    return solved
}

}
