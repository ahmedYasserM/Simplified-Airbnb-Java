//
// Created by Yasser.
//


import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Account {

    // attributes
    private static HashMap<String, Account> ALL_ACCOUNTS = new HashMap<>();
    public static int account_orderd_ID_Cnt=0;   //(khtb)    just added to count every account created, but don't worry it doesn't affect any thing :) .

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
    private LinkedList<Place> hostedPlaces; // this LinkedList will contain the places which every user host
    private Place reservedPlace; // this object will contain the place which is reserved by the traveler

    private int account_orderd_ID;  //(khtb)    to make the account have an "ordered" ID  it doesn't affect any thing .


    //methods

    // default constructor
    public Account() {
        account_orderd_ID = ++account_orderd_ID_Cnt;
        hostedPlaces = new LinkedList<Place>();
    }


    // parametrised constructor
    public Account(String userName, String password, String phoneNumber, Date dateOfBirth) {
        account_orderd_ID = ++account_orderd_ID_Cnt;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        hostedPlaces = new LinkedList<Place>();
    } // end of Account parametrised constructor


    // PRINT CLASS DATA
    @Override
    public String toString() {

        String finalShape = "";

        finalShape += "Name: " + firstName + ' ';
        finalShape += lastName + '\n';
        finalShape += "Username: " + userName + '\n';
        finalShape += "Phone Number: " + phoneNumber + '\n';

        return finalShape;
    } // end of toString function



    public static void signUp(Account account) {

        ALL_ACCOUNTS.put(account.getUserName(), account);
        System.out.println("Account has been added"); // if we want to remove this statement no problem :)

    } // end of signUp function



    public static void login(String userName, String password) {

        Account user = findAccount(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account in the TreeMap Data Structure has the username which is provided.
        if (user == null) {
            System.out.println("Incorrect username");
            Pages.loginPage();
        } else if (user.getPassword() != password) {
            System.out.println("Incorrect password");
            Pages.loginPage();
        } else {
            Pages.homePage();
        }

    } // end of login function



    public static void logout() {

        Pages.loginPage();
    } // end of logout function




    public static void deleteAccount(String userName) {
        /*before the user delete his account the user will be asked to enter  his username
          ( because this is important operation so, he has to be sure of that) and then you will send his userName to this function to delete the account :)
        */

        Account user = ALL_ACCOUNTS.remove(userName); // TreeMap. remove() is a built-in method of TreeMap class and is used to remove the mapping of any particular key from the map.

        if (user == null)
            System.out.println("Incorrect username");
        else
        {
            for (Place place : user.hostedPlaces) {
                Place.removePlace(place.getPlaceID());
            }
            System.out.println("Account has been deleted");
            account_orderd_ID_Cnt--;        //(khtb)
        }

    } // end of deleteAccount function



    public static Account findAccount(String userName) {
      /*
        The java.util.TreeMap.get() method of TreeMap class is used to retrieve or fetch the value mapped by a particular key mentioned in the parameter.
         It returns NULL when the map contains no such mapping for the key.
         */

        Account user = ALL_ACCOUNTS.get(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account in the TreeMap Data Structure has the username which is provided.
       /* if (user == null) {
            System.out.println("Incorrect username");
        }*/
        return user; // if the there is no account has the username provided it will return => null

    } // end of findAccount function

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

    public void setUserName(String userName) {
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

    public LinkedList<Place> getHostedPlaces() {
        return hostedPlaces;
    }
}
