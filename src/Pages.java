//
// Created by Yasser.
//


import java.util.Scanner;


public class Pages {

    static Account currentUser;

    public static void home_page() {
        System.out.println();
        System.out.println("\t-----( Home Page )-----");

        // Every time we return to the 'Home Page' i.e "logged out", we set the currentUser to null
        currentUser = null;


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
    } // end of home_Page function

    public static void signup_page(){
        System.out.println();
        System.out.println("\t-----( SignUp )-----");

        Scanner in = new Scanner(System.in);

        Account user = new Account();
        user.inputInterface();
        Account.signUp(user);

        currentUser = user;
    } // end of signup_page function

    public static  void login_page(){
        System.out.println();
        System.out.println("\t-----( Login )-----");

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
    } // end of login_page function



    public static void profile_page(){
        System.out.println();
        System.out.println("\t-----( Profile )-----");

        System.out.println("1 -> Change First Name: ");
        System.out.println("2 -> Change Last Name: ");
        System.out.println("3 -> Change Password: ");
        System.out.println("4 -> Change Phone Number: ");
        System.out.println("5 -> Delete Account: ");

        Scanner in = new Scanner(System.in);

        int choice = in.nextInt();

        switch(choice){
            case 1: {
                System.out.println("Enter new First Name: ");
                String name = in.nextLine();
                currentUser.setFirstName(name);
                System.out.println("First Name has been changed successfully");
            }
            break;

            case 2: {System.out.println("Enter new Last Name: ");
                String name = in.nextLine();
                currentUser.setLastName(name);
                System.out.println("Last Name has been changed successfully");

            }
            break;

            case 3: {
                System.out.println("Enter new Password: ");
                String password = in.nextLine();
                currentUser.setPassword(password);
                System.out.println("Password has been changed successfully");
            }
            break;

            case 4: {
                System.out.println("Enter new Phone Number: ");
                String number = in.nextLine();
                currentUser.setPhoneNumber(number);
                System.out.println("Phone Number has been changed successfully");
            }
            break;

            case 5: {
                boolean isDeleted = Account.deleteAccount(currentUser);
                if(isDeleted == true)
                home_page();
                else
                    profile_page();
            }
            break;
        }

    } // end of profile_page function



    public static void user_menu() {
        System.out.println();
        System.out.println("\t-----( Main Menu )-----");

        Scanner in = new Scanner(System.in);

        System.out.println("1 -> Host a Place: ");
        System.out.println("2 -> Check available Places: ");
        System.out.println("3 -> Profile: ");
        System.out.println("4 -> Logout: ");
        System.out.println("5 -> Exit: ");

        int choice = in.nextInt();
        switch (choice) {
            case 1: {
                hosting();
            }
            break;

            case 2: {
                System.out.println();
                System.out.println("1 -> Reserve");
                System.out.println("2 -> Back");

                int choice_2 = in.nextInt();
                if (choice_2 == 1)
                    reserving();
                else
                    user_menu();
            }
            break;

            case 3: {
                profile_page();
            }
            break;

            case 4: {
                home_page();
            }
            break;

            case 5: {
                System.exit(0);
            }
        }
        user_menu();
    } // end of user_menu function



    public static void hosting() {
        System.out.println();
        System.out.println("\t-----( Hosting )-----");

        Place place = new Place();
        place.inputInterface();
        currentUser.hostPlace(place);
        System.out.println("Place added successfully");
    } // end of hosting function



    public static void reserving() {
        System.out.println();
        System.out.println("\t-----( Available Places )-----");

        // Prints all the available places
        Place.displayPlaces();

        // Checks if the user has a reserved place
        if (currentUser.getReservedPlace() != null) {
            System.out.println("You cannot reserve 2 at a time.");
            return;
        }

        Scanner in = new Scanner(System.in);

        String ID;
        Place place;

        // The loop is for wrong entries, it will keep iterating till the user enters an existing ID
        do {
            System.out.print("Type the Place ID, or press enter to return: ");
            ID = in.nextLine();

            // Return to the main menu on Enter press
            if (ID.length() == 0)
                user_menu();

            place = Place.getAllPlaces().get(ID);

            //  Checks if the ID matches with any of the displayed places
            //  and if the entered ID is somehow the ID of a non-printed reserved place
            if (place == null || place.isReserved_place()) {
                System.out.println("Wrong ID, try again.");
                System.out.println();

                // if the place is reserved
                place = null;
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

    } // end of reserving function

}