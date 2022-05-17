import java.util.HashMap;
import java.util.Random;

public class Place {

// --- MEMBERS ---
    // list containing all the places in the program
    private static HashMap<String, Place> PLACES = new HashMap<String, Place>();
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
    private String placeID;

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
    private String generatePlaceID() {
        // string contains all characters that could be in the ID
        String alpha_numeric = "0123456789" +
                "ABCDEFJHIJKLMNOPQRSTUVWXYZ"
                + "0123456789" + "abcdefghijklmnopqrstuvwxyz";


        /*
            NOTICE: we used 'StringBuilder' instead of regular 'String' because of
                    the immutability of the 'String' class.
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

        } while (PLACES.get(id.toString()) != null); // making sure no duplicates IDs
        return id.toString();
    }

    // PRINT CLASS DATA
    @Override
    public String toString() {
        String finalShape = "";
        finalShape += "--- " + placeType.toUpperCase() + " ---" + '\n';
        finalShape += "Owner: " + host.getFirstName() + ' ' + host.getLastName() + '\n';
        finalShape += "ID: " + placeID + '\n';
        finalShape += "Status: " + (isReserved ? "Reserved" : "Available") + '\n';
        finalShape += "Area: " + area + " m\n";
        finalShape += "Rooms: " + numOfRooms + '\n';
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
    // removing a place from the PLACES container and returning it
    // removes by ID
    public static Place removePlace(String placeID) {
        return PLACES.remove(placeID);
    }

    public static HashMap<String, Place> getPlaces() {
        return PLACES;
    }

    // prints all the places in the PLACES hashmap
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

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }
}

