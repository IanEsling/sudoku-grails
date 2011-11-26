<g:if test="${session.board != null}">
    <div id="boardContainer">
        <div>
            <table id="board">
                <g:each in="${session.board.rows}" var="row" status="rowIndex">
                    <tr <g:if test="${rowIndex == 2 || rowIndex == 5}">class="row_divider"</g:if>>
                        <g:each in="${row.cells}" var="cell" status="cellIndex">
                            <g:set var="divider" value="${cellIndex == 2 || cellIndex == 5}"/>
                            <g:set var="lastOneSolved" value="${cell.lastOneSolved}"/>
                            <g:findAll in="${session.board.regions}" expr="${it.contains(cell)}">
                                <g:set var="light" value="${it.regionNumber % 2 == 0}"/>
                            </g:findAll>
                            <td id="${cell.row}${cell.column}" class="<g:if test='${divider}'>cell_divider</g:if>
                            <g:if test='${lastOneSolved}'>last_one_solved</g:if>
                            <g:if test='${light}'>light</g:if><g:else>dark</g:else>
                            <g:if test="${cell.values.size() > 1}">
                                <g:if test='${light}'>light_with_notes</g:if><g:else>dark_with_notes</g:else>
                            </g:if>
                            "
                                <g:if test="${cell.values.size() > 1}">
                                    onclick="report('${cell.row}_${cell.column}')"
                                </g:if>>
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
                                    <table id="table${cell.row}${cell.column}" class="hide_notes">
                                        <tr class="note_row">
                                            <g:each in="[1,2,3]" var="col">
                                                <td class="note_cell"><g:if
                                                        test="${cell.values.contains(col)}">${col}</g:if>
                                                    <g:else>&nbsp;</g:else></td>
                                            </g:each>
                                        </tr>
                                        <tr class="note_row">
                                            <g:each in="[4,5,6]" var="col">
                                                <td class="note_cell"><g:if
                                                        test="${cell.values.contains(col)}">${col}</g:if>
                                                    <g:else>&nbsp;</g:else></td>
                                            </g:each>
                                        </tr>
                                        <tr class="note_row">
                                            <g:each in="[7,8,9]" var="col">
                                                <td class="note_cell"><g:if
                                                        test="${cell.values.contains(col)}">${col}</g:if>
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
        </div>


        <div class="notes_link">
            <p>
                <g:if test="${session.board != null && !session.board.solved && failed==null}">
                    <a class="btn primary" onclick="go()" href="#">
                        Click to solve a square
                    </a>

                    <a id="showNotes" onclick="showNotes()" href="#" class="btn primary"
                       style="display:none;">Show All Notes</a>
                    <a id="hideNotes" onclick="hideNotes()" href="#" class="btn primary"
                       style="display:none;">Hide All Notes</a>
                </g:if>
            </p>
        </div>
    </div>

    <div class="reports" <g:if test="${report == null}">style="display:none"</g:if>>
        <div id="solvedCellReport" class="report">

            <p>Notes for solved square :</p>
            <ul>
                <g:if test="${failed}">
                    <li>${report}</li>
                </g:if>
                <g:else>
                    <g:each in="${report}" var="line">
                        <li>${line}</li>
                    </g:each>
                </g:else>
            </ul>
        </div>

        <div id="cellNotes" class="report">

        </div>
    </div>

    <script type="text/javascript">
        if (${failed != null}) {
            $("notesVisible").checked = true;
        }
        notesVisible();
    </script>
</g:if>