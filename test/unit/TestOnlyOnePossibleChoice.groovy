import org.junit.Test
import static org.junit.Assert.assertEquals

class TestOnlyOnePossibleChoice {

    Board board

    @Test
    void oneChoiceInRow(){
        board = new Board("..3.4.5...876543211..9...5.......................................................")
        assertEquals("next number 9 not solved correctly:${board.toString()}",
        "..3.4.5..9876543211..9...5.......................................................",
        board.solveNextSquare())
        board = new Board("............................84756293.............................................")
        assertEquals("next number 1 not solved correctly:\n${board.toString()}",
        "...........................84756293.............................................",
        board.solveNextSquare())
    }
}
