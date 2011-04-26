<div id="main">
    <div id="board">
        <g:if test="${session.board != null}">
            <table>
                <g:each in="${session.board.rows}" var="row" status="rowIndex">
                    <tr <g:if test="${rowIndex == 2 || rowIndex == 5}">style="border-bottom-color:black; border-bottom-width: 2px;"</g:if>>
                        <g:each in="${row.cells}" var="cell" status="cellIndex">
                            <td <g:if test="${cellIndex == 2 || cellIndex == 5}">style="border-right-color:black;border-right-width:2px"</g:if>>
                                <g:if test="${cell.values.size() == 1}">${cell.values[0]}</g:if></td>
                        </g:each>
                    </tr>
                </g:each>
            </table>
            <g:formRemote name="solve" url="[controller:'home',action:'solve']"
                    update="[success:'main']">
                <g:submitButton name="solveMe" value="Click to solve a square"/>
            </g:formRemote>
        </g:if>
    </div>

    <g:render template="report"/>
</div>