import java.io.*;
import java.util.*;

public class UserStorage {

    // Checks if the typed username and password match exactly what is in the file
    public boolean login(String username, String password) {
        try {
            // File name changed here
            File file = new File("users data.txt");
            
            if (!file.exists()) {
                System.out.println("Error: User file not found.");
                return false; 
            }
            
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Splits name and password
                
                String savedName = parts[0];
                String savedPassword = parts[1];
                
                // If both match perfectly, login is successful
                if (savedName.equals(username) && savedPassword.equals(password)) {
                    scanner.close();
                    return true; 
                }
            }
            scanner.close();
            
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
        
        return false; // Login failed
    }
}