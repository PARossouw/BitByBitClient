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
                    <form action="CategoryServlet" method="get">
                        <a href="storiesByCategories.jsp">
                            <input class="button1" name="submit" type="submit" value="View stories by categories">
                        </a>
                    </form>
                </li>
                <li>
                    <a href="ReferFriend.jsp">
                        <input class="button1" name="submit" type="submit" value="Refer a Friend">
                    </a>
                </li>
                <li>
                    <form action="UserServlet" method="get">
                        <input class="button1" name="submit" type="submit" value="Profile">
                    </form>
                </li>
                <li>
                    <form action="StoryServlet" method="get">
                        <input class="button1" name="submit" type="submit" value="Review Story">
                    </form>
                </li>
                <li>
                    <form action="StoryServlet" method="get">
                        <input class="button1" name="submit" type="submit" value="Choose Story of The Day">
                    </form>
                </li>
                <li>
                    <!--<form action="UserServlet" method="get">-->
                    <a href="BlockWriter.jsp">
                        <input class="button1" name="submit" type="submit" value="Block Writer">
                    </a>
                    <form action="StoryServlet" method="post">
                        <input class="button1" name="submit" type="submit" value="Turn Off Comments">
                        </a>
                    </form>
                    <!--</form>-->
                </li>
                <!-- Don't add new buttons below this line it will break the Login button-->

                <%
                User userLoggedIn = (User) session.getAttribute("loggedInUser");
                    
                if(userLoggedIn == null) {
                %>
                <li class="same">
                    <a href=LoginRegister.jsp>
                        <button type="button">Login/Register</button>
                    </a>
                </li>
                <%
                } else {
                    if(userLoggedIn.getRoleID() < 3) {
                %>
                <li class="same">
                    <form action="UserServlet" method="get">
                        <input class="button1" name="submit" type="submit" value="Profile">
                    </form>
                </li>
                <%} else {%>
                <li>
                    <form action="StoryServlet" method="get">
                        <input class="button1" name="submit" type="submit" value="Profile">
                    </form>
                </li>
                <%
                    }
                }
                %>
            </ul>
        </nav>
    </section>
</html>