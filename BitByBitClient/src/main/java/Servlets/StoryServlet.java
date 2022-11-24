package Servlets;

import Story.Model.Story;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import RestClientRemoteController.RestClientStory;
import User_Interactions.Comment.Model.Comment;
import jakarta.annotation.ManagedBean;
import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "StoryServlet", urlPatterns = {"/StoryServlet"})
@ManagedBean
public class StoryServlet extends HttpServlet {

    public static RestClientStory restClientStory;

    public StoryServlet() {
        this.restClientStory = new RestClientStory("http://localhost:8080/RIP/RIP");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case ("getFiveStoriesForStoryOfTheDay"):

                List<Story> storyList = restClientStory.getFiveStoriesForStoryOfTheDay();

                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Calendar cal = Calendar.getInstance();

        switch (request.getParameter("submit")) {

            case ("Story of the Day"):
                Story story = new Story(1, "mock title yo ma se", "mock writer", "mock description", "mock imagepath", "mock body", false, true, cal, true, true, 10, 57, 4.0);
Story storyTest = new Story();
storyTest = restClientStory.retrieveStory(story);

                
                request.setAttribute("story", story);
                Comment comment = new Comment();
                comment.setCommentBody("Mock comment body");
                request.setAttribute("comment", comment);
                session.getAttribute("user");
                RequestDispatcher rd = request.getRequestDispatcher("viewstory.jsp");
                rd.forward(request, response);
                break;

//            case ("View Story"):
//                Story storyView = new Story(1, "mock view title", "mock writer", "mock description", "mock imagepath", "mock view body", false, true, cal, true, true, 10, 57, 4.6);
//
//                String storyBody = storyView.getBody();
//                request.setAttribute("story", storyView);
//
//                Comment commentView = new Comment();
//                commentView.setCommentBody("Mock view comment body");
//                request.setAttribute("comment", commentView);
//                break;

            case ("Like Story"):
                 Story storyView1 = new Story();
                 storyView1 = null;
                request.setAttribute("story", storyView1);
//
                Comment commentView1 = new Comment();
                commentView1 = null;
                
                request.setAttribute("comment", commentView1);
                 session.getAttribute("user");
//
                request.setAttribute("likes", "You have liked the story");
                
                RequestDispatcher rd2 = request.getRequestDispatcher("viewstory.jsp");
                rd2.forward(request, response);
           
                break;
//              

            case ("getFiveStoriesForStoryOfTheDay"):

                List<Story> storyList = restClientStory.getFiveStoriesForStoryOfTheDay();

                break;

        }
//          Story storyView1 = new Story(1, "mock like title", "mock writer", "mock description", "mock imagepath", "mock view body", false, true, cal, true, true, 10, 57, 4.6);
//
//                String storyBody1 = storyView1.getBody();
//                request.setAttribute("story", storyView1);
//
//                Comment commentView1 = new Comment();
//                commentView1.setCommentBody("Mock like comment body");
//                request.setAttribute("comment", commentView1);
                

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
