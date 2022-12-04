
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
       <p style="text-align:center;">  <img  src="images/pexels-steve-johnson-1843716.jpg" alt="Choose Preffered Categories" width="100%" height="400"> </p>
<body style="background-color:black">
            <nav>
                

            </nav>


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
                <h3 style="color:white">Displaying Category List</h3>
                <h4 style="color:white">Please select your preferred categories</h4>
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
                            <label for="vehicle3" style="color:white"><%=std.get(i).getName()%></label><br>

                        </td>


                    </tr>
                    <%}}%>

                </table> 
                <hr/>
                <input class="buttonMain" name="submit" type="submit" value="submitCategories">
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




<jsp:include page="footer.jsp"></jsp:include>
    </body>

</html>