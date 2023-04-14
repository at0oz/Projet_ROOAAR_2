import java.sql.*;

public class JDBC {
    public static void main(String[] args ){
       String url= "jdbc:mysql://localhost:3306/ecom";
       String username="root";
       String password="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from customer");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+" "+ resultSet.getString(2)+" "+ resultSet.getString(3) +" "+ resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getString(6));
            }
         connection.close();


        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
