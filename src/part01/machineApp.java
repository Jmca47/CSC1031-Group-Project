package part01;

public class machineApp {

	static vendingMachine myMachine = new vendingMachine();
	// This class is used to call Vending Machine and run it

	final static String options[] = { "Buy Item", "Machine Location", "Select Currency", "Exit", };
	final static String title = "\nBoolean Hooligans Vending Machine";
	static Menu myMenu = new Menu(title, options);
	static final int QUIT = options.length;

	public static void main(String[] args) {
		int choice;
		do {
			choice = myMenu.getUserChoice();
			if (choice != QUIT) {
				processChoice(choice);
			}
		} while (choice != QUIT); // or while (quit == false)
		System.out.println("\nGoodbye!");
	}

	private static void processChoice(int choice) {
		switch (choice) {
			case 1:
				myMachine.displayItems();
				break;
			case 2:
				myMachine.location();
				break;

			case 3:
				myMachine.currency();
				break;
			default:
				System.out.println("Option " + choice + " is invalid.");
		}
		System.out.println();
	}

}
