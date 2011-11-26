package sudoku

import javax.servlet.http.HttpSession

class HomeController {

    def about = {
        render(view: "about")
    }

    def clear = {
        clearSession(session)
        render(view: "index")
    }

    private def clearSession(HttpSession session) {
        session.board = null
        session.originalBoard = null
    }

    def solve = {
        if (params.board != null) {
            NakedPairsSolver solver = new NakedPairsSolver()
            def board = new Board(params.board)
            def map
            if (solver.solveForBoard(board)) {
                map = [report: solver.report]
            } else {
                map = [failed: true, report: """Sorry I can't solve this one, this is as far as I've got with it.<br/><br/>
This puzzle's been logged and shall be investigated, please feel free to get in touch if you can see a way forward that I'm missing.<br/><br/>
I'm always looking for ways to improve so this will be a big help, thankyou."""]
            }
            session.putValue("board", board)
            render(view: "index", model: map)
        }
        else {
            redirect(uri: "/home/clear")
        }
    }

//    def newBoard = {
//        clearSession(session)
//        if (params.board != null) {
//            Board board = new Board(params.board)
//            session.putValue("board", board)
//            session.putValue("originalCells", board.originalCells())
//            session.putValue("originalBoard", board.asString())
//        }
//        render(view: "index")
//    }

    def index = {
        def exception = request.exception
        if (exception != null) {
            render(view: "index", model: [exception: exception.message])
        }
    }
}
