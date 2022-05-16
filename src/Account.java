//
// Created by Yasser.
//


import java.util.Vector;
import java.util.TreeMap;

public class Account {


    //attributes
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private Vector<Place>hostedPlaces; // this vector will contain the places which every user host
    private Vector<Place>reservedPlaces; // this vector will contain the places which every user reserve
    private static TreeMap<String,Account>accounts = new TreeMap<String, Account>();
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

    // parametrised constructor
    Account(String userName, String password, String phoneNumber,String dateOfBirth)
    {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;

    } // end of Account parametrised constructor


    static void signUp(String userName, String password, String phoneNumber,String dateOfBirth){
        accounts.put(userName, new Account( userName, password,  phoneNumber, dateOfBirth));
        System.out.println("Account has been added"); // if we want to remove this statement no problem :)
    } // end of signUp function



    static void login(String userName, String password)
    {
        Account user = findAccount(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account in the TreeMap Data Structure has the username which is provided.
        if(user == null){
            System.out.println("Incorrect username");
            Pages.loginPage();
        }
        else if(user.getPassword() != password){
            System.out.println("Incorrect password");
            Pages.loginPage();
        }
        else{
            Pages.homePage();
        }

    } // end of login function



    static void logout()
    {
        Pages.loginPage();
    } // end of logout function




  /*before the user delete his account the user will be asked to enter  his username
  ( because this is important operation so, he has to be sure of that) and then you will send his userName to this function to delete the account :)
  */
  static void deleteAccount(String userName)
    {
        Account user = accounts.remove(userName); // TreeMap. remove() is an inbuilt method of TreeMap class and is used to remove the mapping of any particular key from the map.

        if (user == null)
            System.out.println("Incorrect username");
        else
            System.out.println("Account has been deleted");

    } // end of deleteAccount function

    static Account findAccount(String userName){
      /*

        The java.util.TreeMap.get() method of TreeMap class is used to retrieve or fetch the value mapped by a particular key mentioned in the parameter.
         It returns NULL when the map contains no such mapping for the key.

         */
        Account user = accounts.get(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account in the TreeMap Data Structure has the username which is provided.
        if(user == null){
            System.out.println("Incorrect username");
        }
        return user;

    } // end of findAccount function



    void reservePlace()
    {

    }  // end of reservePlace function

    void hostPlace()
    {

    } // end of hostPlace function

    void deleteHostedPlace()
    {

    } // end of deleteHostedPlace function


    //setters and getters

    void setPassword(String password)
    {
        this.password = password;
    }


    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }


    public String getUserName()
    {
        return userName;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    String getPassword()
    {
        return password;
    }


    String getFirstName()
    {
        return firstName;
    }


    String getLastName()
    {
        return lastName;
    }



    String getPhoneNumber()
    {
        return  phoneNumber;
    }


}
