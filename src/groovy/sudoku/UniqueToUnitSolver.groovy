package sudoku

import com.google.common.collect.Maps

class UniqueToUnitSolver {

    Report report

    boolean solveForBoard(Board board) {
        if (!solveUnits(board.rows, board.regions)) {
            if (!solveUnits(board.columns, board.regions)) {
                if (!solveUnits(board.regions, board.rows)) {
                    if (!solveUnits(board.regions, board.columns)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    boolean solveUnits(Set<? extends Unit> units, Set<? extends Unit> otherUnits) {
        units.any {unit ->
            solveUnit unit, otherUnits
        }
    }

    boolean solveUnit(Unit unit, Set<? extends Unit> otherUnits) {
        report = new Report()
        boolean solved = false
        unit.possibleNumbers().each {number ->
            def unsolvedCells = unit.unsolvedCells.findAll {cell ->
                cell.values.contains(number)
            }
            if (!solved && otherUnits.any {otherUnit ->
                otherUnit.cells.containsAll(unsolvedCells)
            }) {
                otherUnits.find {otherUnit ->
                    otherUnit.cells.containsAll(unsolvedCells)
                }.unsolvedCells.each {cell ->
                    if (!solved && !unsolvedCells.contains(cell) && cell.values.contains(number)) {
                        cell.report.add("${cell} " + cell.values +
                                " cannot be a $number because it can only exist in $unit in " + otherUnits.find {otherUnit ->
                            otherUnit.cells.containsAll(unsolvedCells)
                        }, unit.unsolvedCells.intersect(otherUnits.find {otherUnit ->
                            otherUnit.cells.containsAll(unsolvedCells)
                        }.unsolvedCells))
                        cell.remove number
                        if (cell.values.size() == 1) {
                            report = cell.report
                            solved = true
                        }
                    }
                }
            }
        }
        return solved
    }
}
