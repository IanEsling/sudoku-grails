package sudoku

class HomeController {

    def solve = {
        NakedPairsSolver solver = new NakedPairsSolver()
        def board = new Board(session.board.asString())
        if (solver.solveForBoard(board)) {
            def map = [report: solver.report]
            session.putValue("board", board)
            render (template:"board", model: map)
        } else {
            render (view:"failed")
        }
    }

    def newBoard = {
        Board board = new Board(params.newBoard)
        session.putValue("board", board)
        session.putValue("originalCells", board.originalCells())
        session.putValue("originalBoard", board.asString())
        render(view:"index")
    }

    def index = { }
}
