

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<%@page import="Category.Model.Category;" %>
<%@page import="java.util.ArrayList"%>
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
        <section class="banner" background-img src="images/h_bg1.jpg">
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

                <form action="StoryServlet" method="post">
                    <div>
                        <label for="Categories">Select the categories you wish to search by.</label>

                        <%
                            ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
                            if(categories != null && categories.size()>0){
                        %>
                        <table>
                            <tr>
                                <%
                                    for(int i = 0; i < categories.size(); i++){
                                        if(i % 7 == 0){
                                %>
                            </tr>

                            <tr>
                                <%
                                    }//end if
                                %>
                                <td>
                                    <input type="checkbox" name="category" value="<%=i%>">
                                    <label for="category" style="color:black"><%=categories.get(i).getName() + " "+i%></label>
                                </td>
                            </tr>

                            <%
                                    }//end for
                                }//end if
                            %>
                        </table>

                        <input class="button1" name="submit" type="submit" value="Search">


                    </div>
                </form>
                <%
                    ArrayList<Story> stories = (ArrayList<Story>) request.getAttribute("stories");
                %>

                <li></li>
                <div class="story_list">
                    <%
                if(stories != null && stories.size()>0){
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

                </div>
                <%
            }
        }
                %>



        </section>
    </body>
</html>
