package part01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private String items[];
	private String title;
	private Scanner input;

	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	private void display() {
		System.out.println(title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		System.out.println();
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		System.out.println();

	}

	public int getUserChoice() {
		int value = 0;
		boolean loop = true;
		while (loop) {
			try {
				display();
				System.out.print("Enter Selection: ");
				value = input.nextInt();
				loop = false;
			} catch ( InputMismatchException e){
				System.out.println("Invalid value");
				input.next();
			}
		}
		return value;	

	}
}

