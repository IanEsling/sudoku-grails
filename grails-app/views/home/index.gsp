<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Teach Me Sudoku</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'sudoku.css')}"/>
    <g:javascript library="prototype"/>
    %{--<g:javascript library="jquery"/>--}%
    <script type="text/javascript">
        function notesVisible() {
            if ($("notesVisible").checked) {
                showNotes();
            } else {
                hideNotes();
            }
        }

        function showNotes(event) {
            $$("table.hideNotes").each(function(element) {
                element.className = "visibleNotes";
            });
            $("hideNotes").show();
            $("showNotes").hide();
            $("notesVisible").checked = true;
        }

        function hideNotes(event) {
            $$("table.visibleNotes").each(function(element) {
                element.className = "hideNotes";
            });
            $("showNotes").show();
            $("hideNotes").hide();
            $("notesVisible").checked = false;
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
<g:render template="newBoardTextField"/>

<a id="showNotes" href="#">Show Notes</a>
<a id="hideNotes" href="#">Hide Notes</a>
<g:hiddenField id="notesVisible" name="notesVisible" checked="false"/>
<g:render template="board"/>

</body>
</html>