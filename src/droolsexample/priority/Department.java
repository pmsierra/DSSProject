package droolsexample.priority;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import droolsexample.priority.Resource;




public class Department {
	







private String name;
private Integer npatients;
private Float ratio;
private Integer avghours;
private Integer nemployees;

LinkedList<Resource> wishlistshopping;
private Integer cartWeight;
Float priorityLevel;
private Float expenses;
private Boolean isHighest;

private Integer user_id;

public Department() {
	super();
	// TODO Auto-generated constructor stub
}

public Department(String name,Integer npatients, Float ratio, Integer avghours, Integer nemployees,
		LinkedList<Resource> wishlistshopping, Float expenses, Boolean isHighest) {
	super();
	this.name = name;
	this.npatients = npatients;
	this.ratio = ratio;
	this.avghours = avghours;
	this.nemployees = nemployees;
	this.wishlistshopping = wishlistshopping;
	this.expenses = expenses;
	this.isHighest = isHighest;
	this.cartWeight = 0;
	
	this.calculatePriority();
}

public Department(String name, Integer npatients, Float ratio, Integer avghours, Integer nemployees,
			Integer cartWeight, Float priorityLevel,Boolean isHighest, Integer user_id, LinkedList<Resource> wishlistshopping) {
		super();
		this.name = name;
		this.npatients = npatients;
		this.ratio = ratio;
		this.avghours = avghours;
		this.nemployees = nemployees;
		this.cartWeight = cartWeight;
		this.isHighest = isHighest;
		this.user_id = user_id;
		this.priorityLevel = priorityLevel;
		this.wishlistshopping = wishlistshopping;
		this.calculatePriority();
		
	}

public Department(String name, Integer npatients, Float ratio, Integer avghours, Integer nemployees,
		Boolean isHighest, Integer user_id) {
	super();
	this.name = name;
	this.npatients = npatients;
	this.ratio = ratio;
	this.avghours = avghours;
	this.nemployees = nemployees;
	this.isHighest = isHighest;
	this.user_id = user_id;
	

}

/*public void cartWeight() {
	this.cartWeight=0;
	for(int i =0; i<this.wishlistshopping.size(); i++) {
		

		if(this.wishlistshopping.get(i).getPriority().equals(Priorities.HIGH)) {
			
			this.cartWeight = this.cartWeight + 10;
		}
		if(this.wishlistshopping.get(i).getPriority().equals(Priorities.MEDIUM)) {
			
			this.cartWeight = this.cartWeight + 5;
		}
		else {
			this.cartWeight = this.cartWeight + 2;

		}
	}*/
	
	public void cartWeight() {
		this.cartWeight=0;
		for(int i =0; i<this.wishlistshopping.size(); i++) {
			

			if(this.wishlistshopping.get(i).getPriority().equals("HIGH")) {
				
				this.cartWeight = this.cartWeight + 10;
			}
			if(this.wishlistshopping.get(i).getPriority().equals("MEDIUM")) {
				
				this.cartWeight = this.cartWeight + 5;
			}
			else {
				this.cartWeight = this.cartWeight + 2;

			}
		}
	}
		
	

public void priorityValueCalculation() {
	this.priorityLevel=(float) (this.npatients*0.23+this.ratio*0.34+this.avghours*0.10+this.nemployees*0.037+this.cartWeight*0.285);
}
public void calculatePriority() {
	this.cartWeight();
	this.priorityValueCalculation();
}
public Integer getNpatients() {
	return npatients;
}
public void setNpatients(Integer npatients) {
	this.npatients = npatients;
}
public Float getRatio() {
	return ratio;
}
public void setRatio(Float ratio) {
	this.ratio = ratio;
}
public Integer getAvghours() {
	return avghours;
}
public void setAvghours(Integer avghours) {
	this.avghours = avghours;
}
public Integer getNemployees() {
	return nemployees;
}
public void setNemployees(Integer nemployees) {
	this.nemployees = nemployees;
}
public LinkedList<Resource> getWishlistshopping() {
	return wishlistshopping;
}
public void setWishlistshopping(LinkedList<Resource> wishlistshopping) {
	this.wishlistshopping = wishlistshopping;
	this.calculatePriority();
}
public Float getPriorityLevel() {
	return priorityLevel;
}
public void setPriorityLevel(Float priorityLevel) {
	this.priorityLevel = priorityLevel;
}
public float getExpenses() {
	return expenses;
}
public void setExpenses(float expenses) {
	this.expenses = expenses;
}

public Integer getcartWeight() {
	return cartWeight;
}

public void setcartWeight(Integer cartWeight) {
	this.cartWeight = cartWeight;
}

public Boolean getIsHighest() {
	return isHighest;
}

public void setIsHighest(Boolean isHighest) {
	this.isHighest = isHighest;
}

public void setExpenses(Float expenses) {
	this.expenses = expenses;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getUser_id() {
	return user_id;
}

public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}

/*
 * public void orderWishlist() { LinkedList<Resource> high; LinkedList<Resource>
 * medium; LinkedList<Resource> low; for(int
 * i=0;i<this.wishlistshopping.size();i++) {
 * if(this.wishlistshopping.get(i).getPriority() == Priorities.HIGH) {
 * high.add(); } }
 */
}

