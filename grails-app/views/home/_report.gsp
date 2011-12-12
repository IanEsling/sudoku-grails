<g:each in="${report.reports}" var="line">
    <li onmouseout="removeReportHighlight([
        <g:each in="${report.reportCells[line]}" var="cell" status="status">
        ${cell.pageId}<g:if test="${status < (line.value.size())}">, </g:if>
        </g:each>
    ])"
        onmouseover="reportHighlight([
            <g:each in="${report.reportCells[line]}" var="cell" status="status">
            ${cell.pageId}<g:if test="${status < (line.value.size())}">, </g:if>
            </g:each>
        ])">${line}</li>
</g:each>