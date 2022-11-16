<%-- 
    Document   : register.jsp
    Created on : 13 Oct 2022, 11:17:25
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
        <%
    String responseMessage = (String) request.getAttribute("message1");
        %>            
        <div>register</div>
        <%if(responseMessage != null)
        {
        %><div>
            <h3><%=responseMessage%></h3>
            <%}%>
        </div>
        }
        <form action="MyFirstServlet" method="post">
            name: <input name="name" type="text"><br>
            surname: <input name="surname" type="text"> <br>
            password: <input name="password" type="password"><br>
            email: <input name="email" type="text"><br>
            <input name="submit" type="submit" value="register">
        </form> 
    </body>
</html>
