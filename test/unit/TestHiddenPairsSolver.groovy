import org.junit.Test
import static org.junit.Assert.assertTrue
import sudoku.Board
import sudoku.HiddenPairsSolver
import sudoku.NakedPairsSolver

class TestHiddenPairsSolver {

    HiddenPairsSolver testee = new HiddenPairsSolver()

//   4  .  .  |  .  7  .  |  .  6  .
//   3  5  7  |  4  .  6  |  .  .  .
//   2  6  8  |  .  .  .  |  4  .  .
//-  -------- + --------- + ---------
//   .  .  3  |  1  .  .  |  .  .  5
//   .  4  .  |  .  9  .  |  .  3  .
//   6  .  .  |  .  .  3  |  1  .  .
//-  -------- + --------- + ---------
//   .  .  4  |  .  .  .  |  7  .  8
//   .  .  .  |  8  .  4  |  .  9  .
//   8  3  .  |  .  5  .  |  .  .  .
    @Test
    void findHiddenPair() {
        Board board = new Board("4...7..6.3574.6...268...4....31....5.4..9..3.6....31....4...7.8...8.4.9.83..5....")
        new NakedPairsSolver().solveForBoard(board)
        assertTrue("cell 7,4 values not correct for naked pairs", [2,3,6,9].containsAll(board.getCell(7,4).values))
        assertTrue("cell 7,5 values not correct for naked pairs", [1,2,3,6].containsAll(board.getCell(7,5).values))
        testee.solveForBoard(board)
        assertTrue("cell 7,4 values not correct for hidden pairs: ${board.getCell(7,4).values}", [3,6].containsAll(board.getCell(7,4).values))
        assertTrue("cell 7,5 values not correct for hidden pairs: ${board.getCell(7,5).values}", [3,6].containsAll(board.getCell(7,5).values))
    }
}
