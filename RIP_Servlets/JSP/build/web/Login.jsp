<%-- 
    Document   : Login
    Created on : 13 Oct 2022, 09:32:44
    Author     : tarun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
            String responseMessage = (String) request.getAttribute("message");
        %>            
        <div>Login</div>
        <%if(responseMessage != null){%>
                <div>
                    <h3><%=responseMessage%></h3>
                </div>
          <%}%>
        <form action="MyFirstServlet" method="post">
            password: <input name="password" type="password"><br>
            email: <input name="email" type="text"><br>
            <input name="submit" type="submit" value="login">
        </form> 
    </body>
</html>
