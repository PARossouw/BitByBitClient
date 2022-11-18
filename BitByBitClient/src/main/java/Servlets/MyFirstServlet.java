package Servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyFirstServlet", urlPatterns = {"/MyFirstServlet"})
public class MyFirstServlet extends HttpServlet {

    private List<Person> people;

    public MyFirstServlet() {
        people = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyFirstServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome " + request.getParameter("name") + " " + request.getParameter("surname") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            switch (request.getParameter("submit")) {
            case "register":
                 String emailNew = request.getParameter("email");
                String passwordNew = request.getParameter("password");
                String messageRegister = "You have been successfully registered "+ emailNew + " !";
                 request.setAttribute("message", messageRegister);
                RequestDispatcher rd5 = request.getRequestDispatcher("Home.jsp");
                rd5.forward(request, response);          
                
                break;
                
                
                
            case "viewjoke":
                
                request.setAttribute("user", "Reader is Yoda");
                RequestDispatcher rd53 = request.getRequestDispatcher("Reader.jsp");
                rd53.forward(request, response); 
                break;
                
            default :
                  
                request.setAttribute("user", "wtf");
                RequestDispatcher rd54 = request.getRequestDispatcher("Reader.jsp");
                rd54.forward(request, response); 
                break;
                
                
            }




        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
