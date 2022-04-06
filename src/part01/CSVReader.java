package part01;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.List; 

public class CSVReader {
	
	public static void main(String[] args) {
		List<Item> items = readItemsFromCSV("items.txt");
		
		for (Item i : items) {
			System.out.println(i);
		}
	}
	
	private static List<Item> readItemsFromCSV(String fileName){
		List<Item> items = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
			
			String line = br.readLine();
			
			while (line != null) {
				String[] attributes = line.split(",");
				
				Item item = createItem(attributes);
				
				items.add(item);
				
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return items;
	}
	
	private static Item createItem(String[] data) {
		String name = data[0];
		int price = Integer.parseInt(data[1]);
		
		return new Item(name, price);
	}
	
	

}
