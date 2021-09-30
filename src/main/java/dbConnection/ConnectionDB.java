package dbConnection;

import users.servlet.pckg.User;

import java.sql.*;
import java.util.Properties;


public  class ConnectionDB {

    String dbName = "jdbc:postgresql://localhost/servletdata";
    String dbDriver = "org.postgresql.Driver";
    String userName = "servletdata";
    String password = "servletdata";

    public int i = 0;

    public Connection dbConnection (){

        try{

            Connection conn = DriverManager.getConnection(dbName,userName,password);

            try{
                Class.forName(dbDriver);
            }catch(ClassNotFoundException e){
                System.err.println("Class not Found:" + e);
            }
            if(conn != null){
                System.out.println("Connected to db" + i);
                i++;
                return conn;
            }
        }catch(Exception e){
            System.err.println("Connection Error" + e);
        }
        return null;
    }

    public  void insertUser(User user){

        String query = "insert into Customers (name,lastName,c_id,phone) values (?,?,?,?)";


        try{
            PreparedStatement insertUser = dbConnection().prepareStatement(query);
            insertUser.setString(1,user.getName());
            insertUser.setString(2,user.getLastName());
            insertUser.setString(3,user.getC_id());
            insertUser.setString(4,user.getPhone());

            int count =  insertUser.executeUpdate();
            System.out.println("Total Rows Affected: " + count);
            dbConnection().close();
        }catch(java.sql.SQLException e){
            System.err.println("insertUser error " + e);
        }
    }

    public void deleteUser (User user){

        String query = "Delete from Customers where c_id = ?";

        try{
            PreparedStatement insertUser = dbConnection().prepareStatement(query);
            insertUser.setString(1,user.getC_id());

            int count =  insertUser.executeUpdate();
            System.out.println("Total Rows Affected: " + count);
            dbConnection().close();
        }catch(Exception e){
            System.err.println("Delete error: " + e);
        }
    }

    public void updateUser (User user){
        String query = "CALL update_data(?,?,?,?)";

        try{
            CallableStatement statement = dbConnection().prepareCall(query);

            statement.setString(1,user.getName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getC_id());
            statement.setString(4,user.getPhone());
            statement.execute();
            dbConnection().close();
        }catch(Exception e){
            System.err.println("Update error: " + e);
        }

    }
}
