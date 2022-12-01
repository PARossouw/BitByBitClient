<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="User.Model.User"%>
<%@page import="User.Model.Reader"%>
<%@page import="User.Model.Writer"%>
<%@page import="User.Model.Editor"%>
<%@page import="User.Model.AdminEditor"%>
<%@page import="Story.Model.Story"%>
<%@page import="Category.Model.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html> 
<html>
    <head>
        <title>User Home Page</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <section class="banner_common">
                <div class="banner_common">
                    <h2 style="color:black">Welcome to your user homepage</h2>
                </div>
            </section>

            <section>
            <%
            User userLoggedIn = (User) session.getAttribute("user");
                
            if(userLoggedIn.getRoleID() == 1) {
            %>
            <jsp:include page="Reader.jsp"></jsp:include>
            <%
                } else if (userLoggedIn.getRoleID() == 2) {
            %>
            <jsp:include page="Writer.jsp"></jsp:include>
            <%
                } else if (userLoggedIn.getRoleID() == 3) {
            %>
            <jsp:include page="Editor.jsp"></jsp:include>
            <%
                } else {
            %>
            <jsp:include page="AdminEditor.jsp"></jsp:include>
            <%
                }
            %>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>