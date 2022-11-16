<%-- 
    Document   : Prize.jsp
    Created on : 13 Oct 2022, 10:43:41
    Author     : tarunsing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>You have won a new Iphone 11s. Please give us your id number</div>
             <%
            String responseMessage = (String) request.getAttribute("message");
        %>            
        <div>Prize</div>
        <%if(responseMessage != null){%>
                <div>
                    <h3><%=responseMessage%></h3>
                </div>
          <%}%>
        <form action="MyFirstServlet" method="post">
            email: <input name="email" type="text"><br>
            <input name="submit" type="submit" value="Prize">
        </form> 
    </body>
</html>
