package sudoku

import com.google.common.collect.Lists

class UniqueToUnitSolver {

    List<String> report

    boolean solveForBoard(Board board) {
        if (!solveUnits(board.getRows(), board.regions)) {
            if (!solveUnits(board.getColumns(), board.regions)) {
                if (!solveUnits(board.regions, board.rows)) {
                    if (!solveUnits(board.regions, board.rows)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    boolean solveUnits(Set<Unit> units, Set<Unit> otherUnits) {
        units.any {unit ->
            solveUnit unit, otherUnits
        }
    }

    boolean solveUnit(Unit unit, Set<Unit> otherUnits) {
        report = Lists.newArrayList()
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
                    if (!unit.unsolvedCells.findAll {
                        it.values.contains(number)
                    }.contains(cell)) {
                        cell.report.add("${cell.row},${cell.column} " + cell.values + " cannot be a $number because it can only exist in $unit")
                        cell.remove number
                        if (cell.values.size() == 1) {
                            solved = true
                        }
                    }
                }
            }
        }
        return solved
    }

}
