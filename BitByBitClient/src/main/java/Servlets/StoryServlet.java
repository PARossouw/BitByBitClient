package Servlets;

import Category.Model.Category;
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
import RestClientRemoteController.RestClientCategory;
import RestClientRemoteController.RestClientStory_Transaction;
import User.Model.Editor;
import User.Model.User;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StoryServlet", urlPatterns = {"/StoryServlet"})
@ManagedBean
public class StoryServlet extends HttpServlet {

    public static RestClientStory restClientStory;

    public static RestClientCategory restClientCategory;
    public static RestClientStory_Transaction restClientStory_Transaction;

    private Story storyToReview;
    private Story storyView = new Story(); 
    List<Story> storyReviewList = new ArrayList<>();

    public StoryServlet() {
        this.restClientStory = new RestClientStory("http://localhost:8080/RIP/RIP");
        this.restClientCategory = new RestClientCategory("http://localhost:8080/RIP/RIP");
        this.restClientStory_Transaction = new RestClientStory_Transaction ("http://localhost:8080/RIP/RIP");
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

                    if (i == storyReviewList.size() - 1) {
                        request.setAttribute("storyReview", null);
                        rdisp = request.getRequestDispatcher("Editor.jsp");
                        rdisp.forward(request, response);
                        break;
                    }

                    if (this.storyToReview == storyReviewList.get(i)) {

                        this.storyToReview = storyReviewList.get(i + 1);
                        request.setAttribute("storyReview", this.storyToReview);
                        rdisp = request.getRequestDispatcher("Editor.jsp");
                        rdisp.forward(request, response);
                        break;
                    }

                }

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Calendar cal = Calendar.getInstance();

        switch (request.getParameter("submit")) {

            case ("View Story"):
                Story story = new Story(1, "mock title yo ma se good person", "mock writer", "mock description", "mock imagepath", "mock body", false, true, cal, true, true, 10, 57, 4.0);

                this.storyView = restClientStory.retrieveStory(story);
                

                
                request.setAttribute("story", this.storyView);
                Comment comment = new Comment();
                comment.setCommentBody("Mock comment body");
                request.setAttribute("comment", comment);


                session.getAttribute("user");
                RequestDispatcher rd = request.getRequestDispatcher("viewstory.jsp");
                rd.forward(request, response);

                break;
            case ("Search"):


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
                
                RequestDispatcher rd6 = request.getRequestDispatcher("viewstory.jsp");
                rd6.forward(request, response);
           
                break;
//              

        
            case ("viewStoriesByCategory"):
                List<Category> allCategories = new ArrayList<>();
                allCategories = restClientCategory.displayAllCategories();

                List<Category> searchByCategories = new ArrayList<>();

                String[] checkedBoxes = request.getParameterValues("category");

                if (checkedBoxes.length > 0) {
                    for (int i = 0; i < checkedBoxes.length; i++) {
                        searchByCategories.add(allCategories.get(Integer.parseInt((String) checkedBoxes[i])));
                    }
                    List<Story> retrievedStories = new ArrayList<>();
                    retrievedStories = restClientStory.searchStoriesByCategories(searchByCategories);
                    request.setAttribute("stories", retrievedStories);
                    RequestDispatcher rd2 = request.getRequestDispatcher("storiesByCategory.jsp");
                    rd2.forward(request, response);
                }
                break;

            case ("getFiveStoriesForStoryOfTheDay"):

                //List<Story> storyList = restClientStory.getFiveStoriesForStoryOfTheDay();
                break;
            case ("Approve"):
                User editor = new User();
                editor = (User)session.getAttribute("user");
                int editorID = editor.getUserID();
                
                Editor editorr = new Editor();
                editorr.setUserID(editorID);
                
                RequestDispatcher rd2 = request.getRequestDispatcher("Editor.jsp");
                
                Story storyToApprove = this.storyToReview;
                String message = restClientStory_Transaction.approvePendingStory(editorr, storyToApprove);
                //String message = "Story successfully approved";
                
                request.setAttribute("message", message);
                
                //getting the next story
                for (int i = 0; i < storyReviewList.size(); i++) {

                    if (i == storyReviewList.size() - 1) {
                        request.setAttribute("storyReview", null);
                        rd2 = request.getRequestDispatcher("Editor.jsp");
                        rd2.forward(request, response);
                        break;
                    }

                    if (this.storyToReview == storyReviewList.get(i)) {

                        this.storyToReview = storyReviewList.get(i + 1);
                        request.setAttribute("storyReview", this.storyToReview);
                        rd2 = request.getRequestDispatcher("Editor.jsp");
                        rd2.forward(request, response);
                        break;
                    }

                }
                rd2.forward(request, response);

                break;
            case ("Reject"):

                break;
            case ("Next Story"):


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
