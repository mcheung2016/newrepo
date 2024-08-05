package org.example;

public class Slot <T extends Product> {
	private T product;
	private int quantity;

	public Slot() {	}

	public Slot(T product, int quantity) {
		if(product == null || quantity < 0) {
			throw new IllegalArgumentException("Product must be valid and quantity must be positive");
		}
		this.product = product;
		this.quantity = quantity;
	}

	public T getProduct() {
		return product;
	}

	public void setProduct(T product) {
		if(product == null || quantity < 0) {
			throw new IllegalArgumentException("Product must be valid and quantity must be positive");
		}
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//fori loop, each iteration prints "Slot[i}
	@Override
	public String toString() {
		return "Slot{" +
			"product=" + product +
			", quantity=" + quantity +
			'}';
	}

}
