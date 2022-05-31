

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;



public class Account {

    // attributes
    private static Scanner input = new Scanner(System.in);
    private static HashMap<String, Account> ALL_ACCOUNTS = new HashMap<>();
    public static float Male_Counter = 0;
    public static float Female_Counter = 0;

    public static int AccountCnt = 0;   // counter for Accounts NEW - Khatab

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
    private String gender;
    private Vector<Place> hostedPlaces; // this vector will contain the places which every user host
    private Place reservedPlace; // this object will contain the place which is reserved by the traveler

    //methods

    // default constructor
    public Account() {
        hostedPlaces = new Vector<Place>();
        dateOfBirth = new Date();
        AccountCnt++;
    }



    // parametrised constructor
    /*public Account(String userName, String password, String phoneNumber, String gender,Date dateOfBirth) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;

        hostedPlaces = new Vector<Place>();

        this.gender = gender;
        if(gender.equals('M') || gender.equals('m'))
            Male_Counter++;
        else if(gender.equals('F') || gender.equals('f'))
            Female_Counter++;


    } // end of Account parametrised constructor
*/

    @Override
    public String toString() {

        String finalShape = "";

        finalShape += "Name: " + firstName + ' ';
        finalShape += lastName + '\n';
        finalShape += "Phone Number: " + phoneNumber + '\n';
        finalShape += "Gender: " + gender + '\n';
        finalShape += "Username: " + userName + '\n';
        return finalShape;
    } // end of toString function (contract version)


    // PRINT CLASS DATA => used with admin
    public String toString(int param){
        String finalShape = toString();

        finalShape += "Password: " + password + '\n';
        finalShape += "Date of Birth: " + dateOfBirth.toString() + '\n';
        if(reservedPlace != null){
            finalShape += "Reserved Place ID: " + reservedPlace.getPlaceID() + '\n';
        }
        finalShape += "Hosted Places IDs: ";
        if(hostedPlaces.size() != 0){
            for (Place place : hostedPlaces) {
                finalShape += place.getPlaceID() + "  ";
            }
        }

        finalShape += '\n';

        return finalShape;
    } // end of toString function (admin version)



    public void inputInterface() {
        System.out.println("--- Enter your information ---");


        System.out.print("First Name: ");
        setFirstName(input.nextLine());

        System.out.print("Last Name: ");
        setLastName(input.nextLine());

        String name;
        // this do_while loop to prevent the user to use userName admin
        do {
            System.out.print("Username: ");
            name = input.nextLine();
            if (name.equals("admin") || name.equals("ADMIN") || name.equals("Admin")|| ALL_ACCOUNTS.get(name) != null ) {
                System.out.println("Username already taken!, try again.");
                System.out.println();
                continue;
            } else {
                setUserName(name);
            }
        }while(name.equals("admin") || name.equals("ADMIN") || name.equals("Admin") || ALL_ACCOUNTS.get(name) != null);

        System.out.print("Password: ");
        setPassword(input.nextLine());

        System.out.print("Phone Number: ");
        setPhoneNumber(input.nextLine());

        System.out.print("Gender (M for Male | F for female): ");
        String gender = input.nextLine();
        if(gender.equals('M') || gender.equals('m'))
            Male_Counter++;
        else if(gender.equals('F') || gender.equals('f'))
            Female_Counter++;
        setGender(gender);


        dateOfBirth.Date_inputInterface("Birth");
    } // end of inputInterface function


    public static void signUp(Account account) {

        ALL_ACCOUNTS.put(account.getUserName(), account);
        System.out.println("Account has been created successfully.");

    } // end of signUp function


    public static Account findAccount(String userName) {
      /*
        The java.util.HashMap.get() method of HashMap class is used to retrieve or fetch the value mapped by a particular key mentioned input the parameter.
         It returns NULL when the map contains no such mapping for the key.
         */

        Account user = ALL_ACCOUNTS.get(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account input the TreeMap Data Structure has the username which is provided.
        if (user == null) {
            System.out.println("Incorrect username");
        }
        return user; // if the there is no account has the username provided it will return => null

    } // end of findAccount function



    public static Account login(String userName, String password) {

        Account user = findAccount(userName); // here we fetch the account whose username is provided from the TreeMap Data Structure
        // if user is equal to null => this mean that there is no account input the TreeMap Data Structure has the username which is provided.

        if (user == null) {
            return null;
        } else if (!(user.getPassword()).equals(password)) {
            System.out.println("Incorrect password");
            return null;
        }

        return user;
    } // end of login function



    public static boolean deleteAccount(Account user) {
        System.out.println("\n In order to delete your account write \'DELETE MY ACCOUNT\' without quotes");

        String choice1 = input.nextLine();
        if(!(choice1.equals("DELETE MY ACCOUNT"))) {
            System.out.println("Account hasn't been deleted");
            return false;
        }


        for (Place place : user.hostedPlaces) {
            String placeId = place.getPlaceID();

            if(place.isReserved() == true){
                System.out.println("The place with id "+placeId +" is reserved.");
                System.out.println("If you still want to delete your account you must pay "+ place.getContract().getPenaltyClause()+" $, the penalty clause value.");

                System.out.println("1 -> continue: ");
                System.out.println("2 -> back: ");

                int choice2 = input.nextInt();
                input.nextLine();

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


    public void edit(){

        int choice;
        do {
            System.out.println("[1] Change First Name: ");
            System.out.println("[2] Change Last Name: ");
            System.out.println("[3] Change Password: ");
            System.out.println("[4] Change Phone Number: ");
            System.out.println("[5] Delete Account: ");
            System.out.println("[0] Back: ");

            System.out.print("> ");
            choice = input.nextInt();
            input.nextLine();

            if(choice > 5 || choice < 0){
                System.out.println("Incorrect input, please choose number from 0 to 5.");
                System.out.println();
                System.out.println("===================================================");
                System.out.println();
                continue;
            }


            switch (choice) {
                case 1: {
                    System.out.println("Your OLD first Name: " + firstName );
                    System.out.print("Enter new First Name: ");
                    String name = input.nextLine();


                    this.firstName = name;
                    System.out.println("First Name has been changed successfully");
                    System.out.println();
                }
                break;

                case 2: {

                    System.out.println("Your OLD last Name: " + lastName );

                    System.out.print("Enter new Last Name: ");

                    String name = input.nextLine();


                    this.lastName = name;
                    System.out.println("Last Name has been changed successfully");
                    System.out.println();
                }
                break;

                case 3: {

                    System.out.print("Enter Your OLD Password : ");         // Khatab - NEW
                    String old_PW = input.nextLine();

                    System.out.println(); // just for spacing

                    if(!old_PW.equals(password))
                    {
                        System.out.println("you entered a Wrong OlD Password ...\n");
                        break;
                    }

                    System.out.print("Enter new Password: ");
                    String password = input.nextLine();


                    this.password = password;
                    System.out.println("Password has been changed successfully");
                    System.out.println();

                }
                break;

                case 4: {

                    System.out.println("Your OLD Phone Num: " + phoneNumber );

                    System.out.print("Enter new Phone Number: ");
                    String number = input.nextLine();

                    this.phoneNumber = number;
                    System.out.println("Phone Number has been changed successfully");
                    System.out.println();
                }
                break;

                case 5: {
                    boolean isDeleted = Account.deleteAccount(this);
                    if(isDeleted == true){
                        System.out.println();
                        Pages.home_page();
                    }

                }
                break;

            }

        }  while (choice > 0);

    } // end edit function


    public void reservePlace(Place place) {
        reservedPlace = place;
    }  // end of reservePlace function



    public void deleteReservedPlace(){
        reservedPlace.setReserved(false);
        reservedPlace = null;
        System.out.println("The place has been deleted successfully.");

    } // end of deleteReservedPlace function



    public void hostPlace(Place place) {
        hostedPlaces.add(place); // adds the place to LinkedList of places
    } // end of hostPlace function


    // deleting the hosted place by its ID
    public void deleteHostedPlace(String id) {
        Place place = Place.removePlace(id); // returns the place and removes it from the PLACES container

        if (place != null)  {// checks if the place exists   // ???? and you have to check if this place is taken or not also from a user (khtb)
            hostedPlaces.remove(place);

        }
        else
            System.out.println("Place not found.");
    } // end of deleteHostedPlace function

    //setters and getters

    public void setPassword(String password) {

        this.password = password;
    }

    public void setUserName(String userName) {
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static HashMap<String, Account> getAllAccounts() {
        return ALL_ACCOUNTS;
    } // end of getAllAccounts function

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

    public String getGender() {
        return gender;
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

} // end of Account class

