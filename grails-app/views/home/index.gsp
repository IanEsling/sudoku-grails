<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Teach Me Sudoku</title>
    <link rel="stylesheet" href="${resource(dir:'css',file:'sudoku.css')}" />
</head>
<body>
<g:render template="newBoardTextField"/>

<g:render template="board"/>

</body>
</html>