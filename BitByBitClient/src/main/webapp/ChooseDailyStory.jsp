<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Story.Model.Story;"%>
<%@page import="java.util.List;"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <head>
        <title>Choose Story of the Day</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            //this message should say something like story of the day successfully chosen
            String message = (String) request.getAttribute("message");
            List<Story> storyReviewList = (List) request.getAttribute("storyReviewList");
        
            if(message != null){
        %>
        <h3 style = "color:green"><%=message%></h3>
        <%
        
        }if(storyReviewList != null){
        %>


        <div class="h_div">
            <h1>Approve and Reject Stories</h1>

            <section class="main_content">
                <form action="StoryServlet" method="get">
                    <a>
                        <input class="button1" name="submit" type="submit" value="Load More Stories">
                    </a>
                </form>
                <div class="side_nav">
                </div>

                <%
                    for(int i =0; i < storyReviewList.size(); i++){
                %>
                <div>
                    <img src=images/title.png>
                    <h3 style = "color:black"> Writer: <%=storyReviewList.get(i).getWriter()%></h3>
                    <p style = "color:black">Title: <%=storyReviewList.get(i).getTitle()%></br> </p>
                    <p style = "color:black">Story Description: <%=storyReviewList.get(i).getDescription()%></br> </p>
                    <h5 style = "color:black">Rating: <%=storyReviewList.get(i).getAvgRating()%></br> </h5>

                    <form action="StoryServlet" method="post">
                        <a>
                            <input class="button1" name="submit" type="submit" value="Make Story of the Day">

                            <input type="hidden" name="submit_id" value="<%=i%>"/>
                        </a>
                    </form>
                    <form action="StoryServlet" method="get">
                        <a>
                            <input class="button1" name="submit" type="submit" value="Story Info">
                            <input type="hidden" name="story_id" value="<%=storyReviewList.get(i).getStoryID()%>"/>
                        </a>
                    </form>
                </div>
                <%}%>
                <%

                }else{
                %>
                <h3>No stories to review</h3>
                <%     
                     }


                %>
            </section>
            <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>