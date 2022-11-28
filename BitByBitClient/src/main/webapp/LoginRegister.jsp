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

                    <form action="UserServlet" method="post">
                        <h3 style="color:black">Login</h3>
                    <%
                        String responseMessage = (String) request.getAttribute("message");
                        String userLoggedIn = (String) request.getAttribute("user");
                         session.setAttribute("user", userLoggedIn);
                        
                    %>
                    <%
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

                <form action="UserServlet" method="post">

                    <%
                        String responseMessageRegister = (String) request.getAttribute("messageRegister");
                    %>
                    <%
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

                    <input type="text" class="form" name ="Email" placeholder="Email">

                    <input type="text" class="form" name ="PhoneNumber" placeholder="Phone Number">
                    <br></br>

                    <input type="text" class="form" name ="Password" placeholder="Password">

                    <input type="text" class="form" name ="ConfirmPassword" placeholder="Confirm Password">

                    <br></br>

                    <input class="button1" name="submit" type="submit" value="Register">
                </form>



                <%--
            <%
                User userLoggedIn = (User) session.getAttribute("user");
            %>
            <%
                if(userLoggedIn != null) {
            %>
            <div>
                <h3 style="color:red"><%=userLoggedIn%></h3>
            </div>
            <%
                }
            %>

                --%>


            </div>
        </section>

        <section class="main_content">
            <div class="side_nav">
            </div>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
