package sudoku

import sudoku.Unit

class UnitTestUtils {

    static Integer solvedUnits(Set<Unit> units) {
        int solved = 0
        units.each {unit->
            if (unit.solvedNumbers().size() == 9) solved++
        }
        return solved
    }
}
