package droolsexample.priority;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
	public void Insert_new_hospital(String hospitalName, float budget) {
        try {
            String table = "INSERT INTO Hospital (hospitalName, budget) " + "VALUES (?,?);";
            PreparedStatement template = this.sqlite_connection.prepareStatement(table);
            template.setString(1, hospitalName);
            template.setFloat(2, budget);
            template.executeUpdate();

        } catch(SQLException new_resource_error) {
            new_resource_error.printStackTrace(); //esto o nos lo creamos o nos hacemos el error por defecto
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
	
	public boolean Update_hospital(Hospital hospital) {
        try {
            String SQL_code = "UPDATE Hospital SET  budget = ? WHERE hospitalName  = ?";
            PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
            template.setFloat(1, hospital.getBudget());
            template.setString(2, hospital.getHospitalName());
            template.executeUpdate();
            template.close();
            return true;
        } catch (SQLException update_hospital_error) {
            update_hospital_error.printStackTrace(); //same as always, no existen estos errores concretos
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
	
	
	
	public Resource Search_resource_by_name (String resourceName ) {
		try {
			String SQL_code = "SELECT * FROM Resource WHERE resourceName LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, resourceName);
			Resource resource= new Resource();
			ResultSet result_set = template.executeQuery();
			result_set.next();
           	resource.setPriority(result_set.getString("priority"));
            resource.setPrice(result_set.getFloat("price"));
            resource.setName(resourceName);
			template.close();
			return resource;
		} catch (SQLException search_resource_error) {
			search_resource_error.printStackTrace();
			return null;
	}
}




public Hospital Search_hospital_by_name (String hospitalName ) {
		try {
			String SQL_code = "SELECT * FROM Hospital WHERE hospitalName LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, hospitalName);
			Hospital hospital= new Hospital();
			ResultSet result_set = template.executeQuery();
			result_set.next();
            hospital.setBudget(result_set.getFloat("budget"));
            hospital.setHospitalName(hospitalName);
			template.close();
			return hospital;
		} catch (SQLException search_hospital_error) {
			search_hospital_error.printStackTrace();
			return null;
	}
}

public Department Search_department_by_name (String departmentName) {
	try {
		String SQL_code = "SELECT * FROM Department WHERE departmentName LIKE ?";
		PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
		template.setString(1,departmentName);
		Department department= new Department();
		ResultSet result_set = template.executeQuery();
		result_set.next();
       	department.setNpatients(result_set.getInt("npatients"));
        department.setRatio(result_set.getFloat("price"));
		department.setAvghours(result_set.getInt("avghours"));
		department.setNemployees(result_set.getInt("nemployees"));
		department.setCartWeigth(result_set.getInt("cartWeigth"));
        department.setName(departmentName);
		template.close();
		return department;
	} catch (SQLException search_department_error) {
		search_department_error.printStackTrace();
		return null;
}
}
public List<Department> List_all_departments() {
    List<Department> departments = new LinkedList<Department>();
    try {
        Statement statement = this.sqlite_connection.createStatement();
        String SQL_code = "SELECT * FROM Department";
        ResultSet rs = statement.executeQuery(SQL_code);
        while(rs.next()) {
            String departmentName = rs.getString("departmentName ");
            Integer npatients= rs.getInt("npatients");
            Float ratio = rs.getFloat("ratio");
            Integer avghours= rs.getInt("avghours");
            Integer nemployees= rs.getInt("nemployees");
            Integer cartWeight= rs.getInt("cartWeight");
            Float priorityLevel = rs.getFloat("priorityLevel ");
            Boolean isHighest = rs.getBoolean("isHighest ");
            Integer user_id= rs.getInt("user_id");


            departments.add(new Department(departmentName , npatients, ratio, avghours, nemployees, cartWeight, priorityLevel , isHighest, user_id));
        }
        return departments;
    } catch (SQLException search_departments_error) {
        search_departments_error.printStackTrace(); //lo mismo de los errores
        return null;
    }

}


public List<Resource> List_all_resources() {
    List<Resource> resources= new LinkedList<Resource>();
    try {
        Statement statement = this.sqlite_connection.createStatement();
        String SQL_code = "SELECT * FROM Resource";
        ResultSet rs = statement.executeQuery(SQL_code);
        while(rs.next()) {
            String resourceName= rs.getString("resourceName");
            String priority = rs.getString("priority");
            Float price = rs.getFloat("price");
            
            resources.add(new Resource(resourceName, priority, price));
        }
        return resources;
    } catch (SQLException search_resources_error) {
        search_resources_error.printStackTrace(); //lo mismo de los errores
        return null;
    }

}

}