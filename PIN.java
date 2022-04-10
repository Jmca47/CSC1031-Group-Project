package part01;
import java.util.Scanner;

public class PIN {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Pin Code: ");
		
		int pin = scan.nextInt();
		
		if (pin == 2022) {
			System.out.println("Welcome!");
		} else {
			System.out.println("Incorrect Pin Code");
		}

	}

}
