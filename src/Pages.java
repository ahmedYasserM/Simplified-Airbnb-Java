//
// Created by Yasser.
//


import java.beans.Customizer;
import java.util.Scanner;




public class Pages {

    static Account currentUser;

    public static void home_page() {

        // Every time we return to the 'Home Page' i.e "logged out", we set the currentUser to null
        currentUser = null; 
        
        System.out.println("     HOME PAGE     ");
        Scanner in = new Scanner(System.in);
        System.out.println("1 -> Login: ");
        System.out.println("2 -> Signup: ");

        int n = in.nextInt();

        switch (n) {
            case 1: login_page();
                break;

            case 2: signup_page();
                break;
        }

        user_menu();
    }

    public static void signup_page(){
        System.out.println("     SignUp PAGE     ");
        Scanner in = new Scanner(System.in);

        Account user = new Account();
        user.inputInterface();
        Account.signUp(user);

        currentUser = user;
    }

    public static  void login_page(){
        System.out.println("    LOGIN PAGE    ");
        Scanner in = new Scanner(System.in);

        System.out.print("Username: ");
        String userName = in.nextLine();

        System.out.print("Password: ");
        String password = in.nextLine();

        currentUser = Account.login(userName, password);

        if (currentUser == null) {
            System.out.println("try again.");
            login_page();
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
                reserving();
            }
            break;

            case 3: {
                home_page();
            }
            break;
            
            case 4: {
                System.exit(0);
            }
        }
        user_menu();
    }

    public static void hosting() {
        Place place = new Place();
        place.inputInterface();
        currentUser.hostPlace(place);
        System.out.println("Place was added successfully");
    }

    public static void reserving() {
        // Checks if the user has a reserved place 
        if (currentUser.getReservedPlace() != null) {
            System.out.println("You cannot reserve 2 at a time.");
            return;
        }

        // Prints all the available places
        Place.displayPlaces();

        Scanner in = new Scanner(System.in);

        String ID;
        Place place;

        // The loop is for wrong entries, it will keep iterating till the user enters an existing ID
        do {
            System.out.print("Enter place ID: ");
            ID = in.nextLine();
            place = Place.getAllPlaces().get(ID);

            //  Checks if the ID matches with any of the displayed places
            if (place == null || !(place.isReserved_place())) {
                System.out.println("Wrong ID, try again.");
                System.out.println();
            }

            // Prevents the user from reserving his own places
            else if (place.getHost() == currentUser) {
                System.out.println("You cannot reserve your own places.");
                place = null;
            }

        } while (place == null);
    
        // Creating a contract with currentUser as a "Customer"
        place.create_contract(currentUser);
        currentUser.reservePlace(place);
        System.out.println("Place was reserved successfully");

    }

}
