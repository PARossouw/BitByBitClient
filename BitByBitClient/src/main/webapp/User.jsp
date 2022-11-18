<%-- 
    Document   : HomeReader
    Created on : 17 Nov 2022, 09:45:06
    Author     : tarunsing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>

    <head>
        <title>User Home Page</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="dailystory.css">
    </head>


    <section class="header" background-img src="images/storyOfTheDay.jpg">
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
                    <button type="button">Paul</button>
                </li>
            </ul>

        </nav>
        <div class="h_div">


            <h3 style="color:black">Welcome to your user homepage</h3>

            <body>



            <h5 style="color:black">Readers Options</h5>
                <a href=dailystory.html>
                    <button class="button1">My Likes</button>
                </a>

                <a href=viewstory.html>
                    <button class="button2">My Categories</button>
                </a>

                <a href=viewstory.html>
                    <button class="button2">Become a Writer</button>
                </a>

                <%-- 
Writers Functionality. 
                JSP should remove become a writer from above. 
                --%>

                <div>

                    <br>   </br>
                    <h5 style="color:black">Writers Options</h5>


                    <a href=dailystory.html>
                        <button class="button1">My Likes</button>
                    </a>

                    <a href=viewstory.html>
                        <button class="button2">My Categories</button>
                    </a>

                    <a href=dailystory.html>
                        <button class="button1">My Drafts</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">Create Story</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">My Approved Stories</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">My Rejected Stories</button>
                    </a>

                    <a href=viewstory.html>
                        <button class="button2">My Pending Stories</button>
                    </a>


                </div>


                <div>

                    <br>   </br>
                    <h5 style="color:black">Editor Options</h5>
                    <a href=dailystory.html>
                        <button class="button1">Pending Stories</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">Approved Stories</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">Rejected Stories</button>
                    </a>

                </div>


                <div>

                    <br>   </br>
                    <h5 style="color:black">Admin Editor Options</h5>
                    <a href=dailystory.html>
                        <button class="button1">Pending Stories</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">Approved Stories</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">Rejected Stories</button>
                    </a>

                    <a href=viewstory.html>
                        <button class="button2">Create Editor</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">Create Category</button>
                    </a>


                    <a href=viewstory.html>
                        <button class="button2">Statistics</button>
                    </a>

                </div>









        </div>
    </section>

    <section class="main_content">
        <div class="side_nav">
        </div>

    </section>
                
 
</body>


</html>



