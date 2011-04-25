package sudoku

import com.google.common.collect.Lists

class NakedPairsSolver {

    List<String> report
    BasicSolver basicSolver = new BasicSolver()
    OnlyPossibleInUnitSolver onlyPossibleInUnitSolver = new OnlyPossibleInUnitSolver()
    HiddenPairsSolver hiddenPairsSolver = new HiddenPairsSolver()

    boolean solveForBoard(Board board) {
        if (!basicSolver.solveForBoard(board)) {
            hiddenPairsSolver.solveForBoard(board)
            if (!onlyPossibleInUnitSolver.solveForBoard(board)) {
                if (!solveUnits(board.getRows())) {
                    if (!solveUnits(board.getColumns())) {
                        if (!solveUnits(board.regions)) {
                            return false
                        }
                    }
                }
            }
            else {
                report = onlyPossibleInUnitSolver.report
            }
        } else {
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
        } > 1) {
            List<Cell> pairs = new ArrayList<Cell>(unit.unsolvedCells.findAll {cell ->
                cell.values.size() == 2
            })
            pairs.each {pair ->
                pairs.findAll {
                    !pair.equals(it)
                }.each {otherPair ->
                    if (pair.values.intersect(otherPair.values).size() == 2) {

                        unit.unsolvedCells.findAll {cell ->
                            !pairs.contains(cell)
                        }.each {cell ->
                            if (!solved) {
                                def tempValues = cell.values.clone()
                                cell.values.removeAll(pair.values.intersect(otherPair.values))
                                if (cell.values.size() == 1) {
                                    report = cell.report
                                    report << "${cell.row},${cell.column} ${tempValues} cannot be a ${pair.values[0]} or a ${pair.values[1]} " +
                                            "because they are both the only possible values in (${pair.row},${pair.column})" +
                                            " and (${otherPair.row},${otherPair.column})"
                                    report << "so it must be a ${cell.values[0]}"
                                    solved = true
                                }
                            }
                        }
                    }
                }
            }
        }

        return solved
    }

}
