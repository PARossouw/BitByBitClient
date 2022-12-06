<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<%@page import="Category.Model.Category;" %>
<%@page import="java.util.ArrayList"%>
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

    <body style="background-color:#1c1c1c">
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <section class="main_content">
                <div>

                    <p style="text-align:center;">  <img  src="images/pexels-steve-johnson-1269968.jpg" alt="Contribute a story" width="100%" height="400"> </p>

                    <form id="sectionForm" action="StoryServlet" method="get">
                        <div>
                            <label for="Categories" style="color:white">Select the categories you wish to search by.</label>

                        <%
                            List<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
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
                                    <label for="category" style="color:white"><%=categories.get(i).getName()%></label>
                                </td>

                                <%
                                    }//end for
                                %>
                            </tr>
                            <%
                                }//end if
                            %>
                        </table>

                        <input class="buttonMainGreen" name="submit" type="submit" value="Search">


                    </div>
                </form>
                <script src="CheckBox.js"></script>

            </div>






            <%
                
                    
 
                            List<Story> stories = (ArrayList<Story>) request.getAttribute("storiesCat");
                            
                            if(stories != null && stories.size()>0){
                                
            %>

            <div id="outer_wrapper">

                <div id="inner_wrapper">


                    <%
                                
                                
                                
                                
                                
                                
for (Story story : stories){       
                    %>

                    <div class="box">
                        <p style="text-align:center;">  <img  src="images/pexels-pixabay-267586.jpg" alt="Contribute a story" width="365" height="170"> </p>
                        <h3 style="color:white"><%=story.getTitle()%></h3>
                        <h5 style="color:white">Written by : <%=story.getWriter()%></h5>
                        <h5 style="color:white"><%=story.getDescription()%></h5>
                        <form action="StoryServlet" method="get">
                            <input class="buttonMain" name="story_id" type="hidden" value="<%=story.getStoryID()%>">
                            <input class="buttonMain" name="submit" type="submit" value="Story Info">
                        </form>
                    </div>

                    <%}}%>

                </div>
            </div>








        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
