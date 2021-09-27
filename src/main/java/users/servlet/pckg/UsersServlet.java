package users.servlet.pckg;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import dbConnection.ConnectionDB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "usersServlet", value = "/users-servlet")
public class UsersServlet extends HttpServlet {

    private ConnectionDB db = new ConnectionDB();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/UserRegister.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String c_id = req.getParameter("c_id");
        String phone = req.getParameter("phone");

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setC_id(c_id);
        user.setPhone(phone);

        try{
            db.insertUser(user);
        }catch(Exception e){
            System.err.println("Post error: " + e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req,resp);

    }
}
