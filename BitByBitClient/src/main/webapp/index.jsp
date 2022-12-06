<!DOCTYPE html>
<%@page import="Story.Model.Story"%>
<%@page import="User.Model.User"%>
<%@page import="Category.Model.Category" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="RestClientRemoteController.RestClientStory"%>
<html>
    <%
        
        RestClientStory rcsStoryOfTheDay = new RestClientStory("http://localhost:8080/RIP/RIP");
    
        Story storyOfTheDay = new Story();
        storyOfTheDay = rcsStoryOfTheDay.getStoryOfTheDay();
    
    %>
    <head>
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body style="background-color:#1c1c1c">



    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <section class="banner">
                <div>
                    <h1>Welcome to Readers are Innovators</h1>
                    <p style="color:black">Making reading accessible to all.</p><br>
                    <!--                    <form action="StoryServlet" method="post">
                                            <input class="button1" name="submit" type="submit" value="Edit Story">
                                            <input class="button1" name="submit" type="submit" value="Story of the Day">
                                            <a href=DailyStory.jsp>
                                                <button class="button2">View All Stories</button>
                                            </a>
                    -->

                <%
User userLoggedInRole = (User)request.getAttribute("loggedInUser");
if(userLoggedInRole != null)
{
int userLoggedInRoleID = userLoggedInRole.getRoleID();
                }


                %>
                <form action="StoryServlet" method="post">
                    <input class="button1" name="role_id" type="hidden" value="3">
                
                   <input class="buttonMain" name="story_id" type="hidden" value="<%=storyOfTheDay.getStoryID()%>">
                    <input class="buttonMainBig" name="submit" type="submit" value="Read Story Of The Day">
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
                <h3 style="color:red" ><%=createStoryOutcome%></h3>
            </div>
            <%
                }
            %>
            <!--
                        <form action="StoryServlet" method="post" style="inline-block width: 75%">
                            <input type="text" class="form" placeholder="Story title or author">
                            <input class="button1" name="submit" type="submit" value="Search for Story">
                        </form>
            -->

            <form action="StoryServlet" method="post" style="inline-block width: 75%">
                <input type="text" class="form" name ="titleOrAuthor" placeholder="Story Title or Author" required>
                <input class="buttonMainGreen" name="submit" type="submit" value="Search for Story">
            </form>


            <%
                 List<Story> searchedStories =
                         (ArrayList<Story>)request.getAttribute("storiesSearchedFor");
            %>

            <%
    
            if(searchedStories != null)
            {
     
for (Story story : searchedStories){       
            %>

            <div class="box">
                <h3 style="color:white"><%=story.getTitle()%></h3>
                <h5 style="color:white">Written by : <%=story.getWriter()%></h5>
                <h5 style="color:white">Views : <%=story.getViews()%></h5>
                <h5 style="color:white">Likes : <%=story.getLikes()%></h5>
                <h5 style="color:white">Rating : <%=story.getAvgRating()%></h5>
                <li></li>
                <p style="color:white"><%=story.getDescription()%></p>
                <form action="StoryServlet" method="get">
                    <input class="buttonMain" name="story_id" type="hidden" value="<%=story.getStoryID()%>">
                    <input class="buttonMain" name="submit" type="submit" value="Story Info">
                </form>
            </div>


            <




            <%}}

            %>
















            <%--<div class="vid_list">--%>
            <%
            RestClientStory rcs = new RestClientStory("http://localhost:8080/RIP/RIP");
            List<Story> stories = new ArrayList<>();
            User user = new User();
                
       User userLoggedIn = (User)request.getAttribute("loggedInUser");
       String username = "";
       
       if(userLoggedIn != null) {
       username = userLoggedIn.getUsername();
               if(userLoggedIn.getRoleID()<3){
            %>
            <div id="outer_wrapper">
                <h3 style="color:white">Stories Based On Your Preferred Categories</h3>
                <div id="inner_wrapper">
                    <%
               
//                   user.setUserID(8778);
//                   user.setUsername("Mike");
//                   user.setRoleID(1);
                
                //storiesMostRated = rcs.getTop20StoriesForMonth();
                
                
                    stories = rcs.searchStoriesByCategories(userLoggedIn);
                
                
            
            
                    if(stories.size()<1){
                    %>
                    <div>Sorry, no stories were found</div>




                    <%
                }
                    for(Story story : stories){
                    %>

                    <div class="box">


                        <p style="text-align:center;">  <img  src="images/pexels-pixabay-267586.jpg" alt="Contribute a story" width="365" height="170"> </p>
                       <!--<img src=<%=story.getImagePath()%>>-->
                        <p align="center">
                        <h3 style="color:white"><%=story.getTitle()%></h3>
                        <h5 style="color:white">Author : <%=story.getWriter()%></h5>
                        <p style="color:white"><%=story.getDescription()%></p>
                        <form action="StoryServlet" method="get">
                            <input class="buttonMain"  name="story_id" type="hidden" value="<%=story.getStoryID()%>">
                            <input class="buttonMain"   name="submit" type="submit" value="Story Info">
                        </form>

                    </div>



                    <%
                        }//end for  
                    
                    %>
                </div>


            </div>
                <%}%>
            <%}%>

            <div id="outer_wrapper">
                <h3 style="color:white">Top Rated Stories</h3>
                <div id="inner_wrapper">


                    <%      RestClientStory rcsTop = new RestClientStory("http://localhost:8080/RIP/RIP");
                      List<Story> storiesTopStories = new ArrayList<>();
                      User userTop = new User();
                
                 // userTop = (User)request.getAttribute("loggedInUser");
//                                     userTop.setUserID(8778);
//                     userTop.setUsername("Mike");
//                     userTop.setRoleID(1);
                   
  //                   storiesTryNewStories = rcs.getRandomApprovedStories();
                     storiesTopStories = rcsTop.getTop20StoriesForMonth();
                   
                    
                    
                    
                    %>


                    <%----------------------------------------------------------------------------------------------------------------------------%>

                    <%
                    if(user == null){
                    %>
                    <a href="LoginRegister.jsp">Login to see stories from your preffered categories</a>
                    <%}
                        if(storiesTopStories.size()<1 || user == null){
                    %>



                    <%
                } else{
                 for(Story story : storiesTopStories){
                    %>

                    <div class="box">
                        <p style="text-align:center;">  <img  src="images/pexels-pixabay-415071.jpg" alt="Contribute a story" width="365" height="170"> </p>
<!--<img src=<%=story.getImagePath()%>>-->
                        <p align="center">
                        <h3 style="color:white"><%=story.getTitle()%></h3>
                        <h5 style="color:white">Author : <%=story.getWriter()%></h5>
                        <p style="color:white"><%=story.getDescription()%></p>


                        <form action="StoryServlet" method="get">
                            <input class="buttonMain" name="story_id" type="hidden" value="<%=story.getStoryID()%>">
                            <input class="buttonMain" name="submit" type="submit" value="Story Info">
                        </form>
                    </div>

                    <%
                        }//end for  
    }//end else
                    %>

                </div>
            </div>

            <div id="outer_wrapper">
                <h3 style="color:white">Recommended Reads</h3>
                <div id="inner_wrapper">

                    <%
                        RestClientStory rcs3 = new RestClientStory("http://localhost:8080/RIP/RIP");
                    List<Story> storiesTryNewStories = new ArrayList<>();
                    User user2 = new User();
                
               // user = (User)request.getAttribute("loggedInUser");
//                                   user2.setUserID(8778);
//                   user2.setUsername("Mike");
//                   user2.setRoleID(1);
                   
                   storiesTryNewStories = rcs3.getRandomApprovedStories();

                        if(storiesTryNewStories.size()<1){
                    %>
                    <a>No stories found.</a>
                    <%
                } else{
                 for(Story storyNew : storiesTryNewStories){
                    %>


                    <div class="box">
                        <p style="text-align:center;">  <img  src="images/pexels-stas-knop-1340588.jpg" alt="Contribute a story" width="365" height="170"> </p>
<!--<img src=<%=storyNew.getImagePath()%>>-->
                        <p align="center">
                        <h3 style="color:white"><%=storyNew.getTitle()%></h3>
                        <h5 style="color:white">Author : <%=storyNew.getWriter()%></h5>
                        <p style="color:white"><%=storyNew.getDescription()%></p>


                        <form action="StoryServlet" method="get">
                            <input class="buttonMain" name="story_id" type="hidden" value="<%=storyNew.getStoryID()%>">
                            <input class="buttonMain" name="submit" type="submit" value="Story Info">
                        </form>
                    </div>

                    <%
                        }//end for  
        }//end else
                    %>


                </div>
            </div>

        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>