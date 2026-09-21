import java.util.Scanner;

public class AdminLogin {

    static Scanner sc = new Scanner(System.in);

    public static boolean login() {

        String correctUsername = "admin";
        String correctPassword = "admin123";

        int maxAttempts = 3;

        System.out.println("\n==============================");
        System.out.println("         ADMIN LOGIN");
        System.out.println("==============================");

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            if (username.equals(correctUsername)
                    && password.equals(correctPassword)) {

                System.out.println("\n✅ Login Successful!");
                return true;

            } else {

                int remaining = maxAttempts - attempt;

                if (remaining > 0) {
                    System.out.println(
                            "\n❌ Invalid Username or Password!"
                    );
                    System.out.println(
                            "Attempts remaining: " + remaining
                    );
                    System.out.println();
                }
            }
        }

        System.out.println("\n🚫 Maximum login attempts exceeded!");
        System.out.println("Access denied.");

        return false;
    }
}