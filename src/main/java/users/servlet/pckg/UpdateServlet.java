package users.servlet.pckg;

import dbConnection.ConnectionDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/update-servlet")
public class UpdateServlet extends HttpServlet {
   private ConnectionDB update = new ConnectionDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/UserUpdate.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String c_id = request.getParameter("c_id");
        String phone = request.getParameter("phone");

        user.setName(name);
        user.setLastName(lastName);
        user.setC_id(c_id);
        user.setPhone(phone);

        try{
            update.updateUser(user);

        }catch (Exception e){
            System.err.println("Update Error " + e);
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");

    }
}
