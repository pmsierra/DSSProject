package droolsexample.priority;

import java.sql.SQLException;

public class kk {

public static void main(String[] args) throws SQLException {
		
		SQLiteManager manager = new SQLiteManager();
		boolean everything_ok = manager.Connect();
		boolean tables_ok = manager.CreateTables();
		System.out.println(manager.getSqlite_connection().getWarnings());
		
		System.out.println(everything_ok + " ");
	}
}
