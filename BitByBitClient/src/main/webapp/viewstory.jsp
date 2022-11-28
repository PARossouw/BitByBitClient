<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<%@page import="User_Interactions.Comment.Model.Comment;"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html> 
<html>

    <head>
        <title>View Story</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <body style="background-color:aquamarine;">

        <%
                    Story story = (Story) request.getAttribute("story");
                            
                    String storyID = "";
                    String storyBody = "";
                    String title = "";
                    String rating = "";
                   String writer = ""; 
                   String description = "";
                   String views = "";
                   String likes = "";
                           
                            
                           
                    if(story != null)
                    {
                    storyID = ""+ story.getStoryID();
                   storyBody = story.getBody();
                   title = story.getTitle();
                   rating = "Rating : " + story.getAvgRating();
                   writer = story.getWriter();
                   description = story.getDescription();
                   views = "Views : "+ story.getViews();
                   likes = "Likes : " + story.getLikes();
        }
                
        %>


 

        <h2 style="color:black"><%=title%></h2>
        <h5 style="color:black">Author : <%=writer%></h5>
        <h5 style="color:black"><%=rating%></h5>
        <h5 style="color:black"><%=views%></h5>
        <h5 style="color:black"><%=likes%></h5>
        <h5 style="color:black"><%=description%></h5>


        <%
 String responseMessageRegister = (String) request.getAttribute("likes");
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
        
        <h3 style="color:purple">Previous Comments</h3>
                <%
                    
                    List<Comment> allComments = 
                    (ArrayList<Comment>)request.getAttribute("comment");

            if(allComments != null)
            {
            
            for(int i = 0; i<allComments.size(); i++)
            {
            
                  //commentBody = comment.getCommentBody();
                  %>
                  
                  <h5 style="color:purple"><%=allComments.get(i).getCommentBody()%></h5>
                  <%
        }}
      
         %>
        

        <%
String userToComment = (String) request.getAttribute("optsToComment");
        %>
        <%
            if(userToComment != null) {
        %>
        <div>
            <form action="StoryServlet" method="post">
                <input class="button1" name="story_id" type="hidden" value="<%=storyID%>">
                <label for="text">Please enter a comment below</label>
                <br>
                <textarea id="text" name="storyComment" rows="2" cols="80" ></textarea >
                <br/>
                <input class="button1" name="submit" type="submit" value="SubmitComment">
            </form>

        </div>
        <%
            }
        %>
        

        


        <form action="StoryServlet" method="post">

            <input class="button1" name="story_id" type="hidden" value="<%=storyID%>">
            <input class="button1" name="submit" type="submit" value="Like">
            <input class="button1" name="submit" type="submit" value="Comment">


        </form>


        <form action="StoryServlet" method="post">
            <br></br>
            <input class="button1" name="story_id" type="hidden" value="<%=storyID%>">
            <input type="radio"  name="user_Rating" value="1">
            <label for="1">1 Star</label>
            <input type="radio"  name="user_Rating" value="2">
            <label for="2">2 Star</label>
            <input type="radio"  name="user_Rating" value="3">
            <label for="3">3 Star</label>
            <input type="radio"  name="user_Rating" value="4">
            <label for="4">4 Star</label>
            <input type="radio"  name="user_Rating" value="5">
            <label for="5">5 Star</label>
            <input name="submit" type="submit" value="Rate">
            <br></br>
        </form> 

        <h4 style="color:black"><%=storyBody%></h4>




    </div>
</section>

<div>


</div>

</section>
</body>

</html>




