//
// Created by Yasser.
//


import java.util.Scanner;


public class Pages {
    static Account currentUser;

    public static void home_page(){

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
                if (currentUser.getReservedPlace() != null) {
                    System.out.println("You reserved a place already!");
                    break;
                }
                if (Place.getAllPlaces().size() == currentUser.getHostedPlaces().size() || Place.getAllPlaces().size() == 0) {
                    System.out.println("No Avaliable places at the moment.");
                    break;
                }
                Place.displayPlaces(currentUser);
                reserving();
                System.out.println("Place was reserved successfully");

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
        Scanner in = new Scanner(System.in);

        System.out.print("Enter place ID: ");
        String ID = in.nextLine();

        Place place;
        try {
            place = Place.getAllPlaces().get(ID);
            place.create_contract(currentUser);
            currentUser.reservePlace(place);
        } catch (Exception e) {
            System.out.println("Wrong ID, try again.");
            reserving();
        }

    }




}
