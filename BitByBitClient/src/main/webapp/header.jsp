<%@page import="User.Model.User"%>
<html>
    <head>
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <section class="header">
        <nav>
            <ul class="clearfix">
                <li class="nav_logo">
                    <img src="images/open-book.png" class="logo_img" alt="Netflix Logo">
                </li>
                <li>
                    <a href=index.jsp>
                        <button class="button3">Home</button>
                    </a>
                </li>
                <li>
                    <form action="index" method="get">
                        <a href="storiesByCategories.jsp">
                            <input class="button1" name="submit" type="submit" value="View stories by categories">
                        </a>
                    </form>
                </li>
                <li>
                    <button type="button">Refer a Friend</button>
                </li>
                <li>
                    <a href="User.jsp">
                        <button type="button">Contact Us</button>
                    </a>
                </li>
                <li>
                    <form action="StoryServlet" method="get">
                        <a>
                            <input class="button1" name="submit" type="submit" value="Review Story">
                        </a>
                    </form>
                </li>
                
                <!-- Don't add new buttons below this line it will break the Login button-->
                
                <%
                User userLoggedIn = (User) session.getAttribute("loggedInUser");
                    
                if(userLoggedIn != null) {
                    if(userLoggedIn.getRoleID() < 3) {%>
                <li class="same">
                    <a href=User.jsp>
                        <button type="button">Profile</button> 
                    </a>
                </li>
                <li>
                    <%} else {%>
                    <form action="StoryServlet" method="get">
                        <a>
                            <input class="button1" name="submit" type="submit" value="Profile">
                        </a>
                    </form>
                </li>
                <%}
                } else {
                %>
                <li class="same">
                    <a href=LoginRegister.jsp>
                        <button type="button">Login/Register</button>
                    </a>
                </li>
                <%
                }
                %>
            </ul>
        </nav>
    </section>
</html>