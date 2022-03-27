package droolsexample.priority;



public class Resource {
	

	private String name;
	private String priority;
	private Float price;
	
	public Resource() {
		super();
		}
	
	
	public Resource(String name, String priority, Float price) {
	
		this.name = name;
		this.priority = priority;
		this.price = price;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	@Override
	public String toString() {
		return "Resource [name=" + name + ", priority=" + priority + ", price=" + price + "]";
	}
	
}