<%-- 
    Document   : reportsAnton
    Created on : 01 Dec 2022, 14:32:41
    Author     : ametr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="RestClientRemoteController.RestClientView"%>
<html>

    <%
    
        //RestClientView rcv = new RestClientView("http://localhost:8080/RIP/RIP");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <a href=Top10MostViewedBookesInACertainPeriod.jsp>
            <button class="button3">top 10 most viewed books in a certain period</button>
        </a>
        <br></br>
        <a href=Top20MostRatedBooksOfTheMonth.jsp>
            <button class="button3">top 20 most rated books in month</button>
        </a>
        <br></br>
        <a href=Top20LikedBooksInAMonth.jsp>
            <button class="button3">top 20 liked books in a month</button>
        </a>
        <br></br>
        <a href=Top3CategoriesInAMonth.jsp>
            <button class="button3">top 3 categories in a month</button>
        </a>
        <br></br>
        <a href=Top30Writers.jsp>
            <button class="button3">Top30Writers</button>
        </a>
        <br></br>
        <a href=Top5WritersWithHighestRejections.jsp>
            <button class="button3">Top 5 Writers Rejected Writers for the current month</button>
        </a>
        <br></br>
        <a href=Top3HighestApprovingEditors.jsp>
            <button class="button3">Top 3 Highest Approving Editors</button>
        </a>
        <br></br>
        <a href=PieChartPractice.jsp>
            <button class="button3">Pie Chart Practice</button>
        </a>
        <br></br>
        <a href=TablePDFPractice.jsp>
            <button class="button3">Table PDF Practice</button>
        </a>



        <!--        <div class="dropdown">
                    <label>Calendar: </label>
                    <input type="date">-->


        <!--<form action="StoryServlet" method="get">
          <label>
            Enter the Start date
            <input class="button1" type="date" name="submit" value="top 10">
            <br>
            Enter the End date
            <input class="button1" type="date" name="submit" value="top 10">
          <p><button>Submit</button></p>
          </label>
        
        </form>-->

        <!--        <form action="StoryServlet" method="get">
                    <a>
                    <input class="button1" name="startDate" type="date" value="top 10">
        
                    </a>
                        <input class="button1" name="submit" type="submit" value="top 10">
                </form>-->







    </body>
</html>
