class OnlyOneChoiceSolver {

    void solveUnits(Set<Unit> units) {
        units.each {unit->
            solveUnit unit
        }
    }

    private void solveUnit(Unit unit) {
        List<Integer> definiteNumbers = new ArrayList<Integer>()

        unit.cells.each {cell ->
            if (cell.values.size() == 1) {
                definiteNumbers.add(cell.values[0])
            }
        }
        unit.cells.each {cell->
            if (cell.values.size() > 1) {
                cell.values.removeAll(definiteNumbers)
            }
        }
    }
}
