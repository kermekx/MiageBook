package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.comment.Comments;
import model.friends.Friends;
import model.status.Status;
import model.user.User;

public class SQLiteJDBC {

	private static SQLiteJDBC INSTANCE = null;
	private Connection c;

	public SQLiteJDBC() {

	}

	public static SQLiteJDBC getInstance() throws SQLException {
		if (INSTANCE == null) {
			INSTANCE = new SQLiteJDBC();
		}
		
		if (INSTANCE.c == null || INSTANCE.c.isClosed()) {
			INSTANCE.openConnection();
		}

		return INSTANCE;
	}

	private void openConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:miagetest.db");
			createTablesIfNotExist();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Opened database successfully");
	}
	
	private void createTablesIfNotExist() {
		 Statement stmt = null;
		 
		 try {
	         stmt = c.createStatement();
	         stmt.executeUpdate(User.CREATE_TABLE);
	         stmt.close();
	         
	         stmt = c.createStatement();
	         stmt.executeUpdate(Friends.CREATE_TABLE);
	         stmt.close();
	         
	         stmt = c.createStatement();
	         stmt.executeUpdate(Comments.CREATE_TABLE);
	         stmt.close();
	         
	         stmt = c.createStatement();
	         stmt.executeUpdate(Status.CREATE_TABLE);
	         stmt.close();
	         
	      } catch ( Exception e ) {
	    	  e.printStackTrace();
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	}
	
	public Connection getC() {
		return c;
	}

}
