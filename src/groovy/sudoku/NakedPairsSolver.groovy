package sudoku

import com.google.common.collect.Lists

class NakedPairsSolver {

    Report report
    BasicSolver basicSolver = new BasicSolver()
    OnlyPossibleInUnitSolver onlyPossibleInUnitSolver = new OnlyPossibleInUnitSolver()
    HiddenPairsSolver hiddenPairsSolver = new HiddenPairsSolver()
    UniqueToUnitSolver uniqueToUnitSolver = new UniqueToUnitSolver()

    boolean solveForBoard(Board board) {
        def boardState = board.toStringForFailedBoard()
        if (!basicSolver.solveForBoard(board)) {
            hiddenPairsSolver.solveForBoard(board)
            if (!onlyPossibleInUnitSolver.solveForBoard(board)) {
                if (!solveUnits(board.rows)) {
                    if (!solveUnits(board.columns)) {
                        if (!solveUnits(board.regions)) {
                            if (!uniqueToUnitSolver.solveForBoard(board)) {
                                //if not solved a square but we've narrowed some possibles down then try again
                                if (!boardState.equals(board.toStringForFailedBoard())) {
                                    return solveForBoard(board)
                                } else {
                                    return false
                                }
                            } else {
                                report = uniqueToUnitSolver.report
                            }
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

    boolean solveUnits(Set<? extends Unit> units) {
        units.any {unit ->
            solveUnit unit
        }
    }

    boolean solveUnit(Unit unit) {
        report = new Report()
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
                            !pairs.contains(cell) &&
                                    cell.values.intersect(pair.values.intersect(otherPair.values)).size() > 0
                        }.each {cell ->
                            if (!solved) {
                                def tempValues = cell.values.clone()
                                cell.values.removeAll(pair.values.intersect(otherPair.values))
                                report = cell.report
                                report.add("${cell} ${tempValues} cannot be a ${pair.values[0]} or a ${pair.values[1]} " +
                                        "because they are both the only possible values in ${pair} " +
                                        "and ${otherPair}", Lists.newArrayList(pair, otherPair))
                                if (cell.values.size() == 1) {
                                    report["so it must be a ${cell.values[0]}"] = Lists.newArrayList(cell)
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
