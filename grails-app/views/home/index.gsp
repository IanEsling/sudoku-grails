<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Teach Me Sudoku</title>
    <style type="text/css">
    table {
        width: 70%;
        height: 70%;
        border-collapse: collapse;
    }

    tr {
        border: 0 solid gray;
        border-bottom-width: 1px;
    }

    td {
        width: 10%;
        height: 10%;
        border: 1px solid gray;
        border-bottom-width: 0;
        text-align: center;
    }

    </style>
</head>
<body>
<g:form name="newBoard" url="[controller:'home',action:'newBoard']">
    Enter New Board:
    <g:textField name="board" size="81">type new board here...</g:textField>
    <g:submitButton name="Submit" value="Submit"/>
</g:form>

<table>
    <g:each in="${board.rows}" var="row" status="rowIndex">
        <tr
            <g:if test="${rowIndex == 2 || rowIndex == 5}">style="border-bottom-color:black; border-bottom-width: 2px;"</g:if>>
            <g:each in="${row.cells}" var="cell" status="cellIndex">
                <td
                    <g:if test="${cellIndex == 2 || cellIndex == 5}">style="border-right-color:black;border-right-width:2px"</g:if>>
                    <g:if test="${cell.values.size() == 1}">${cell.values[0]}</g:if></td>
            </g:each>
        </tr>
    </g:each>
</table>
</body>
</html>