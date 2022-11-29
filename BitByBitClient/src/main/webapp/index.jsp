<!DOCTYPE html>
<html>
    <head>
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <section class="banner">
            <div>
                <h1>Welcome to Readers are Innovators</h1>
                <p style="color:black">Making reading accessible to all.</p><br>
                <form action="StoryServlet" method="post">
                    <input class="button1" name="submit" type="submit" value="View Story">
                    
             
                     <input class="button1" name="submit" type="submit" value="Edit Story">

            <section class="banner">
                <div>
                    <h1>Welcome to Readers are Innovators</h1>
                    <p style="color:black">Making reading accessible to all.</p><br>
                    <form action="StoryServlet" method="post">
                        <input class="button1" name="submit" type="submit" value="View Story">

                    <input class="button1" name="submit" type="submit" value="Story of the Day">
                    <a href=DailyStory.jsp>
                        <button class="button2">View All Stories</button>
                    </a>
                </form>
                
                
                  <form action="StoryServlet" method="get">
                    <input class="button1" name="submit" type="submit" value="View Story Get">
                    
             
            
                </form>
                
                
       
                   
                
            </div>
        </section>
        <section class="main_content">
            <div class="side_nav">
                <nav>
                <ul class="browse">
                    <li class="bbh">Browse</li>
                    <li class="active">Editors Picks</li>
                    <li>RIP Originals</li>
                    <li>Trending</li>
                    <li>Latest</li>
                </ul>

                </nav>
            </div>
            
                        <%

                        <input class="button1" name="submit" type="submit" value="Create Story">



                        <input class="button1" name="submit" type="submit" value="Story of the Day">
                        <a href=DailyStory.jsp>
                            <button class="button2">View All Stories</button>
                        </a>
                    </form>
                </div>
            </section>
            <section class="main_content">
                <div class="side_nav">
                    <nav>
                        <ul class="browse">
                            <li class="bbh">Browse</li>
                            <li class="active">Editors Picks</li>
                            <li>RIP Originals</li>
                            <li>Trending</li>
                            <li>Latest</li>
                        </ul>

                    </nav>
                </div>

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



            <div class="vid_list">
                <form action="StoryServlet" method="post">
                    <div>
                        <input type="text" class="form" placeholder="Search input">
                        <input class="button1" name="submit" type="submit" value="Search for Story">
                    </div>
                </form>
                <div>
                    <img src=images/title.png>
                    <h3>Harry Potter</h3>
                    <p>The boy who survived</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>My List</button>
                </div>
                <div>
                    <img src=images/title.png>
                    <h3>Lord of the Rings</h3>
                    <p>Lorem ipsum is  layout,</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>Like Story</button>
                </div>
                <div>
                    <img src=images/title.png>
                    <h3>Goosebumps</h3>
                    <p>Lorem ipsum is  layout,</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>Like Story</button>
                </div>
                <div>
                    <img src=images/title.png>
                    <h3>Star Wars</h3>
                    <p>Lorem ipsum is  layout,</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>Like Story</button>
                </div>
                <div>
                    <img src=images/title.png>
                    <h3>50 Shades of Grey</h3>
                    <p>Lorem ipsum is  layout,</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>Like Story</button>
                </div>
                <div>
                    <img src=images/title.png>
                    <h3>Software Concepts for Dummies</h3>
                    <p>Lorem ipsum is  layout,</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>Like Story</button>
                </div>
                <div>
                    <img src=images/title.png>
                    <h3>The Jungle Book</h3>
                    <p>Lorem ipsum is  layout,</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>Like Story</button>
                </div>
                <div>
                    <img src=images/title.png>
                    <h3>The boy from Pretoria</h3>
                    <p>Lorem ipsum is  layout,</br> and printing em ipsum is  layout, and printin</p>
                    <button>Read now</button>
                    <button>Like Story</button>
                </div>

            </div>

        </section>
    </body>

</html>


