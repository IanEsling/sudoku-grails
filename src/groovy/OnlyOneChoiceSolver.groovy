class OnlyOneChoiceSolver {

    String solveBoard(Board board) {
        solveRows(board.grid.rowMap())
        solveRows(board.grid.columnMap())
        solveRegions(board)
        return board.asString()
    }

    boolean solveRegions(Board board) {
        board.regions.asMap().each {entry ->
            entry.value.each {columnList ->
            List<Integer> squares = []
                entry.key.each {row ->
                    columnList.each {column ->
                        if (board.grid.get(row, column).size() == 1) {
                            squares.add(board.grid.get(row, column)[0])
                        }
                    }
                    columnList.each {column ->
                        if (board.grid.get(row, column).size() > 1) {
                            board.grid.get(row, column).removeAll(squares)
                        }
                    }
                }
            }
        }
        return true
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
