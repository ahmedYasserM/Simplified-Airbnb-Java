import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Place {

// --- MEMBERS ---
    // list containing all the places in the program
    private static HashMap<String, Place> ALL_PLACES = new HashMap<String, Place>();
    // public static int place_orderd_ID_Cnt;   //(khtb)   just added to count every place created, but don't worry it doesn't affect any thing :) .

    Account host;
    String placeType;
    private int area;
    private int numOfRooms;
    private Location location;
    private int place_price;
    private int rentalDuration;

    private class PlaceRules {
        int maximumGuests;
        boolean arePetsAllowed;
        boolean isSmokeFree;
    }
    PlaceRules rules;
    Contract place_contract;
    private String place_description;
    private boolean isReserved_place;
    private String placeID;

    //private int place_ordered_ID = 0 ;  //  (khtb)      to make the place have an "ordered" ID  it doesn't affect any thing .

// --- CONSTRUCTORS ---

    public Place() {
        //place_ordered_ID = ++place_orderd_ID_Cnt;  //(khtb)
        rules = new PlaceRules();
        isReserved_place = false;
        location = new Location();
        placeID = generatePlaceID();
        ALL_PLACES.put(placeID, this); // adding the place to PLACES list every time we create a place
    }

    public Place(String placeType, Account host, int area, int numOfRooms, Location location, int place_price,
                 int rentalDuration, PlaceRules rules, String place_description, boolean isReserved_place) {

        //place_ordered_ID = ++place_orderd_ID_Cnt; //(khtb)
        this.placeType = placeType;
        this.host = host;
        this.area = area;
        this.numOfRooms = numOfRooms;
        this.location = location;
        this.place_price = place_price;
        this.rentalDuration = rentalDuration;
        this.rules = rules;
        this.place_description = place_description;
        this.isReserved_place = false;
        this.placeID = generatePlaceID();
        ALL_PLACES.put(placeID, this); // adding the place to PLACES list every time we create a place
    }

// --- METHODS ---

    // helper function that generates unique random ID for every place
    private String generatePlaceID() {
        // string contains all characters that could be in the ID
        String alpha_numeric = "0123456789ABCDEFJHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        /*
            NOTICE: we used 'StringBuilder' instead of regular 'String' because of
                    the immutability of the 'String' class .
            In other words to SAVE MEMORY.
         */
        StringBuilder id = new StringBuilder();  // empty string to add in it the chosen characters from the alpha_numeric string

        /* the "do while" loop is to make sure we don't have two identical IDs.
            chance to happen less than 1%.
         */
        do {
            Random rand = new Random();
            id.setLength(0);

            // the number of iteration is the preferred size of the ID
            for (int i = 0; i < 5; i++) {
                // generating random index of the 'alpha_numeric' string
                int index = Math.abs(rand.nextInt(alpha_numeric.length()));

                // appending the character at position 'index' to 'id' string
                id.append(alpha_numeric.charAt(index));
            }

        } while (ALL_PLACES.get(id.toString()) != null); // making sure no duplicates IDs
        return id.toString();
    }

    public void create_contract(Account customer) {
        place_contract = new Contract();
        place_contract.setHost(host);
        place_contract.setCustomer(customer);
        place_contract.setPrice(place_price);
        Date date = new Date();

        date.inputInterface("Booking");
        place_contract.setDateOfBooking(date);

        date.inputInterface("Arrival");
        place_contract.setDateOfArrival(date);

        date.inputInterface("Leaving");
        place_contract.setDateOfLeaving(date);

    }

    // PRINT CLASS DATA
    @Override
    public String toString() {
        String finalShape = "";
        finalShape += "--- " + placeType.toUpperCase() + " ---" + '\n';
        finalShape += "Owner: " + host.getFirstName() + ' ' + host.getLastName() + '\n';
        finalShape += "ID: " + placeID + '\n';
        finalShape += "Status: " + (isReserved_place ? "Reserved" : "Available") + '\n';
        finalShape += "Area: " + area + " m\n";
        finalShape += "Rooms: " + numOfRooms + '\n';
        finalShape += "Location: " + location.toString() + '\n';
        finalShape += "Rental Duration: " + rentalDuration + " day/s" + '\n';
        finalShape += "Rules: " + '\n';
        finalShape += (rules.arePetsAllowed ? "\tPets Allowed." : "\tNo Pets.") + '\n';
        finalShape += (rules.isSmokeFree ? "\tSmoking Allowed." : "\tNo Smoking.") + '\n';
        finalShape += "\t" + rules.maximumGuests + " Guest/s at most.\n";
        finalShape += "Price: " + place_price + "$\n";
        finalShape += "Additional details: \n" + "\t\"" + place_description + "\"\n";

        return finalShape;
    }

// --- STATIC METHODS ---
    // removing a place from the PLACES container and returning it
    // removes by ID
    public static Place removePlace(String placeID) {
        return ALL_PLACES.remove(placeID);
    }  //this method is used in another method : removeHostedPlace (Khtb)

    public static HashMap<String, Place> getAllPlaces() {
        return ALL_PLACES;
    }

    // prints all the places in the PLACES hashmap
    public static void displayPlaces(Account user) {
        for (Map.Entry<String, Place> place : ALL_PLACES.entrySet()) {
            Place p = place.getValue();
            if (p.getHost().getUserName() == user.getUserName())
                continue;
            if (!p.isReserved_place())
                System.out.println(p.toString());
            System.out.println("#####################################################################");
        }
    }

    // returns the place with the passed id
    public static Place findPlace(String id) throws Exception {
        Place place = ALL_PLACES.get(id);
        if (place != null)
            return place;
        throw new Exception("Place doesn't exist."); // throwing an exception if no place found
    }

    public void inputInterface() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter place type: ");
        setPlaceType(in.nextLine());

        System.out.print("Enter Place area: ");
        setArea(in.nextInt());

        System.out.print("Enter place number of rooms: ");
        setNumOfRooms(in.nextInt());

//        System.out.print("Enter ");

    }

// --- SETTERS & GETTERS ---
    public Account getHost() {
        return host;
    }

    public void setHost(Account host) {
        this.host = host;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPrice_of_place() {
        return place_price;
    }

    public void setPrice_of_place(int place_price) {
        this.place_price = place_price;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public PlaceRules getRules() {
        return rules;
    }

    public void setRules(PlaceRules rules) {
        this.rules = rules;
    }

    public boolean isSmokeFree() {
        return rules.isSmokeFree;
    }

    public void setSmokeFree(boolean smokeFree) {
        rules.isSmokeFree = smokeFree;
    }

    public boolean arePetsAllowed() {
        return rules.arePetsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        rules.arePetsAllowed = petsAllowed;
    }

    public int getMaximumGuests() {
        return rules.maximumGuests;
    }

    public void setMaximumGuests(int maximumGuests) {
        rules.maximumGuests = maximumGuests;
    }

    public String getPlace_description() {
        return place_description;
    }

    public void setPlace_description(String place_description) {
        this.place_description = place_description;
    }

    public boolean isReserved_place() {
        return isReserved_place;
    }

    public void setReserved_place(boolean reserved_place) {
        isReserved_place = reserved_place;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public Contract getContract() {
        return place_contract;
    }

    public void setContract(Contract contract) {
        this.place_contract = contract;
    }
}

