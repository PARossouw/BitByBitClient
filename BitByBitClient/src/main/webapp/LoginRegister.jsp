<%-- 
    Document   : LoginRegister
    Created on : 17 Nov 2022, 10:38:52
    Author     : tarunsing, pieterrossouw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html> 
<html>

    <head>
        <title>Login</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="dailystory.css">
    </head>

    <body>
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
                         <button type="button">Login/Register</button>
                    </li>
                </ul>
               
            </nav>
            <div class="h_div">
              
                
               
                <h3 style="color:black">Login</h3>
                <input type="text" class="form" placeholder="Username">
                <br>
                </br>
                <input type="text" class="form" placeholder="Password">
                <br>
                </br>
                <a href=dailystory.html>
                    <button class="button1">Login</button>
                </a>
                
                <h3 style="color:black">Register</h3>
                <input type="text" class="form" placeholder="Username">
                <input type="text" class="form" placeholder="Password">
                <input type="text" class="form" placeholder="Confirm Password">
                
                <a href=viewstory.html>
                   <button class="button2">Register</button>
                </a>
                

            </div>
        </section>

        <section class="main_content">
            <div class="side_nav">
            </div>

            <div>
                <p style="color:black" >Created by: 
                    Anton Metrowich 
                    Pieter Rossouw 
                    Ryan Jurisich 
                    Tarun Sing<p>
            </div>
              
                    
           

        </section>
    </body>

</html>
