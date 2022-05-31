
import java.util.Scanner;

public class Admin {

    private static Scanner input = new Scanner(System.in);
    // Attributes
    private final String userName = "admin";
    private final String password = "admin";

    // Getters
    public String getUserName() {
        return userName;
    } // end of getUserName function

    public String getPassword() {
        return password;
    } // end of getPassword function

    // Display functions
    public void displayPlaces() {
        Place.displayPlaces();
    } // end of displayPlaces function

    public void displayAllAccounts(){

        int accountNumber = 1;

        for(Account user : Account.getAllAccounts().values()){

            System.out.println("Account # " + accountNumber + ":");
            System.out.println(user.toString(0));
            System.out.println("#####################################################################");
            accountNumber++;
        }


    } // end of displayAllAccounts function

    public void displayAllUserNames(){

        int userNumber = 1;
        for(Account user : Account.getAllAccounts().values()){
            System.out.println("User # "+userNumber+" => "+ user.getUserName());
            userNumber++;
        }


    } // end of displayAllUserNames function


    // Delete functions
    public void deletePlace(String id) {
        Account host = Place.getAllPlaces().get(id).getHost();
        host.deleteHostedPlace(id);
    } // end of deletePlace function


    // delete an account using its username
    public boolean deleteAccount(String userName) {
        Account user = Account.findAccount(userName);

        if(user != null) {

            System.out.println("\n In order to delete this account write \'DELETE ACCOUNT\' without quotes");

            String choice1 = input.nextLine();
            if (!(choice1.equals("DELETE ACCOUNT"))) {
                System.out.println("Account hasn't been deleted");
                return false; // if you do not want to delete the acccount
            }

            for (Place place : user.getHostedPlaces()) {
                String placeId = place.getPlaceID();
                Place.removePlace(placeId);

            }
            Account.getAllAccounts().remove(user.getUserName()); // HashMap.remove() is a built-in method of HashMap class and is used to remove the mapping of any particular key from the map.
            System.out.println("Account has been deleted successfully");
            return true; // if you deleted the account

        }
        return false; // if the account not found
    } // end of deleteAccount function


    // Edit the attributes of the places
    public void editPlaces(String id) throws Exception {

        Place place = Place.findPlace(id);
        int change;
        System.out.println("[1] Place Type.");
        System.out.println("[2] Location.");
        System.out.println("[3] Description.");
        System.out.println("[4] Number of Rooms.");
        System.out.println("[5] Number of Beds.");
        System.out.println("[6] Number of Bathrooms.");
        System.out.println("[7] Price.");
        System.out.println("[8] Rules.");
        System.out.println("[9] Rental Duration.");
        System.out.println("[10] Area.");
        System.out.println("[11] Place Reservation Status.");
        System.out.println("[0] Back.");
        change = input.nextInt();
        input.nextLine();

        switch (change) {
            case 1: {
                String placeType;
                System.out.println("Enter the new place type");
                placeType = input.nextLine();
                place.setPlaceType(placeType);
                System.out.println("The place type is updated successfully");
            }
            break;

            case 2: {
                Location location = new Location();
                String country, city, town, street, bn;
                System.out.println("Enter the new country");
                country = input.nextLine();
                location.setCountry(country);

                System.out.println("Enter the new city");
                city = input.nextLine();
                location.setCity(city);

                System.out.println("Enter the new town");
                town = input.nextLine();
                location.setTown(town);

                System.out.println("Enter the new street");
                street = input.nextLine();
                location.setStreet(street);

                System.out.println("Enter the new building number");
                bn = input.nextLine();
                location.setBuildingNumber(bn);

                place.setLocation(location);
                System.out.println("The country is updated successfully");
            }
            break;

            case 3: {
                String description;
                System.out.println("Enter the new description");
                description = input.nextLine();
                place.setDescription(description);
                System.out.println("The description of the place is updated successfully");
            }
            break;

            case 4: {
                int roomsNum;
                System.out.print("Enter the new number of rooms: ");
                roomsNum = input.nextInt();
                input.nextLine();

                place.setNumOfBedrooms(roomsNum);
                System.out.println("The number of rooms is updated successfully");
            }
            break;

            case 5: {
                int bedsNum;
                System.out.print("Enter the new number of beds: ");
                bedsNum = input.nextInt();
                input.nextLine();

                place.setNumOfBeds(bedsNum);
                System.out.println("The number of beds is updated successfully");
            }
            break;

            case 6: {
                int bathRoomsNum;
                System.out.print("Enter the new number of bathrooms: ");
                bathRoomsNum = input.nextInt();
                input.nextLine();

                place.setNumOfBedrooms(bathRoomsNum);
                System.out.println("The number of bathrooms is updated successfully");
            }
            break;

            case 7: {
                int price;
                System.out.print("Enter the new price: ");
                price = input.nextInt();
                input.nextLine();

                place.setPrice(price);
                System.out.println("The price of the place is updated successfully");
            }
            break;


            case 8: {
                int maximumGuests = 0, allowPets = 0, allows = 0;
                System.out.print("Enter the maximum number of guests: ");
                maximumGuests = input.nextInt();
                input.nextLine();

                place.setMaximumGuests(maximumGuests);
                System.out.println("The maximum number of guests is updated successfully");
                System.out.println("If you wants to allow pets in this place enter 1");
                allowPets = input.nextInt();
                input.nextLine();

                if (allowPets == 1) {
                    place.setPetsAllowed(true);
                    System.out.println("Pets are allowed");
                } else {
                    place.setPetsAllowed(false);
                    System.out.println("Pets are not allowed");
                }
                System.out.println("If you wants to allow pets in this place enter 1");
                allowPets = input.nextInt();
                input.nextLine();

                if (allows == 1) {
                    place.setSmokeFree(true);
                    System.out.println("Smoking is allowed");
                } else {
                    place.setSmokeFree(false);
                    System.out.println("Smoking is not allowed");
                }
            }
            break;


            case 9: {
                int rentalDuration;
                System.out.print("Enter the new rental duration: ");
                rentalDuration = input.nextInt();
                input.nextLine();

                place.setRentalDuration(rentalDuration);
            }
            break;

            case 10: {
                int area;
                System.out.print("Enter the new area: ");
                area = input.nextInt();
                input.nextLine();

                place.setArea(area);
            }
            break;

            case 11: {
                boolean reserved;
                System.out.print("Enter 1 to make the place reserved or 0 to make it unreserved: ");
                reserved = input.hasNext();
                if (reserved == true)
                    place.setReserved(true);
                else
                    place.setReserved(false);
            }
            break;
            case 0:{
                return;
            }

            default:
                System.out.println("You entered wrong number");
                break;
        }

    } // end of editPlaces function

    // Edit the attributes of the accounts
    public void editAccounts(String userName) {

        Account account = Account.findAccount(userName);
        // if the userName does not exist findAccount() will return null and print(incorrect userName) and then exit editAccounts function
        if(account != null) {


            int choice;
            do {

                System.out.println("[1] Change First Name: ");
                System.out.println("[2] Change Last Name: ");
                System.out.println("[4] Change Username: ");
                System.out.println("[4] Change Password: ");
                System.out.println("[5] Change Date of Birth: ");
                System.out.println("[6] Change Phone Number: ");
                System.out.println("[0] Back: ");

                System.out.print("> ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1: {

                        System.out.print("Enter new First Name: ");
                        String name = input.nextLine();
                        account.setFirstName(name);
                        System.out.println("First Name has been changed successfully");
                        System.out.println();
                    }
                    break;

                    case 2: {
                        System.out.print("Enter new Last Name: ");

                        String name = input.nextLine();
                        account.setLastName(name);
                        System.out.println("Last Name has been changed successfully");
                        System.out.println();
                    }
                    break;

                    case 3: {
                        System.out.print("Enter new username: ");

                        String name = input.nextLine();
                        account.setUserName(name);
                        System.out.println("Username has been changed successfully");
                        System.out.println();

                    }
                    break;

                    case 4: {

                        System.out.print("Enter new Password: ");
                        String password = input.nextLine();
                        account.setPassword(password);
                        System.out.println("Password has been changed successfully");
                        System.out.println();
                    }
                    break;

                    case 5:{
                        System.out.print("Enter new ");
                        account.getDateOfBirth().Date_inputInterface("Birth");
                        System.out.println("Date of Birth has been changed successfully");
                        System.out.println();
                    }
                    break;

                    case 6:{
                        System.out.print("Enter new Phone Number: ");
                        String number = input.nextLine();
                        account.setPhoneNumber(number);
                        System.out.println("Phone Number has been changed successfully");
                        System.out.println();

                    }
                    break;

                    case 0:{
                        return;
                    }

                    default:
                        System.out.println("You entered wrong number");
                        break;
                }
            } while (choice > 0);
        }
    } // end of editAccounts function
}
