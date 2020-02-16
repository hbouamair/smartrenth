package API;




import java.sql.DriverManager;
import java.sql.*;


public class Intconnection {
	
	
   Connection conn = null;
    public static Connection conDB() {
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/location","root","root" );
    		return con;
    		
    		
    		
    	}catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
           return null;
    	}
    	
    	
    	
    }

}
