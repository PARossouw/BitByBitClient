package Servlets;

import Category.Model.Category;
import RestClientRemoteController.RestClientCategory;
import Story.Model.Story;
import User.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

@WebServlet(name = "Category", urlPatterns = {"/Category"})
public class CategoryServlet extends HttpServlet {

    private List<User> categories;
    private static RestClientCategory restClientCategory;

    public CategoryServlet() {
        categories = new ArrayList<>();
        restClientCategory = new RestClientCategory("http://localhost:8080/RIP/RIP");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        switch(request.getParameter("submit")){
            
            case "addCategoriesToStory"     :
                
                String message = null;
                Story story = new Story();
                story.setStoryID(1);
                
                List<Category> categories = new ArrayList<>();
                categories.add(new Category("love and death"));
                
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("story", story);
//                jsonObject.put("categories", categories);
                
                message = restClientCategory.addCategoriesToStory(story, categories);
                
                break;
            
        }
    }

    @Override
    public String getServletInfo() {
        return "This Servlet communicates with the rest client remote-control//";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Category</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Category at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}