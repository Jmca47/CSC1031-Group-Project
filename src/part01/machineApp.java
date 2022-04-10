package part01;

public class machineApp {

	static vendingMachine myMachine = new vendingMachine();
	// This class is used to call Vending Machine and run it

	final static String options[] = { "Buy Item", "Machine Location", "Add Item", "Store To CSV", "Exit", };
	final static String title = "\nBoolean Hooligans Vending Machine";
	static Menu myMenu = new Menu(title, options);
	static final int QUIT = options.length;

	public static void main(String[] args) {
		boolean flag = false;
		int choice;
		while (flag == false) {
			if (myMachine.checkPin()) {
				flag = true;
				do {
					choice = myMenu.getUserChoice();
					if (choice != QUIT) {
						processChoice(choice);
					}
				} while (choice != QUIT); // or while (quit == false)
				System.out.println("\nGoodbye!");
			} else {
				flag = false;
			}
		}
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
				myMachine.addItem(null, choice, choice);
				break;

			case 4:
				myMachine.storeItems();
				break;
			default:
				System.out.println("Option " + choice + " is invalid.");
		}
		System.out.println();
	}

}
