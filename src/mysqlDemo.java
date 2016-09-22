import java.sql.*; 
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class mysqlDemo 
{   
	// JDBC driver name and database URL
   private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";

   //  Database credentials
   private static final String USER = "root";
   private static final String PASS = "332319";
   
   public static void main(String[] args) {
   java.sql.Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: 查询语句
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM author";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){//rs.next()==null说明你查询的内容不存在。
         //Retrieve by column name
         int authorid  = rs.getInt("authorid");
         int age = rs.getInt("age");
         String name = rs.getString("name");
         String country = rs.getString("country");

         //Display values
         System.out.print("authorid: " + authorid);
         System.out.print(", name: " + name);
         System.out.print(", Age: " + age);
         System.out.println(", country: " + country);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}