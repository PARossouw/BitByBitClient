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
        <%
            User loggedInUser = (User) request.getAttribute("user");
        %>
        <section class="main_content">
            <div>
                <nav class="browse">
                    <ul class="bbh">
                        <form action="UserServlet" method="get"><a href="ReferFriend.jsp" name="submit" type="submit" value="Refer a Friend"><li>Block Writer</li></a></form>
                                    <%
                                        if(loggedInUser.getRoleID() == 4) {
                                    %>
                        <form action="UserServlet" method="get"><a href="LoginRegister.jsp" name="submit" type="submit" value="Add Editor"><li>Add Editor</li></a></form>
                                    <%}%>
                        <a href=Top10MostViewedBookesInACertainPeriod.jsp><li class="li3">Most Viewed Books</li></a>
                    </ul>
                </nav>
            </div>
            <div>
                <nav class="browse">
                    <ul class="bbh">
                        <a href=Top20MostRatedBooksOfTheMonth.jsp><li class="li3">Highest Rated Books</li></a>
                        <a href=Top20LikedBooksInAMonth.jsp><li class="li3">Most Liked Books</li></a>
                        <a href=Top3CategoriesInAMonth.jsp><li class="li3">Top Liked Categories</li></a>
                    </ul>
                </nav>
            </div>
            <div>
                <nav class="browse">
                    <ul class="bbh">
                        <a href=Top30Writers.jsp><li class="li3">Top Writers</li></a>
                        <a href=Top5WritersWithHighestRejections.jsp><li class="li3">Top Rejected Writers</li></a>
                        <a href=Top3HighestApprovingEditors.jsp><li class="li3">Top Approving Editors</li></a>
                    </ul>
                </nav>
            </div>

            <table class="stats">
                <tr id="title">
                    <th>Pending Stories</th>
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
                List<Story> pendingStories = (ArrayList<Story>)request.getAttribute("pendingStories");
                        
                if(pendingStories != null) {
                    for(Story story : pendingStories) {
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
                        <form action="StoryServlet" method="get">
                            <input class="buttonMain" name="story_id" type="hidden" value="<%=story.getStoryID()%>">
                            <input class="buttonMain" name="submit" type="submit" value="Display Story To Edit">
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td>
                        <label style="color:black">Here you can track all the pending stories.</label><br>
                    </td>
                </tr>
                <%
                }
                %>
            </table>
        </section>
    </body>
</html>
