<!DOCTYPE html>
<%@page import="Story.Model.Story;"%>
<%@page import="Category.Model.Category;" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="RestClientRemoteController.RestClientStory"%>
<html>
    <head>
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <section class="banner">
                <div>
                    <h1>Welcome to Readers are Innovators</h1>
                    <p style="color:black">Making reading accessible to all.</p><br>
                    <form action="StoryServlet" method="post">


                        <input class="button1" name="submit" type="submit" value="Edit Story">



                        <input class="button1" name="submit" type="submit" value="Story of the Day">

                        <a href=DailyStory.jsp>
                            <button class="button2">View All Stories</button>
                        </a>
                    </form>

                    <form action="StoryServlet" method="get">

                        <input class="button1" name="submit" type="submit" value="View Story Get">

                        <input class="button1" name="submit" type="submit" value="Display Story To Edit">
                        
                        <input class="button1" name="submit" type="submit" value="Create Story">

                    </form>







                </div>
            </section>

            <section class="main_content">
                <div class="side_nav">
                    <nav>
                        <ul class="browse">
                            <li class="bbh">Browse</li>
                            <form action="StoryServlet" method="get">      
                                <input class="button1" name="submit" type="submit" value="Refresh Stories"><br><br>
                                <input class="button1" name="submit" type="submit" value="Your Preffered categories"><br><br>
                                <input class="button1" name="submit" type="submit" value="Your liked stories"><br><br>
                            </form>
                        </ul>
                    </nav>
                </div>


            <%

        String createStoryOutcome = (String) request.getAttribute("createStory");
            %>
            <%
                if(createStoryOutcome != null) {
            %>
            <div>
                <h3 style="color:red"><%=createStoryOutcome%></h3>
            </div>
            <%
                }
            %>

            <form action="StoryServlet" method="post">

                <input type="text" class="form" placeholder="Story title or author">
                <input class="button1" name="submit" type="submit" value=" Search for Story">

            </form>

            <div class="vid_list">
                
                <%
                RestClientStory rcs = new RestClientStory("http://localhost:8080/RIP/RIP");
                List<Story> stories = new ArrayList<>();
                stories = (List<Story>) request.getAttribute("stories");
                //List<Story> stories = null;
                if(stories == null){
                    stories = rcs.getStoriesForStoryOfTheDay();
                }
                if(stories.size()<1){
                    %>
                    <div>Sorry, no stories were found</div>
                    <%
                }
                    for(Story story : stories){
                %>

                <div>
                    <img src=<%=story.getImagePath()%>>
                    <h3 style="color:black"><%=story.getTitle()%></h3>
                    <h5 style="color:black">Written by : <%=story.getWriter()%></h5>
                    <h5 style="color:black">Views : <%=story.getViews()%></h5>
                    <h5 style="color:black">Likes : <%=story.getLikes()%></h5>
                    <h5 style="color:black">Rating : <%=story.getAvgRating()%></h5>
                    <li></li>
                    <p><%=story.getDescription()%></p>

                    <a href=viewStory.jsp>
                        <button class="button1">Read now</button>
                    </a>

                </div>

                <%
                    }//end for     
                %>

            </div>


        </section>
    </body>

</html>


