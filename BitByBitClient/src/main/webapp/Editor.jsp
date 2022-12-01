<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
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

        <section class="banner_common">

            <form action="StoryServlet" method="get">
                <input class="button1" name="submit" type="submit" value="Review Story">
            </form>
            <form action="StoryServlet" method="get">
                <input class="button1" name="submit" type="submit" value="Choose Story of The Day">
            </form>
            <!--<form action="UserServlet" method="get">-->
            <a href="BlockWriter.jsp">
                <input class="button1" name="submit" type="submit" value="Block Writer">
            </a>
            <!--</form>-->
            <div class="h_div">
                <h1>Approve and Reject Stories</h1>

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
                        <!--                        <br>
                                                <h1>Choose a Story of the day</h1>-->

                    </div>





                </section>

                </body>


                </html>
