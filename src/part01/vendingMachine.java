package part01;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class vendingMachine {
	static String csvFilePath = "Items.csv";
	// ---------- //
	// Pin to enter machine
	static int pin = 2022;
	// ---------- //

	final static String options[] = { "Customer Mode", "Management Mode", "Refill Mode", "Exit" }; // Not implemented
																									// yet.

	// final static String title = "\nBoolean Hooligans Vending Machine";
	// static Menu myMenu = new Menu(title, options);

	static final int QUIT = options.length;

	private static ArrayList<item> itemData;
	static Scanner input = new Scanner(System.in);
	public static int moneyAdded;

	public vendingMachine() {
		vendingMachine.itemData = new ArrayList<item>();
		// defaultItems(); // Function to Add default items (eg chocolate coke fanta)
		// into machine.
	}

	/*
	 * Missing features
	 * 
	 * - When the item is purchased, it needs to ask the user to enter coins, and
	 * loop until enough coins has been input, which it will then calculate change.
	 * - The displayItems doesnt display the items like a vending machine would it
	 * just appears in a list. - Refill and management mode not implemented at all.
	 * These need to be separate classes altogether.
	 */

	// Display the items in the machine
	public String[] displayItems() {
		ArrayList<item> itemData = readItemsFromCSV(csvFilePath, true);
		currency();
		System.out.println("\nListing all Items.");
		String data[] = new String[itemData.size()];
		int index = 0;
		String newInput;
		// If itemData isn't empty, call the .toString() method to print the list of
		// items onto screen
		if (itemData.size() == 0) {
			System.out.println("No items in machine.");
		} else {
			for (item newItem : itemData) {
				data[index++] = newItem.toString();
			}

			// Purchase item via its position on the arraylist
			boolean flag = true;
			while (flag) {
				try {
					System.out.print("Enter ID to Purchase: ");
					newInput = input.nextLine();
					int itemId = Integer.parseInt(newInput);
					if (itemId > itemData.size() - 1) {
						System.out.println("\nNot within range, try again\n");
						flag = true;
					} else {
						purchaseItem(itemId);
						flag = false;
					}

				} catch (NumberFormatException e) { // User didnt input a valid integer
					System.out.println("\nInvalid input - try again\n");
					flag = true;

				}
			}
		}

		return data;
	}

	// Using itemId, call play method for a tune, return null if doesn't exist.
	public String purchaseItem(int itemId) {
		String str = "";

		for (item newItem : itemData) {
			int value = newItem.getId();
			double itemPrice = newItem.getPrice();
			if (itemId == value) {
				while (moneyAdded < itemPrice) {
					System.out.println(moneyAdded + "p");
					insertCoin();
				}
				double changeDue = moneyAdded - itemPrice;
				System.out.println(changeDue + " change due");
				double changeReturned[] = calculateChange(changeDue);
				int i = 0;
				// return the change due
				while (changeReturned.length > i) {
					if (i == 0 && changeReturned[i] > 0) {
						double twoPoundDBL = changeReturned[i];
						int twoPoundInt = (int) twoPoundDBL;
						str += twoPoundInt + " ???2 returned \n";
					}
					if (i == 1 && changeReturned[i] > 0) {
						double poundDBL = changeReturned[i];
						int poundInt = (int) poundDBL;
						str += poundInt + " ???1 returned \n";
					}
					if (i == 2 && changeReturned[i] > 0) {
						double fiftyDBL = changeReturned[i];
						int fiftyInt = (int) fiftyDBL;
						str += fiftyInt + " 50p returned \n";
					}
					if (i == 3 && changeReturned[i] > 0) {
						double twentyDBL = changeReturned[i];
						int twentyInt = (int) twentyDBL;
						str += twentyInt + " 20p returned \n";
					}
					i++;
				}
				System.out.println("\n" + str);
				newItem.purchase();
				break;
			}
		}
		return str;
	}

	// return location of machine
	public void location() {
		String[] array = { "38.9072??? N, 77.0369??? W", "51.5007??? N, 0.1246??? W", "43.7230??? N, 10.3966??? E",
				"40.4530??? N, 3.6883??? W", "41.3809??? N, 2.1228??? E", "53.4621??? N, 2.2766??? W" };

		Random rand = new Random();
		// selects random int from the array
		int selection = rand.nextInt(array.length);

		System.out.println("Location: " + array[selection]);
	}

	public boolean checkPin() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Pin Code: ");
		boolean flag = false;
		int enteredPin = scan.nextInt();

		if (enteredPin == pin) {
			flag = true;
		} else {
			System.out.println("Incorrect Pin Code");
		}
		return flag;

	}

	// select currency
	public void currency() {
		readItemsFromCSV(csvFilePath, true);
		Scanner entry = new Scanner(System.in);
		System.out.println("Enter which currency you would like to use: \n1. ?? \n2. $ ");

		String currency = entry.nextLine();
		switch (currency) {
			case "1":
				System.out.println("You have chosen to pay in Pounds GDP (??)");
				changePrices(1);
				break;
			case "2":
				System.out.println("You have chosen to pay in Dollars USD ($)");
				changePrices(2);
				break;
		}
	}

	public void changePrices(int input) {
		int index = 0;
		if (input == 1) {
			do {
				if (index == itemData.size()) {
					return;
				} else {
					for (item newItem : itemData) {
						// Get the title
						double oldPrice = newItem.getPrice();

						newItem.setPrice(oldPrice * 0.8);
						index++;
					}
				}
			} while (index < itemData.size());

		} else if (input == 2) {

			do {
				if (index == itemData.size()) {
					return;
				} else {
					for (item newItem : itemData) {
						// Get the title
						double oldPrice = newItem.getPrice();

						newItem.setPrice(oldPrice * 0.8);
						index++;
					}
				}
			} while (index < itemData.size());

		}
	}

	// Insert the item into the array list of items.
	public static void insertItem(item newItem) {
		int count = itemData.size();
		itemData.add(count, newItem);
	}

	// Calculate the change to be given to customer
	public static double[] calculateChange(double inputtedMoney) {
		double money = inputtedMoney;
		double[] changeReturned = new double[4];

		// Calculate change returned
		if (money > 0) {
			double twopound, pound, fifty, twenty;

			twopound = money / 200;
			money = money % 200;

			pound = money / 100;
			money = money % 100;

			fifty = money / 50;
			money = money % 50;

			twenty = money / 20;
			money = money % 20;

			// Always round down value
			twenty = Math.floor(twenty);
			fifty = Math.floor(fifty);
			pound = Math.floor(pound);
			twopound = Math.floor(twopound);

			// Append the coins returned to an array
			if (twopound % 1 == 0) {
				changeReturned[0] = twopound;
			} else {
				changeReturned[0] = 0;
			}

			if (pound * 1 % 1 == 0) {
				changeReturned[1] = pound;
			} else {
				changeReturned[1] = 0;
			}

			if (fifty % 1 == 0) {
				changeReturned[2] = fifty;
			} else {
				changeReturned[2] = 0;
			}

			if (twenty % 1 == 0) {
				changeReturned[3] = twenty;
			} else {
				changeReturned[3] = 0;
			}

		} else {
			System.out.print("Invalid entry, please try again: ");
		}
		return changeReturned; // returns the change dispensed as array
	}

	// Function to insert coin into machine
	public int insertCoin() {
		int value = 0;
		boolean flag = true;
		while (flag) {
			try {
				System.out.print("Enter Coin: ");
				int coin = input.nextInt();
				value = coin;
				if (checkCoin(value)) {
					moneyAdded = moneyAdded + value;
				} else {
					System.out.println("Money in machine (Penny): " + moneyAdded);
					System.out.println("Invalid coin, enter 10, 20, 50, 100 or 200");
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input - returning to menu");
				flag = false;
				;
			}
		}
		return value;

	}

	// Method to check if the coin that was input is valid
	private static boolean checkCoin(int value) {
		int coin = value;
		boolean flag = false;
		if (coin == 20) {
			flag = true;
			return flag;
		}
		if (coin == 50) {
			flag = true;
			return flag;
		}
		if (coin == 100) {
			flag = true;
			return flag;
		}
		if (coin == 200) {
			flag = true;
			return flag;
		} else {
			flag = false;
		}
		return flag;
	}

	// This function is purely for testing purposes, it just creates a few classes
	// of different items to be added to the arraylist.
	public static void defaultItems() {
		item item1 = new item(null, 0, 0);
		item1.setName("Coke");
		item1.setPrice(150);
		item1.setQuantity(100);

		item item2 = new item(null, 0, 0);
		item2.setName("Fanta");
		item2.setPrice(150);
		item2.setQuantity(100);

		item item3 = new item(null, 0, 0);
		item3.setName("Chocolate");
		item3.setPrice(200);
		item3.setQuantity(100);

		insertItem(item1);
		insertItem(item2);
		insertItem(item3);

	}

	public static int getMoneyAdded() {
		return moneyAdded;
	}

	public static void setMoneyAdded(int moneyAdded) {
		vendingMachine.moneyAdded = moneyAdded;
	}

	public void addItem(String name, double price, int quantity){
		item newItem = new item(name, price, quantity);

		System.out.println("\n Add a new Item.");
		System.out.print("Enter Name: ");
		name = input.nextLine();
		newItem.setName(name);

		try {
			System.out.print("Enter Price: ");
			price = input.nextInt();
			newItem.setPrice(price);
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid value, must be double");
			input.nextLine();
		}

		try {
			System.out.print("Enter Quantity: ");
			quantity = input.nextInt();
			newItem.setQuantity(quantity);
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid value, must be int");
			input.nextLine();
		}
		insertItem(newItem);
		
	

	}
	// Load CSV File
	public static ArrayList<item> readItemsFromCSV(String fileName, boolean hasHeader) {
		// Vending machine Objects will be stored here and returned to calling method
		try {
			File myFile = new File(csvFilePath);
			Scanner mySc = new Scanner(myFile);
			if (hasHeader) {
				mySc.nextLine();

			}
			while (mySc.hasNextLine()) {
				String record = mySc.nextLine();
				String[] data = record.split(",");

				String name = data[0].trim();
				double price = Double.parseDouble(data[1]);
				int quantity = Integer.parseInt(data[2]);

				// Add a new tune to the array list to be returned.
				if (!checkName(name)) {
					itemData.add(new item(name, price, quantity));
				}
			}

			mySc.close();
			return itemData;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// A null object is returned in the event of any errors.
			return null;
		}
	}

	private static boolean checkName(String title) {
		boolean flag = false;
		for (item newItem : itemData) {
			// Get the title
			String value = newItem.getName();
			do {
				if (title.equals(value)) {
					// Matching title
					flag = true;
					return flag;
				} else {
					// The title doesn't match another title in the system
					flag = false;
					break;
				}
			} while (flag = false);
		}
		return flag;
	}

	// Store CSV File
	public void storeItems() {
		FileWriter myPw;
		try {
			myPw = new FileWriter(csvFilePath, true);
			myPw.append("Name, Price, Qty\n");

			for (item newItem : itemData) {
				myPw.append(newItem.getName() + ", ");
				myPw.append(newItem.getPrice() + ", ");
				myPw.append(newItem.getQuantity() + "\n");
			}
			System.out.println("Succesfully wrote to csvFile");
			myPw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}