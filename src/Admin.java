
import java.util.Scanner;

public class Admin {

    private static Scanner input = new Scanner(System.in);
    // Attributes
    private String username;
    private String password;

    // Getters
    public String getusername() {
        return username;
    }

    public String getpassword() {
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
        Place.removePlace(id);
    }

    public void deleteAccount(String userName) {
        Account.deleteAccount(Account.getAllAccounts().get(userName));
    }

    // Edit the attributes of the places
    public void editPlaces(String id) throws Exception {

        Place p = Place.findPlace(id);
        int change;
        System.out.println("To change place type enter 1");
        System.out.println("To change location enter 2");
        System.out.println("To change discreption enter 3");
        System.out.println("To change number of rooms enter 4");
        System.out.println("To change price enter 5");
        System.out.println("To change rules enter 6");
        System.out.println("To change rental duration enter 7");
        System.out.println("To change area enter 8");
        change = input.nextInt();
        input.nextLine();
        
        switch (change) {
            case 1: {
                String placetype;
                System.out.println("Enter the new place type");
                placetype = input.next();
                p.setPlaceType(placetype);
                System.out.println("The place type is updated successfully");
            }
                break;
                
            case 2: {
                Location location = null;
                String country, city, town, street, bn;
                System.out.println("Enter the new country");
                country = input.next();
                location.setCountry(country);
                System.out.println("Enter the new city");
                city = input.next();
                location.setCity(city);
                System.out.println("Enter the new town");
                town = input.next();
                location.setTown(town);
                System.out.println("Enter the new building number");
                bn = input.next();
                location.setBuildingNumber(bn);
                p.setLocation(location);
                System.out.println("The country is updated successfully");
            }
                break;

            case 3: {
                String description;
                System.out.println("Enter the new description");
                description = input.next();
                p.setDescription(description); 
                System.out.println("The description of the place is updated successfully");
            }
                break;

            case 4: {
                int roomsnum;
                System.out.println("Enter the new number of rooms");
                roomsnum = input.nextInt();
                input.nextLine();

                p.setNumOfRooms(roomsnum);
                System.out.println("The number of rooms is updated successfully");
            }       
                break;

            case 5: {
                int price;
                System.out.println("Enter the new price");
                price = input.nextInt();
                input.nextLine();

                p.setPrice(price);
                System.out.println("The price of the place is updated successfully");
            }
                break;

            case 6: {
                int maximumGuests = 0, allowp = 0, allows = 0;
                System.out.println("Enter the maximum number of guests");
                maximumGuests = input.nextInt();
                input.nextLine();

                p.setMaximumGuests(maximumGuests);
                System.out.println("The maximum number of guests is updated successfully");
                System.out.println("If you wants to allow pets in this place enter 1");
                allowp = input.nextInt();
                input.nextLine();

                if (allowp == 1) {
                    p.setPetsAllowed(true);
                    System.out.println("Pets are allowed");
                } else {
                    p.setPetsAllowed(false);
                    System.out.println("Pets are not allowed");
                }
                System.out.println("If you wants to allow pets in this place enter 1");
                allowp = input.nextInt();
                input.nextLine();

                if (allows == 1) {
                    p.setSmokeFree(true);
                    System.out.println("Smoking is allowed");
                } else {
                    p.setSmokeFree(false);
                    System.out.println("Smoking is not allowed");
                }
            }
                break;

            case 7: {
                int rentalduration;
                System.out.println("Enter the new rental duration");
                rentalduration = input.nextInt();
                input.nextLine();

                p.setRentalDuration(rentalduration);
            }
                break;

            case 8: {
                int area;
                System.out.println("Enter the new area");
                area = input.nextInt();
                input.nextLine();

                p.setArea(area);
            }
                break;

            case 9: {
                boolean reserved;
                System.out.println("Enter 1 to make the place reserved or 0 to make it unreserved");
                reserved = input.hasNext();
                if (reserved == true)
                    p.setReserved(true);
                else
                    p.setReserved(false);
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
                String firstname;
                System.out.println("Enter the new first name");
                firstname = input.next();
                a.setFirstName(firstname);
            }
                break;
            case 2: {
                String lastname;
                System.out.println("Enter the new last name");
                lastname = input.next();
                a.setLastName(lastname);
            }
                break;
            case 3: {
                String password;
                System.out.println("Enter the new password");
                password = input.next();
                a.setPassword(password);
            }
                break;
            case 4: {
                String phonenumber;
                System.out.println("Enter the new phone number");
                phonenumber = input.next();
                a.setPhoneNumber(phonenumber);
            }
                break;
            default:
                System.out.println("You entered wrong number");
                break;
        }
    }
}
