package sudoku

class HiddenPairsSolver {

    boolean solveForBoard(Board board) {
        if (!solveUnits(board.getRows())) {
            if (!solveUnits(board.getColumns())) {
                if (!solveUnits(board.regions)) {
                    return false
                }
            }
        }
        return false
    }

    boolean solveUnits(Set<? extends Unit> units) {
        units.any {unit ->
            solveUnit unit
        }
    }

    boolean solveUnit(Unit unit) {
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
                            //and not already a pair
                            if (cells.any {
                                it.values.size() > 2
                            }) {
                                if (unit.unsolvedCells.findAll {unsolvedCell ->
                                    if (!cells.contains(unsolvedCell)) {
                                        unsolvedCell.values.contains(possible) ||
                                                unsolvedCell.values.contains(otherPossible)
                                    }
                                }.size() == 0) {
                                    cells.each {cell ->
                                        cell.report << "${cell.row},${cell.column} hidden pair of either " +
                                                "${possible} or ${otherPossible} with " +
                                                cells.find {
                                                    it != cell
                                                }.toString() + " in " + unit.toString()
                                        cell.values.retainAll([possible, otherPossible])
                                    }
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
