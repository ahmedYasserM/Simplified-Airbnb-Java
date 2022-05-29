
import java.util.Scanner;

import javax.xml.crypto.Data;


public abstract class Pages {

    public static Account currentUser;
    public static Scanner input = new Scanner(System.in);

    public static void home_page() {
        System.out.println();
        System.out.println("\t-----( Home Page )-----");
        
        // Every time we return to the 'Home Page' i.e "logged out", we set the currentUser to null
        currentUser = null; 

        
        System.out.println("[1] Login: ");
        System.out.println("[2] Signup: ");
        System.out.println("[0] Exit: ");

        System.out.print("> ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1: login_page();
                break;

            case 2: signup_page();
                break;

            case 0: {
                System.exit(0);
            }
                break;

            default: {
                System.out.println("Invalid input, try again.");
                home_page();
            }
                break;
        }

        user_menu();
    }  // end of home_page function

    public static void signup_page(){
        System.out.println();
        System.out.println("\t-----( SignUp )-----");

        

        Account user = new Account();
        user.inputInterface();
        Account.signUp(user);

        currentUser = user;
    } // end of signup_page function


    public static  void login_page(){
        System.out.println();
        System.out.println("\t-----( Login )-----");

        

        System.out.print("Username: ");
        String userName = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        currentUser = Account.login(userName, password);

        if (currentUser == null) {
            System.out.println("try again.");
            login_page();
        }
    } // end of login_page function



    public static void user_menu() {
        System.out.println();
        System.out.println("\t-----( Main Menu )-----");

        

        System.out.println("[1] Profile: ");
        System.out.println("[2] Host a Place: ");
        System.out.println("[3] Check available Places: ");
        System.out.println("[4] Logout: ");
        System.out.println("[0] Exit: ");

        System.out.print("> ");
        int choice = input.nextInt();
        input.nextLine();

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
                System.out.println("\t-----( Available Places )-----");
        
                if (Place.getAllPlaces().size() == 0) {
                    System.out.println("No Available Places at the moment.");
                    break;
                }
                // Prints all the available places
                Place.displayPlaces();

                System.out.println("[1] Reserve");
                System.out.println("[0] Back");

                System.out.print("> ");
                int choice_2 = input.nextInt();
                input.nextLine();

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
            break;

            default:
                System.out.println("You entered wrong number");
                break;
        }
        user_menu();
    } // end of user_menu function
    




    public static void profile_page() {
        System.out.println();
        System.out.println("\t-----( Profile )-----");

        System.out.println(currentUser.toString());

        

        System.out.println("[1] Edit your personal info");
        System.out.println("[2] View Hosted Places");
        System.out.println("[0] Back");

        System.out.print("> ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            // Edit your personal info case
            case 1: {
                currentUser.edit();
                profile_page();
            }
            break;

            // View Hosted Places case
            case 2: {
                
                System.out.println();

                var hostedPlaces = currentUser.getHostedPlaces();
                /*
                 var can be used in a local variable declaration instead of the variable’s type.
                 With var, the Java compiler infers the type of the variable at compile time, using type information obtained from the variable’s initializer.
                 The inferred type is then used as the static type of the variable.
                 */

                int hp_size = hostedPlaces.size();
                for (int i = 0; i < hp_size; i++) {
                    System.out.println("# " + (i + 1));
                    System.out.println(hostedPlaces.elementAt(i).toString());
                    System.out.println("===================================================");
                }

                System.out.println();
                
                System.out.print("Enter place number to edit, or press Enter to return: ");
                String place_idx = input.nextLine();

                if (place_idx.length() == 0) {
                    profile_page();
                    return;
                }

                hostedPlaces.elementAt(Integer.valueOf(place_idx) - 1).edit();

                profile_page();
            }
            break;

            // Back case (exit profile function)
            case 0: {
                user_menu();
            }
            break;

            default:
                System.out.println("You entered wrong number");
                break;
        }
    }

    public static void hosting() {
        System.out.println();
        System.out.println("\t-----( Hosting )-----");

        Place place = new Place();
        place.inputInterface();
        currentUser.hostPlace(place);

        DataFiles.writePlace(place);
        String typeDef = "hp" + (currentUser.getHostedPlaces().size() + 1) + ": ";

        DataFiles.editFile(currentUser.getUserName(), typeDef, "", place.getPlaceID());

        System.out.println("Place added successfully");
    }

    public static void reserving() {

        // Checks if the user has a reserved place 
        if (currentUser.getReservedPlace() != null) {
            System.out.println("You cannot reserve 2 at a time.");
            return;
        }

        String ID;
        Place place;

        // The loop is for wrong entries, it will keep iterating till the user enters an existing ID
        do {
            System.out.print("Type the Place ID, or press enter to return: ");
            ID = input.nextLine();

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