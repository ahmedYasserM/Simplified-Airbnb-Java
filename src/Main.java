import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }
     public static Account main_menu() {
          Scanner in = new Scanner(System.in);
          int n = in.nextInt();
          System.out.println("1 -> Login: ");
          System.out.println("2 -> Signup: ");
          System.out.println("Enter username: " );
          String userName = in.nextLine();
          System.out.println("Enter password: " );
          String password = in.nextLine();
          // Account.signUp(new Account(userName, password, ));

         return null;
     }
}


