import java.util.Random;

public class location {

	public static void main(String[] args) {
		String [] array = {"38.9072° N, 77.0369° W", "51.5007° N, 0.1246° W", "43.7230° N, 10.3966° E", "40.4530° N, 3.6883° W", "41.3809° N, 2.1228° E","53.4621° N, 2.2766° W"};

		Random rand = new Random();
		//selects random int from the array
		int selection = rand.nextInt(array.length);
		
		System.out.println("Location: " + array[selection]);
	}

}


