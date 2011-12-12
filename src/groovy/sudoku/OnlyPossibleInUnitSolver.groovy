package sudoku

class OnlyPossibleInUnitSolver {

    BasicSolver basicSolver = new BasicSolver()
    Report report

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

    boolean solveUnits(Set<? extends Unit> units) {
        units.any {unit ->
            solveUnit unit
        }
    }

    private boolean solveUnit(Unit unit) {
        report = new Report()
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
                    cell.lastOneSolved = true
                    solved = true
                    report.add("${cell} is the only possible in its ${unit.type} to be $number", unit.unsolvedCells)
                    cell.report.add(report)
                }
            }
        }
        return solved
    }
}