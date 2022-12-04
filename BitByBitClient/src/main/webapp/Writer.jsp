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
                        <form action="StoryServlet" method="get"><a href="createNewStory.jsp" name="submit" type="submit" value="Create Story"><li>Write a Story</li></a></form>
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
            
            <table class="stats">
                <tr id="title">
                    <th>Your Stories</th>
                </tr>
                <tr>
                    <th>Title</th>
                    <th>Likes</th>
                    <th>Views</th>
                    <th>Avg. Rating</th>
                    <th>Comments Allowed</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <%
                List<Story> writerStories = (ArrayList<Story>)request.getAttribute("writerStories");
                        
                if(writerStories != null) {
                    for(Story story : writerStories) {
                %>
                <tr>
                    <td><%=story.getTitle()%></td>
                    <td><%=story.getLikes()%></td>
                    <td><%=story.getViews()%></td>
                    <td><%=story.getAvgRating()%></td>
                    <%
                        if(story.getAllowComments()) {
                    %>
                    <td>Yes</td>
                    <%} else {%>
                    <td>No</td>
                    <%}%>
                    <%
                        if(story.getIsDraft()) {
                    %>
                    <td>Draft</td>
                    <%} else if(!(story.getIsDraft()) && !(story.getIsApproved())) {%>
                    <td>Pending</td>
                    <%} else if(!(story.getIsDraft()) && story.getIsApproved()) {%>
                    <td>Approved</td>
                    <%} else {%>
                    <td>Rejected</td>
                    <%}%>
                    
                    <td>
                        <form action="StoryServlet" method="post">
                            <input name="submit" type="submit" value="Turn Off Comments">
                            <input class="buttonMain"  name="story_id" type="hidden" value="<%=story.getStoryID()%>"> 
                        </form>
                        <form action="StoryServlet" method="get">
                            <input name="submit" type="submit" value="View Story Get">
                            <input name="submit" type="submit" value="Display Story To Edit">
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td>
                        <label style="color:black">Here you can track all your stories.</label><br>
                    </td>
                </tr>
                <%
                }
                %>
            </table>
            <%
                    String commentMessage = (String)request.getAttribute("commentMessage");
                    
                    if(commentMessage != null){
            %>
            <h4><%=commentMessage%></h4>
            <%}%>
        </section>
    </body>
</html>