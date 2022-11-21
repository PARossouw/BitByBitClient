

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
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

                        <button type="button">Categories</button>
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
            
            <div class="vid_list">
                <input type="text" class="form" placeholder="Search for a category">

                <%
                    List<Category> categories = request.getAttribute("categories");
                    if(categories.size()>0){
                        for(Category c : categories){
                            %>
                                <div>
                                    <h3><%=c.getName()%></h3>
                                    <button>View Stories</button>
                                    <button></button>
                                </div>
                            <%
                        }
                    }
                %>

            </div>

        </section>
    </body>
</html>
