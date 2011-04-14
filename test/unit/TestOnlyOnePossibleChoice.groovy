import org.junit.Test
import static org.junit.Assert.assertEquals
import com.google.common.collect.Lists

class TestOnlyOnePossibleChoice {

    Board board

    @Test
    void oneChoiceInRow(){
        board = new Board("..3.4.5...876543211..9...5.......................................................")
        assertEquals("next number 9 not solved correctly:\n${board.toString()}",
        "..3.4.5..9876543211..9...5.......................................................",
        solveNextSquare(board))
        board = new Board("............................84756293.............................................")
        assertEquals("next number 1 not solved correctly:\n${board.toString()}",
        "...........................184756293.............................................",
        solveNextSquare(board))
    }

    String solveNextSquare(Board board) {
        board.grid.rowMap().each {row->
            List<Integer> definiteNumbers = new ArrayList<Integer>()
            row.value.each {column->
                if (column.value.size()==1) {
                    definiteNumbers.add(column.value[0])
                }
            }
            row.value.each {column->
                if (column.value.size() > 1) {
                    column.value.removeAll(definiteNumbers)
                }
            }
        }
        return board.asString()
    }
}
