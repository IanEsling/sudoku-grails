class OnlyOneChoiceSolver {

    String solveBoard(Board board) {
        solveRows(board.grid.rowMap())
        solveRows(board.grid.columnMap())
        return board.asString()
    }

    private boolean solveRows(Map<Integer, Map<Integer, List<Integer>>> row) {
        row.each {rowMap ->
            List<Integer> definiteNumbers = new ArrayList<Integer>()
            rowMap.value.each {column ->
                if (column.value.size() == 1) {
                    definiteNumbers.add(column.value[0])
                }
            }
            rowMap.value.each {column ->
                if (column.value.size() > 1) {
                    column.value.removeAll(definiteNumbers)
                }
            }
        }
        return true
    }
}
