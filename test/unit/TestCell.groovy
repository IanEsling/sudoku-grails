import com.google.common.collect.Sets
import org.junit.Test
import static org.junit.Assert.assertEquals

class TestCell {

    Cell testee

    @Test
    void cellReport() {
        Unit unit = [solvedNumbers: {Sets.newHashSet(1, 2, 3)}, toString: {"test unit 1"}] as Unit
        testee = new Cell(1, 2, (1..9))
        testee.remove unit
        assertEquals("wrong number of values left in cell", 6, testee.values.size())
        assertEquals("wrong report from cell", "1,2 [1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2 or 3 because they are in test unit 1",
                testee.report[0].toString())
        unit = [solvedNumbers: {Sets.newHashSet(8)}, toString: {"test unit 2"}] as Unit
        testee.remove unit
        assertEquals("wrong report[0] from cell", "1,2 [1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2 or 3 because they are in test unit 1",
                testee.report[0].toString())
        assertEquals("wrong report[1] from cell", "1,2 [4, 5, 6, 7, 8, 9] cannot be a 8 because it is in test unit 2",
                testee.report[1].toString())
        unit = [solvedNumbers: {Sets.newHashSet(8, 9)}, toString: {"test unit 3"}] as Unit
        testee.remove unit
        assertEquals("wrong report[0] from cell", "1,2 [1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2 or 3 because they are in test unit 1",
                testee.report[0].toString())
        assertEquals("wrong report[1] from cell", "1,2 [4, 5, 6, 7, 8, 9] cannot be a 8 because it is in test unit 2",
                testee.report[1].toString())
        assertEquals("wrong report[2] from cell", "1,2 [4, 5, 6, 7, 9] cannot be a 9 because it is in test unit 3",
                testee.report[2].toString())
        unit = [solvedNumbers: {Sets.newHashSet(8, 9)}, toString: {"test unit 3"}] as Unit
        testee.remove unit
        assertEquals("wrong number of reports for cell", 3, testee.report.size())
    }
}
