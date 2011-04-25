package sudoku

class HomeController {

    def newBoard = {
        def map = [board: new Board(params.board)]
        render(view:"index", model: map)
    }

    def index = { }
}
