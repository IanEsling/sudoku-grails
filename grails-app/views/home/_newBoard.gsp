<g:if test="${session.board == null}">
    <div id="newBoard">
        <g:if test="${exception!=null}">
            <div class="exception">
                ${exception}
            </div>
        </g:if>
        <table>
            <g:each in="${1..9}" var="row" status="rowIndex">
                <tr <g:if test="${rowIndex == 2 || rowIndex == 5}">class="row_divider"</g:if>>
                    <g:each in="${1..9}" var="col" status="cellIndex">
                        <g:set var="divider" value="${cellIndex == 2 || cellIndex == 5}"/>
                        <g:set var="light"
                               value="${([4,5,6].contains(col) && ([1,2,3].contains(row) || [7,8,9].contains(row))) || (([1,2,3].contains(col) || [7,8,9].contains(col)) && [4,5,6].contains(row))}"/>
                        <td id="${row}${col}"
                            onmouseover="showTable('table${row}${col}')"
                            onmouseout="hideTable('table${row}${col}')"
                            class="<g:if test='${divider}'>cell_divider</g:if>
                            <g:if test='${light}'>light</g:if><g:else>dark</g:else>
                            ">
                            <table id="table${row}${col}"
                                   class="select_notes_<g:if test='${light}'>light</g:if><g:else>dark</g:else>">
                                <tr class="note_row">
                                    <g:each in="[1,2,3]" var="cell">
                                        <td class="select_note_cell"
                                            onclick="selectForCell(${cell}, '${row}${col}')">${cell}</td>
                                    </g:each>
                                </tr>
                                <tr class="note_row">
                                    <g:each in="[4,5,6]" var="cell">
                                        <td class="select_note_cell"
                                            onclick="selectForCell(${cell}, '${row}${col}')">${cell}</td>
                                    </g:each>
                                </tr>
                                <tr class="note_row">
                                    <g:each in="[7,8,9]" var="cell">
                                        <td class="select_note_cell"
                                            onclick="selectForCell(${cell}, '${row}${col}')">${cell}</td>
                                    </g:each>
                                </tr>
                            </table>
                        </td>
                    </g:each>
                </tr>
            </g:each>
        </table>
    </div>

    <div style="margin-top: 30px; margin-left: auto; margin-right: auto; width:10%">
        <a onclick="go()" href="#" style="margin-left: 50px;">Go!</a>
    </div>
</g:if>