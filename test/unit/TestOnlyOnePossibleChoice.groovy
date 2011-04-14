import org.junit.Test
import static org.junit.Assert.assertEquals

class TestOnlyOnePossibleChoice {

    Board board
    OnlyOneChoiceSolver solver = new OnlyOneChoiceSolver()

    @Test
    void oneChoiceInRow() {
        board = new Board("..3.1.4...876543211..9...5.......................................................")
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "..3.1.4..9876543211..9...5.......................................................",
                solver.solveBoard(board))
        board = new Board("............................84756293.............................................")
        assertEquals("next number 1 not solved correctly:\n${board.toString()}",
                "...........................184756293.............................................",
                solver.solveBoard(board))
    }

    @Test
    void oneChoiceInColumn() {
        board = new Board("1........2........3........4........5........6........7........8.................")
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "1........2........3........4........5........6........7........8........9........",
                solver.solveBoard(board))
        board = new Board("........1........2........3........4........5........6........7........8.........")
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "........1........2........3........4........5........6........7........8........9",
                solver.solveBoard(board))
    }

    @Test
    void oneChoiceInRegion() {
        board = new Board("123......456......78.......2........5.................9..........................")
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "123......456......789......2........5.................9..........................",
                solver.solveBoard(board))
        board = new Board("12345678945678912378912345.2........5.................9..........................")
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
                "1234567894567891237891234562........5.................9..........................",
                solver.solveBoard(board))
    }
}
