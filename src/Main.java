import java.util.Scanner;

/*
*   fuction to compare to objects of accounts { implementing comparable }
*
* */

public class Main {
     static Account currentUser;

    public static void main(String[] args) {
         main_menu();
        System.out.println(currentUser.getReservedPlace().getContract().toString());
    }

    public static void user_menu() {
         Scanner in = new Scanner(System.in);

         System.out.print("1 -> Host a Place: ");
         System.out.print("2 -> Reserve a Place: ");
         int choose = in.nextInt();
         switch (choose) {
              case 1: {
                   hosting();
                   user_menu();
              }
                    break;
              case 2: {
                  reserving();
              }

         }
    }
    public static void reserving() {
         Scanner in = new Scanner(System.in);

         Place.displayPlaces();
         System.out.print("Enter place ID: ");
         String ID = in.nextLine();
         Place place;
         try {
            place = Place.getAllPlaces().get(ID);
            currentUser.reservePlace(place);
         } catch (Exception e) {
             System.out.println("Wrong ID");
         }


    }
    public static void hosting() {
         Place place = new Place();
         place.inputInterface();
         currentUser.hostPlace(place);

         place.create_contract(currentUser);
         System.out.println("Place was added successfully");
    }

    public static void main_menu() {
         Scanner in = new Scanner(System.in);
         System.out.println("1 -> Login: ");
         System.out.println("2 -> Signup: ");
         int n = in.nextInt();
         switch (n) {
              case 1:
                   break;
              case 2: sign_up();
                   break;
         }
         user_menu();
    }

     public static void sign_up() {
          Scanner in = new Scanner(System.in);

          Account user = new Account();
          
          System.out.print("Enter username: " );
          String userName = in.nextLine();

          if (Account.findAccount(userName) != null) {
               System.out.println("Username already taken!, try again.");
               sign_up();
          }
          else
                user.setUserName(userName);

          System.out.println();

          System.out.print("Enter password: " );
          String password = in.nextLine();
          user.setPassword(password);

          System.out.println();

          System.out.print("Enter Phone Number: " );
          String phoneNumber = in.nextLine();
          user.setPhoneNumber(phoneNumber);
          Account.signUp(user);
          currentUser = user;
     }
}


