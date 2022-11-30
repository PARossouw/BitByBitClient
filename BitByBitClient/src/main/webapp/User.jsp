<%@page import="User.Model.Reader"%>
<%@page import="User.Model.Writer"%>
<%@page import="Story.Model.Story"%>
<%@page import="User.Model.Editor"%>
<%@page import="Category.Model.Category"%>
<%@page import="User.Model.AdminEditor"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            <section class="main_content">
                <div class="side_nav">
                    <ul class="browse">
                        <li>
                            <form action="UserServlet" method="get">
                                <input class="button1" name="submit" type="submit" value="My Liked Stories">
                            </form>
                        </li>
                        <li>
                            <form action="UserServlet" method="post">
                                <input class="button1" name="submit" type="submit" value="My Preferred Categories">
                            </form>
                        </li>
                        <li>
                            <form action="UserServlet" method="post">
                                <input class="button1" name="submit" type="submit" value="Become a Writer">
                            </form>
                        </li>
                    </ul>
                </div>
                <h4 style="color:black">Your Likes</h4>
            <%
            List<Story> likedStories = (ArrayList<Story>)request.getAttribute("likedStories");
                        
            if(likedStories != null) {
                for(Story story : likedStories) {
            %>
            <tr>
                <td>
                    <label style="color:black"><%=story.getTitle()%></label><br>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td>
                    <label style="color:black">Here you can track all the stories you have liked</label><br>
                </td>
            </tr>
            <%
            }
            %>

            <h4 style="color:black">Your Preferred Category</h4>
            <%
            List<Category> preferredCategories = (ArrayList<Category>)request.getAttribute("preferredCategories");
                        
            if(preferredCategories != null) {
                for(Category category : preferredCategories) {
            %>
            <tr>
                <td>
                    <label style="color:black"><%=category.getName()%></label><br>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td>
                    <label style="color:black">Here you can track all your preferred categories</label><br>
                </td>
            </tr>
            <%
            }
            %>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>