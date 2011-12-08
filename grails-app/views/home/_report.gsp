<g:each in="${report}" var="line">
    <li onmouseout="removeReportHighlight([
        <g:each in="${line.value}" var="cell" status="status">
        ${cell.pageId}<g:if test="${status < (line.value.size())}">, </g:if>
        </g:each>
    ])"
        onmouseover="reportHighlight([
            <g:each in="${line.value}" var="cell" status="status">
            ${cell.pageId}<g:if test="${status < (line.value.size())}">, </g:if>
            </g:each>
        ])">${line.key}</li>
</g:each>