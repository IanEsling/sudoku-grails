import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class RunReportingSolver {

    OnlyPossibleInUnitSolver testee = new OnlyPossibleInUnitSolver()

    @Test
    void onlyPossibleInUnitReport() {
        Board board = new Board(".53...79...97534..1.......2.9..8..1....9.7....8..3..7.5.......3..76412...61...94.")
        ReportingSolver reportingSolver = new ReportingSolver()
        reportingSolver.solve board
    }
}
