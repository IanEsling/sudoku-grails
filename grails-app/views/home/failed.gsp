
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Oh Noes</title></head>
  <body>I'm sorry, I tried.  But I failed.<br/><br/>
    <% println session.board.toStringForFailedBoard() %>
  </body>
</html>