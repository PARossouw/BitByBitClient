<%-- 
import Story
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<%@page import="User_Interactions.Comment.Model.Comment;"%>
<!DOCTYPE html> 
<html>

    <head>
        <title>View Story</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="dailystory.css">
    </head>

    <body>
        <section class="header" background-img src="images/storyOfTheDay.jpg">
            <nav>
                <ul class="clearfix">
                    <li class="nav_logo">
                        <img src="images/open-book.png" class="logo_img" alt="Netflix Logo">

                    </li>
                    <li>

                        <a href=index.html>
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
                    <li>
                        <button type="button">Login/Register</button>
                    </li>
                </ul>

            </nav>
            <div class="h_div">
                
 
                    
                
                

                <%
                            Story story = (Story) request.getAttribute("story");
                            String storyBody = story.getBody();
                            String title = story.getTitle();
                            //include avgrating and views
                            
                            Comment comment = (Comment) request.getAttribute("comment");
                            String commentBody = comment.getCommentBody();
                            
                            String rating = "Rating " + story.getAvgRating();
                            
                            String writer = story.getWriter();
                            
                            
                            
                %>

                <h3 style="color:black"><%=title%></h3>
                <h5 style="color:black">Written by : <%=writer%></h5>
                <h5 style="color:black"><%=rating%></h5>



                <a href=dailystory.html>
                    <button class="button1">Like</button>
                </a>

                <a href=viewstory.html>
                    <button class="button2">Comment</button>
                </a>


                <a href=viewstory.html>
                    <button class="button2">Rate</button>
                </a>



            </div>
        </section>

        <section class="main_content">
            <div class="side_nav">
            </div>

            <div>

                <form action="StoryServlet" method="post">
                    <p style="color:black">


                        <%
                            if(storyBody != null) {
                        
                        %>
                    <div>
                        <h1 style="color:black"><%=title%></h1>
                        <h3 style="color:black"><%=storyBody%></h3>
                        <h5 style="color:black"><%=commentBody%></h5>
                    </div>
                    <%
                        }
                    %>

                    <p>
                </form>





            </div>




        </section>
    </body>

</html>




