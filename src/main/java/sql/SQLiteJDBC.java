package sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteJDBC {

	private static SQLiteJDBC INSTANCE = null;
	private Connection c;

	public SQLiteJDBC() {

	}

	public static SQLiteJDBC getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SQLiteJDBC();
		}
		
		if (INSTANCE.c == null) {
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
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
	
	private void createTablesIfNotExist() {
		
	}

}
