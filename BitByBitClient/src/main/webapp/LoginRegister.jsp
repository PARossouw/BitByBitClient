<%-- 
    Document   : LoginRegister
    Created on : 17 Nov 2022, 10:38:52
    Author     : tarunsing, pieterrossouw
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html> 
<html>

    <head>
        <title>Login</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="registerlogin.css">
    </head>

    <body>
        <section class="header" background-img src="images/storyOfTheDay.jpg">
            <nav>
                <ul class="clearfix">
                    <li class="nav_logo">
                        <img src="images/open-book.png" class="logo_img" alt="Netflix Logo">

                    </li>
                    <li>
                        <a href=index.jsp>
                            <button type="button">Home</button>
                        </a>
                    </li>
                    <li>
                        <button type="button">Categories</button>
                    </li>
                    <li>                       
                        <button type="button">Refer a Friend</button>
                    </li>
                    <li>                       
                        <button type="button">Contact Us</button>
                    </li>


                   <%
                    String userLoggedInHeader = (String) session.getAttribute("user");
                %>
                <%
                    if(userLoggedInHeader != null) {
                %>
               <li>
                        <button type="button">Profile</button>
               </li>
                <%
                    }
else 
{
   %>
               <li>
                        <button type="button">Login/Register</button>
               </li>
                <%

}


                %>
                    

                </ul>

            </nav>
            <div class="h_div">

                <form action="UserServlet" method="post">
                    <h3 style="color:black">Login</h3>
                    <%
                        String responseMessage = (String) request.getAttribute("message");
                    %>
                    <%
                        if(responseMessage != null) {
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
                    
                    
  
                    
                    



                <%
                    String userLoggedIn = (String) session.getAttribute("user");
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



            </div>
        </section>

        <section class="main_content">
            <div class="side_nav">
            </div>

            <div>
                <p style="color:black" >Brought to you Bit by Bit<p>
            </div>




        </section>
    </body>

</html>
