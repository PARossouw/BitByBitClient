<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Full Story</title>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            String storyBody = (String) request.getAttribute("storyBody");
                          
               
        %>

        <h1><%=storyBody%></h1>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
