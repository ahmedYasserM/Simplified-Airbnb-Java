//
// Created by Yasser.
//

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;


public class Account {

    // attributes
    private static HashMap<String, Account> ALL_ACCOUNTS = new HashMap<>();


    /*this map will contain the account of every user
     (the key will be the username of the user and the value will be its account)
    __________________________________________________________________________________

     # important note
    => this is private member if you want to:
      1-add account           >> use signUp function
      2-delete account        >> use deleteAccount function
      3-find account          >> use findAccount function
    */

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phoneNumber;
    private Vector<Place> hostedPlaces; // this vector will contain the places which every user host

    private Place reservedPlace; // this object will contain the place which is reserved by the traveler




    //methods

    // default constructor
    public Account() {
        hostedPlaces = new Vector<>();
        dateOfBirth = new Date();
    }


    // parametrised constructor
    public Account(String userName, String password, String phoneNumber, Date dateOfBirth) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        hostedPlaces = new Vector<Place>();
    } // end of Account parametrised constructor


    // PRINT CLASS DATA => used with contract
    @Override
    public String toString() {

        String finalShape = "";

        finalShape += "Name: " + firstName + ' ';
        finalShape += lastName + '\n';
        finalShape += "Phone Number: " + phoneNumber + '\n';
        finalShape += "Username: " + userName + '\n';


        return finalShape;
    } // end of toString function (contract version)







    // PRINT CLASS DATA => used with admin
    public String toString(int param){
        String finalShape = toString();

        finalShape += "Password: " + password + '\n';
        finalShape += "Date of Birth: " + dateOfBirth.toString() + '\n';
        finalShape += "Reserved Place ID: " + reservedPlace.getPlaceID() + '\n';
        finalShape += "Hosted Places IDs: ";
        for (Place place : hostedPlaces) {
          finalShape += place.getPlaceID() + "  ";
        }

        finalShape += '\n';

        return finalShape;
    } // end of toString function (admin version)

    public void displayAccounts(){
        String data = "";

        for(Account user : ALL_ACCOUNTS.values()){
            data =  user.toString();
            System.out.println(data);
        }


    } // end of displayAccounts function


    public void inputInterface() {
        Scanner in = new Scanner(System.in);
        System.out.println("--- Enter your information ---");

        System.out.print("First Name: ");
        setFirstName(in.nextLine());

        System.out.print("Last Name: ");
        setLastName(in.nextLine());

        System.out.print("Username: ");
        setUserName(in.nextLine());

        System.out.print("Password: ");
        setPassword(in.nextLine());

        System.out.print("Phone Number : ");
        setPhoneNumber(in.nextLine());

        dateOfBirth.inputInterface("Birth");
    } // end of inputInterface function


    public static void signUp(Account account) {

        ALL_ACCOUNTS.put(account.getUserName(), account);
        System.out.println("Account has been created successfully.");

    } // end of signUp function


    public static Account findAccount(String userName) {
      /*
        The java.util.HashMap.get() method of HashMap class is used to retrieve or fetch the value mapped by a particular key mentioned in the parameter.
         It returns NULL when the map contains no such mapping for the key.
         */

        Account user = ALL_ACCOUNTS.get(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account in the TreeMap Data Structure has the username which is provided.
        if (user == null) {
            System.out.println("Incorrect username");
        }
        return user; // if the there is no account has the username provided it will return => null

    } // end of findAccount function


    public static Account login(String userName, String password) {

        Account user = findAccount(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account in the TreeMap Data Structure has the username which is provided.

        if (user == null) {
            return null;
        } else if (!(user.getPassword()).equals(password)) {
            System.out.println("Incorrect password");
            return null;
        }

        return user;
    } // end of login function



    public static void logout() {

        Pages.login_page();
    } // end of logout function




    public static boolean deleteAccount(Account user) {
        System.out.println("\n In order to delete your account write \'DELETE MY ACCOUNT\' without quotes");
        Scanner in1 = new Scanner(System.in);
        String choice1 = in1.nextLine();
        if(!(choice1.equals("DELETE MY ACCOUNT"))){
            System.out.println("Account hasn't been deleted");
            return false;
        }


            for (Place place : user.hostedPlaces) {
                String placeId = place.getPlaceID();

                if(place.isReserved_place() == true){
                    System.out.println("The place with id "+placeId +" is reserved.");
                    System.out.println("if you still want to delete your account you must pay "+ place.getContract().getPenaltyClause()+" $, the penalty clause value.");

                    System.out.println("1 -> continue: ");
                    System.out.println("2 -> back: ");

                    Scanner in2 = new Scanner(System.in);
                    int choice2 = in2.nextInt();

                    if(choice2 == 0){
                        System.out.println("Account hasn't been deleted");
                        return false;
                    }

                }
                Place.removePlace(placeId);

            }
            ALL_ACCOUNTS.remove(user.getUserName()); // HashMap.remove() is a built-in method of HashMap class and is used to remove the mapping of any particular key from the map.
            System.out.println("Account has been deleted successfully");
            return true;

    } // end of deleteAccount function





    public static HashMap<String, Account> getAllAccounts() {
        return ALL_ACCOUNTS;
    }

    public void reservePlace(Place place) {
        reservedPlace = place;
        place.setReserved_place(true);
    }  // end of reservePlace function



    public void deleteReservedPlace(){

        reservedPlace.setReserved_place(false);
        reservedPlace = null;
        System.out.println("The place has been deleted successfully.");

    } // end of deleteReservedPlace function



    public void hostPlace(Place place) {
        hostedPlaces.add(place); // adds the place to LinkedList of places
        place.setHost(this); // sets the host of the place to the calling object which is the class account
    } // end of hostPlace function


    // deleting the hosted place by its ID
    public void deleteHostedPlace(String id) {
        Place place = Place.removePlace(id); // returns the place and removes it from the PLACES container

        if (place != null) { // checks if the place exists   // ???? and you have to check if this place is taken or not also from a user (khtb)
            hostedPlaces.remove(place);
            System.out.println("The place has deleted successfully.");
            //Place.place_orderd_ID_Cnt--;   //(khtb)
        }
        else
            System.out.println("Place not found.");
    } // end of deleteHostedPlace function



    //setters and getters

    public void setPassword(String password) {

        this.password = password;
    }

    private void setUserName(String userName) {
        if (ALL_ACCOUNTS.get(userName) != null) {
            System.out.println("Username already taken!, try again.");
            this.inputInterface();
        }
        else
            this.userName = userName;
    }


    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }


    public void setLastName(String lastName) {

        this.lastName = lastName;
    }


    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }


    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {

        return userName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Place getReservedPlace() {
        return reservedPlace;
    }

    public Vector<Place> getHostedPlaces() {
        return hostedPlaces;
    }
}