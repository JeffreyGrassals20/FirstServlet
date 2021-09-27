package dbConnection;

import users.servlet.pckg.User;

import java.sql.*;


public  class ConnectionDB {

    String dbName = "jdbc:postgresql://localhost/servletdata";
    String dbDriver = "org.postgresql.Driver";
    String userName = "servletdata";
    String password = "servletdata";

    public  void insertUser(User user){

        String query = "insert into Customers (name,lastName,c_id,phone) values (?,?,?,?)";

        try{
            Class.forName(dbDriver);
        }catch(ClassNotFoundException e){
            System.err.println("Class not Found:" + e);
        }
        try(Connection conn = DriverManager.getConnection(dbName,userName,password)){

            if(conn != null)
                System.out.println("Connected");

            PreparedStatement insertUser = conn.prepareStatement(query);
            insertUser.setString(1,user.getName());
            insertUser.setString(2,user.getLastName());
            insertUser.setString(3,user.getC_id());
            insertUser.setString(4,user.getPhone());

            int count =  insertUser.executeUpdate();
            System.out.println("Total Rows Affected: " + count);


        }catch(java.sql.SQLException e){
            System.err.println("Connection error " + e);
        }
    }

    public void deleteUser (User user){
        String query = "Delete from Customers where c_id = ?";

        try(Connection conn = DriverManager.getConnection(dbName,userName,password)){
            try{
                Class.forName(dbDriver);
            }catch(ClassNotFoundException e){
                System.err.println("Class not Found:" + e);
            }

            if(conn != null)
                System.out.println("Connected");

            PreparedStatement insertUser = conn.prepareStatement(query);
            insertUser.setString(1,user.getC_id());

            int count =  insertUser.executeUpdate();
            System.out.println("Total Rows Affected: " + count);
        }catch(Exception e){
            System.err.println("Connection error: " + e);
        }
    }
}
