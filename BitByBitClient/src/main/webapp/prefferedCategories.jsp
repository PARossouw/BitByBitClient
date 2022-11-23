
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>
        <title>Login</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="registerlogin.css">
    </head>

    <body>
        <section class="header" background-img src="images/storyOfTheDay.jpg">
            <nav>
                <ul class="clearfix">
                    <li class="nav_logo">
                        <img src="images/open-book.png" class="logo_img" alt="Netflix Logo">

                    </li>
                    <li>
                        <a href=index.jsp>
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


            <h1>Displaying Category List</h1>
            <table border ="1" width="500" align="center">
                <tr bgcolor="00FF7F">


                </tr>
                <%-- Fetching the attributes of the request object
                     which was previously set by the servlet 
                      "StudentServlet.java"
                --%> 
                <%ArrayList<String> std = 
                    (ArrayList<String>)request.getAttribute("categoryList");
                for(String s:std){%>
                <%-- Arranging data in tabular form
                --%>
                <tr>
                    <td>

                        <input type="checkbox" id="vehicle3" name="vehicle3" value="Boat">
                        <label for="vehicle3" style="color:black"><%=s%></label><br>

                    </td>


                </tr>
                <%}%>
            </table> 
            <hr/>



        </section>




        <section class="main_content">
            <div class="side_nav">
            </div>

            <div>
                <p style="color:black" >Brought to you Bit by Bit<p>
            </div>




        </section>
    </body>

</html>
