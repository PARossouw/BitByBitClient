package Servlets;

import Category.Model.Category;
import Story.Model.Story;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import RestClientRemoteController.RestClientStory;
import User_Interactions.Comment.Model.Comment;
import jakarta.annotation.ManagedBean;
import jakarta.servlet.RequestDispatcher;
import RestClientRemoteController.RestClientCategory;
import RestClientRemoteController.RestClientComment;
import RestClientRemoteController.RestClientLike;
import RestClientRemoteController.RestClientRating;
import RestClientRemoteController.RestClientSMS;
import RestClientRemoteController.RestClientStory_Transaction;
import SMS.smsreq;
import User.Model.Reader;
import User.Model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "StoryServlet", urlPatterns = {"/StoryServlet"})
@ManagedBean
@MultipartConfig
public class StoryServlet extends HttpServlet {

    public static RestClientStory restClientStory;
    public static RestClientRating restClientRating;
    public static RestClientLike restClientLike;
    public static RestClientComment restClientComment;

    public static RestClientCategory restClientCategory;
    public static RestClientStory_Transaction restClientStory_Transaction;
    //private static User loggedInUser;

    private Story storyToReview;
    private Story storyBeingRead = new Story();
    private Story storyView = new Story();
    private List<Story> storyReviewList = new ArrayList<>();
    private User user;
    private String message;
    private Comment comment;
    private RequestDispatcher rd;
    private RestClientSMS restClientSms;
    private Story storyToApprove;
    private smsreq sms;
    public static Story storyOfTheDay;

    public StoryServlet() {
        this.restClientStory = new RestClientStory("http://localhost:8080/RIP/RIP");
        this.restClientComment = new RestClientComment("http://localhost:8080/RIP/RIP");
        this.restClientRating = new RestClientRating("http://localhost:8080/RIP/RIP");
        this.restClientLike = new RestClientLike("http://localhost:8080/RIP/RIP");
        this.restClientCategory = new RestClientCategory("http://localhost:8080/RIP/RIP");
        this.restClientStory_Transaction = new RestClientStory_Transaction("http://localhost:8080/RIP/RIP");
        this.restClientSms = new RestClientSMS("http://196.41.180.157:8080/sms/sms_request");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //  HttpSession session = request.getSession();

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

            case ("Choose Story of The Day"):

                storyReviewList = restClientStory.getStoriesForStoryOfTheDay();

                request.setAttribute("storyReviewList", storyReviewList);
                rd = request.getRequestDispatcher("ChooseDailyStory.jsp");

                rd.forward(request, response);
                break;

            case ("Load More Stories"):

                storyReviewList = restClientStory.getStoriesForStoryOfTheDay();

                request.setAttribute("storyReviewList", storyReviewList);
                rd = request.getRequestDispatcher("ChooseDailyStory.jsp");

                rd.forward(request, response);
                break;

            case ("View Story Get"):
                storyBeingRead = new Story();

                String storyIDToGet = "4";
                storyBeingRead = restClientStory.retrieveStoryGet(storyIDToGet);
                //story.setStoryID(22);
                request.setAttribute("story", this.storyBeingRead);
                RequestDispatcher rdViewStoryGet = request.getRequestDispatcher("viewstory.jsp");
                rdViewStoryGet.forward(request, response);
                break;

            case ("Display Story To Edit"):

                //storyBeingRead = new Story();

               // this.storyToReview.getStoryID();
                String storyIDToGet1 = "4";
                this.storyBeingRead = restClientStory.retrieveStoryGet(storyIDToGet1);

               
                List<Category> categoryList = new ArrayList<>();
                categoryList = restClientCategory.displayAllCategories();
                request.setAttribute("categoryList", categoryList);
                

                Category category3 = new Category();

                category3.setName("Horror");

                

                List<Category> categoryUserList = new ArrayList<>();
                //categoryUserList = this.storyBeingRead.getCategoryList();
                
                
                categoryUserList.add(category3);
                
                
                this.storyBeingRead.setCategoryList(categoryUserList);
                
                request.setAttribute("story", this.storyBeingRead);

                request.setAttribute("categoryList", categoryList);
                
                
                
                Boolean isEditor = false;
             //   UserServlet.loggedInUser.getRoleID();
                if(UserServlet.loggedInUser.getRoleID()==3)
                {
                    isEditor = true;
                }
  
              
                
                
                request.setAttribute("isEditor",isEditor );
                
                RequestDispatcher rdCreate = request.getRequestDispatcher("editStory.jsp");
                rdCreate.forward(request, response);

                
                break;
                
  


            case ("View Previous Comments"):
                List<Comment> allStoryComments = new ArrayList<>();
                String storySearch = "" + this.storyBeingRead.getStoryID();
                allStoryComments = restClientComment.getAllComments(storySearch);
                request.setAttribute("comment", allStoryComments);
                request.setAttribute("story", this.storyBeingRead);
                RequestDispatcher rdPrevComments = request.getRequestDispatcher("viewstory.jsp");
                rdPrevComments.forward(request, response);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Calendar cal = Calendar.getInstance();

        switch (request.getParameter("submit")) {

            case ("View Story"):
                // This method has been replaced with the View Story Get method above. 
                // Long live the king. 
                Story story = new Story(1, "mock title yo ma se good person", "mock writer", "mock description", "mock imagepath", "mock body", false, true, cal, true, true, 10, 57, 4.0);

                this.storyView = restClientStory.retrieveStory(story);

                request.setAttribute("story", this.storyView);

                session.getAttribute("user");
                RequestDispatcher rd = request.getRequestDispatcher("viewstory.jsp");
                rd.forward(request, response);

                break;

            case ("Edit Story"):

                // Method discontinued due to get method above working. 
                // We can delete this case. 
                List<Category> categoryList = new ArrayList<>();
                Category category1 = new Category();
                Category category2 = new Category();
                Category category3 = new Category();

                category1.setName("Comedy");
                category2.setName("Fiction");
                category3.setName("Horror");

                categoryList.add(category1);
                categoryList.add(category2);
                categoryList.add(category3);

                request.setAttribute("categoryList", categoryList);

                // Story that we are editing / Creating
                Story storyContinueCreating = new Story();

                Story storyToEdit = new Story();
                storyToEdit.setStoryID(5);

                storyBeingRead = restClientStory.retrieveStoryGet("5");
                //story.setStoryID(22);
                // request.setAttribute("story", this.storyBeingRead);
//                storyContinueCreating.setTitle("Harold and Kumar");
//                storyContinueCreating.setDescription("They want some weed description");
//                storyContinueCreating.setBody("Story about two broke asians");

                List<Category> categoryUserList = new ArrayList<>();
                categoryUserList.add(category3);
                storyContinueCreating.setCategoryList(categoryUserList);
                request.setAttribute("story", storyBeingRead);

                request.setAttribute("categoryList", categoryList);
                RequestDispatcher rdCreate = request.getRequestDispatcher("editStory.jsp");
                rdCreate.forward(request, response);

                break;

            case ("Save Changes"):
                Story storyToSave = new Story();
                storyToSave.setStoryID(this.storyBeingRead.getStoryID());
                storyToSave.setTitle((String) request.getParameter("StoryTitle"));
                storyToSave.setDescription((String) request.getParameter("StoryDescription"));
                storyToSave.setImagePath((String) request.getParameter("ImagePath"));
                storyToSave.setBody((String) request.getParameter("StoryBody"));
              
                
                /*
                adds user chosen categories to a list. 
                String[] checkedBoxes = request.getParameterValues("category");

                if (checkedBoxes.length > 0) {
                    for (int i = 0; i < checkedBoxes.length; i++) {
                        String chosenName = myList.get(Integer.parseInt((String) checkedBoxes[i])).getName();
                        Category category = new Category();
                        category.setName(chosenName);
                        prefferedCategories.add(category);
                    }

                }
                String chosenCategories = "";
                */
                
                
                
                
                
                
                
                
                storyToSave.setIsDraft(true);
                storyToSave.setIsApproved(false);
                storyToSave.setViews(0);
                storyToSave.setLikes(0);
                storyToSave.setAvgRating(0D);
   
                String saveChanges = restClientStory.saveStory(storyToSave);

                request.setAttribute("createStory", saveChanges);

                
                
                
                
                
                
                // For Editor edits, this should direct to the Editor Approvval page again
                RequestDispatcher rdSaveChanges = request.getRequestDispatcher("index.jsp");
                rdSaveChanges.forward(request, response);
                break;

            case ("Submit For Review"):
                Story storyToReview = new Story();
                storyToReview.setStoryID(this.storyBeingRead.getStoryID());
                storyToReview.setTitle((String) request.getParameter("StoryTitle"));
                storyToReview.setDescription((String) request.getParameter("StoryDescription"));
                storyToReview.setImagePath((String) request.getParameter("ImagePath"));
                storyToReview.setBody((String) request.getParameter("StoryBody"));
                storyToReview.setIsDraft(false);  // Not sure about this
                storyToReview.setIsApproved(false);

                // Getting the image data 
                Part part = request.getPart("file");
                String fileName = part.getSubmittedFileName();

                String path = "/Users/tarunsing/Documents/Van Zyl /Files/" + fileName;
                //String path = getServletContext().getRealPath("/"+"files"+File.separator+fileName);
                InputStream is = part.getInputStream();
              //  String reviewMessage = "Good Work its submitted " + fileName + "\n" + "Path is : " + path;
//                boolean succs = uploadFile(is, path);
//                if (succs) {
//                    reviewMessage = "Did Write";
//                } else {
//                    reviewMessage = "Did not write";
//                }

                String reviewMessage = restClientStory.submitCompletedStory(storyToReview);
                request.setAttribute("createStory", reviewMessage);
                RequestDispatcher rdSubmitForReview = request.getRequestDispatcher("index.jsp");
                rdSubmitForReview.forward(request, response);
                break;

            case ("Like"):

                // Story likedStory= new Story();
                //likedStory.setStoryID(88);
                Reader reader = new Reader();
                reader.setUserID(53);
                reader.setUsername("Mike");

                String storyLike = restClientLike.likeStory(reader, storyBeingRead);
                request.setAttribute("story", storyBeingRead);
                request.setAttribute("likes", storyLike);

                Story storyView2 = new Story();
                int storyID = Integer.parseInt((String) request.getParameter("story_id"));
                storyView2.setStoryID(storyID);
                this.storyView = restClientStory.retrieveStory(storyView2);
                // Reader reader = (Reader) session.getAttribute("user");
                restClientLike.likeStory(reader, this.storyView);
                request.setAttribute("story", this.storyView);
                request.setAttribute("likes", "You have liked the story ");

                RequestDispatcher rd6 = request.getRequestDispatcher("viewstory.jsp");
                rd6.forward(request, response);
                break;

            case ("Comment"):

//                Story storyView2 = new Story();
//                String storyIDComment = (String) request.getParameter("story_id"); 
                // storyView2 = restClientStory.retrieveStoryGet(storyIDComment);
                request.setAttribute("story", storyBeingRead);

                Story storyView3 = new Story();
                int storyIDComment = Integer.parseInt((String) request.getParameter("story_id"));
                storyView3.setStoryID(storyIDComment);
                this.storyView = restClientStory.retrieveStory(storyView3);
                request.setAttribute("story", this.storyView);

                session.getAttribute("user");
                request.setAttribute("optsToComment", "add a comment");

                List<Comment> allStoryComments = new ArrayList<>();
// 

                // Test Data
//                Comment testComment = new Comment();
//                testComment.setCommentBody("Good StoryLine");
//
//                Comment testComment2 = new Comment();
//                testComment2.setCommentBody("Nice Plot twist");
//
//                Comment testComment3 = new Comment();
//                testComment3.setCommentBody("Long and insightful");
//
//                allStoryComments.add(testComment);
//                allStoryComments.add(testComment2);
//                allStoryComments.add(testComment3);
                //>> End of Test Data 
                //allStoryComments = restClientComment.getAllComments(storyView2);
//                request.setAttribute("comment", allStoryComments);
                RequestDispatcher rdComment = request.getRequestDispatcher("viewstory.jsp");
                rdComment.forward(request, response);
                break;

            case ("SubmitComment"):
                Story storyViewSubmitComment = new Story();
                String storyIDCommentSubmit = (String) request.getParameter("story_id");
                storyViewSubmitComment = restClientStory.retrieveStoryGet(storyIDCommentSubmit);
                request.setAttribute("story", storyViewSubmitComment);
                this.comment = new Comment();

                this.comment.setCommentBody((String) request.getParameter("storyComment"));

                //Reader readerComment = (Reader) session.getAttribute("user");
                Reader readerComment = new Reader();
                readerComment.setUserID(998);

                this.comment.setReader(readerComment);
                this.comment.setStory(storyViewSubmitComment);
                String commentStatus = restClientComment.commentOnAStory(this.comment);
                request.setAttribute("story", storyBeingRead);

                request.setAttribute("likes", commentStatus);
                RequestDispatcher rdSubmitComment = request.getRequestDispatcher("viewstory.jsp");
                rdSubmitComment.forward(request, response);
                break;

            case ("Rate"):
                Story storyViewRate = new Story();

                int userRating = Integer.parseInt((String) request.getParameter("user_Rating"));
                int storyIDRate = Integer.parseInt((String) request.getParameter("story_id"));
                storyViewRate.setStoryID(storyIDRate);
                //  this.storyView = restClientStory.retrieveStory(storyViewRate);
                Reader loggedInReader = (Reader) session.getAttribute("user");
                // restClientRating.rateStory(this.storyView, loggedInReader, userRating);

                request.setAttribute("story", this.storyView);
                request.setAttribute("likes", "You have Rated the story " + userRating + " Star");
                RequestDispatcher rdRate = request.getRequestDispatcher("viewstory.jsp");
                rdRate.forward(request, response);
                break;

            case ("Search"):

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

            case "Approve":

                this.user = (User) session.getAttribute("user");
                this.storyToApprove = this.storyToReview;

                this.sms = restClientStory_Transaction.approvePendingStory(user, this.storyToReview);
                //this.sms = new smsreq(); sms.setMessage("tesssst");

                message = sms.getMessage();

                //request.setAttribute("message", message);
                this.rd = request.getRequestDispatcher("Editor.jsp");

                //getting the next story
                for (int i = 0; i < storyReviewList.size(); i++) {

                    if (i == storyReviewList.size() - 1) {
                        request.setAttribute("storyReview", null);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        break;
                    } else if (this.storyToReview == storyReviewList.get(i)) {

                        this.storyToReview = storyReviewList.get(i + 1);
                        request.setAttribute("storyReview", this.storyToReview);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        break;
                    }
                }

//                //send sms CHANGING THE DATE TO TEST
//                sms = new smsreq();
//                //sms.setDatetime(new Date(2022,11,24,23,0,0));
//                sms.setDatetime("2022/05/20,10:00:00");
//                sms.setUser("GROUP2");
//                sms.setPass("2group");
//                sms.setMsisdn("0716772150");
//                sms.setMessage("test message");
                try {
                    JAXBContext jaxBContext = JAXBContext.newInstance(smsreq.class);

                    Marshaller marshaller = jaxBContext.createMarshaller();

                    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                    StringWriter sw = new StringWriter();
                    //marshaller.setProperty(, );
                    //File file = new File("C:\\Users\\ametr\\Desktop\\xmlHELL.txt");
                    marshaller.marshal(sms, sw);
                    String smsResponse = restClientSms.sendMessage(sw);

                    //hardcoding
                    request.setAttribute("message", sw.toString());

                    rd = request.getRequestDispatcher("Editor.jsp");
                    rd.forward(request, response);

                } catch (JAXBException ex) {
                    Logger.getLogger(StoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            case ("Reject"):

                this.user = (User) session.getAttribute("user");
                this.storyToApprove = this.storyToReview;

                this.sms = restClientStory_Transaction.rejectPendingStory(user, this.storyToReview);
                //message = sms.getMessage();
//                request.setAttribute("message", message);

                //this.rd = request.getRequestDispatcher("Editor.jsp");
                //getting the next story
                for (int i = 0; i < storyReviewList.size(); i++) {

                    if (i == storyReviewList.size() - 1) {
                        request.setAttribute("storyReview", null);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        //rd.forward(request, response);
                        break;
                    }

                    if (this.storyToReview == storyReviewList.get(i)) {

                        this.storyToReview = storyReviewList.get(i + 1);
                        request.setAttribute("storyReview", this.storyToReview);
                        rd = request.getRequestDispatcher("Editor.jsp");
                        //rd.forward(request, response);
                        break;
                    }

                }
                try {
                    JAXBContext jaxBContext = JAXBContext.newInstance(smsreq.class);

                    Marshaller marshaller = jaxBContext.createMarshaller();

                    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                    StringWriter sw = new StringWriter();
                    marshaller.marshal(sms, sw);
                    String smsResponse = restClientSms.sendMessage(sw);

                    //can comment this out, hardcoding to see response from msg server
                    String x = "Mrs dgvsbskjnl";
                    request.setAttribute("message", x);
                    //

                    rd = request.getRequestDispatcher("Editor.jsp");
                    rd.forward(request, response);
                } catch (JAXBException ex) {
                    Logger.getLogger(StoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case ("Make Story of the Day"):

                int i = Integer.parseInt(request.getParameter("submit_id"));
                this.storyOfTheDay = storyReviewList.get(i);

                message = storyReviewList.get(i).getTitle() + " was made story of the day";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("ChooseDailyStory.jsp");
                rd.forward(request, response);

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

    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read();
//                    FileOutputStream fops = new FileOutputStream("/Users/tarunsing/Documents/Van Zyl /Files/infodata12");
            File myObj = new File(path);
            myObj.createNewFile();
            FileOutputStream fops = new FileOutputStream(path);

            fops.write(byt);
            fops.flush();
            fops.close();
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

}
