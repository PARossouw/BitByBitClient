<!DOCTYPE html>
<%@page import="Story.Model.Story"%>
<%@page import="User.Model.User"%>
<%@page import="Category.Model.Category" %>
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

            <form action="StoryServlet" method="post" style="inline-block width: 75%">
                <input type="text" class="form" placeholder="Story title or author">
                <input class="button1" name="submit" type="submit" value=" Search for Story">
            </form>


            <%--<div class="vid_list">--%>
            <div id="outer_wrapper">
                <div id="inner_wrapper">
                    <%
                    RestClientStory rcs = new RestClientStory("http://localhost:8080/RIP/RIP");
                    List<Story> stories = new ArrayList<>();
                    stories = (List<Story>) request.getAttribute("stories");
                    //List<Story> stories = null;
                    //if(stories == null){
                        stories = rcs.getStoriesForStoryOfTheDay();
                    //}
            
            
                    if(stories.size()<1){
                    %>
                    <div>Sorry, no stories were found</div>

                    <%
                }
                    for(Story story : stories){
                    %>

                    <div class="box">

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
            </div>


            <div >

                <%
                rcs = new RestClientStory("http://localhost:8080/RIP/RIP");
                stories = new ArrayList<>();
                //List<Story> storiesMostRated = new ArrayList<>();
                //stories = (List<Story>) request.getAttribute("stories");
                //List<Story> stories = null;
                //if(stories == null){
                User user = new User();
                
                user = (User)request.getAttribute("loggedInUser");
                
                //storiesMostRated = rcs.getTop20StoriesForMonth();
                
                if(user != null){
                stories = rcs.searchStoriesByCategories(user);
                }
                %>
                <%----------------------------------------------------------------------------------------------------------------------------%>
                <h3>Stories by Preferred Categories</h3>
                <%
                if(user == null){
                %>
                <a href="LoginRegister.jsp">Login to see stories from your preffered categories</a>
                <%}
                    if(stories.size()<1 || user == null){
                %>
                <a>No stories found from your preffered categories</a>
                <%
            } else{
             for(Story story : stories){
                %>

                <div>
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
                </div>

                <%
                    }//end for  
}//end else
                %>

            </div>

            <h3>Top rated stories of the month</h3>
            <%
                if(stories.size()<1){
            %>
            <a>No stories found.</a>
            <%
        } else{
         for(Story story : stories){
            %>


            <div>
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
            </div>

            <%
                }//end for  
}//end else
            %>

            <h3>Stories by Preferred Categories</h3>
            <%
            if(user == null){
            %>
            <a href="LoginRegister.jsp">Login to see stories from your preffered categories</a>
            <%}
                if(stories.size()<1){
            %>
            <a>No stories found from your preffered categories</a>
            <%
        } else{
         for(Story story : stories){
            %>

            <div>
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
            </div>

            <%
                }//end for  
    }//end else
            %>

        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>