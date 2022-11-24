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
        <section class="banner" background-img src="images/storyOfTheDay.jpg">
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
                        <button type="button">Profile</button>
                    </li>
                </ul>

            </nav>

            <div class="banner">

                
 
                    
                
                


                <%
                            Story story = (Story) request.getAttribute("story");
                            
                            String storyBody = "";
                            String title = "";
                            String rating = "";
                           String writer = ""; 
                            
                           
                            if(story != null)
                            {
                           storyBody = story.getBody();
                           title = story.getTitle();
                           rating = "Rating " + story.getAvgRating();
                            
                           writer = story.getWriter();
                            
                            //include avgrating and views
                }
                
                %>
                            
                           
                  <%          
                       Comment comment = (Comment) request.getAttribute("comment");
                       String commentBody = "";
                      if(comment != null)
                      {
                            commentBody = comment.getCommentBody();
                  }

                %>

                <h3 style="color:black"><%=title%></h3>
                <h5 style="color:black">Written by : <%=writer%></h5>
                <h5 style="color:black"><%=rating%></h5>

                <%--
                <a >
                    
                    
                    <input class="button1" name="submit" type="submit" value="submitCategories">
                </a>

                <a href=viewstory.html>
                    <button class="button2">Comment</button>
                </a>


                <a href=viewstory.html>
                    <button class="button2">Rate</button>
                </a>
                --%>

                <form action="StoryServlet" method="post">

                    <input class="button1" name="submit" type="submit" value="Like Story">


                    <a href=DailyStory.jsp>
                        <button class="button2">View All Stories</button>
                    </a>
                </form>

                
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




