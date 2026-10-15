public class Main {
    public static void main(String[] args) {
        ProductStorage storage = new ProductStorage();
        storage.viewAvailableStock();

        UserStorage user = new UserStorage();
        boolean loginSuccess = user.login("satish", "A25026");
        if (loginSuccess) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }
}
