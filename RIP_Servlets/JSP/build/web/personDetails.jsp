<%-- 
    Document   : personDetails.jsp
    Created on : 13 Oct 2022, 10:58:22
    Author     : tarunsing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Example.Person;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Person person = (Person)request.getAttribute("person");%>
        
        <h1>Welcome </h1>
        <ol>
            <li> Name : <%=person.getName()%></li>
            <li> Surname : <%=person.getSurname()%></li>
            <li> Email : <%=person.getEmail()%></li>
            <li> password : <%=person.getPassword()%></li>
            
         </ol>
        
    </body>
</html>
