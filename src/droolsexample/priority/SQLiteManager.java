package droolsexample.priority;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLiteManager {
	private Connection sqlite_connection;
	private SQLiteMethods methods;
	
	public SQLiteManager() {
		
	}

	
	public SQLiteMethods getMethods() {
		return methods;
	}


	public void setMethods(SQLiteMethods methods) {
		this.methods = methods;
	}


	public Connection getSqlite_connection() {
		return sqlite_connection;
	}




	public boolean Connect() {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.sqlite.JDBC");
			this.sqlite_connection = DriverManager.getConnection("jdbc:sqlite:./db/database.db");//hay que poner nuestra database
			sqlite_connection.createStatement().execute("PRAGMA foreign_keys=ON");
			this.methods = new SQLiteMethods(sqlite_connection);
			return true;

		} catch (ClassNotFoundException | SQLException connection_error) {
			connection_error.printStackTrace();
			return false;
		}
	}


	public boolean CreateTables() {
		try {
			Statement stmt0 = sqlite_connection.createStatement();
			String sql0 = "CREATE TABLE Department " + "(departmentName TEXT PRIMARY KEY UNIQUE, "
					+ " npatients INTEGER, " + "ratio FLOAT, "+"avghours INTEGER,"+"nemployees INTEGER,"+"cartWeigth INTEGER,"+"priorityLevel FLOAT,"+"isHighest BOOLEAN)";
			stmt0.execute(sql0);
			
			Statement stmt1 = sqlite_connection.createStatement();
			String sql1 = "CREATE TABLE Resource " + "(resourceName TEXT PRIMARY KEY UNIQUE, "
                    + " priority TEXT CHECK( priority IN ('HIGH','MEDIUM','LOW') )," 
                    + " price FLOAT NULL)";
			stmt1.execute(sql1);

			Statement stmt2 = sqlite_connection.createStatement();
			String sql2 = "CREATE TABLE Hospital " + "(hospitalName TEXT PRIMARY KEY UNIQUE, "
                    + " budget FLOAT)";// + " departmentName FOREIGN KEY TEXT REFERENCES Department(departmentName))";
			
			stmt2.execute(sql2);
			
			Statement stmt3 = sqlite_connection.createStatement();
			String sql3 = "CREATE TABLE DepartmentResource " + "(departmentName REFERENCES Department(departmentName),"+"resourceName REFERENCES Resource(resourceName),"+"PRIMARY KEY (departmentName, resourceName))";
			stmt3.execute(sql3);
			return true;
		}catch (SQLException tables_error) {
			if (tables_error.getMessage().contains("already exists")) {
				System.out.println("Database already exists.");
				return false;
			} else {
				System.out.println("Error creating tables! Abort.");
				tables_error.printStackTrace();
				return false;
			}
		}
	}
	
	
	// -------> CLOSE DATABASE CONNECTION <---------
	
	public boolean Close_connection() {
		// TODO Auto-generated method stub
		try {
			this.sqlite_connection.close();
			return true;
		} catch (SQLException close_connection_error) {
			close_connection_error.printStackTrace();
			return false;
		}
	}

}