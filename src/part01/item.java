package part01;
// Item is a class which will be used to create a new item in the vending machine. Eg coke, chocolate etc will each be a class belonging to the vending machine.
public class item {
	
	private double price;
	private String name;
	private int id;
	private int quantity;
	private static int nextId = 0;
	
	// Constructor class
	public item(String name, double price, int quantity) {
		setName(name);
		setPrice(price);
		setQuantity(quantity);
		this.id = nextId;
		nextId++;
	}
	
	
	// Purchase item. Removes 1 from the quantity of the item.
	public String purchase() {
		String str = "You purchased... " + this.getName() + ", for " + this.getPrice();
		quantity--;
		System.out.println(str);
		return str;
	}
	// test
	// Returns a string containing all the info of the class
	public String toString() {
		String str = "ID: "+this.getId()+ "Item Name: " + this.getName() + " Price: " + this.getPrice() + " Qty: " + this.getQuantity();
		System.out.println(str);
		return str;
	}

	// Getters and setters
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return this.id = id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public static int getNextId() {
		return nextId;
	}


	public static void setNextId(int nextId) {
		item.nextId = nextId;
	}
	
	
	
	

}


