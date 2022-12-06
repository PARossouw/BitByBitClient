package Servlets;

import Category.Model.Category;
import RestClientRemoteController.RestClientCategory;
import RestClientRemoteController.RestClientSMS;
import RestClientRemoteController.RestClientStory;
import RestClientRemoteController.RestClientUser;
import SMS.smsreq;
import Story.Model.Story;
import User.Model.Reader;
import User.Model.User;
import User.Model.Writer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private static RestClientUser restClientUser;
    private static RestClientCategory restClientCategory;
    private static RestClientStory restClientStory;
    private static RestClientSMS restClientSMS;

    public static User loggedInUser;
    public static Reader registeredUser;
    private List<Writer> writersSearched;

    public UserServlet() {
        this.restClientUser = new RestClientUser("http://localhost:8080/RIP/RIP");
        this.restClientCategory = new RestClientCategory("http://localhost:8080/RIP/RIP");
        this.restClientStory = new RestClientStory("http://localhost:8080/RIP/RIP");
        this.restClientSMS = new RestClientSMS("http://196.41.180.157:8080/sms/sms_request");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);

        switch (request.getParameter("submit")) {

            case "Top 3 Highest Approving Editors":

                Map<String, Integer> editorsMap = new HashMap<>();

                editorsMap = restClientUser.topApprovingEditors();

                request.setAttribute("editorsMap", editorsMap);

                RequestDispatcher rdisp104 = request.getRequestDispatcher("Top3HighestApprovingEditors.jsp");

                rdisp104.forward(request, response);

                break;

            case "Top 5 Writers":

                Map<String, Integer> rejectedWritersMap = new HashMap<>();

                rejectedWritersMap = restClientUser.topRejectedWritersForMonth();

                request.setAttribute("rejectedWritersMap", rejectedWritersMap);

                RequestDispatcher rdisp103 = request.getRequestDispatcher("Top5WritersWithHighestRejections.jsp");

                rdisp103.forward(request, response);

                break;

            case "Top 30 Writers"://if we were to give a full writer object here it would expose the details of the writer so we just use the username over here

                Map<String, Integer> writersMap = new HashMap<>();

                writersMap = restClientUser.topWriters();
                request.setAttribute("writersMap", writersMap);

                RequestDispatcher rdisp102 = request.getRequestDispatcher("Top30Writers.jsp");

                rdisp102.forward(request, response);

                break;

            case "Likes":
                String likes = "This is my likes";
                request.setAttribute("likes", likes);
                RequestDispatcher rd = request.getRequestDispatcher("User.jsp");
                rd.forward(request, response);
                break;

            case "Search Writer":

                //hardCoding
//                List<Writer> writers = new ArrayList<>();
//                Writer w = new Writer();
//                Writer x = new Writer();
//                Writer y = new Writer();
//                Writer z = new Writer();
//                w.setUsername("writer 1");
//                writers.add(w);
//                x.setUsername("writer 2");
//                writers.add(x);
//                y.setUsername("writer 3");
//                writers.add(y);
//                z.setUsername("writer 4");
//                writers.add(z);
//                
                //doing it
                String writerSearch = (String) request.getParameter("writer");

                writersSearched = restClientUser.searchWriter(writerSearch);

                request.setAttribute("writers", writersSearched);
                RequestDispatcher rd3 = request.getRequestDispatcher("BlockWriter.jsp");
                rd3.forward(request, response);

                //RequestDispatcher rd = request.getRequestDispatcher("BlockWriter.jsp");
                //rd.forward(request, response);
                break;

            case "Profile":

                if (loggedInUser.getRoleID() < 3) {

                    Reader reader = new Reader();

                    reader.setUserID(loggedInUser.getUserID());
                    reader.setUsername(loggedInUser.getUsername());
                    reader.setEmail(loggedInUser.getEmail());

                    List<Category> preferredCategories = restClientCategory.getPreferredCategories(reader.getUserID());
                    List<Story> likedStories = restClientStory.viewLikedStories(reader.getUserID());

                    request.setAttribute("preferredCategories", preferredCategories);
                    request.setAttribute("likedStories", likedStories);

                    if (loggedInUser.getRoleID() == 2) {
                        Writer writer = new Writer();

                        writer.setUserID(reader.getUserID());
                        writer.setUsername(reader.getUsername());
                        writer.setEmail(reader.getEmail());

                        List<Story> writerStories = restClientStory.viewStoriesByWriter(writer.getUserID());

                        request.setAttribute("writerStories", writerStories);
                    }
                } else {

                    List<Story> pendingStories = restClientStory.viewPendingStories();

                    request.setAttribute("pendingStories", pendingStories);
                }

                request.setAttribute("user", loggedInUser);

                RequestDispatcher rd1 = request.getRequestDispatcher("User.jsp");
                rd1.forward(request, response);
                break;

            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        processRequest(request, response);
        HttpSession session = request.getSession(false);

        switch (request.getParameter("submit")) {

            case "Add Editor":

                String message = "adding editor";
                request.setAttribute("message", message);

                RequestDispatcher rdee = request.getRequestDispatcher("LoginRegister.jsp");
                rdee.forward(request, response);

                break;

            case "Login":

                User userCheck = new User();
                User userFeedback = new User();

                String usernameOrEmail = (String) request.getParameter("UsernameOrEmail");
                String password = (String) request.getParameter("Password");

                if (usernameOrEmail.contains("@") && usernameOrEmail.contains(".")) {

                    userCheck.setEmail(usernameOrEmail);
                } else {
                    userCheck.setUsername(usernameOrEmail);
                }
                userCheck.setPassword(password);
                userFeedback = restClientUser.login(userCheck);

                if (userFeedback != null) {
                    session = request.getSession(true);
                    session.setAttribute("user", userFeedback);

//                    this.loggedInUser = (User) session.getAttribute("user");
                    this.loggedInUser = new User();
                    this.loggedInUser = userFeedback;

                    request.setAttribute("loggedInUser", this.loggedInUser);
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);

                } else {
                    String msg2 = "Login failed, please try again.";
                    request.setAttribute("message", msg2);
                    RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
                    rd.forward(request, response);

                    //loggedInUser = (User) session.getAttribute("user");
                }
                break;

            case "Register":

                Boolean sendToDatabase = true;

                List<Category> categoryList = new ArrayList<>();
                categoryList = restClientCategory.displayAllCategories();
                String usernameRegister = (String) request.getParameter("Username");
                String emailRegister = (String) request.getParameter("Email");
                String phoneRegister = (String) request.getParameter("PhoneNumber");
                String passwordRegister = (String) request.getParameter("Password");
                String passwordConfirm = (String) request.getParameter("ConfirmPassword");

                String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";

                if (usernameRegister.isEmpty() || emailRegister.isEmpty() || passwordRegister.isEmpty() || passwordConfirm.isEmpty()) {
                    String msg = "Unsuccessful registration. Please ensure that all fields are filled in.";
                    request.setAttribute("messageRegister", msg);

                    RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
                    rd.forward(request, response);
                    sendToDatabase = false;

                } else {
                    String msg2 = "Successsfully Registered.";

                    // Checks if passwords match
                    if (!passwordRegister.equals(passwordConfirm)) {
                        msg2 = "Passwords do not match. Please try again.";
                        sendToDatabase = false;
                    }

                    // Checks for a special String.
                    String inputString = usernameRegister;
                    for (int i = 0; i < inputString.length(); i++) {
                        char ch = specialCharactersString.charAt(i);
                        if (inputString.contains(Character.toString(ch))) {
                            msg2 = "Invalid Username. Special Characters are not permitted. Please try again";
                            sendToDatabase = false;
                            break;
                        }

                        // Checks email
                        if (!emailRegister.contains("@")) {
                            msg2 = "Invalid Email. Please try again";
                            sendToDatabase = false;
                        }

                        // Check phone number
                        if (phoneRegister.length() != 10 || phoneRegister.charAt(0) != '0' || !phoneRegister.toUpperCase().equals(phoneRegister.toLowerCase())) {
                            msg2 = "Invalid Phone Number. Please try again";
                            sendToDatabase = false;
                        }
                    }
                    // Sending data to the database
                    User userCheck2 = new User();
                    if (sendToDatabase) {

                        User userFeedback2 = new User();

                        userCheck2.setUsername(usernameRegister);
                        userCheck2.setEmail(emailRegister);
                        userCheck2.setPhoneNumber(phoneRegister);
                        userCheck2.setPassword(passwordRegister);
                        if (loggedInUser == null) {
                            userCheck2.setRoleID(1);
                        } else {
                            userCheck2.setRoleID(3);
                        }

                        msg2 = restClientUser.registerUser(userCheck2);
                    }
                    request.setAttribute("messageRegister", msg2);
                    request.setAttribute("categoryList", categoryList);

                    // Registration was successful. Please log in above.
                    if (msg2.equals("Registration was successful. Please log in above.")) {

                        this.registeredUser = new Reader();
                        //registeredUser =(Reader) restClientUser.login(userCheck2);
                        int registeredUserID = restClientUser.login(userCheck2).getUserID();
                        this.registeredUser.setUserID(registeredUserID);

                        if (loggedInUser != null) {
                            String youveAddedAnEditor = "Editor successfully added";
                            request.setAttribute("createStory", youveAddedAnEditor);
                            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                            rd.forward(request, response);
                            break;
                        }

                        RequestDispatcher rd = request.getRequestDispatcher("prefferedCategories.jsp");
//                    prefferedCategories.jsp
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");

                        rd.forward(request, response);
                    }
                }
                break;

            case "submitCategories":
                List<Category> myList = restClientCategory.displayAllCategories();

                List<String> userPrefferedCategories = new ArrayList<>();
                List<Category> prefferedCategories = new ArrayList<>();

                String[] checkedBoxes = request.getParameterValues("category");

                if (checkedBoxes.length > 0) {
                    for (int i = 0; i < checkedBoxes.length; i++) {
                        String chosenName = myList.get(Integer.parseInt((String) checkedBoxes[i])).getName();
                        int categoryID = myList.get(Integer.parseInt((String) checkedBoxes[i])).getCategoryID();
                        Category category = new Category();
                        category.setName(chosenName);
                        category.setCategoryID(categoryID);
                        prefferedCategories.add(category);
                    }

                }
                String chosenCategories = "";

                this.registeredUser.setPreferredCategories(prefferedCategories);

                restClientUser.addPreferredCategoriesToNewUser(this.registeredUser);

                //  restClientUser.addPreferredCategoriesToUser(readerTest, prefferedCategories);
                request.setAttribute("messageRegister", "Thank you for registering! Please login to access your account.");
                RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");

                rd.forward(request, response);
                break;

            case "Block Selected Writers":
                String[] results = request.getParameterValues("results");

                Writer w = new Writer();
                String writerResults = "";
//                for (int i = 0; i < writersSearched.size(); i++) {
//                    if(writersSearched.get(i).getUsername().equals(results)){
//                        w = writersSearched.get(i);
//                    }
//                }
                writerResults = restClientUser.blockWriter(writersSearched.get(Integer.parseInt(results[0])));

                request.setAttribute("writerResults", writerResults);
                RequestDispatcher rd2 = request.getRequestDispatcher("BlockWriter.jsp");

                rd2.forward(request, response);

                break;

            case "ReferFriend":

                String phoneNumber = (String) request.getParameter("phoneNumber");

                User user = loggedInUser;

                String x = user.getUsername();

                //String [] reply = new String [2];
                String reply = restClientUser.referFriend(user, phoneNumber);
                x = restClientSMS.sendMessage(reply);

                request.setAttribute("createStory", "referral successful");

                RequestDispatcher rd4 = request.getRequestDispatcher("index.jsp");

                rd4.forward(request, response);

                break;

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
