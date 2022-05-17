//
// Created by Yasser.
//


import javax.swing.*;
import java.util.Vector;
import java.util.TreeMap;

public class Account {

    //attributes
    private static TreeMap<String, Account> ACCOUNTS = new TreeMap<String, Account>();

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private Vector<Place> hostedPlaces; // this vector will contain the places which every user host
    private Vector<Place> reservedPlaces; // this vector will contain the places which every user reserve
    /*this map will contain the account of every user
     (the key will be the username of the user and the value will be its account)
    __________________________________________________________________________________

     # important note
    => this is private member if you want to:
      1-add account           >> use signUp function
      2-delete account        >> use deleteAccount function
      3-find account          >> use findAccount function
    */


    //methods
    public Account() {
        hostedPlaces = new Vector<Place>();
        reservedPlaces = new Vector<Place>();
    }

    // parametrised constructor
    public Account(String userName, String password, String phoneNumber, String dateOfBirth) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        hostedPlaces = new Vector<Place>();
        reservedPlaces = new Vector<Place>();
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
    }

    public static void signUp(String userName, String password, String phoneNumber, String dateOfBirth) {
        if (ACCOUNTS.get(userName) != null)  // checks if the username is taken or not
            System.out.println("User name already taken!");
        else {
            ACCOUNTS.put(userName, new Account(userName, password, phoneNumber, dateOfBirth));
            System.out.println("Account has been added"); // if we want to remove this statement no problem :)
        }
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


    /*before the user delete his account the user will be asked to enter  his username
    ( because this is important operation so, he has to be sure of that) and then you will send his userName to this function to delete the account :)
    */
    public static void deleteAccount(String userName) {
        Account user = ACCOUNTS.remove(userName); // TreeMap. remove() is a built-in method of TreeMap class and is used to remove the mapping of any particular key from the map.

        if (user == null)
            System.out.println("Incorrect username");
        else
            System.out.println("Account has been deleted");

    } // end of deleteAccount function

    public static Account findAccount(String userName) {
      /*

        The java.util.TreeMap.get() method of TreeMap class is used to retrieve or fetch the value mapped by a particular key mentioned in the parameter.
         It returns NULL when the map contains no such mapping for the key.

         */
        Account user = ACCOUNTS.get(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account in the TreeMap Data Structure has the username which is provided.
        if (user == null) {
            System.out.println("Incorrect username");
        }
        return user;

    } // end of findAccount function


    public void reservePlace() {

    }  // end of reservePlace function

    public void hostPlace(Place place) {
        hostedPlaces.add(place); // adds the place to vector of places
        place.setHost(this); // sets the host of the place to the calling object which is the class account
    } // end of hostPlace function

    // deleting the hosted place by its ID
    public void deleteHostedPlace(int id) {
        Place place = Place.removePlace(id); // returns the place and removes it from the PLACES container
        if (place != null) { // checks if the place exists
            hostedPlaces.remove(place);
            System.out.println("The place was deleted successfully.");
        }
        else
            System.out.println("Place not found.");
    } // end of deleteHostedPlace function

    //setters and getters

    public void setPassword(String password) {
        this.password = password;
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


    public String getUserName() {
        return userName;
    }

    public String getDateOfBirth() {
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

}
