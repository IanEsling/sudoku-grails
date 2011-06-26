package sudoku

class HomeController {

    def clear = {
        session.board = null
        session.originalBoard = null
        render(view: "index")
    }

    def solve = {
        NakedPairsSolver solver = new NakedPairsSolver()
        def board = new Board(session.board.asString())
        def map
        if (solver.solveForBoard(board)) {
            map = [report: solver.report]
        } else {
            map = [failed: true, report: """Sorry I can't solve this one, this is as far as I've got with it.<br/><br/>
This puzzle's been logged and shall be investigated, I'm always looking for ways to improve so this will be a big help, thankyou."""]
        }
        session.putValue("board", board)
        render(template: "board", model: map)
    }

    def newBoard = {
        Board board = new Board(params.newBoardString)
        session.putValue("board", board)
        session.putValue("originalCells", board.originalCells())
        session.putValue("originalBoard", board.asString())
        render(view: "index")
    }

    def index = { }
}
