<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Full Story</title>
    </head>
    <body>

        <%
            String storyBody = (String) request.getAttribute("storyBody");
                          
               
        %>

        <h1><%=storyBody%></h1>
         
    </body>
</html>
