<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<%@page import="Category.Model.Category;"%>
<%@page import="User_Interactions.Comment.Model.Comment;"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="User.Model.User;"%>
<!DOCTYPE html> 
<html>



    <head>
        <title>Create a new Short Story</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>
    
   

    <body>
        <jsp:include page="header.jsp"></jsp:include>
         <p style="text-align:center;">  <img  src="images/pexels-steve-johnson-1843716.jpg" alt="Create a story" width="100%" height="400"> </p>

    <body style="background-color:#1c1c1c">
            <form action="StoryServlet" method="post" enctype="multipart/form-data">
                

            <%
String createStoryOutcome = (String) request.getAttribute("createStory");
            %>
            <%
                if(createStoryOutcome != null) {
            %>
            <div>
                <h3 style="color:red"><%=createStoryOutcome%></h3>
            </div>
            <%
                }
            %>

<p align="center">
            <label for="text" style="color:white">Story Title</label>
            <br>
            <textarea id="text" name="StoryTitle" rows="2" cols="80" ></textarea >
            <br/>

            <p align="center">
            <label for="text"style="color:white" >Story Description</label>
            <br>
            <textarea id="text" name="StoryDescription" rows="5" cols="80"></textarea>
            <br/>

<p align="center">
            <label for="photo" style="color:white" >Choose a photo!</label>

            <input 
                type="file"
                id="photo" 
                name="file"
                accept ="image/*"
                 class = "buttonMainGreen">
           

            <br>

<p align="center">
            <label for="text" style="color:white" >Body of Story</label>
            <br>
            <textarea id="text" name="StoryBody" rows="60" cols="80"></textarea>
            <br/>

<p align="center">
            <h4 style="color:white">Please select the categories of which the story falls under.</h4>
            <table border ="1" width="500" align="center">
                <tr bgcolor="00FF7F">


                </tr>
                <%List<Category> std = 
                    (ArrayList<Category>)request.getAttribute("categoryList");
                      int chosenCategories = 0;  
                     if(std.size() != 0)
                     {
                        
                for(int i = 0; i<std.size() ; i++){
                
            
                %>

                <tr>
                    <td>

                        <%
                            String variableName = ""+i;
                        %>

    
      

                        <input type="checkbox" value ="<%=variableName%>" name ="category">
                        <label for="vehicle3" style="color:white"><%=std.get(i).getName()%></label><br>
                        <%
                            }%>
                    </td>


                </tr>
                <%}%>
                




            </table> 
            <hr/>
            




             <input class="buttonMain" name="submit" type="submit" value="Save Changes For New Story" >
            

            <input class="buttonMainGreen" name="submit" type="submit" value="Submit For Review">
            <br></br>
        </form>



        <div>


        </div>
    </section>

    <section class="main_content">
        <div class="side_nav">
        </div>




    </section>
    <jsp:include page="footer.jsp"></jsp:include>
</body>

</html>




