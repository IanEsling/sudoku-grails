<div id="mainContainer">
<div id="board">
    <g:if test="${session.board != null}">
        <table>
            <g:each in="${session.board.rows}" var="row" status="rowIndex">
                <tr <g:if test="${rowIndex == 2 || rowIndex == 5}">class="row_divider"</g:if>>
                    <g:each in="${row.cells}" var="cell" status="cellIndex">
                        <g:set var="divider" value="${cellIndex == 2 || cellIndex == 5}"/>
                        <g:set var="lastOneSolved" value="${cell.lastOneSolved}"/>
                        <g:set var="originalCell" value="${session.originalCells.contains(cell)}"/>
                        <g:findAll in="${session.board.regions}" expr="${it.contains(cell)}">
                            <g:set var="light" value="${it.regionNumber % 2 == 0}"/>
                        </g:findAll>
                        <td class="<g:if test='${divider}'>cell_divider</g:if>
                        <g:if test='${lastOneSolved}'>last_one_solved</g:if>
                        <g:if test='${originalCell}'>original_cell</g:if>
                        <g:if test='${light}'>light</g:if><g:else>dark</g:else>
                        ">
                            <g:if test="${cell.values.size() == 1}">${cell.values[0]}
                            </g:if>
                            <g:else>
                                <table class="hideNotes">
                                    <tr class="note_row">
                                        <g:each in="[1,2,3]" var="col">
                                            <td class="note_cell"><g:if test="${cell.values.contains(col)}">${col}</g:if></td>
                                        </g:each>
                                    </tr>
                                    <tr class="note_row">
                                        <g:each in="[4,5,6]" var="col">
                                            <td class="note_cell"><g:if test="${cell.values.contains(col)}">${col}</g:if></td>
                                        </g:each>
                                    </tr>
                                    <tr class="note_row">
                                        <g:each in="[7,8,9]" var="col">
                                            <td class="note_cell"><g:if test="${cell.values.contains(col)}">${col}</g:if></td>
                                        </g:each>
                                    </tr>
                                </table>
                            </g:else>
                        </td>
                    </g:each>
                </tr>
            </g:each>
        </table>
        <script type="text/javascript">
            notesVisible();
        </script>
        <g:if test="${!session.board.solved}">
            <g:remoteLink action="solve" update="mainContainer">
            %{--<g:submitButton name="solveMe" value="Click to solve a square"/>--}%
                Click to solve a square
            </g:remoteLink>
        </g:if>
        </div>

        <g:render template="report"/>
    </g:if>
</div>