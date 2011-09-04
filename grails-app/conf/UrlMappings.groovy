import sudoku.BoardCreationException
import sudoku.InvalidBoardException

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(controller: "home", action: "about")
        "500"(controller: "home", action: "index", exception: BoardCreationException)
        "500"(controller: "home", action: "index", exception: InvalidBoardException)
		"500"(view:'/error')
	}
}

//static mappings = {
//   "403"(view: "/errors/forbidden")
//   "404"(view: "/errors/notFound")
//   "500"(controller: "errors", action: "illegalArgument",
//         exception: IllegalArgumentException)
//   "500"(controller: "errors", action: "nullPointer",
//         exception: NullPointerException)
//   "500"(controller: "errors", action: "customException",
//         exception: MyException)
//   "500"(view: "/errors/serverError")
//}