class OnlyOneChoiceSolver {

    GString report

    void solveUnits(Set<Unit> units) {
        units.any {unit ->
            solveUnit unit
        }
    }

    private boolean solveUnit(Unit unit) {
        report = null
        boolean solved = false
        Set<Integer> definiteNumbers = unit.solvedNumbers()
        unit.cells.each {cell ->
            if (!solved && cell.values.size() > 1) {
                cell.values.removeAll(definiteNumbers)
                if (cell.values.size() == 1) {
                    solved = true
                    report = "${cell.row},${cell.column} must be a ${cell.values[0]} because it's the only choice for" +
                            " this ${unit.type}"
                }
            }
        }
        return solved
    }
}