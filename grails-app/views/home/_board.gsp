<div id="main">
    <div id="board">
        <g:if test="${session.board != null}">
            <table>
                <g:each in="${session.board.rows}" var="row" status="rowIndex">
                    <tr <g:if test="${rowIndex == 2 || rowIndex == 5}">class="row_divider"</g:if>>
                        <g:each in="${row.cells}" var="cell" status="cellIndex">
                            <g:set var="divider" value="${cellIndex == 2 || cellIndex == 5}"/>
                            <g:set var="lastOneSolved" value="${cell.lastOneSolved}"/>
                            <g:findAll in="${session.board.regions}" expr="${it.contains(cell)}">
                                <g:set var="light" value="${it.regionNumber % 2 == 0}"/>
                            </g:findAll>
                            <td class="<g:if test='${divider}'>cell_divider </g:if>
                            <g:if test='${lastOneSolved}'>last_one_solved </g:if>
                            <g:if test='${light}'>light </g:if><g:else>dark </g:else>"
                            <td>
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