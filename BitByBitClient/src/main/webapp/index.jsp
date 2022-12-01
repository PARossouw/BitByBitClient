<!DOCTYPE html>
<%@page import="Story.Model.Story"%>
<%@page import="User.Model.User"%>
<%@page import="Category.Model.Category" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="RestClientRemoteController.RestClientStory"%>
<html>
    <head>c
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <style type="text/css">
        #outer_wrapper {
            overflow: scroll;
            width:100%;
        }
        #outer_wrapper #inner_wrapper {

            width:20000px; /* If you have more elements, increase the width accordingly /
        }
        #outer_wrapper #inner_wrapper div.box { / Define the properties of inner block */

            width: 400px;
            height:300px;
            float: left;
            margin: 0 4px 0 0;
            border:1px grey solid;
        }
    </style>

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

            <form action="StoryServlet" method="post" style="inline-block;width: 75%">
                <input type="text" class="form" placeholder="Story title or author">
                <input class="button1" name="submit" type="submit" value=" Search for Story">
            </form>
            <div id="outer_wrapper">
                <div id="inner_wrapper">
                    <%
                    RestClientStory rcs = new RestClientStory("http://localhost:8080/RIP/RIP");
                    List<Story> stories = new ArrayList<>();
                    List<Story> storiesMostRated = new ArrayList<>();
                    //stories = (List<Story>) request.getAttribute("stories");
                    //List<Story> stories = null;
                    //if(stories == null){
                    User user = new User();
                
                    user = (User)request.getAttribute("loggedInUser");
                
                    storiesMostRated = rcs.getTop20StoriesForMonth();
                
                    if(user != null){
                    stories = rcs.searchStoriesByCategories(user);
                    }
                    //}
                    %>
                    <%----------------------------------------------------------------------------------------------------------------------------%>
                    <h3>Stories by Preferred Categories</h3>
                    <%
                    if(user == null){
                    %>
                    <a href="LoginRegister.jsp">Login to see stories from your preffered categories</a>
                    <%
                        }else
                        if(stories.size()<1){
                    %>
                    <a>No stories found from your preffered categories</a>
                    <%
                } else{
                 for(Story story : stories){
                    %>

                    <div class="box">
                        <li>
                            <img src=<%=story.getImagePath()%>>
                            <h3 style="color:black"><%=story.getTitle()%></h3>
                            <h5 style="color:black">Written by : <%=story.getWriter()%></h5>
                            <h5 style="color:black">Views : <%=story.getViews()%></h5>
                            <h5 style="color:black">Likes : <%=story.getLikes()%></h5>
                            <h5 style="color:black">Rating : <%=story.getAvgRating()%></h5>
                            <p><%=story.getDescription()%></p>

                            <a href=viewStory.jsp>
                                <button class="button1">Read now</button>
                            </a>
                        </li>
                    </div>

                    <%
                        }//end for  
    }//end else
                    %>

                </div>
            </div>

			
                <%----------------------------------------------------------------------------------------------------------------------------%>
				<div id="outer_wrapper">
                <div id="inner_wrapper">

			  <h3>Top 20 rated stories of the month</h3>
                <%
                    if(storiesMostRated.size()<1){
                %>
                <a>No stories found.</a>
                <%
            } else{
             for(Story story : storiesMostRated){
                %>

                <div class="box">

                    <li>
                        <img src=<%=story.getImagePath()%>>
                        <h3 style="color:black"><%=story.getTitle()%></h3>
                        <h5 style="color:black">Written by : <%=story.getWriter()%></h5>
                        <h5 style="color:black">Views : <%=story.getViews()%></h5>
                        <h5 style="color:black">Likes : <%=story.getLikes()%></h5>
                        <h5 style="color:black">Rating : <%=story.getAvgRating()%></h5>
                        <p><%=story.getDescription()%></p>

                        <a href=viewStory.jsp>
                            <button class="button1">Read now</button>
                        </a>
                    </li>

                <%
                    }//end for  
    }//end else
                %>
		</div>
            </div>
            <%----------------------------------------------------------------------------------------------------------------------------%>

        </section>
    </body>

</html>
