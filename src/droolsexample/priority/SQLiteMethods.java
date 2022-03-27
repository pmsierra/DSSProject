package droolsexample.priority;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SQLiteMethods {

	private Connection sqlite_connection;
	

	// -----> INSERT METHODS <-----
	
	public SQLiteMethods(Connection sqlite_connection) {
		this.sqlite_connection = sqlite_connection;
	}
	
	public void Insert_new_resource(Resource resource) {
        try {
            String table = "INSERT INTO Resource (resourceName, priority, price) " + "VALUES (?,?,?);";
            PreparedStatement template = this.sqlite_connection.prepareStatement(table);
            template.setString(1, resource.getName());
            template.setString(2, resource.getPriority());
            template.setFloat(3, resource.getPrice());
            template.executeUpdate();
            
        } catch(SQLException new_resource_error) {
            new_resource_error.printStackTrace(); 
        }
    }

	
	public void Insert_new_department(Department department) {
        try {
            String table = "INSERT INTO Department (departmentName, npatients, ratio, avghours, nemployees) " + " VALUES(?,?,?,?,?);";
            PreparedStatement template = this.sqlite_connection.prepareStatement(table);
            String name = department.getName();
            template.setString(1, name);
            template.setInt(2, department.getNpatients());
            template.setFloat(3, department.getRatio());
            template.setInt(4, department.getAvghours());
            template.setInt(5, department.getNemployees());
            template.executeUpdate();
            
            LinkedList<Resource> wishlistshopping = department.getWishlistshopping();
            for(Resource resource: wishlistshopping ) {
                table = "INSERT INTO DepartmentResource (departmentName, resourceName) " 
                        + "VALUES (?,?);";
                template = this.sqlite_connection.prepareStatement(table);
                template.setString(1, name);
                template.setString(2, resource.getName());
                template.executeUpdate();
                template.close();
            }
            
            
        } catch (SQLException insert_department_error) {
            insert_department_error.printStackTrace(); 
        }
	}
	
	public void Insert_new_departmentresource(Department department) {
	    try {
	    	String table="";
	    	String name = department.getName();
	    	PreparedStatement template = this.sqlite_connection.prepareStatement(table);
	    	LinkedList<Resource> wishlistshopping = department.getWishlistshopping();
            for(Resource resource: wishlistshopping ) {
                table = "INSERT INTO DepartmentResource (departmentName, resourceName) " 
                        + "VALUES (?,?);";
                template = this.sqlite_connection.prepareStatement(table);
                template.setString(1, name);
                template.setString(2, resource.getName());
                template.executeUpdate();
                template.close();
            }
	        
	    } catch(SQLException new_resource_error) {
	        new_resource_error.printStackTrace();
	    }
	}
	
	public void Insert_new_hospital(Hospital hospital) {
        try {
            String table = "INSERT INTO Hospital (hospitalName, budget) " + "VALUES (?,?);";
            PreparedStatement template = this.sqlite_connection.prepareStatement(table);
            template.setString(1, hospital.getHospitalName());
            template.setFloat(2, hospital.getBudget());
            template.executeUpdate();

        } catch(SQLException new_resource_error) {
            new_resource_error.printStackTrace(); 
        }
    }
	
	
	// -----> UPDATES METHODS <-----
	
	public boolean Update_resource(Resource resource) {
        try {
            String SQL_code = "UPDATE Resource SET priority = ?, price = ? WHERE resourceName = ?";
            PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
            template.setString(1, resource.getPriority());
            template.setFloat(2, resource.getPrice());
            template.setString(3, resource.getName());
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
            String SQL_code = "UPDATE Department SET npatients = ?, ratio = ?, avghours = ?, nemployees = ?, cartWeight = ?, priorityLevel= ?, isHighest = ?, expenses= ? WHERE departmentName = ?";
            PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
            template.setInt(1, department.getNpatients());
            template.setFloat(2, department.getRatio());
            template.setFloat(3, department.getAvghours());
            template.setInt(4, department.getNemployees());
            template.setInt(5, department.getcartWeight());
            template.setFloat(6, department.getPriorityLevel());
            template.setBoolean(7, department.getIsHighest());
            template.setFloat(8, department.getExpenses());
            template.setString(9, department.getName());

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
            String SQL_code = "UPDATE Hospital SET  budget = ?, boughtItems = ?, departmentOrder=? WHERE hospitalName  = ?";
            PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
            template.setFloat(1, hospital.getBudget());
            template.setString(2, hospital.getHospitalName());
            template.setString(3, hospital.getBougthItems().toString());
            template.setString(4, hospital.getDepartmentOrder().toString());
            template.executeUpdate();
            template.close();
            return true;
        } catch (SQLException update_hospital_error) {
            update_hospital_error.printStackTrace(); 
            return false;
        }
    }
	
	
	// -----> DELETE METHODS <-----
	public boolean Delete_department(Department department) {
        try {
            String SQL_code = "DELETE FROM Department WHERE departmentName = ?;";
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
	
	public boolean Delete_resource(Resource resource) {
	    try {
	        String SQL_code = "DELETE FROM Resource WHERE resourceName = ?;";
	        PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
	        template.setString(1, resource.getName());
	        template.executeUpdate();
	        template.close();
	        return true;
	    } catch (SQLException delete_department_error) {
	        delete_department_error.printStackTrace();
	        return false;
	    }
	}

	public boolean Delete_departmentresource(Department department,Resource resource) {
	    try {
	        String SQL_code = "DELETE FROM DepartmentResource WHERE departmentName= ?;";
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
	
	
	
	// -----> SEARCH METHODS <-----
	
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
	            LinkedList<Department> hospitalList = (LinkedList<Department>) List_all_departments();
	            hospital.setHospitalList(hospitalList);
	            hospital.setHospitalName(hospitalName);
	            hospital.setHighestDepartment(hospital.calculatePriorityList());
	        	//LinkedList<Resource> purchaseList = new LinkedList<Resource>();
	        	//LinkedList<Department> departmentOrder = new LinkedList<Department>();
	            String bougthItems=result_set.getString("boughtItems");
	            List<String> purchaseList = Arrays.asList(bougthItems);
	            hospital.setBougthItems(purchaseList);
	            String departmentOrder = result_set.getString("departmentOrder");
	            List<String> decisionDepartments = Arrays.asList(departmentOrder);
	            hospital.setDepartmentOrder(decisionDepartments);
	
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
	        department.setRatio(result_set.getFloat("ratio"));
			department.setAvghours(result_set.getInt("avghours"));
			department.setNemployees(result_set.getInt("nemployees"));
			department.setcartWeight(result_set.getInt("cartWeight"));
	        department.setPriorityLevel(result_set.getFloat("priorityLevel"));
			department.setIsHighest(result_set.getBoolean("isHighest"));
			department.setUser_id(result_set.getInt("user_id"));
			department.setExpenses(result_set.getFloat("expenses"));
			
	        department.setName(departmentName);
			LinkedList<Resource> resource_list = Search_all_resources_from_department(departmentName);
			department.setWishlistshopping(resource_list);
			template.close();
			return department;
		} catch (SQLException search_department_error) {
			search_department_error.printStackTrace();
			return null;
	}
	}
	
	
		// -----> LIST METHODS <-----
	
	public List<Department> List_all_departments() {
	    List<Department> departments = new LinkedList<Department>();
	    try {
	        Statement statement = this.sqlite_connection.createStatement();
	        String SQL_code = "SELECT * FROM Department";
	        ResultSet rs = statement.executeQuery(SQL_code);
	        while(rs.next()) {
	            String departmentName = rs.getString("departmentName");
	            Integer npatients= rs.getInt("npatients");
	            Float ratio = rs.getFloat("ratio");
	            Integer avghours= rs.getInt("avghours");
	            Integer nemployees= rs.getInt("nemployees");
	            Integer cartWeight= rs.getInt("cartWeight");
	            Float priorityLevel = rs.getFloat("priorityLevel");
	            Boolean isHighest = rs.getBoolean("isHighest");
	            Integer user_id= rs.getInt("user_id");
	            Float expenses= rs.getFloat("expenses");
	            LinkedList<Resource> wishlist = Search_all_resources_from_department(departmentName);
	            
	            departments.add(new Department(departmentName , npatients, ratio, avghours, nemployees, cartWeight, priorityLevel , isHighest, user_id, wishlist, expenses));
	        }
	        return departments;
	    } catch (SQLException search_departments_error) {
	        search_departments_error.printStackTrace(); 
	        return null;
	    }
	
	}
	
	public List<Hospital> List_all_hospitals() {
	    List<Hospital> hospitals= new LinkedList<Hospital>();
	    try {
	        Statement statement = this.sqlite_connection.createStatement();
	        String SQL_code = "SELECT * FROM Hospital";
	        ResultSet rs = statement.executeQuery(SQL_code);
	        while(rs.next()) {
	            String hospitalName= rs.getString("hospitalName");
	            Float budget = rs.getFloat("budget");
	            String bougthItems= rs.getString("boughtItems");
	            String departmentOrder = rs.getString("departmentOrder");
	            List<String> purchaseList = Arrays.asList(bougthItems);
	        	//LinkedList<Department> linkedpurchase = (LinkedList<Department>)purchaseList;
	        	//LinkedList<Department> departmentsOrder = new LinkedList<Department>();
	            List<String> decisionDepartments = Arrays.asList(departmentOrder);
	            Integer user_id = rs.getInt("user_id");
	
	            LinkedList<Department> departments = (LinkedList<Department>) List_all_departments();
	
	
	            hospitals.add(new Hospital(hospitalName, departments, budget, purchaseList, decisionDepartments, user_id));
	        }
	        return hospitals;
	    } catch (SQLException search_hospitals_error) {
	        search_hospitals_error.printStackTrace(); 
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
	        search_resources_error.printStackTrace(); 
	        return null;
	    }
	
	}
	
	public LinkedList<Resource> Search_all_resources_from_department(String departmentName) {
		try {
			String SQL_code = "SELECT resourceName FROM DepartmentResource WHERE departmentName LIKE ?";
			PreparedStatement template = this.sqlite_connection.prepareStatement(SQL_code);
			template.setString(1, departmentName);
			ResultSet result_set = template.executeQuery();
			LinkedList<Resource> resource_list = new LinkedList<Resource>();
			while (result_set.next()) {
				Resource resource = Search_resource_by_name(result_set.getString("resourceName"));
				resource_list.add(resource);
			}
			return resource_list;
		} catch (SQLException list_department_resource_error) {
			list_department_resource_error.printStackTrace();
			return null;
		}
	}




}