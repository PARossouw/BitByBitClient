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
        <title>Edit Story</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="css/scrollWheel.css">
    </head>





    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <body style="background-color:#151515">
            <p style="text-align:center;">  <img  src="images/pexels-pixabay-267586.jpg" alt="Contribute a story" width="100%" height="400"> </p>




            <form id="sectionForm" action="StoryServlet" method="post" enctype="multipart/form-data">




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
            <%
                           Story story = (Story) request.getAttribute("story");                          
                           String storyBody = "";
                           String title = "";
                           String description = "";
                           List<Category> userCategoryList = null;               
                           
                           if(story != null)
                           {
                          storyBody = story.getBody();
                          title = story.getTitle();
                          description = story.getDescription();     
                          userCategoryList =  (ArrayList<Category>)story.getCategoryList();
                         // userCategoryList = story.getCategoryList();
               }            
            %>


            <p align="center">

                <label for="text" style="color:white">Story Title</label>
                <br>
                <textarea id="text" name="StoryTitle" rows="2" cols="80" required><%=title%> </textarea >
                <br>
            <p style="line-height:3.5em;"></p>

            <p align="center">
                <label for="text" style="color:white">Story Description</label>
                <br>
                <textarea id="text" name="StoryDescription" rows="5" cols="80" required><%=description%> </textarea>
                <br>

            <p style="line-height:3.5em;"></p>

            <p align="center">



                <label for="text" style="color:white">Body of Story</label>
                <br>
                <textarea id="text" name="StoryBody" rows="60" cols="80" style="color:black" required><%=storyBody%> </textarea>
                <br>
            <p style="line-height:3.5em;"></p>
            <p align="center">
                <label for="photo" style="color:white">Choose a photo!</label>

                <input 
                    type="file"
                    id="photo" 
                    name="file"
                    accept ="image/*"
                    class ="buttonMainGreen"
                    >

                <br>
            <p style="line-height:3.5em;"></p>


            <h4 style="color:white" align="center">Please select the categories of which the story falls under.</h4>
        </p>
        <table border ="1" width="500" align="center">
            <tr bgcolor="00FF7F">


            </tr>
            <%List<Category> std = 
                (ArrayList<Category>)request.getAttribute("categoryList");
                  int chosenCategories = 0;  
                 if(userCategoryList.size() != 0)
                 {
                        
            for(int i = 0; i<std.size() ; i++){
                
            for(int j = 0 ; j<userCategoryList.size() ; j++)
            {
            %>

            <tr>
                <td>

                    <%
                        String variableName = ""+i;
                    %>

                    <%
                        if(std.get(i).getName().equals(userCategoryList.get(j).getName()))
                        {
                    %>
                    <input type="checkbox" value ="<%=variableName%>" name ="category" checked>
                    <label for="vehicle3" style="color:white"><%=std.get(i).getName()%></label><br>
                    <%}

                        else{
                    %>

                    <input type="checkbox" value ="<%=variableName%>" name ="category">
                    <label for="vehicle3" style="color:white"><%=std.get(i).getName()%></label><br>
                    <%
                            }%>
                </td>


            </tr>
            <%}}}
                
    else{
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
                            }}%>
                </td>


            </tr>

        </table> 
        <hr/>

        <%
            User loggedInUser = (User) request.getAttribute("user");
            if(loggedInUser.getRoleID() > 2) {
        %>

        <input class="buttonMainGreen" name="submit" type="submit" value="Approve" >
        
        <input class="buttonMainYellow" name="submit" type="submit" value="Reject" >
        <input class="buttonMain"  name="story_id" type="hidden" value="<%=story.getStoryID()%>">

        <%
            } else {
        %>
        <input class="buttonMain" name="submit" type="submit" value="Save Changes" >
        <input class="buttonMainGreen" name="submit" type="submit" value="Submit For Review">
        <%
            }
        %>
    </form>
    <script src="CheckBox.js"></script>



    <div>


    </div>
</section>

<section class="main_content">
    <div class="side_nav">
    </div>




</section>
</body>

</html>




