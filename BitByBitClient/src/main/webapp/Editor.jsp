<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<!DOCTYPE html>
<html>
    <%
        String message = (String) request.getAttribute("message");
        Story story = (Story) request.getAttribute("storyReview");
        
        if(message != null){
        
    %>
    <h3 style = "color:green"><%=message%></h3>
    <%
        
    }if(story != null){
    
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

            <section class="main_content">
                <div class="side_nav">
                </div>

                <div>
                    <img src=images/title.png>
                    <h3 style = "color:black"> Writer: <%=writer%></h3>
                    <p style = "color:black">Title: <%=title%></br> </p>
                    <p style = "color:black">Story body: <%=body%></br> </p>

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




                    <%

                    }else{
                    %>
                    <h3>No stories to review</h3>
                    <%     
                         }


                    %>
                </div>




            </section>

    </body>


</html>
