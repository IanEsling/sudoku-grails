<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Teach Me Sudoku</title>
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}"/>--}%
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

        function showNotes(event) {
            $$("table.hide_notes").each(function(element) {
                element.className = "visible_notes";
            });
            if ($("hideNotes") != null) {
                $("hideNotes").show();
            }
            if ($("showNotes") != null) {
                $("showNotes").hide();
            }
            $("notesVisible").checked = true;
        }

        function hideNotes(event) {
            $$("table.visible_notes").each(function(element) {
                element.className = "hide_notes";
            });
            if ($("showNotes") != null) {
                $("showNotes").show();
            }
            if ($("hideNotes") != null) {
                $("hideNotes").hide();
            }
            $("notesVisible").checked = false;
        }

        function report(reportMe) {
            $("cellNotes").update($(reportMe).innerHTML);
        }

        function showTable(table) {
            if ($(table) != null) {
                $(table).addClassName("select_notes");
            }
        }

        function hideTable(table) {
            if ($(table) != null) {
                $(table).removeClassName("select_notes");
            }
        }

        function selectForCell(number, cell) {
            var html = '<div>' + $(cell).innerHTML + '</div>';
            $(cell).update(number);
            $(cell).observe('click', function(event) {
                event.findElement().update(html);
            });
        }

        function go() {
            var board = "";
            var row;
            var col;
            for (row = 1; row <= 9; row++) {
                for (col = 1; col <= 9; col++) {
                    var tableName = "table" + row + col;
                    if ($(tableName) == null) {
                        board = board + $(row.toString() + col.toString()).innerText;
                    } else {
                        board = board + ".";
                    }
                }
            }
            document.location.href = "${g.createLink(url: "/board/")}" + board ;
        }

//        document.observe("dom:loaded", function() {
//                    hideNotes();
//                }
//        )
    </script>
</head>

<body>
<g:checkBox id="notesVisible" name="notesVisible" checked="false" style="display:none;"/>

<g:render template="navigation"/>

<div id="mainContainer">
    <g:render template="board"/>
    <g:render template="newBoard"/>

</div>

<div style="margin-top: 25px; width:100%; float: left;">
    <g:if test="${session.originalBoard != null}">
        <p>Playing original board of: <span
                style="font-size:large;font-family:monospace">${session.originalBoard}</span></p>
    </g:if>
    <g:form name="newBoardForm" url="[controller:'home',action:'newBoard']" style="display: none;">
        Enter New Board:
        <g:textField name="newBoardString" size="81"/>
        <g:submitButton name="Submit" value="Submit"/>
    </g:form>
</div>
</body>
</html>