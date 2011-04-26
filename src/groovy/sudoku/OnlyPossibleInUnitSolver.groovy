package sudoku

import com.google.common.collect.Lists

class OnlyPossibleInUnitSolver {

    BasicSolver basicSolver = new BasicSolver()
    List<String> report

    boolean solveForBoard(Board board) {
        if (!basicSolver.solveForBoard(board)) {
            if (!solveUnits(board.rows)) {
                if (!solveUnits(board.columns)) {
                    return solveUnits(board.regions)
                }
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

    private boolean solveUnit(Unit unit) {
        report = Lists.newArrayList()
        boolean solved = false
        unit.possibleNumbers().each {number ->
            if (!solved) {

                Integer total = 0
                unit.unsolvedCells.each {cell ->
                    if (cell.values.contains(number)) {
                        total++;
                    }
                }
                if (total == 1) {
                    Cell cell = unit.unsolvedCells.find {cell ->
                        cell.values.contains(number)
                    }
                    cell.values.retainAll([number])
                    solved = true
                    report << "${cell.row},${cell.column} is the only possible in its ${unit.type} to be $number"
                }
            }
        }
        return solved
    }
}