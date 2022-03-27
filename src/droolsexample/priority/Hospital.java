package droolsexample.priority;

import java.util.Comparator;
import java.util.LinkedList;

public class Hospital {
	
	private String hospitalName;
	private int user_id;
	private LinkedList<Department> hospitalList;
	private Department highestDepartment;
	private Float budget;
	LinkedList<Resource> bougthItems;
	LinkedList<Department> departmentOrder;
	
	
	
	public Hospital() {
		super();
	}
	
	public Hospital(String hospitalName, Float budget) {
		super();
		this.hospitalName = hospitalName;
		this.budget = budget;
	}
	
	public Hospital(LinkedList<Department> hospitalList, Float budget,
			LinkedList<Resource> bougthItems, LinkedList<Department> departmentOrder) {
		super();
		this.hospitalList = hospitalList;
		this.budget = budget;
		this.bougthItems = bougthItems;
		this.departmentOrder=departmentOrder;
		this.calculatePriorityList();
	}
	
	public Hospital(String hospitalName2, LinkedList<Department> departments, Float budget2, LinkedList<Resource> bougthItems, LinkedList<Department> departmentOrder, Integer user_id) {
		
		super();
		this.hospitalName = hospitalName2;
		this.hospitalList = departments;
		this.budget = budget2;
		this.bougthItems = bougthItems;
		this.departmentOrder=departmentOrder;
		this.user_id= user_id;
		
	}
	
	
	
	public Department calculatePriorityList() {
			
		if(this.hospitalList.size()==0) {
			this.highestDepartment.setIsHighest(false);
			return highestDepartment;
		}
			this.hospitalList.getFirst().setIsHighest(false);
			this.hospitalList.sort(Comparator.comparing(Department::getPriorityLevel).reversed());
			System.out.println("New first is: " + this.hospitalList.getFirst());
			this.hospitalList.getFirst().setIsHighest(true);
			this.setHighestDepartment(this.hospitalList.getFirst());
	
		
		return highestDepartment;
	}
	public LinkedList<Department> getHospitalList() {
		return this.hospitalList;
	}
	
	public void lowBudget(LinkedList departmentList) {
		
		for (int i=0; i<departmentList.size();i++) {
			Department department= (Department) departmentList.get(i);
			LinkedList wishlist = department.getWishlistshopping();
			for (int j=0;j<wishlist.size();j++) {
				Resource resource = (Resource) wishlist.get(j);
				if(resource.getPrice()>this.budget) {
					wishlist.remove(j);
				}
			}
		}
	}
	
	public void conclude() {
		
		String conclusion="";
		for(int i = 0; i<this.departmentOrder.size();i++ ) {
			conclusion = conclusion + this.departmentOrder.get(i).getName()+ " department bougth: " + this.bougthItems.get(i).getName()+" for: " +this.bougthItems.get(i).getPrice() + "\n";
			
		
		}
		conclusion = conclusion + "Final budget: " + this.budget;
		System.out.println(conclusion);
		
	}
	
	
	
	public void setHospitalList(LinkedList<Department> hospitalList) {
		this.hospitalList = hospitalList;
	}
	
	public Department getHighestDepartment() {
		return highestDepartment;
	}
	
	public void setHighestDepartment(Department highestDepartment) {
		this.highestDepartment = highestDepartment;
	}
	
	
	public Float getBudget() {
		return budget;
	}
	public void setBudget(Float budget) {
		this.budget = budget;
	}
	public LinkedList<Resource> getBougthItems() {
		return bougthItems;
	}
	public void setBougthItems(LinkedList<Resource> bougthItems) {
		this.bougthItems = bougthItems;
	}
	
	public LinkedList<Department> getDepartmentOrder() {
		return departmentOrder;
	}
	public void setDepartmentOrder(LinkedList<Department> departmentOrder) {
		this.departmentOrder = departmentOrder;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	
	@Override
	public String toString() {
		return "Hospital [hospitalName=" + hospitalName + ", user_id=" + user_id + ", hospitalList=" + hospitalList
				+ ", highestDepartment=" + highestDepartment + ", budget=" + budget + ", bougthItems=" + bougthItems
				+ ", departmentOrder=" + departmentOrder + "]";
	}
}