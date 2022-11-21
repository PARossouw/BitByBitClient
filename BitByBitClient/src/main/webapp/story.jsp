

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stories</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>
    <body>
        <section class="header" background-img src="images/h_bg1.jpg">
            <nav>
                <ul class="clearfix">
                    <li class="nav_logo">
                        <img src="images/open-book.png" class="logo_img" alt="Netflix Logo">

                    </li>
                    <li>
                        <a href=index.html>
                            <button class="button3">Home</button>
                        </a>

                    </li>
                    <li>

                    </li>
                    <li>                       
                        <button type="button">Refer a Friend</button>
                    </li>
                    <li>                       
                        <button type="button">Contact Us</button>
                    </li>
                    <li>

                        <a href=User.jsp>
                            <button type="button">Login/Register</button>
                        </a>




                    </li>
                </ul>

            </nav>

        </section>

        <section class="main_content">

            <div>
                <input type="text" class="form" placeholder="Search for a category">

                <form action="CategoryServlet" method="get">
                    <div>
                        <label for="Categories">Select the categories you wish to search by. You may control-click (Windows) or command-click (Mac) to select more than one</label>

                        <%
                            List<String> categories = (List<String>) request.getAttribute("categories");
                            if(categories != null && categories.size()>0){
                        %>
                        <select multiple size = "<%categories.size();%>" name="Categories" id="Categories">
                            <%
                                for(int i = 0; i < categories.size(); i++){
                            %>

                            <option value="<%=i%>"><%=categories.get(i)%></option>
                            <%
                                    }//end for
                                }//end if
                            %>
                        </select>


                        <h3 style="color:black">Search</h3>

                        <input type="text" class="form" placeholder="search">

                        <input class="button1" name="submit" type="submit" value="Search">
                        </form>

                    </div>
                    <%
                        List<Story> stories = (List<Story>) request.getAttribute("stories");
                        if(stories != null && stories.size()>0){
                            for(Story story : stories){
                    %>
                    <section class="main_content">

                        <div class="story_list">

                            <div>
                                <img src=<%=story.getImagePath%>>
                                <h3 style="color:black"><%=story.getTitle%></h3>
                                <h5 style="color:black">Written by : <%=story.getWriter%></h5>
                                <h5 style="color:black">Views : <%=story.getViews%></h5>
                                <h5 style="color:black">Likes : <%=story.getLikes%></h5>
                                <h5 style="color:black">Rating : <%=story.getAvgRating%></h5>
                                <li></li>
                                <p><%=story.getDescription%></p>
                                
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

                        </div>
                        <%
                    }
                }
                        %>



                    </section>
                    </body>
                    </html>
