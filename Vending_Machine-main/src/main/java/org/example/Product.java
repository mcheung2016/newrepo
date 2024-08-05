package org.example;

public class Product {
	//public or private?
	private String name;
	private double price;

	public Product(String name, double price) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Product name cannot be null or empty" +
				"cannot be negative");
		}
		if(price < 0) {
			throw new IllegalArgumentException("Product price cannot be negative");
		} else {
			this.name = name;
			this.price = price;
		}
	}
	public Product(String code, String name, double price, int quantity) {

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Product name cannot be null or empty");
		}
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if(price < 0) {
			throw new IllegalArgumentException("Product price cannot be negative");
		}
		this.price = price;
	}


	@Override
	public String toString() {
		return "Product{" +
			"name='" + name + '\'' +
			", price=" + price +
			'}';
	}
}

/*

 */
