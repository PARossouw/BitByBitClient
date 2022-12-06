<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
 
<!DOCTYPE html>
<%
    
        String startDate = (String)request.getAttribute("startDate");
        String endDate = (String)request.getAttribute("endDate");
        HashMap <Story, Integer> storyViewsMap = (HashMap<Story, Integer>)request.getAttribute("storyViewsMap");
        String responseMessage = (String)request.getAttribute("responseMessage");
    
%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Top 10 Viewed Books</title>
    </head>
    <body>

        <h4>Top 10 Most Viewed Books in a Certain Period</h4>
        <%if(responseMessage == null){%>
        <!--the date picker-->
        <form action="StoryServlet" method="get">
            <a>
                <input class="button1" name="startDate" type="date" value="top 10" required>
                <input class="button1" name="endDate" type="date" value="top 10" required>

            </a>
            <input class="button1" name="submit" type="submit" value="top 10">
        </form>
        
        <%
        
        if(storyViewsMap != null){
        
        %>



        <h2 style="text-align: center;">Top Ten Most Viewed Books  between <%=startDate%> and <%=endDate%></h2>
        <br />
        <!--the table-->
    <center>
        <table class="table" id="simple_table">
            <tr>
                <th>Story Title</th>
                <th>Author</th>
                <th>Views</th>
            </tr>

            <%
                    if(storyViewsMap != null){
                            for(Map.Entry<Story,Integer> entry: storyViewsMap.entrySet()){
                            
                            Story story = entry.getKey();
            %>
            <tr>
                <td><%=story.getTitle()%></td>
                <td><%=story.getWriter()%></td>
                <td><%=entry.getValue()%></td>
            </tr>
            <%
                        
            }
    }
            %>
        </table>
        
        <br />
        <input type="button" onclick="generate()" value="Export To PDF" />
        <script src="script.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.6/jspdf.plugin.autotable.min.js"></script>
        <%String jspvar = "is this working";%>
        <script>var sec = 25
        export {sec};</script>
        
        <br>
        <br>
        <br>
    </center>
    <!--the pie chart-->
    <section style="display: inline-block">
        <h3>Pie Chart</h3>

        
        <div id="piechart"></div>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script type="text/javascript">
            // Load google charts
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            // Draw the chart and set the chart values
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Story', 'Views'],

            <%
        
                if(storyViewsMap != null){
                        
                        for(Map.Entry<Story,Integer> entry: storyViewsMap.entrySet()){
            %>
                        ['<%=entry.getKey()%>', <%=entry.getValue()%>],<%
                        
                        }
                }
            %>

                ]);

                // add a title and set the width and height of the chart
                var options = {'title': 'Top 10 most viewed books in selected period', is3D: true, 'width': 700, 'height': 750};

                // Display the chart inside the <div> element with id="piechart"
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }
        </script>
    </section>
<!--the bar graph-->
    <section style="display: inline-block; width:40%">
        <h3>Bar Graph</h3>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <body>
            <div id="myChart" style="width:100%; max-width:600px; height:500px;"></div>

            <script>
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Books', 'Views'],

                <%
                            if(storyViewsMap != null){
                        
                                for(Map.Entry<Story,Integer> entry: storyViewsMap.entrySet()){
                %>


                                ['<%=entry.getKey()%>', <%=entry.getValue()%>],<%
                        
                                }
                            }
                %>

                ]);

                var options = {
                    title: 'Top 10 most viewed books in selected period'
                };

                var chart = new google.visualization.BarChart(document.getElementById('myChart'));
                chart.draw(data, options);
            }
            </script>

            <%

                }
}else{

            %>
            
            <h4><%=responseMessage%></h4>
        
        
        <%}%>

    </section>
</body>
</html>
