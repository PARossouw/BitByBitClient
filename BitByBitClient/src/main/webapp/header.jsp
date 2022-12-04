<%@page import="User.Model.User"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
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
                        <button class="buttonMainHeader">Home</button>
                    </a>
                </li>
                <li>
                    <form action="CategoryServlet" method="get">
                        <a href="storiesByCategories.jsp">
                            <input class="buttonMainHeader" name="submit" type="submit" value="View stories by categories">
                        </a>
                    </form>
                </li>
                <!-- Don't add new buttons below this line it will break the Login button-->
                <%
                User userLoggedIn = (User) session.getAttribute("user");
                
                if(userLoggedIn == null) {
                %>
                <li class="same">
                    <a href=LoginRegister.jsp>
                        <button type="button" class="buttonMainHeader">Login/Register</button>
                    </a>
                </li>
                <%
                } else {
                    if(userLoggedIn.getRoleID() < 3) {
                %>
                <li class="same">
                    <form action="UserServlet" method="get">
                        <input class="buttonMainHeader" name="submit" type="submit" value="Profile">
                    </form>
                </li>
                <%} else {%>
                <li class="same">
                    <form action="UserServlet" method="get">
                        <input class="buttonMainHeader" name="submit" type="submit" value="Profile">
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