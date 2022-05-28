
import java.util.Scanner;

public class Admin {

    private static Scanner input = new Scanner(System.in);
    // Attributes
    private final String userName = "admin";
    private final String password = "admin";

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    // Display functions
    public void displayPlaces() {
        Place.displayPlaces();
    }

    public void displayAccounts() {
        Account.displayAccounts();
    }

    // Delete functions
    public void deletePlace(String id) {
        Account host = Place.getAllPlaces().get(id).getHost();
        host.deleteHostedPlace(id);
    }

    public void deleteAccount(String userName) {
        Account.deleteAccount(Account.getAllAccounts().get(userName));
    }

    // Edit the attributes of the places
    public void editPlaces(String id) throws Exception {

        Place place = Place.findPlace(id);
        int change;
        System.out.println("[1] Place Type");
        System.out.println("[2] Location");
        System.out.println("[3] Description");
        System.out.println("[4] Number of Rooms");
        System.out.println("[5] Price");
        System.out.println("[6] Rules");
        System.out.println("[7] Rental Duration");
        System.out.println("[8] Area");
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
                System.out.println("Enter the new number of rooms");
                roomsNum = input.nextInt();
                input.nextLine();

                place.setNumOfRooms(roomsNum);
                System.out.println("The number of rooms is updated successfully");
            }       
                break;

            case 5: {
                int price;
                System.out.println("Enter the new price");
                price = input.nextInt();
                input.nextLine();

                place.setPrice(price);
                System.out.println("The price of the place is updated successfully");
            }
                break;

            case 6: {
                int maximumGuests = 0, allowp = 0, allows = 0;
                System.out.println("Enter the maximum number of guests");
                maximumGuests = input.nextInt();
                input.nextLine();

                place.setMaximumGuests(maximumGuests);
                System.out.println("The maximum number of guests is updated successfully");
                System.out.println("If you wants to allow pets in this place enter 1");
                allowp = input.nextInt();
                input.nextLine();

                if (allowp == 1) {
                    place.setPetsAllowed(true);
                    System.out.println("Pets are allowed");
                } else {
                    place.setPetsAllowed(false);
                    System.out.println("Pets are not allowed");
                }
                System.out.println("If you wants to allow pets in this place enter 1");
                allowp = input.nextInt();
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

            case 7: {
                int rentalduration;
                System.out.println("Enter the new rental duration");
                rentalduration = input.nextInt();
                input.nextLine();

                place.setRentalDuration(rentalduration);
            }
                break;

            case 8: {
                int area;
                System.out.println("Enter the new area");
                area = input.nextInt();
                input.nextLine();

                place.setArea(area);
            }
                break;

            case 9: {
                boolean reserved;
                System.out.println("Enter 1 to make the place reserved or 0 to make it unreserved");
                reserved = input.hasNext();
                if (reserved == true)
                    place.setReserved(true);
                else
                    place.setReserved(false);
            }
                break;
                
            default:
                System.out.println("You entered wrong number");
                break;
        }
    }

    // Edit the attributes of the accounts
    public void editAccounts(String userName) {

        Account a = Account.findAccount(userName);
        int change;
        System.out.println("To change first name enter  1");
        System.out.println("To change last name enter 2");
        System.out.println("To change password enter 3");
        System.out.println("To change phone number enter 4");
        
        change = input.nextInt();
        input.nextLine();

        switch (change) {
            case 1: {
                String firstName;
                System.out.println("Enter the new first name");
                firstName = input.nextLine();
                a.setFirstName(firstName);
            }
                break;
            case 2: {
                String lastname;
                System.out.println("Enter the new last name");
                lastname = input.nextLine();
                a.setLastName(lastname);
            }
                break;
            case 3: {
                String password;
                System.out.println("Enter the new password");
                password = input.nextLine();
                a.setPassword(password);
            }
                break;
            case 4: {
                String phoneNumber;
                System.out.println("Enter the new phone number");
                phoneNumber = input.nextLine();
                a.setPhoneNumber(phoneNumber);
            }
                break;
            default:
                System.out.println("You entered wrong number");
                break;
        }
    }
}
