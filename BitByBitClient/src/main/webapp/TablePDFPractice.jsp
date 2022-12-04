<!DOCTYPE html>
<html lang="en">

<head>
    <%
        
        String nameAnton = "Anton";
        String namePieter = "Pieter";
        int ageAnton = 32;
        int agePieter = 28;
    
    
    %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Export Data Table to PDF</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
    <h2 style="text-align: center;">Top Ten Most Viewed Books</h2>
    <br />
    <table class="table" id="simple_table">
        <tr>
            <th>Story Title</th>
            <th>Author</th>
            <th>Views</th>
        </tr>
        <tr>
            <td>1</td>
            <td><%=nameAnton%></td>
            <td><%=ageAnton%></td>
        </tr>
        <tr>
            <td>2</td>
            <td><%=namePieter%></td>
            <td><%=agePieter%></td>
        </tr>
        <tr>
            <td>3</td>
            <td>Selvaraj</td>
            <td>63</td>
        </tr>
        <tr>
            <td>4</td>
            <td>Rohan</td>
            <td>90</td>
        </tr>
        <tr>
            <td>5</td>
            <td>Sandhya</td>
            <td>82</td>
        </tr>
        <tr>
            <td>6</td>
            <td>Nayanthara</td>
            <td>56</td>
        </tr>
    </table>
    <br />
    <input type="button" onclick="generate()" value="Export To PDF" />
    <script src="script.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.6/jspdf.plugin.autotable.min.js"></script>
</body>

</html>