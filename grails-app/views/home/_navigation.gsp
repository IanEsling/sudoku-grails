<g:if test="${session.board != null}">
    <div class="navigation">
        <ul>
            <li>
                <g:link action="clear">
                    Start new board
                </g:link>
            </li>
            <li>
                <g:link action="about">
                    About
                </g:link>
            </li>
        </ul>
    </div>
</g:if>