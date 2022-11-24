<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>

    <head>
        <title>Reader Home Page</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <section class="banner" background-img src="images/storyOfTheDay.jpg">
                
                <div class="banner">


                    <h3 style="color:black">Welcome to your Reader homepage</h3>


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

