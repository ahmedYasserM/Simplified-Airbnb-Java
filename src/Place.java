import java.util.LinkedList;

public class Place {

// --- MEMBERS ---
    // list containing all the places in the program
    public static LinkedList<Place> PLACES = new LinkedList<Place>();

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

// --- CONSTRUCTORS ---
    public Place() {
        rules = new PlaceRules();
        PLACES.add(this); // adding the place to PLACES list every time we create a place

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
        this.isReserved = isReserved;
        PLACES.add(this);
    }

// --- METHODS ---

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
    // removes by object
    public static void removePlace(Place obj) {
        PLACES.remove(obj);
    }

    // removes by index
    public static void removePlace(int index) {
        PLACES.remove(index);
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
}
