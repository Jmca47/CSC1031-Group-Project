import java.util.*;

public class paymentCurrency {

	public static void main(String[] args) {
		
	Scanner entry = new Scanner(System.in);
	System.out.println("Enter which currency you would like to use (£/$/¥ accepted): ");
	
	String currency = entry.nextLine();
	entry.close();
	switch (currency) {
	case "£":
		System.out.println("You have chosen to pay in Pounds GDP (£)");
        break;
     case "$":
        System.out.println("You have chosen to pay in Dollars USD ($)");
        break;
     case "¥":
    	 System.out.println("You have chosen to pay in Yuan CNY (¥)");
    	 break;
		
	}
	}

}
