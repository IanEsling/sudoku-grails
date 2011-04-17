import org.junit.Test

class RunBasicSolver {

    @Test
    void runBasicSolver() {
        Board board = new Board(".53...79...97534..1.......2.9..8..1....9.7....8..3..7.5.......3..76412...61...94.")
        BasicSolver solver = new BasicSolver()
        println solver.report
        println board.toString()
        while (solver.solveForBoard(board)) {
            println solver.report
            println board.toString()
        }
    }
}
