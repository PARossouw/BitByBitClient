<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<%
    
        HashMap <String, Integer> ratedBooksMap = (HashMap<String, Integer>)request.getAttribute("ratedBooksMap");
        String month = (String)request.getAttribute("month");
        
%>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Most Rated Books</title>
    </head>
    <body>

        <h4>Top 20 Rated Books of the Month</h4>

        <!--the date picker-->
        <form action="StoryServlet" method="get">
            <a>
                <input class="button1" name="Date" type="month" value="Top 20 Most Rated Books of the Month">


            </a>
            <input class="button1" name="submit" type="submit" value="Top 20 Most Rated Books of the Month">
        </form>
        
        <%
        
        if(ratedBooksMap != null){
        
        %>
        
        <h2 style="text-align: center;">Top 20 Most Liked Books  in <%=month%></h2>
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
                    if(ratedBooksMap != null){
                            for(Map.Entry<String,Integer> entry: ratedBooksMap.entrySet()){
                            
                            //Story story = entry.getKey();
            %>
            <tr>
                <td><%=entry.getKey()%></td>
                <td><%=entry.getKey()%></td>
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
                    ['Story', 'Rating'],

            <%
        
                if(ratedBooksMap != null){
                        
                        for(Map.Entry<String,Integer> entry: ratedBooksMap.entrySet()){
            %>
                        ['<%=entry.getKey()%>', <%=entry.getValue()%>],<%
                        
                        }
                }
            %>

                ]);

                // add a title and set the width and height of the chart
                var options = {'title': 'Top 20 most Rated books', is3D: true, 'width': 700, 'height': 750};

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
                            if(ratedBooksMap != null){
                        
                                for(Map.Entry<String,Integer> entry: ratedBooksMap.entrySet()){
                %>


                                ['<%=entry.getKey()%>', <%=entry.getValue()%>],<%
                        
                                }
                            }
                %>

                ]);

                var options = {
                    title: 'Top 20 most liked books'
                };

                var chart = new google.visualization.BarChart(document.getElementById('myChart'));
                chart.draw(data, options);
            }
            </script>

            
    </section>
        
        <%

                }

        %>


    </body>
</html>
