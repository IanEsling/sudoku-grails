import com.google.common.collect.Lists

class HiddenPairsSolver {

    List<String> report
    BasicSolver basicSolver = new BasicSolver()
    OnlyPossibleInUnitSolver onlyPossibleInUnitSolver = new OnlyPossibleInUnitSolver()
    NakedPairsSolver nakedPairsSolver = new NakedPairsSolver()

    boolean solveForBoard(Board board) {
        if (!nakedPairsSolver.solveForBoard(board)) {
            if (!solveUnits(board.getRows())) {
                if (!solveUnits(board.getColumns())) {
                    if (!solveUnits(board.regions)) {
                        return false
                    }
                }
            }
        }
        else {
            report = nakedPairsSolver.report
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
        //can only have hidden twins if more than 4 numbers to play with
        if (unit.unsolvedCells.size() > 4) {
            unit.possibleNumbers().each {possible ->
                unit.possibleNumbers().each {otherPossible ->
                    if (possible != otherPossible) {
                        //see how many cells contain these 2 numbers
                        Set<Cell> cells = (unit.unsolvedCells.findAll {cell ->
                            cell.values.intersect([possible, otherPossible]).size() == 2
                        })
                        if (cells.size() == 2) {//possible pair, make sure neither number is possible elsewhere
                            if (unit.unsolvedCells.findAll {unsolvedCell ->
                                if (!cells.contains(unsolvedCell)) {
                                    unsolvedCell.values.contains(possible) ||
                                            unsolvedCell.values.contains(otherPossible)
                                }
                            }.size() == 0) {
                                cells.each {cell ->
                                    cell.values.retainAll([possible, otherPossible])
                                }
                            }
                        }
                    }
                }
            }
        }

//        if (unit.unsolvedCells.inject(0) {count, cell ->
//            count + (cell.values.size() == 2 ? 1 : 0)
//        } > 1) {
//            List<Cell> pairs = new ArrayList<Cell>(unit.unsolvedCells.findAll {cell ->
//                cell.values.size() == 2
//            })
//            pairs.each {pair ->
//                pairs.findAll {
//                    !pair.equals(it)
//                }.each {otherPair ->
//                    if (pair.values.intersect(otherPair.values).size() == 2) {
//
//                        unit.unsolvedCells.findAll {cell ->
//                            !pairs.contains(cell)
//                        }.each {cell ->
//                            if (!solved) {
//                                def tempValues = cell.values.clone()
//                                cell.values.removeAll(pair.values.intersect(otherPair.values))
//                                if (cell.values.size() == 1) {
//                                    report = cell.report
//                                    report << "${cell.row},${cell.column} ${tempValues} cannot be a ${pair.values[0]} or a ${pair.values[1]} " +
//                                            "because they are both the only possible values in (${pair.row},${pair.column})" +
//                                            " and (${otherPair.row},${otherPair.column})"
//                                    report << "so it must be a ${cell.values[0]}"
//                                    solved = true
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

        return solved
    }

}
