
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
            out.println("<h1>Welcome " + request.getParameter("name") + " " + request.getParameter("surname")  + "</h1>");
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
                
                String message1 = null;
                for(Person p1 : people)
                {
                    if(request.getParameter("email").equals(p1.getEmail()))
                    {
                     message1 = "An account with this email already exists";   
                    }
                }
                if(message1 == null)
                {
                
                Person p = new Person(request.getParameter("name"),
                        request.getParameter("surname"),
                        request.getParameter("email"),
                        request.getParameter("password"));

                people.add(p);
                message1 = "You have successfuly registered";
                request.setAttribute("message1", message1);
                    RequestDispatcher rd1 = request.getRequestDispatcher("Login.jsp");
                    rd1.forward(request, response);
                }
                
                break;
            case "login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Person per = null;
                
                for (Person person : people) {
                    if ((email.equalsIgnoreCase(person.getEmail()) && (password.equals(person.getPassword())))) {
                        per = person;
                    }
                }
                if (per != null) {
             
                    request.setAttribute("person", per);
                    RequestDispatcher rd = request.getRequestDispatcher("personDetails.jsp");
                    rd.forward(request, response);
//                    out.println("<!DOCTYPE html>");
//                    out.println("<html>");
//                    out.println("<head>");
//                    out.println("<title>Home</title>");            
//                    out.println("</head>");
//                    out.println("<body>");
//                    out.println("<h1>Welcome " + per.getName() + "</h1>");
//                    out.println("</body>");
//                    out.println("</html>");
                }else{
                    String message = "The email and password do not exist";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                    rd.forward(request, response);
                }
                break;
                
            case "Prize":
                String emails = request.getParameter("email");
                String message = "Your email "+ emails + "is a winner !";
                request.setAttribute("message", message);
                RequestDispatcher rd = request.getRequestDispatcher("Prize.jsp");
                rd.forward(request, response);
                break;
            }
            
        }
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MyFirstServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Welcome " + request.getParameter("name") + " " + request.getParameter("surname")  + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
