
import java.util.Scanner;


public class Pages {

    static Account currentUser;

    public static void home_page() {
        System.out.println();
        System.out.println("\t-----( Home Page )-----");
        
        // Every time we return to the 'Home Page' i.e "logged out", we set the currentUser to null
        currentUser = null; 
        

        Scanner in = new Scanner(System.in);
        System.out.println("[1] Login: ");
        System.out.println("[2] Signup: ");

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
        System.out.println();
        System.out.println("\t-----( SignUp )-----");

        Scanner in = new Scanner(System.in);

        Account user = new Account();
        user.inputInterface();
        Account.signUp(user);

        currentUser = user;
    }

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
    }

    public static void user_menu() {
        System.out.println();
        System.out.println("\t-----( Main Menu )-----");

        Scanner in = new Scanner(System.in);

        System.out.println("[1] Profile: ");
        System.out.println("[2] Host a Place: ");
        System.out.println("[3] Check available Places: ");
        System.out.println("[4] Logout: ");
        System.out.println("[0] Exit: ");

        int choice = in.nextInt();
        switch (choice) {
            case 1: {
                profile_page();
            }
            break;

            case 2: {
                hosting();
            }
            break;

            case 3: {
                System.out.println();
                System.out.println("[1] Reserve");
                System.out.println("[2] Back");

                int choice_2 = in.nextInt();
                if (choice_2 == 1) 
                    reserving();
                else 
                    user_menu();
            }
            break;

            case 4: {
                home_page();
            }
            break;
            
            case 0: {
                System.exit(0);
            }
        }
        user_menu();
    }

    public static void profile_page() {
        System.out.println();
        System.out.println("\t-----( Profile )-----");

        System.out.println(currentUser.toString());

        Scanner in = new Scanner(System.in);

        System.out.println("[1] Edit your personal info");
        System.out.println("[2] View Hosted Places");
        System.out.println("[0] Back");

        int choice = in.nextInt();

        switch (choice) {
            case 1: {
                
            }
            break;

            case 2: {
                Scanner input = new Scanner(System.in);
                System.out.println();

                var hostedPlaces = currentUser.getHostedPlaces();

                int hp_size = hostedPlaces.size();
                for (int i = 0; i < hp_size; i++) {
                    System.out.println("# " + (i + 1));
                    System.out.println(hostedPlaces.elementAt(i).toString());
                    System.out.println("===================================================");
                }

                System.out.println();
                
                System.out.print("Enter place number to edit, or press Enter to return:");
                String place_idx = input.nextLine();

                if (place_idx.length() == 0) {
                    profile_page();
                    return;
                }

                hostedPlaces.elementAt(Integer.valueOf(place_idx) - 1).edit();

                profile_page();
            }
            break;

            case 0: 
                user_menu();
            break;
        }
    }

    public static void hosting() {
        System.out.println();
        System.out.println("\t-----( Hosting )-----");

        Place place = new Place();
        place.inputInterface();
        currentUser.hostPlace(place);
        System.out.println("Place added successfully");
    }

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
            if (ID.length() == 0) {
                user_menu();
                return;
            }
            
            place = Place.getAllPlaces().get(ID);

            //  Checks if the ID matches with any of the displayed places
            //  and if the entered ID is somehow the ID of a non-printed reserved place
            if (place == null || place.isReserved()) {
                System.out.println("Wrong ID, try again.");
                System.out.println();

                // In case the place is reserved then we set it back to null
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
    }

}
