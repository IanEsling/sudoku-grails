class OnlyOneChoiceSolver {

    void solveUnits(Set<Unit> units) {
        units.any {unit->
            solveUnit unit
        }
    }

    private boolean solveUnit(Unit unit) {
        boolean solved = false
        Set<Integer> definiteNumbers = unit.solvedNumbers()
        unit.cells.each {cell->
            if (!solved && cell.values.size() > 1) {
                cell.values.removeAll(definiteNumbers)
                if (cell.values.size() == 1) {
                    solved = true
                }
            }
        }
        return solved
    }
}
