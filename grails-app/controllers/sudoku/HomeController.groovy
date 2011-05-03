package sudoku

class HomeController {

    def solve = {
        NakedPairsSolver solver = new NakedPairsSolver()
        def board = new Board(session.board.asString())
        if (solver.solveForBoard(board)) {
            def map = [report: solver.report]
            session.putValue("board", board)
            //TODO: redirect to index, render report from last solved cell instead of solver (i.e. get it all from the board)
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
