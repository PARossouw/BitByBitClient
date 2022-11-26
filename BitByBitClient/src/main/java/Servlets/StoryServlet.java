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
import SMS.smsreq;
import User.Model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "StoryServlet", urlPatterns = {"/StoryServlet"})
@ManagedBean
public class StoryServlet extends HttpServlet {

    public static RestClientStory restClientStory;

    public static RestClientCategory restClientCategory;
    public static RestClientStory_Transaction restClientStory_Transaction;

    private Story storyToReview;
    private Story storyView = new Story();
    private List<Story> storyReviewList = new ArrayList<>();
    private User user;
    private String message;
    private RequestDispatcher rd;

    public StoryServlet() {
        this.restClientStory = new RestClientStory("http://localhost:8080/RIP/RIP");
        this.restClientCategory = new RestClientCategory("http://localhost:8080/RIP/RIP");
        this.restClientStory_Transaction = new RestClientStory_Transaction("http://localhost:8080/RIP/RIP");
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
                break;

            case ("Create Story"):
                // request.setAttribute("createStory", "Story has been created.");

                List<Category> categoryList = new ArrayList<>();
                //categoryList = restClientCategory.displayAllCategories();
                Category category1 = new Category();
                Category category2 = new Category();
                Category category3 = new Category();

                category1.setName("Comedy");
                category2.setName("Fiction");
                category3.setName("Horror");

                categoryList.add(category1);
                categoryList.add(category2);
                categoryList.add(category3);
                
                
                // Story that we are editing / Creating
                Story storyContinueCreating = new Story();
                storyContinueCreating.setTitle("Harold and Kumar");
                storyContinueCreating.setDescription("They want some weed description");
                storyContinueCreating.setBody("Story about two broke asians");
               
                
                  List<Category> categoryUserList = new ArrayList<>();
                  categoryUserList.add(category3);
                  storyContinueCreating.setCategoryList(categoryUserList);
                  
                  
                
                
                
                
                request.setAttribute("story", storyContinueCreating);
                
                

                request.setAttribute("categoryList", categoryList);
                RequestDispatcher rdCreate = request.getRequestDispatcher("createStory.jsp");
                rdCreate.forward(request, response);

                break;

            case ("Save Changes"):

                Story storyToSave = new Story();

                storyToSave.setTitle((String) request.getParameter("StoryTitle"));
                storyToSave.setDescription((String) request.getParameter("StoryDescription"));
                storyToSave.setImagePath((String) request.getParameter("ImagePath"));
                storyToSave.setBody((String) request.getParameter("StoryBody"));
                storyToSave.setIsDraft(true);
                storyToSave.setIsApproved(false);

                String saveChanges = "Story changes have been saved and will be ready for you to complete at a later time.";
                request.setAttribute("createStory", saveChanges);

                // For Editor edits, this should direct to the Editor Approvval page again
                RequestDispatcher rdSaveChanges = request.getRequestDispatcher("index.jsp");
                rdSaveChanges.forward(request, response);
                break;

            case ("Submit For Review"):
                String reviewMessage = "Your story has been submitted for a review."
                        + "\n Once compete, you will be notified via Text Message regarding the outcome."
                        + "\n Thank you for contributing to Bit-By-Bit";

                request.setAttribute("createStory", reviewMessage);
                RequestDispatcher rdSubmitForReview = request.getRequestDispatcher("index.jsp");
                rdSubmitForReview.forward(request, response);

                break;

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

                user = (User) session.getAttribute("user");
                Story storyToApprove = this.storyToReview;

                message = restClientStory_Transaction.approvePendingStory(user, this.storyToReview);

                request.setAttribute("message", message);

                rd = request.getRequestDispatcher("Editor.jsp");

                //getting the next story
                for (int i = 0; i < storyReviewList.size(); i++) {

                    if (i == storyReviewList.size() - 1) {
                        request.setAttribute("storyReview", null);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        rd.forward(request, response);
                        break;
                    } else if (this.storyToReview == storyReviewList.get(i)) {

                        this.storyToReview = storyReviewList.get(i + 1);
                        request.setAttribute("storyReview", this.storyToReview);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        rd.forward(request, response);
                        break;
                    }

                }
                //send sms CHANGING THE DATE TO TEST
                smsreq sms = new smsreq();
                //sms.setDatetime(new Date(2022,11,24,23,0,0));
                sms.setDatetime("2022/05/20,10:00:00");
                sms.setUser(1);
                sms.setPass("password");
                sms.setMsisdn("0739068691");
                sms.setMessage("test message");

                try {
                    JAXBContext jaxBContext = JAXBContext.newInstance(smsreq.class);

                    Marshaller marshaller = jaxBContext.createMarshaller();

                    File xmlOutput = new File("C:\\Users\\ametr\\Desktop\\xmlTestOutput.xml");
                    marshaller.marshal(sms, xmlOutput);

                    //dunno about this
                    rd = request.getRequestDispatcher("http://196.41.180.157:8080/sms/sms_request");
                    request.setAttribute("smsreq", xmlOutput);
                    rd.forward(request, response);

                } catch (JAXBException ex) {
                    Logger.getLogger(StoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case ("Reject"):

                user = (User) session.getAttribute("user");

                message = restClientStory_Transaction.rejectPendingStory(user, this.storyToReview);

                request.setAttribute("message", message);

                rd = request.getRequestDispatcher("Editor.jsp");

                //getting the next story
                for (int i = 0; i < storyReviewList.size(); i++) {

                    if (i == storyReviewList.size() - 1) {
                        request.setAttribute("storyReview", null);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        rd.forward(request, response);
                        break;
                    }

                    if (this.storyToReview == storyReviewList.get(i)) {

                        this.storyToReview = storyReviewList.get(i + 1);
                        request.setAttribute("storyReview", this.storyToReview);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        rd.forward(request, response);
                        break;
                    }

                }

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
