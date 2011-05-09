<div id="report">
    <div id="spinner" style="display:none;">
        <img src="${createLinkTo(dir: 'images', file: 'spinner.gif')}" border="0" alt="Loading..." title="Loading..." width="32" height="32"/>
    </div>

    <g:each in="${report}" var="line">
        ${line}<br/><br/>
    </g:each>
</div>