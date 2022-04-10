
import java.util.Scanner;

public class paymentType {

	public static void main(String[] args) {
		
	Scanner entry = new Scanner(System.in);
	System.out.println("Enter which currency you would like to use ('CASH','CARD' and 'APPLEPAY' accepted): ");
	
	String type = entry.nextLine();
	entry.close();
	switch (type) {
		case "CASH":
			System.out.println("You have chosen to pay with CASH");
	        break;
	     case "CARD":
	        System.out.println("You have chosen to pay with CARD");
	        break;
	     case "APPLEPAY":
	    	 System.out.println("You have chosen to pay with APPLEPAY");
	    	 break;
	     default:
	    	 System.out.println("You have entered an invalid option, try again.");
		}
	 }
	}




