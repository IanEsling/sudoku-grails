package sudoku

import org.junit.Test
import static org.junit.Assert.*

class TestUniqueToUnitSolver {

    NakedPairsSolver testee = new NakedPairsSolver()

//   8  .  1  |  .  .  7  |  .  9  .
//   5  9  .  |  .  8  .  |  .  6  1
//   .  3  .  |  .  .  .  |  .  8  .
//-  -------- + --------- + ---------
//   .  1  .  |  2  7  5  |  8  4  3
//   3  5  8  |  .  6  .  |  1  2  7
//   2  7  4  |  1  3  8  |  9  5  6
//-  -------- + --------- + ---------
//   .  8  .  |  .  .  .  |  .  3  .
//   1  .  .  |  8  2  .  |  .  7  9
//   .  2  .  |  7  .  .  |  4  1  8

    @Test
    void otherPairInUnit() {
        UniqueToUnitSolver testee = new UniqueToUnitSolver()
        Board board = new Board("8.1..7.9.59..8..61.3.....8..1.275843358.6.127274138956.8.....3.1..82..79.2.7..418")

        NakedPairsSolver solver = new NakedPairsSolver()
        if (solver.solveForBoard(board)){
            println solver.report
            fail("naked pairs solver solved something: \n${board.toStringForFailedBoard()}")
        }
        testee.solveForBoard(board)
        assertFalse("3 not removed from 2,6: \n${board.toStringForFailedBoard()}", board.getCell(2,6).values.contains(3))
    }
}