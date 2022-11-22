package Servlets;

import RestClientRemoteController.RestClientUser;
import User.Model.User;
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

    public UserServlet() {
        this.restClientUser = new RestClientUser("http://localhost:8080/RIP/RIP");
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
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession session = request.getSession();
        switch (request.getParameter("submit")) {
            case "Login":

                User userCheck = new User();
                User userFeedback = new User();

                String usernameOrEmail = (String) request.getParameter("UsernameOrEmail");
                String password = (String) request.getParameter("Password");

                if (usernameOrEmail.contains("@")) {
                    userCheck.setEmail(usernameOrEmail);
                } else {
                    userCheck.setUsername(usernameOrEmail);
                }

                userCheck.setPassword(password);

                userFeedback = restClientUser.login(userCheck);

                if (userFeedback != null) {
                    session = request.getSession(true);
                    session.setAttribute("user", userFeedback.getUsername()); // setting the session object. 
                    session.setAttribute("userID", userFeedback.getUserID());
                    session.setAttribute("roleID", userFeedback.getRoleID());

                    String msg = "Successfuly logged in";
                    request.setAttribute("message", msg);
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);

                } else {
                    String msg2 = "Login failed, please try again.";
                    request.setAttribute("message", msg2);
                    RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
                    rd.forward(request, response);
                }
                break;

            case "Register":

                Boolean sendToDatabase = true;

                ArrayList<String> categoryList = new ArrayList<>();
                categoryList.add("Horror");
                categoryList.add("Comedy");
                categoryList.add("Fiction");

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
                    String specialCharactersStringCheck = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
                    for (int i = 0; i < inputString.length(); i++) {
                        char ch = inputString.charAt(i);
                        if (specialCharactersStringCheck.contains(Character.toString(ch))) {
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
                List<String> myList = new ArrayList<>();
                myList.add("Horror");
                myList.add("Comedy");
                myList.add("Fiction");
                
                List<Integer> categoryCheckedList = new ArrayList<>();
                
                String[] checkedBoxes = request.getParameterValues("category");

                String allCheckedBoxes = "";

                int userChecked = 0 ; 
                
                for (int i = 0; i < checkedBoxes.length; i++) {
                  //  allCheckedBoxes += (String) checkedBoxes[i] + "\n";
                    
                    categoryCheckedList.add(Integer.parseInt( (String) checkedBoxes[i]));
                }
                
                
                
                

                request.setAttribute("checked", myList.get(categoryCheckedList.get(0)));

                RequestDispatcher rd3 = request.getRequestDispatcher("prefferedCategories.jsp");

                rd3.forward(request, response);

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
