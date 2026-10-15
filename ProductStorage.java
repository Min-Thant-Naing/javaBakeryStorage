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
                    // Changed to currentQuantity to avoid mixing up the names
                    int currentQuantity = Integer.parseInt(parts[1]); 

                    if (itemName.equals(name)) {
                        currentQuantity = currentQuantity + quantity;
                        
                        if (currentQuantity < 0) {
                            currentQuantity = 0; 
                        }
                        found = true;
                    }
                    
                    allProducts.add(itemName + "," + currentQuantity);
                }
                scanner.close();
            }

            if (!found) {
                if (quantity < 0) {
                    quantity = 0;
                }
                allProducts.add(name + "," + quantity);
            }

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
                
                if (currentQuantity > 0) {
                    System.out.println(itemName + ": " + currentQuantity);
                }
            }
            scanner.close();
            
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }
}