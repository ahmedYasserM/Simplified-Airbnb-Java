import java.util.HashMap;
import java.util.Random;

public class Place {

// --- MEMBERS ---
    // list containing all the places in the program
    private static HashMap<Integer, Place> PLACES = new HashMap<Integer, Place>();
    private static int ID = 0;

    Account host;
    String placeType;
    private int area;
    private int numOfRooms;
    private Location location;
    private int price;
    private int rentalDuration;
    private class PlaceRules {
        int maximumGuests;
        boolean arePetsAllowed;
        boolean isSmokeFree;
    }
    PlaceRules rules;
    private String description;
    private boolean isReserved;
    private int placeID;

// --- CONSTRUCTORS ---
    public Place() {
        rules = new PlaceRules();
        isReserved = false;
        placeID = generatePlaceID();
        PLACES.put(placeID, this); // adding the place to PLACES list every time we create a place
    }
    public Place(String placeType, Account host, int area, int numOfRooms, Location location, int price,
                 int rentalDuration, PlaceRules rules, String description, boolean isReserved) {
        this.placeType = placeType;
        this.host = host;
        this.area = area;
        this.numOfRooms = numOfRooms;
        this.location = location;
        this.price = price;
        this.rentalDuration = rentalDuration;
        this.rules = rules;
        this.description = description;
        this.isReserved = false;
        this.placeID = generatePlaceID();
        PLACES.put(placeID, this); // adding the place to PLACES list every time we create a place
    }

// --- METHODS ---

    // helper function that generates unique random ID for every place
    private int generatePlaceID() {
        Random rand = new Random();
        int id;
        do {
            id = Math.abs(rand.nextInt());
        } while (PLACES.get(id) != null);
        return id;
    }

    // PRINT CLASS DATA
    @Override
    public String toString() {
        String finalShape = "";
        finalShape += "--- " + placeType.toUpperCase() + " ---" + '\n';
        finalShape += "Owner: " + host.getFirstName() + ' ' + host.getLastName() + '\n';
        finalShape += "Area: " + area + " m\n";
        finalShape += "# Rooms: " + numOfRooms + '\n';
        finalShape += "Location: " + location.toString() + '\n';
        finalShape += "Rental Duration: " + rentalDuration + " day/s" + '\n';
        finalShape += "Rules: " + '\n';
        finalShape += (rules.arePetsAllowed ? "\tPets Allowed." : "\tNo Pets.") + '\n';
        finalShape += (rules.isSmokeFree ? "\tSmoking Allowed." : "\tNo Smoking.") + '\n';
        finalShape += "\t" + rules.maximumGuests + " Guest/s at most.\n";
        finalShape += "Price: " + price + "$\n";
        finalShape += "Additional details: \n" + "\t\"" + description + "\"\n";

        return finalShape;
    }

// --- STATIC METHODS ---
    // removing a place from the list that contains all the places
    // removes by ID
    public static void removePlace(int placeID) {
        PLACES.remove(placeID);
    }

    public static HashMap<Integer, Place> getPlaces() {
        return PLACES;
    }

    public static void displayPlaces() {
        int size = PLACES.size();
        for (int i = 0; i < size; i++) {
            System.out.println(PLACES.get(i).toString());
            System.out.println("#####################################################################");
        }
    }

    // returns the place with the passed id
    public static Place findPlace(int id) throws Exception {
        Place place = PLACES.get(id);
        if (place != null)
            return place;
        throw new Exception("Place doesn't exist."); // throwing an exception if no place found
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }
}
