package Servlets;

import Category.Model.Category;
import RestClientRemoteController.RestClientCategory;
import RestClientRemoteController.RestClientStory;
import RestClientRemoteController.RestClientUser;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private static RestClientUser restClientUser;
    private static RestClientCategory restClientCategory;
    private static RestClientStory restClientStory;
    public static User loggedInUser;
    private List<Writer> writersSearched;

    public UserServlet() {
        this.restClientUser = new RestClientUser("http://localhost:8080/RIP/RIP");
        this.restClientCategory = new RestClientCategory("http://localhost:8080/RIP/RIP");
        this.restClientStory = new RestClientStory("http://localhost:8080/RIP/RIP");
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

                Reader reader = new Reader();

                reader.setUserID(loggedInUser.getUserID());
                reader.setUsername(loggedInUser.getUsername());
                reader.setEmail(loggedInUser.getEmail());

                List<Category> preferredCategories = reader.getPreferredCategories();
                List<Story> likedStories = reader.getLikedStories();

                request.setAttribute("preferredCategories", preferredCategories);
                request.setAttribute("likedStories", likedStories);
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
                    loggedInUser = (User) session.getAttribute("user");
                    request.setAttribute("loggedInUser", loggedInUser);
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");

                    rd.forward(request, response);

                } else {
                    String msg2 = "Login failed, please try again.";
                    request.setAttribute("message", msg2);
                    RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
                    rd.forward(request, response);

                    loggedInUser = (User) session.getAttribute("user");
                }
                break;

            case "Register":

                Boolean sendToDatabase = true;

                List<Category> categoryList = new ArrayList<>();
                categoryList = restClientCategory.displayAllCategories();

                //categoryList.add("Horror");
                //categoryList.add("Comedy");
                //categoryList.add("Fiction");
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
                    if (sendToDatabase) {
                        User userCheck2 = new User();
                        User userFeedback2 = new User();

                        userCheck2.setUsername(usernameRegister);
                        userCheck2.setEmail(emailRegister);
                        userCheck2.setPhoneNumber(phoneRegister);
                        userCheck2.setPassword(passwordRegister);

                        msg2 = restClientUser.registerUser(userCheck2);
                    }
                    request.setAttribute("messageRegister", msg2);
                    request.setAttribute("categoryList", categoryList);

                    RequestDispatcher rd = request.getRequestDispatcher("prefferedCategories.jsp");
                    rd.forward(request, response);
                }
                break;

            case "submitCategories":
                List<Category> myList = new ArrayList<>();
                myList = restClientCategory.displayAllCategories();

                List<String> userPrefferedCategories = new ArrayList<>();
                List<Category> prefferedCategories = new ArrayList<>();

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

                // For Testing purposes <<
                for (int i = 0; i < prefferedCategories.size(); i++) {
                    chosenCategories += prefferedCategories.get(i).getName() + "\n";
                }

                request.setAttribute("checked", chosenCategories);
                RequestDispatcher rd3 = request.getRequestDispatcher("index.jsp");
                rd3.forward(request, response);
                // >>>>>

                Reader reader = new Reader();
                reader.setUsername((String) session.getAttribute("user"));
                reader.setUserID(Integer.parseInt((String) session.getAttribute("userID")));
                reader.setRoleID(Integer.parseInt((String) session.getAttribute("roleID")));

                restClientUser.addPreferredCategoriesToUser(reader, prefferedCategories);
                break;





            case "Profile":

                List<Category> preferredCategories = restClientCategory.getPreferredCategories((User) loggedInUser);

//                switch (loggedInUser.getRoleID()) {
//                    case 1:
//                        preferredCategories = restClientCategory.getPreferredCategories((Reader) loggedInUser);
//                        break;
//                    case 2:
//                        preferredCategories = restClientCategory.getPreferredCategories((Writer) loggedInUser);
//                        break;
//                    default:
//                        preferredCategories = null;
//                }

                List<Story> likedStories = restClientStory.viewLikedStories((User) loggedInUser);
//                switch (loggedInUser.getRoleID()) {
//                    case 1:
//                        likedStories = restClientStory.viewLikedStories((Reader) loggedInUser);
//                        break;
//                    case 2:
//                        likedStories = restClientStory.viewLikedStories((Writer) loggedInUser);
//                        break;
//                    default:
//                        likedStories = null;
//                }
                
                request.setAttribute("preferredCategories", preferredCategories);
                request.setAttribute("likedStories", likedStories);
                request.setAttribute("user", loggedInUser);
                RequestDispatcher rd1 = request.getRequestDispatcher("User.jsp");
                
                rd1.forward(request, response);

                break;
                
            case "Block Selected Writers" :
                String []results = request.getParameterValues("results");

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

            default:
                throw new AssertionError();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
