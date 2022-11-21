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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Calendar cal = Calendar.getInstance();

        switch (request.getParameter("submit")) {

            //we need to make a story of the day method in the Rest Service layer
            case ("Story of the Day"):
                Story story = new Story(1, "mock title", "mock writer", "mock description", "mock imagepath", "mock body", false, true, cal, true, true, 10, 57, 4.0);
                
                
                String story1 = story.getBody();
                request.setAttribute("story", story);
                
                Comment comment = new Comment();
                comment.setCommentBody("Mock comment body");
                request.setAttribute("comment", comment);
                
                
                
                RequestDispatcher rd = request.getRequestDispatcher("viewstory.jsp");
                rd.forward(request, response);
                
                //Story s = new Story();
                //Story story = restClientStory.retrieveStory(s);

                break;

            case ("getLikedStory"):

                break;

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
