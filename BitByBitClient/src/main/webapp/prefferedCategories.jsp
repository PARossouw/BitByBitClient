<%@page import="java.util.ArrayList"%>
<%@page import="Category.Model.Category;"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>
        <title>Login</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <section class="banner" background-img src="images/storyOfTheDay.jpg">
            
            <%
          String responseMessageRegister = (String) request.getAttribute("checked");
            %>
            <%
                if(responseMessageRegister != null) {
            %>
            <div>
                <h3 style="color:red"><%=responseMessageRegister%></h3>
            </div>
            <%
                }
            %>
            

            <form action="UserServlet" method="post">
                <h1 style="color:black">Displaying Category List</h1>
                <table border ="1" width="500" align="center">
                    <tr bgcolor="00FF7F">


                    </tr>
                    <%List<Category> std = 
                        (ArrayList<Category>)request.getAttribute("categoryList");
                        
                         if(std != null)
                         {
                        
                        
                    for(int i = 0; i<std.size() ; i++){%>

                    <tr>
                        <td>

                            <%
                                String variableName = ""+i;
                            %>

                            <input type="checkbox" value ="<%=variableName%>" name ="category" >
                            <label for="vehicle3" style="color:black"><%=std.get(i).getName() + " "+variableName%></label><br>

                        </td>


                    </tr>
                    <%}}%>




                </table> 
                <hr/>
                <input class="button1" name="submit" type="submit" value="submitCategories">


            </form>


            <%
                   String responseMessage = (String) request.getAttribute("categoryStuff");
            %>
            <%
                if(responseMessage != null) {
            %>
            <div>
                <h3 style="color:red"><%=responseMessage%></h3>
            </div>
            <%
                }
            %>


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