<%@page import="User.Model.Reader"%>
<%@page import="User.Model.Writer"%>
<%@page import="User.Model.Editor"%>
<%@page import="User.Model.AdminEditor"%>
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
        <section class="banner" background-img src="images/storyOfTheDay.jpg">
            
            <div class="banner">
                <h3 style="color:black">Welcome to your user homepage</h3>
                <%
                if(request.getAttribute("user") instanceof Reader) {%>

                <h5 style="color:black">Readers Options</h5>
                <form action="UserServlet" method="get">
                    <input class="button1" name="submit" type="submit" value="My Liked Stories">
                </form>
                <form action="UserServlet" method="post">
                    <input class="button1" name="submit" type="submit" value="My Preferred Categories">
                    <input class="button1" name="submit" type="submit" value="Become a Writer">
                </form>    

                <%} else if (request.getAttribute("user") instanceof Writer) {%>

                <div>
                    <br>   </br>
                    <h5 style="color:black">Writers Options</h5>
                    <a href=dailystory.html>
                        <button class="button1">My Likes</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">My Categories</button>
                    </a>
                    <a href=dailystory.html>
                        <button class="button1">My Drafts</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Create Story</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">My Approved Stories</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">My Rejected Stories</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">My Pending Stories</button>
                    </a>
                </div>

                <%} else if (request.getAttribute("user") instanceof Editor) {%>
                <div>
                    <br>   </br>
                    <h5 style="color:black">Editor Options</h5>
                    <a href=dailystory.html>
                        <button class="button1">Pending Stories</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Approved Stories</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Rejected Stories</button>
                    </a>
                </div>
                <%} else if (request.getAttribute("user") instanceof AdminEditor) {%>
                <div>
                    <br>   </br>
                    <h5 style="color:black">Admin Editor Options</h5>
                    <a href=dailystory.html>
                        <button class="button1">Pending Stories</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Approved Stories</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Rejected Stories</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Create Editor</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Create Category</button>
                    </a>
                    <a href=viewstory.html>
                        <button class="button2">Statistics</button>
                    </a>
                </div>
                <%}%>

                <%
                    String storyMessage = (String) request.getAttribute("likedStories");
                    String categoryMessage = (String) request.getAttribute("preferredCategories");
                    if(storyMessage != null) {%>
                <div>
                    <h3 style="color:black"><%=storyMessage%></h3>
                </div>
                <%}
                    if(categoryMessage != null) {%>
                <div>
                    <h3 style="color:black"><%=categoryMessage%></h3>
                </div>
                <%}%>



                <%
                    String responseMessage = (String) request.getAttribute("likes");
                %>
                <%
                    if(responseMessage != null) {
                %>
                <div>
                    <h3 style="color:black"><%=responseMessage%></h3>
                </div>
                <%
                    }
                %>
            </div>
        </section>
        <section class="main_content">
            <div class="side_nav">
            </div>
        </section>
    </body>
</html>