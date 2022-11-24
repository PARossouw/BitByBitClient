<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<!DOCTYPE html>
<html>
    <%
        Story story = (Story) request.getAttribute("storyReview");
    
        String writer = story.getWriter();
        String title = story.getTitle();
        String body = story.getBody();
    
    
    
    
    %>


    <head>
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
<!--        <section class="header" background-img src="images/storyOfTheDay.jpg">
            
            <div class="h_div">

                <h3 style="color:black">Jumpman</h3>
                <h5 style="color:black">Written by : Micheal Jordon</h5>



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
        </section>-->

        <section class="main_content">
            <div class="side_nav">
            </div>

            <div>
                <img src=images/title.png>
                <h3><%=writer%></h3>
                <p><%=title%></br> </p>
                <p><%=body%></br> </p>
                
                 <form action="StoryServlet" method="post">
                            <a>
                                <input class="button1" name="submit" type="submit" value="Approve">
                                <input class="button1" name="submit" type="submit" value="Reject">
                            </a>
                 </form>
                 <form action="StoryServlet" method="get">
                            <a>
                                <input class="button1" name="submit" type="submit" value="Next Story">
                            </a>
                 </form>
                
                
                
            </div>




        </section>
    </body>





</html>
