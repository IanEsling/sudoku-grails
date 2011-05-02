package sudoku

import org.junit.Test
import static org.junit.Assert.*

class TestNakedPairsSolver {

    NakedPairsSolver testee = new NakedPairsSolver()



//    3  .  1  |  2  8  .  |  .  .  7
//    .  .  .  |  1  .  7  |  .  .  .
//    7  .  6  |  9  3  4  |  5  1  .
//-   -------- + --------- + ---------
//    .  7  .  |  4  .  9  |  1  8  .
//    9  .  8  |  .  2  1  |  7  .  4
//    .  1  .  |  8  7  3  |  .  5  .
//-   -------- + --------- + ---------
//    .  .  9  |  .  4  .  |  3  7  1
//    .  .  .  |  7  .  2  |  8  .  5
//    .  .  7  |  3  .  8  |  .  .  6

    @Test
    void otherPairInUnit() {
        Board board = new Board("3.128...7...1.7...7.693451..7.4.918.9.8.217.4.1.873.5...9.4.371...7.28.5..73.8..6")
        OnlyPossibleInUnitSolver solver = new OnlyPossibleInUnitSolver()
        if (solver.solveForBoard(board)) {
            fail("solver solved something: ${solver.report}\n${board.toString()}")
        }
        testee.solveForBoard(board)
        if (!board.getCell(9, 1).values.every {possible ->
            [1, 4, 5].contains possible
        })
            fail("(9,1) values not correct after naked pairs (should be 1,4,5): \n${board.toStringForFailedBoard()}")
    }

//    .   3   .   |   .   .   .   |   .   .   .
//    4   5 [2,7] |   1   9   8   |   .   6   .
//    . [2,7] .   |   .   3   .   |   4   8   1
//    ------------|---------------|------------
//    5   1   .   |   .   .   .   |   .   .   .
//    .   9   .   |   .   .   .   |   .   .   .
//    3   8   6   |   .   .   .   |   .   .   .
//    ------------|---------------|------------
//    .   .   .   |   .   .   .   |   .   .   .
//    .   .   .   |   .   .   .   |   .   .   .
//    .   6   .   |   .   .   .   |   .   .   .

    @Test
    void onlyPossibleInRegion() {
        Board board = new Board(".3.......45.198.6.....3.48151........9.......386.........................6.......")
        OnlyPossibleInUnitSolver solver = new OnlyPossibleInUnitSolver()
        if (solver.solveForBoard(board)) {
            fail("solver solved something: ${solver.report}\n${board.toString()}")
        }
        assertTrue("naked pairs didn't solve anything: \n${board.toString()}", testee.solveForBoard(board))
        assertEquals("naked pairs didn't solve anything in row 3", 5, board.getRow(3).solvedNumbers().size())
        testee.report.each {
            println it
        }
        println board.toString()
    }

//    1   .   3   |   .   .   .   |   .   .   .
//    4   5   9   |   .   .   .   |   .   .   .
//    6   .   8   |   .   .   .   |   .   .   .
//    ------------|---------------|------------
//    5   .   4   |   .   .   .   |   .   .   .
//    .   9   .   |   .   .   .   |   .   .   .
//    3   .   1   |   .   .   6   |   .   .   .
//    ------------|---------------|------------
//    .   .   .   |   .   .   .   |   .   .   .
//    .   .   .   |   .   .   .   |   .   .   .
//    .   .   .   |   .   .   .   |   .   .   .

    @Test
    void onlyPossibleInColumn() {
        Board board = new Board("1.3......459......6.8......5.4.......9.......3.1..6..............................")
        OnlyPossibleInUnitSolver solver = new OnlyPossibleInUnitSolver()
        if (solver.solveForBoard(board)) {
            fail("solver solved something: ${solver.report}\n${board.toString()}")
        }
        assertTrue("naked pairs didn't solve anything", testee.solveForBoard(board))
        assertEquals("naked pairs didn't solve anything in row 2", 3, board.getColumn(2).solvedNumbers().size())
        testee.report.each {
            println it
        }
        println board.toString()
    }
//    1   2   3   |   5   .   7   |   .   .   .
//    .   5   .   |   .   2   .   |   .   .   .
//    6   7   8   |   1   .   3   |   .   .   .
//    ------------|---------------|------------
//    .   .   .   |   .   .   6   |   .   .   .
//    .   .   .   |   .   .   .   |   .   .   .
//    .   .   .   |   .   .   .   |   .   .   .
//    ------------|---------------|------------
//    .   .   .   |   .   .   .   |   .   .   .
//    .   .   .   |   .   .   .   |   .   .   .
//    .   .   .   |   .   .   .   |   .   .   .

    @Test
    void onlyPossibleInRow() {
        Board board = new Board("1235.7....5..2....6781.3........6................................................")
        OnlyPossibleInUnitSolver solver = new OnlyPossibleInUnitSolver()
        if (solver.solveForBoard(board)) {
            fail("solver solved something: ${solver.report}\n${board.toString()}")
        }
        assertTrue("naked pairs didn't solve anything", testee.solveForBoard(board))
        assertEquals("naked pairs didn't solve anything in row 2", 3, board.getRow(2).solvedNumbers().size())
        testee.report.each {
            println it
        }
        println board.toString()
    }
}