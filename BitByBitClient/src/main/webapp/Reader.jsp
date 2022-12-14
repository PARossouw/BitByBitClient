<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="User.Model.User"%>
<%@page import="Story.Model.Story"%>
<%@page import="Category.Model.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html> 
<html>
    <head>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <section class="main_content">
            <div class="side_nav">
                <nav class="browse">
                    <ul class="bbh">
                        <b><lh>Options</lh></b>
                        <a href="ReferFriend.jsp" name="submit" type="submit" value="Refer a Friend"><li>Refer a Friend</li></a>
                       
                        <form action="UserServlet" method="post">
                            <input class="button" name="submit" type="submit" value="Become a Writer">
                        </form>
                        <!--<a href="Writer.jsp" name="submit"><li>Become a Writer</li></a>-->
                    </ul>
                </nav>
            </div>
            <div class="story-list">
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
            </div>
            <div class="category-list">
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
            </div>
        </section>
    </body>
</html>