
package Servlets;

import Category.Model.Category;
import RestClientRemoteController.RestClientCategory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="index", urlPatterns={"/index"})
public class index extends HttpServlet {

    private static RestClientCategory restClientCategory;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet index</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet index at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        switch (request.getParameter("submit")) {
            case "View stories by categories":
                List<Category> categories = restClientCategory.displayAllCategories();
                request.setAttribute("categories", categories);
                RequestDispatcher rd = request.getRequestDispatcher("storiesByCategory.jsp");
                rd.forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
