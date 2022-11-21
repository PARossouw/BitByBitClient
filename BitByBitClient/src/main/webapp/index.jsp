<!DOCTYPE html> 
<html>

    <head>
        <title>Readers Are Innovators</title>
        <link rel="stylesheet" href="normalized.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="stylesheet" href="custom.css">
    </head>

    <body>
        <section class="header" background-img src="images/h_bg1.jpg">
            <nav>
                <ul class="clearfix">
                    <li class="nav_logo">
                        <img src="images/open-book.png" class="logo_img" alt="Netflix Logo">

                    </li>
                    <li>
                        <a href=index.html>
                            <button class="button3">Home</button>
                        </a>

                    </li>
                    <li>
                        <a href="story.jsp">
                            <button type="button">Categories</button>
                        </a>
                    </li>
                    <li>                       
                        <button type="button">Refer a Friend</button>
                    </li>
                    <li>                       
                        <button type="button">Contact Us</button>
                    </li>
                    <li>
                        <a href=LoginRegister.jsp>
                            <button type="button">Login/Register</button>
                        </a>
                    </li>
                    <li>
                        <a>
                            <button type="button">View Profile</button>
                        </a>
                    </li>
                </ul>

            </nav>
            <div class="h_div">
                <h1 style="color:black"><span class="bolded">Welcome to Readers are Innovators</span></h1>

                <p style="color:black">Making reading accessible to all.</br></p>

                <form action="StoryServlet" method="post">

                    <input class="button1" name="submit" type="submit" value="Story of the Day">

                <a href=DailyStory.jsp>
                    <button class="button2">View All Stories</button>
                </a>
                </form>



            </div>















        </section>

        <section class="main_content">
            <div class="side_nav">
                <nav></nav>
                <ul class="browse">
                    <b><lh class="bbh">Browse</lh></b>
                    <li class="active">Editors Picks</li>
                    <li>RIP Originals</li>
                    <li>Trending</li>
                    <li>Latest</li>
                </ul>

                </nav>
            </div>

            <div class="vid_list">
                <input type="text" class="form" placeholder="Search input">

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

