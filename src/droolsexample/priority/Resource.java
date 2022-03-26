package droolsexample.priority;



public class Resource {
	
public enum Priorities  {HIGH, MEDIUM, LOW;}

private String resourceName;
private Priorities priority;
private Float price;


public Resource(String name, Priorities priority, Float price) {

	this.resourceName = name;
	this.priority = priority;
	this.price = price;
}


public String getName() {
	return resourceName;
}
public void setName(String name) {
	this.resourceName = name;
}
public Priorities getPriority() {
	return priority;
}
public void setPriority(Priorities priority) {
	this.priority = priority;
}
public Float getPrice() {
	return price;
}
public void setPrice(Float price) {
	this.price = price;
}
@Override
public String toString() {
	return "Resource [name=" + resourceName + ", priority=" + priority + ", price=" + price + "]";
}

}