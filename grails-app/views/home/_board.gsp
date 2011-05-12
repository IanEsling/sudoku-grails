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
                    <td id="${cell.row}x${cell.column}" class="<g:if test='${divider}'>cell_divider</g:if>
                    <g:if test='${lastOneSolved}'>last_one_solved</g:if>
                    <g:if test='${originalCell}'>original_cell</g:if>
                    <g:if test='${light}'>light</g:if><g:else>dark</g:else>
                    "
                        <g:if test="${cell.values.size() > 1}">onclick="report('${cell.row}_${cell.column}')"
                        onmouseover="hasNotes('${cell.row}x${cell.column}')" onmouseout="noNotes('${cell.row}x${cell.column}')"</g:if>>
                        <g:if test="${cell.values.size() == 1}">${cell.values[0]}
                        </g:if>
                        <g:else>
                            <div style="display:none">
                                <div id="${cell.row}_${cell.column}">
                                    <p style="font-weight: bold;">Notes for ${cell.toString()} ${cell.values} :</p>
                                    <ul>
                                        <g:each in="${cell.report}" var="line">
                                            <li>${line}</li>
                                        </g:each>
                                    </ul>
                                </div>
                            </div>
                            <table class="hide_notes">
                                <tr class="note_row">
                                    <g:each in="[1,2,3]" var="col">
                                        <td class="note_cell"><g:if test="${cell.values.contains(col)}">${col}</g:if>
                                            <g:else>&nbsp;</g:else></td>
                                    </g:each>
                                </tr>
                                <tr class="note_row">
                                    <g:each in="[4,5,6]" var="col">
                                        <td class="note_cell"><g:if test="${cell.values.contains(col)}">${col}</g:if>
                                            <g:else>&nbsp;</g:else></td>
                                    </g:each>
                                </tr>
                                <tr class="note_row">
                                    <g:each in="[7,8,9]" var="col">
                                        <td class="note_cell"><g:if test="${cell.values.contains(col)}">${col}</g:if>
                                            <g:else>&nbsp;</g:else></td>
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
    </div>

    <div class="report">
        <div id="spinner" style="display:none;">
            <img src="${createLinkTo(dir: 'images', file: 'spinner.gif')}" border="0" alt="Loading..." title="Loading..." width="32" height="32"/>
        </div>

        <ul>
            <g:each in="${report}" var="line">
                <li>${line}</li>
            </g:each>
        </ul>
    </div>
    <div id="cellNotes" class="report">

    </div>
</g:if>