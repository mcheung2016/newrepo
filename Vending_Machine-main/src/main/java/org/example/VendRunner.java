package org.example;

import java.io.IOException;

public class VendRunner {

	public static void main(String[] args) {



		try {
			VendingMachine v1 = new VendingMachine();
			v1.loadProductsFromCSV("products.csv");

			v1.displayProducts();
			v1.dispenseProduct("B3");
			v1.dispenseProduct("B3");
			v1.dispenseProduct("B3");
			v1.dispenseProduct("B3");


//			Snack snack1 = new Snack("Snickers", 2.00, true);
//
//
//			Slot<Snack> slot1 = new Slot<Snack>(snack1, 3);
//
//
//			VendingMachine vendingMachine = new VendingMachine();
//			vendingMachine.addProduct("F1", slot1);
//			vendingMachine.dispenseProduct("F1");
//			vendingMachine.dispenseProduct("F1");
//			vendingMachine.dispenseProduct("F1");
//			vendingMachine.dispenseProduct("F1");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
