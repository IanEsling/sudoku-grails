package sudoku

class HomeController {

    def solve = {
        NakedPairsSolver solver = new NakedPairsSolver()
        if (solver.solveForBoard(session.board)) {
            def map = [report: solver.report]
            render (view:"index", model: map)
        } else {
            render (view:"failed")
        }
    }

    def newBoard = {
        session.putValue("board", new Board(params.newBoard))
        render(view:"index")
    }

    def index = { }
}
