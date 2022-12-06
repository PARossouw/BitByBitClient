<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%
    
        String message = (String)request.getAttribute("message");
    
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Refer a Friend</title>
    </head>
    <body>

        <h1>Refer a Friend</h1>

        <form action="UserServlet" method="post">
        <input type="text" class="form" name="phoneNumber" placeholder="Your friend's phone number">
        <br></br>
        <input class="button1" name="submit" type="submit" value="ReferFriend">
        </form>
        
    </body>
</html>
