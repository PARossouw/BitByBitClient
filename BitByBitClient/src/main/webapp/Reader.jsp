<%-- 
    Document   : Reader
    Created on : 17 Nov 2022, 20:36:57
    Author     : tarunsing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>

    <head>
        <title>Reader Home Page</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="dailystory.css">
    </head>


    <section class="banner" background-img src="images/storyOfTheDay.jpg">
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
                    <button type="button">Mike</button>
                </li>
            </ul>

        </nav>
        <div class="banner">


            <h3 style="color:black">Welcome to your Reader homepage</h3>

            <body>

                        <%
        String responseMessage2 = (String) request.getAttribute("user") ;
        %>            
        
        <%if(responseMessage2 != null){%>
        <div>
            <h3><%=responseMessage2%></h3>
        </div>
        <%}%>

         
                    <button class="button1" name="reader_choice" value="likes" >My LikesF</button>
  
                <a href=viewstory.html>
                    <button class="button2">My Categories</button>
                </a>

                <a href=viewstory.html>
                    <button class="button2">Become a Writer</button>
                </a>
                    
                          
     
        <form action="MyFirstServlet" method="post">
   
            <input class = "button1" name ="submit" type ="submit" value ="viewjoke" >
        </form> 
            
   





        </div>
    </section>

    <section class="main_content">
        <div class="side_nav">
        </div>

    </section>


</body>


</html>

