<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="User.Model.User;"%>
<%@page import="java.util.List;"%>
<!DOCTYPE html>
<html>
    <%
        List<User> writers = (List)request.getAttribute("writers");
        
        String writerResults = (String)request.getAttribute("writerResults");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Block Writer</title>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%if(writerResults != null){%>
        <%=writerResults%>
        <%}%>

        <form action="UserServlet" method="get">
            <h3 style="color:black">Block Writer</h3>

            <input type="text" class="form" name="writer" placeholder="Search Writer">
            <input class="button1" name="submit" type="submit" value="Search Writer">
        </form>
        <br></br>
        <%
            if(writers != null && writers.size()>0){
        %>
        <form action="UserServlet" method="post">
            <h3 style="color:black">Displaying List of Writers: </h3>
            <h4 style="color:black">Please select writers to block</h4>
            <table border ="1" width="500">
                <tr bgcolor="00FF7F">


                </tr>
                <%    
                    for(int i = 0; i < writers.size(); i++){
                %>

                <tr>
                    <td>

                        <%
                            String variableName = ""+i;
                        %>

                        <input type="radio" value ="<%=variableName%>" name ="results" >
                        <label for="vehicle3" style="color:black"><%=writers.get(i).getUsername()%></label><br>

                    </td>


                </tr>
                <%}%>


            </table> 
            <form action="UserServlet" method="get">
                <input class="button1" name="submit" type="submit" value="Block Selected Writers">
            </form>
            <hr/>
            <%}%>
            <!--            <br></br>
                        <input class="button1" name="submit" type="submit" value="Display all writers">-->
        </form>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
