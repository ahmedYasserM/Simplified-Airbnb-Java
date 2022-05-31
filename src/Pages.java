
import java.util.Scanner;


public abstract class Pages {
    // Attributes
    public static Account currentUser;
    public static Admin admin;
    public static Scanner input = new Scanner(System.in);

    // Functions
    public static void home_page() {
        System.out.println();
        System.out.println("\t-----( Home Page )-----");

        // Every time we return to the 'Home Page' i.e "logged out", we set the currentUser to null
        currentUser = null;


        System.out.println("[1] Login. ");
        System.out.println("[2] Signup. ");
        System.out.println("[0] Exit. ");

        System.out.print("> ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1: {

                login_page();

            }
            break;

            case 2: {

                signup_page();
            }
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
        if(currentUser != null){
            user_menu();
        }
        else {
            admin_page();
        }

        home_page();
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

        if(userName.equals("admin") && password.equals("admin")){
            admin = new Admin();
            currentUser = null;
        }
        else {

            currentUser = Account.login(userName, password);

            if (currentUser == null) {
                System.out.println();
                System.out.println("[1] Are you Sure you are already Have an Account? Try again:  ");
                System.out.println("[2] Not Registered Yet? SignUp: ");
                System.out.print("> ");
                int in = input.nextInt();
                input.nextLine();

                if (in == 1) {
                    login_page();
                } else if (in == 2) {
                    signup_page();
                } else {
                    System.out.println("Incorrect input, please choose either 1 or 2");
                    login_page(); // dump but clean ...  if enter -> [1]:login // [2]:SignUp  // [incorrect choice]:login -- Khtb
                }


            }
        }
    } // end of login_page function


    public static void user_menu() {
        System.out.println();
        System.out.println("\t-----( Main Menu )-----");



        System.out.println("[1] Profile. ");
        System.out.println("[2] Host a Place. ");
        System.out.println("[3] Filters. "); // contain filter page
        System.out.println("[4] All places.");
        System.out.println("[5] Logout. ");
        System.out.println("[0] Exit. ");

        System.out.print("> ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1: {
                profile_page();

            }
            break;

            case 2: {
                if (currentUser.getHostedPlaces().size() >= 3) {
                    System.out.println("You cannot host more than 3 places.");
                    user_menu();
                    return;
                }
                hosting();
            }
            break;

            case 3: {
                System.out.println();
                System.out.println("\t-----( Filters )-----");

                if (Place.getAllPlaces().size() == 0) {
                    System.out.println("No Available Places at the moment.\n");
                    break;
                }

                Place.tmpPlaces.clear();
                Place.tmpPlaces.putAll(Place.getAllPlaces());

                System.out.println("1-Country and City:");
                System.out.println();
                System.out.print("  Country(0 means no filter): " );
                String country = input.nextLine().toString();
                Place.filterPlacesCountry(country);
                System.out.print("  City(0 means no filter): ");
                String city = input.nextLine().toString();
                Place.filterPlacesCity(city);
                System.out.println();
                System.out.println();

                System.out.println("2-Price range( 0 and 0 means no filter):");
                System.out.println();
                System.out.print("  min price: " );
                int min = input.nextInt();
                System.out.print("  max price: ");
                int max = input.nextInt();
                Place.filterPlacesPrice(min, max);
                System.out.println();
                System.out.println();


                System.out.println("3-Rooms and beds:");
                System.out.println();
                System.out.print("  Bedrooms(0 means no filter): " );
                int bedrooms= input.nextInt();
                Place.filterPlacesNumOfBedrooms(bedrooms);
                System.out.print("  Beds(0 means no filter): ");
                int beds = input.nextInt();
                Place.filterPlacesNumOfBeds(beds);
                System.out.print("  Bathrooms(0 means no filter): ");
                int bathrooms = input.nextInt();
                Place.filterPlacesNumOfBathRooms(bathrooms);
                System.out.println();
                System.out.println();


                System.out.println("\t_______Filtered Places_______\n");

                if(Place.tmpPlaces.isEmpty() == true)
                    System.out.println("No Available Places at the moment.");
                else{
                    for(Place place: Place.tmpPlaces.values())
                        System.out.println(place.toString());
                }

                System.out.println("==============================================================");


                System.out.println("[1] Reserve.");
                System.out.println("[0] Back.");

                System.out.print("> ");
                int choice_2 = input.nextInt();
                input.nextLine();

                if (choice_2 == 1)
                    reserving();
                else
                    break;
            }
            break;

            case 4:{
                if (Place.getAllPlaces().size() == 0) {
                    System.out.println("No Available Places at the moment.");
                    break;
                }

                System.out.println("\t_______All Places_______");
                System.out.println();
                Place.displayPlaces();


                System.out.println("[1] Reserve.");
                System.out.println("[0] Back.");

                System.out.print("> ");
                int choice_2 = input.nextInt();
                input.nextLine();

                if (choice_2 == 1)
                    reserving();
                else
                    break;


            }
            break;

            case 5: {
                return;
            }

            case 0: {
                System.exit(0);
            }
            break;

            default:
                System.out.println("You entered wrong number.");
                break;
        }
        user_menu();


    } // end of user_menu function



    public static void profile_page() {
        System.out.println();
        System.out.println("\t-----( Profile )-----");

        System.out.println(currentUser.toString(0));

        System.out.println("[1] Edit your personal info");
        System.out.println("[2] View Hosted Places");
        if (currentUser.getReservedPlace() != null)
            System.out.println("[3] Depart");
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
                 var can be used in a local variable declaration instead of the variableâ€™s type.
                 With var, the Java compiler infers the type of the variable at compile time, using type information obtained from the variableâ€™s initializer.
                 The inferred type is then used as the static type of the variable.
                 */

                int hp_size = hostedPlaces.size();
                for (int i = 0; i < hp_size; i++) {
                    System.out.println("Place number " + (i + 1));
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
                int index = Integer.valueOf(place_idx) - 1;
                hostedPlaces.elementAt(index).edit();

                profile_page();
            }
            break;

            case 3: {
                if (currentUser.getReservedPlace() != null) {
                    System.out.println("Type 'yes' to confirm or press ENTER to return.");
                    System.out.print("Confirm leaving: ");
                    String check = input.nextLine();
                    if (check.toLowerCase().equals("yes")) {
                        Place place = currentUser.getReservedPlace();

                        place.setReserved(false);

                        currentUser.reservePlace(null);

                        System.out.println("Departure done successfully");
                    }
                    else {
                        profile_page();
                        return;
                    }
                }
            }
            break;

            // Back case (exit profile function)
            case 0: {
                return;
            }

            default:
                System.out.println("You entered wrong number.");
                break;
        }
    } // end of profile_page function

    public static void admin_page(){
        System.out.println();
        System.out.println("\t-----( Admin )-----");


        System.out.println("[1] Display All Places Data. ");
        System.out.println("[2] Display All Accounts' Usernames. ");
        System.out.println("[3] Display All Accounts Data. ");
        System.out.println("[4] Delete Place. ");
        System.out.println("[5] Delete Account. ");
        System.out.println("[6] Edit Places. ");
        System.out.println("[7] Edit Accounts. ");
        System.out.println("[0] Back. ");

        System.out.print("> ");
        int choice = input.nextInt();
        input.nextLine();

        switch(choice){
            case 1:{
                admin.displayPlaces();
            }
            break;

            case 2:{
                admin.displayAllUserNames();
            }
            break;

            case 3:{
                admin.displayAllAccounts();
            }
            break;

            case 4:{
                System.out.print("Enter the id of the place you want to delete: ");
                admin.deletePlace(input.nextLine());

            }
            break;

            case 5:{
                System.out.print("Enter the username of the account you want to delete: ");
                admin.deleteAccount(input.nextLine());
            }
            break;

            case 6:{
                System.out.print("Enter the id of the place you want to edit: ");
                try {
                    admin.editPlaces(input.nextLine());
                }catch (Exception e){
                    e.toString();
                }
            }
            break;

            case 7:{
                System.out.print("Enter the user name of the account you want to edit: ");
                admin.editAccounts(input.nextLine());
            }
            break;

            case 0:{
                return;
            }


        }

        admin_page();
    } // end of admin_page function


    public static void hosting() {
        System.out.println();
        System.out.println("\t-----( Hosting )-----");

        Place place = new Place();
        place.inputInterface();

        currentUser.hostPlace(place);

        place.setHost(currentUser); // sets the host of the place to the calling object which is the class account

        System.out.println("Place added successfully");
    } // end of hosting function


    public static void reserving() {

        // Checks if the user has a reserved place
        if (currentUser.getReservedPlace() != null) {
            System.out.println("You cannot reserve more than one place at a time.");
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

        place.setReserved(true);

        // DataFiles.editFile("Accounts/" + currentUser.getUserName(), typeDef, oldValue, newValue);
        System.out.println("Place has been reserved successfully");
    }


}