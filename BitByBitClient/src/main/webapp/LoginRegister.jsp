<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="User.Model.User;"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <section class="banner_common">
                <div>
                    <h3>Login to your account to be able to read stories or register if you don't have an account yet.</h3>
                </div>
            </section>

            <section class="main_content">
                <form action="UserServlet" method="post" class="login-form">
                    <h3 style="color:black">Login</h3>
                <%
                    String responseMessage = (String) request.getAttribute("message");
                    String userLoggedIn = (String) request.getAttribute("user");
                     session.setAttribute("user", userLoggedIn);
                    if(responseMessage != null) {
                    session.setAttribute("user", userLoggedIn);
                %>
                <div>
                    <h3 style="color:red"><%=responseMessage%></h3>
                </div>
                <%
                    }
                %>
                <input type="text" class="form" name="UsernameOrEmail" placeholder="Username or Email">
                <br></br>
                <input type="password" class="form" name="Password" placeholder="Password">
                <br></br>
                <input class="button1" name="submit" type="submit" value="Login">

            </form>

            <form action="UserServlet" method="post" class="register-form">

                <%
                    String responseMessageRegister = (String) request.getAttribute("messageRegister");
                    if(responseMessageRegister != null) {
                %>
                <div>
                    <h3 style="color:red"><%=responseMessageRegister%></h3>
                </div>
                <%
                    }
                %>

                <h3 style="color:black">Register</h3>
                <input type="text" class="form" name ="Username" placeholder="Username">
                <br></br>

                <input type="text" class="form" name ="Email" placeholder="Email">
                <br></br>

                <input type="text" class="form" name ="PhoneNumber" placeholder="Phone Number">
                <br></br>

                <input type="text" class="form" name ="Password" placeholder="Password">

                <input type="text" class="form" name ="ConfirmPassword" placeholder="Confirm Password">
                <br></br>

                <input class="button1" name="submit" type="submit" value="Register">
            </form>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>