package sudoku

import com.google.common.collect.Sets
import org.junit.Test
import static org.junit.Assert.assertEquals
import com.google.common.collect.Lists

class TestCell {

    Cell testee

    @Test
    void cellReport() {
        Unit unit = [solvedNumbers: {Sets.newHashSet(1, 2, 3)}, toString: {"test unit 1"}, getCellsSolvedFor: {Sets.newHashSet()}] as Unit
        testee = new Cell(1, 2, (1..9))
        testee.remove unit
        assertEquals("wrong number of values left in cell", 6, testee.values.size())
        assertEquals("wrong report from cell", "(a,2)[1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2 or 3 because they are in test unit 1",
                testee.report.reports[0].toString())
        unit = [solvedNumbers: {Sets.newHashSet(8)}, toString: {"test unit 2"}, getCellsSolvedFor: {Sets.newHashSet()}] as Unit
        testee.remove unit
        assertEquals("wrong report[0] from cell", "(a,2)[1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2 or 3 because they are in test unit 1",
                testee.report.reports[0].toString())
        assertEquals("wrong report[1] from cell", "(a,2)[4, 5, 6, 7, 8, 9] cannot be a 8 because it is in test unit 2",
                testee.report.reports[1].toString())
        unit = [solvedNumbers: {Sets.newHashSet(8, 9)}, toString: {"test unit 3"}, getCellsSolvedFor: {Sets.newHashSet()}] as Unit
        testee.remove unit
        assertEquals("wrong report[0] from cell", "(a,2)[1, 2, 3, 4, 5, 6, 7, 8, 9] cannot be a 1, 2 or 3 because they are in test unit 1",
                testee.report.reports[0].toString())
        assertEquals("wrong report[1] from cell", "(a,2)[4, 5, 6, 7, 8, 9] cannot be a 8 because it is in test unit 2",
                testee.report.reports[1].toString())
        assertEquals("wrong report[2] from cell", "(a,2)[4, 5, 6, 7, 9] cannot be a 9 because it is in test unit 3",
                testee.report.reports[2].toString())
        unit = [solvedNumbers: {Sets.newHashSet(8, 9)}, toString: {"test unit 3"}, getCellsSolvedFor: {Sets.newHashSet()}] as Unit
        testee.remove unit
        assertEquals("wrong number of reports for cell", 3, testee.report.reports.size())
    }
}
