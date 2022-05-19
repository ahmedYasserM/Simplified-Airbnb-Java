import java.util.Scanner;

/*
*   fuction to compare to objects of accounts { implementing comparable }
*
* */

public class Main {
     static Account currentUser;


    /////////////////////////////
    public static void main(String[] args) {


         main_menu();
        //System.out.println(currentUser.getReservedPlace().getContract().toString());





    }
    /////////////////////////////


    public static void main_menu() {
          Scanner in = new Scanner(System.in);
          System.out.println("1 -> Login: ");
          System.out.println("2 -> Signup: ");
          int n = in.nextInt();
          switch (n) {
               case 1: login_menu();
                    break;

               case 2: signUp_menu();
                    break;
          }
          
          user_menu();
     }

     public static void signUp_menu() {
          Scanner in = new Scanner(System.in);

          System.out.println("\t**********");
          System.out.println("\t* SignUp *");
          System.out.println("\t**********");

          Account user = new Account();
          user.inputInterface();
          Account.signUp(user);
          currentUser = user;
          
          
     }

     public static void login_menu() {
          Scanner in = new Scanner(System.in);
                    
          System.out.print("Username: ");
          String userName = in.nextLine();

          System.out.print("Password: ");
          String password = in.nextLine();

          currentUser = Account.login(userName, password);
          if (currentUser == null) {
               System.out.println("try again.");
               login_menu();
          }
          
     }

    public static void user_menu() {
         Scanner in = new Scanner(System.in);

         System.out.println("1 -> Host a Place: ");
         System.out.println("2 -> Reserve a Place: ");
         System.out.println("3 -> Logout: ");
         System.out.println("4 -> Exit: ");

         int choose = in.nextInt();
         switch (choose) {
               case 1: {
                   hosting();
               }
               break;
               case 2: {
                    Place.displayPlaces(currentUser);
                    reserving();
               }
               break;
               case 3: {
                    main_menu();
               }
               break;
               case 4: {
                    System.exit(0);
               }
          }
          
    }
    public static void reserving() {
         Scanner in = new Scanner(System.in);

         System.out.print("Enter place ID: ");
         String ID = in.nextLine();

         Place place;
         try {
            place = Place.getAllPlaces().get(ID);
            currentUser.reservePlace(place);
         } catch (Exception e) {
             System.out.println("Wrong ID, try again.");
             reserving();
         }
         System.out.println("Place was reserved successfully");
        
    }
    public static void hosting() {
         Place place = new Place();
         place.inputInterface();
         currentUser.hostPlace(place);

         place.create_contract(currentUser);
         System.out.println("Place was added successfully");
    }

  

     
}


