import java.io.*;
import java.util.*;

public class ProductStorage {
    
    // quantity here is the amount you want to add (positive) or subtract (negative)
    public void updateStock(String name, int quantity) {
        try {
            File file = new File("products.txt");
            ArrayList<String> allProducts = new ArrayList<>();
            boolean found = false;

            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(","); 
                    
                    String itemName = parts[0];
                    int currentQuantity = Integer.parseInt(parts[1]); 

                    if (itemName.equals(name)) {
                        currentQuantity = currentQuantity + quantity;
                        
                        // this is for the case when the quantity goes below 0, we set it to 0 instead of negative
                        if (currentQuantity < 0) {
                            currentQuantity = 0; 
                        }
                        found = true;
                    }
                    
                    allProducts.add(itemName + "," + currentQuantity);
                }
                scanner.close();
            }

            // If the product was not found in the file, add it as a new entry
            if (!found) {
                // it is optional. u can remove that if u want
                if (quantity < 0) {
                    quantity = 0;
                }
                allProducts.add(name + "," + quantity);
            }

            // save the updated list back to the file
            PrintWriter writer = new PrintWriter(file);
            for (String item : allProducts) {
                writer.println(item);
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error updating file.");
        }
    }

    public void viewAvailableStock() {
        try {
            File file = new File("products.txt");
            
            if (!file.exists()) {
                System.out.println("No bakery items available yet.");
                return; 
            }
            
            Scanner scanner = new Scanner(file);
            System.out.println("--- Available Bakery Items ---");
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                
                String itemName = parts[0];
                int currentQuantity = Integer.parseInt(parts[1]);
                
                System.out.println(itemName + ": " + currentQuantity);
            }
            scanner.close();
            
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }
}