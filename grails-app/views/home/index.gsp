<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Teach Me Sudoku</title>
    <style type="text/css">
    body {
        width: 100%;
    }

    table {
        width: 100%;
        height: 70%;
        border-collapse: collapse;
        margin-left: 20px;
        margin-right: -20px;
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

    #board {
        float: left;
        width: 60%; /*margin-left: 10px;*/
    /*margin-right: 10px;*/
    }

    #report {
        float: right;
        width: 30%;
        /*margin-left: 10px;*/
        margin-right: 20px;
    }

    .newBoard {
        width: 50%;
    }


    </style>
</head>
<body>
<g:form name="newBoard" url="[controller:'home',action:'newBoard']">
    Enter New Board:
    <g:textField class="newBoard" name="newBoard" size="81">type new board here...</g:textField>
    <g:submitButton name="Submit" value="Submit"/>
</g:form>

<div id="board">
    <g:if test="${session.board != null}">
        <table>
            <g:each in="${session.board.rows}" var="row" status="rowIndex">
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
        <g:form name="solveASquare" url="[controller: 'home', action:'solve']">
            <g:submitButton name="solveMe" value="Click to solve a square"/>
        </g:form>
    </g:if>
</div>
<div id="report">
    <g:each in="${report}" var="line">
        ${line}<br/><br/>
    </g:each>
</div>
</body>
</html>