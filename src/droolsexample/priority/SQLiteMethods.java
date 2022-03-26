package droolsexample.priority;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class SQLiteMethods {

	private Connection sqlite_connection;
	

	// -----> INSERT METHODS <-----
	
	public SQLiteMethods(Connection sqlite_connection) {
		this.sqlite_connection = sqlite_connection;
	}
	
	public void Insert_new_resource(String name, String priority, Float price) {
        try {
            String table = "INSERT INTO Resource (resource_name, priority, price) " + "VALUES (?,?,?);";
            PreparedStatement template = this.sqlite_connection.prepareStatement(table);
            template.setString(1, name);
            template.setString(2, priority);
            template.setFloat(3, price);
            template.executeUpdate();
            
        } catch(SQLException new_resource_error) {
            new_resource_error.printStackTrace(); //esto o nos lo creamos o nos hacemos el error por defecto
        }
    }
	
	public void Insert_new_department(Department department) {
        try {
            String table = "INSERT INTO Department (department_name, npatients, ratio, avghours, nemployees) " + " VALUES(?,?,?,?,?);";
            PreparedStatement template = this.sqlite_connection.prepareStatement(table);
            template.setString(1, department.getName());
            template.setInt(2, department.getNpatients());
            template.setFloat(3, department.getRatio());
            template.setInt(4, department.getAvghours());
            template.setInt(5, department.getNemployees());
            template.executeUpdate();
            
            LinkedList<Resource> wishlistshopping = department.getWishlistshopping();
            for(Resource resource: wishlistshopping ) {
                table = "INSERT INTO Department-Resource (department_name, resource _name) " 
                        + "VALUES (?,?);";
                template = this.sqlite_connection.prepareStatement(table);
                template.setString(1, department.getName());
                template.setString(2, resource.getName());
                template.executeUpdate();
                template.close();
            }
            
        } catch (SQLException insert_department_error) {
            insert_department_error.printStackTrace(); //lo mismo que puse antes de los errores
        }
}
	
	
	public boolean Update_resource(Resource resource) {
        try {
            String SQL_code = "UPDATE Resource SET priority = ?, price = ? WHERE resource_name = ?";
            PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
            template.setString(1, resource.getName());
            //template.setString(2, resource.getPriority());
            template.setFloat(3, resource.getPrice());
            template.executeUpdate();
            template.close();
            return true;
        } catch (SQLException update_resource_error) {
            update_resource_error.printStackTrace();
            return false;
        }
    }

	public boolean Update_department(Department department) {
        try {
            String SQL_code = "UPDATE Department SET npatients = ?, ratio = ?, avghours = ?, nemployees = ?, cartWeigth = ?, priorityLevel= ?, expenses= ?, isHighest = ?  WHERE department_name = ?";
            PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
            template.setString(1, department.getName());
            template.setInt(2, department.getNpatients());
            template.setFloat(3, department.getAvghours());
            template.setInt(4, department.getNemployees());
            template.setInt(5, department.getCartWeigth());
            template.setFloat(6, department.getPriorityLevel());
            template.setFloat(7, department.getExpenses());
            template.setBoolean(8, department.getIsHighest());
            template.executeUpdate();
            template.close();
            return true;
        } catch (SQLException update_department_error) {
            update_department_error.printStackTrace();
            return false;
        }
    } 
	
	public boolean Delete_department(Department department) {
        try {
            String SQL_code = "DELETE FROM Department WHERE department_name = ?;";
            PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
            template.setString(1, department.getName());
            template.executeUpdate();
            template.close();
            return true;
        } catch (SQLException delete_department_error) {
            delete_department_error.printStackTrace();
            return false;
        }
    }
}