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
        switch (request.getParameter("submit")) {
            case "Login":
                String usernameOrEmail = (String) request.getAttribute("UsernameOrEmail");
                String password = (String) request.getAttribute("Password");
                User user = new User();
                if (usernameOrEmail.contains("@"))
                    user.setEmail(usernameOrEmail);
                else
                    user.setUsername(usernameOrEmail);
                user.setPassword(password);
                user = restClientUser.login(user);
                //message = restClientCategory.addCategoriesToStory(story, categories);
                if (user == null) {
                    String failed = "Login failed";
                    request.setAttribute("response", failed);
                    RequestDispatcher rd = request.getRequestDispatcher("LoginRegister.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("user", user);
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.forward(request, response);
                }
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
