<g:if test="${session.board == null}">
    <div id="newBoard">

        <table>
            <g:each in="${1..9}" var="row" status="rowIndex">
                <tr <g:if test="${rowIndex == 2 || rowIndex == 5}">class="row_divider"</g:if>>
                    <g:each in="${1..9}" var="cell" status="cellIndex">
                        <g:set var="divider" value="${cellIndex == 2 || cellIndex == 5}"/>
                        <g:set var="light"
                                value="${([4,5,6].contains(cell) && ([1,2,3].contains(row) || [7,8,9].contains(row))) || (([1,2,3].contains(cell) || [7,8,9].contains(cell)) && [4,5,6].contains(row))}"/>
                        <td id="${row}${cell}"
                                 onmouseover="showTable('table${row}${cell}')"
                                    onmouseout="hideTable('table${row}${cell}')"
                                    class="<g:if test='${divider}'>cell_divider</g:if>
                        <g:if test='${light}'>light</g:if><g:else>dark</g:else>
                        ">
                            <table id="table${row}${cell}" class="select_notes">
                                <tr class="note_row">
                                    <g:each in="[1,2,3]" var="col">
                                        <td class="select_note_cell" onclick="selectForCell(${col}, '${row}${cell}')">${col}</td>
                                    </g:each>
                                </tr>
                                <tr class="note_row">
                                    <g:each in="[4,5,6]" var="col">
                                        <td class="select_note_cell" onclick="selectForCell(${col}, '${row}${cell}')">${col}</td>
                                    </g:each>
                                </tr>
                                <tr class="note_row">
                                    <g:each in="[7,8,9]" var="col">
                                        <td class="select_note_cell" onclick="selectForCell(${col}, '${row}${cell}')">${col}</td>
                                    </g:each>
                                </tr>
                            </table>
                        </td>
                    </g:each>
                </tr>
            </g:each>
        </table>
    </div>
</g:if>