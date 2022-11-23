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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StoryServlet", urlPatterns = {"/StoryServlet"})
@ManagedBean
public class StoryServlet extends HttpServlet {

    public static RestClientStory restClientStory;
    private Story storyToReview;
    List<Story> storyReviewList = new ArrayList<>();

    public StoryServlet() {
        this.restClientStory = new RestClientStory("http://localhost:8080/RIP/RIP");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        switch (request.getParameter("submit")) {

            case ("Review Story"):
                
                storyReviewList = restClientStory.storyReview();
                this.storyToReview = storyReviewList.get(0);
                
                request.setAttribute("storyReview", this.storyToReview);
                RequestDispatcher rdisp = request.getRequestDispatcher("Editor.jsp");
                
                rdisp.forward(request, response);
                
                break;

        case ("Next Story"):
            
            
            for (int i = 0; i < storyReviewList.size(); i++) {
                
                if(this.storyToReview == storyReviewList.get(i)){
                    this.storyToReview = storyReviewList.get(i+1);
                    break;
                }
                
            }
                
                
                request.setAttribute("storyReview", this.storyToReview);
                rdisp = request.getRequestDispatcher("Editor.jsp");
                
                rdisp.forward(request, response);
                
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();


        Calendar cal = Calendar.getInstance();

        switch (request.getParameter("submit")) {

            //This is gonna be more like a view story method
            case ("Story of the Day"):
                Story story = new Story(1, "mock title", "mock writer", "mock description", "mock imagepath", "mock body", false, true, cal, true, true, 10, 57, 4.0);

                String story1 = story.getBody();
                request.setAttribute("story", story);

                Comment comment = new Comment();
                comment.setCommentBody("Mock comment body");
                request.setAttribute("comment", comment);

                
                
                session.getAttribute("user");

                RequestDispatcher rd = request.getRequestDispatcher("viewstory.jsp");
                rd.forward(request, response);

                //making the actual method: Get a list of five random stories
                
                        
                        
                        //Story s = new Story();
                        //Story story = restClientStory.retrieveStory(s);

                break;

            case ("getFiveStoriesForStoryOfTheDay"):
                
                //List<Story> storyList = restClientStory.getFiveStoriesForStoryOfTheDay();

                break;
            case ("Approve"):
                
//                restClientStory.
                break;
            case ("Reject"):
                

                break;
            case ("Next Story"):
                

                break;

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
