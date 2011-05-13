<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Teach Me Sudoku</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'sudoku.css')}"/>
    <g:javascript library="prototype"/>
    <script type="text/javascript">
        function notesVisible() {
            if ($("notesVisible").checked) {
                showNotes();
            } else {
                hideNotes();
            }
        }

        function hasNotes(cell) {
            $(cell).addClassName("board_cell_with_notes");
        }

        function noNotes(cell) {
            $(cell).removeClassName("board_cell_with_notes");
        }

        function showNotes(event) {
            $$("table.hide_notes").each(function(element) {
                element.className = "visible_notes";
            });
            $("hideNotes").show();
            $("showNotes").hide();
            $("notesVisible").checked = true;
        }

        function hideNotes(event) {
            $$("table.visible_notes").each(function(element) {
                element.className = "hide_notes";
            });
            $("showNotes").show();
            $("hideNotes").hide();
            $("notesVisible").checked = false;
        }

        function report(reportMe) {
            $("cellNotes").update($(reportMe).innerHTML);
        }

        function showTable(table) {
            $(table).show();
        }

        function hideTable(table) {
            $(table).hide();
        }

        function selectForCell(number, cell) {
            $(cell).update(number);
        }

        document.observe("dom:loaded", function() {
            $("showNotes").observe('click', showNotes);
            $("hideNotes").observe('click', hideNotes);
            hideNotes();
        }
                )
    </script>
</head>
<body>
<g:hiddenField id="notesVisible" name="notesVisible" checked="false"/>

<g:render template="newBoardTextField"/>

<div id="mainContainer">
    <g:render template="board"/>
    <g:render template="newBoard"/>
</div>

<div class="notes_link" <g:if test="${session.board == null}">style="display:none"</g:if>>
    <p>
        <g:if test="${session.board != null && !session.board.solved}">
        %{--<p>--}%
            <g:remoteLink style="margin-left:25%" action="solve" update="mainContainer">
                Click to solve a square
            </g:remoteLink>
        %{--</p>--}%
        </g:if>
        <a id="showNotes" href="#">Show All Notes</a>
        <a id="hideNotes" href="#">Hide All Notes</a>
    </p>
    <p>
    <g:if test="${session.originalBoard != null}">
        <p>Playing original board of: <span style="font-size:large;font-family:monospace">${session.originalBoard}</span></p>
    </g:if>
    </p>
</div>
</body>
</html>