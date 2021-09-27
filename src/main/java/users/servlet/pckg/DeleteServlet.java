package users.servlet.pckg;

import dbConnection.ConnectionDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/delete-servlet")
public class DeleteServlet extends HttpServlet {
    private ConnectionDB db = new ConnectionDB();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/UserDelete.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String c_id = request.getParameter("c_id");
        User user = new User();
        user.setC_id(c_id);

        try{
            db.deleteUser(user);
        }catch(Exception e){
            System.err.println("Post error: " + e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
    }
}

