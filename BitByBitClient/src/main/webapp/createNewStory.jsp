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

        <body style="background-color:aquamarine;">
            <form action="StoryServlet" method="post" enctype="multipart/form-data">
                <h1 style="color:black">Edit a short story</h1>

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


            <label for="text">Story Title</label>
            <br>
            <textarea id="text" name="StoryTitle" rows="2" cols="80" ></textarea >
            <br/>

            <label for="text">Story Description</label>
            <br>
            <textarea id="text" name="StoryDescription" rows="5" cols="80"></textarea>
            <br/>


            <label for="photo">Choose a photo!</label>

            <input 
                type="file"
                id="photo" 
                name="file"
                accept ="image/*"
                 >

            <br>


            <label for="text">Body of Story</label>
            <br>
            <textarea id="text" name="StoryBody" rows="60" cols="80"></textarea>
            <br/>


            <h4 style="color:black">Please select the categories of which the story falls under.</h4>
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
                        <label for="vehicle3" style="color:black"><%=std.get(i).getName()%></label><br>
                        <%
                            }%>
                    </td>


                </tr>
                <%}%>
                




            </table> 
            <hr/>
            




             <input class="button2" name="submit" type="submit" value="Save Changes For New Story" >
            

            <input class="button1" name="submit" type="submit" value="Submit For Review">
            <br></br>
        </form>



        <div>


        </div>
    </section>

    <section class="main_content">
        <div class="side_nav">
        </div>




    </section>
</body>

</html>




