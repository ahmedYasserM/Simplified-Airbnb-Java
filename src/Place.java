import java.util.*;



public class Place {

    // --- MEMBERS ---
    // list containing all the places input the program
    private static Scanner input = new Scanner(System.in);
    private static HashMap<String, Place> ALL_PLACES = new HashMap<String, Place>();
    public static HashMap<String, Place> tmpPlaces = new HashMap<>();

    Account host;
    String placeType;
    private int area;
    private int numOfBedrooms;
    private int numOfBeds; // new
    private int numOfBathrooms; // new
    private Location location;
    private int price;
    private int rentalDuration;

    private class PlaceRules {
        int maximumGuests;
        boolean arePetsAllowed;
        boolean isSmokeFree;
    }

    PlaceRules rules;
    Contract place_contract;
    private String description;
    private boolean isReserved;
    private String placeID;



// --- CONSTRUCTORS ---

    public Place(int ignore) {
        rules = new PlaceRules();
        isReserved = false;
        location = new Location();
        placeID = generatePlaceID();
    }

    public Place() {
        rules = new PlaceRules();
        isReserved = false;
        location = new Location();
        placeID = generatePlaceID();
        ALL_PLACES.put(placeID, this); // adding the place to ALL_PLACES list every time we create a place
    }

    public Place(String placeType, Account host, int area, int numOfRooms,int numOfBeds, int numOfBathrooms, Location location, int price,
                 int rentalDuration, PlaceRules rules, String place_description, boolean isReserved_place) {

        this.placeType = placeType;
        this.host = host;
        this.area = area;
        this.numOfBedrooms = numOfRooms;
        this.numOfBeds = numOfBeds; // new
        this.numOfBathrooms = numOfBathrooms; // new
        this.location = location;
        this.price = price;
        this.rentalDuration = rentalDuration;
        this.rules = rules;
        this.description = place_description;
        this.isReserved = false;
        this.placeID = generatePlaceID();
        ALL_PLACES.put(placeID, this); // adding the place to ALL_PLACES list every time we create a place


    }

// --- METHODS ---

    // helper function that generates unique random ID for every place
    private String generatePlaceID() {
        // string contains all characters that could be input the ID
        String alpha_numeric = "0123456789ABCDEFJHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        /*
            NOTICE: we used 'StringBuilder' instead of regular 'String' because of
                    the immutability of the 'String' class .
            In other words to SAVE MEMORY.
         */
        StringBuilder id = new StringBuilder();  // empty string to add input it the chosen characters from the alpha_numeric string

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
        place_contract.setPrice(price);

        Date date = new Date();

        date.Date_inputInterface("Booking");
        place_contract.setDateOfBooking(date);

        date.Date_inputInterface("Arrival");
        place_contract.setDateOfArrival(date);

        date.Date_inputInterface("Leaving");
        place_contract.setDateOfLeaving(date);
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
        finalShape += "Rooms: " + numOfBedrooms + '\n';
        finalShape += "Beds: " + numOfBeds + '\n';
        finalShape += "Bathrooms: " + numOfBathrooms + '\n';
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

    // removing a place from the ALL_PLACES container and returning it
    // removes by ID
    public static Place removePlace(String placeID) {
        return ALL_PLACES.remove(placeID);
    }  //this method is used input another method : removeHostedPlace (Khtb)

    public static HashMap<String, Place> getAllPlaces() {
        return ALL_PLACES;
    }

    // prints all the places input the ALL_PLACES hashmap (if type == 0) and the vec_Temp_Filtered_Places vector (if type == 1)
    public static void displayPlaces() {
        // printing all non-reserved places
        for (Map.Entry<String, Place> it : ALL_PLACES.entrySet()) {
            Place place = it.getValue();

            if (!place.isReserved())
                System.out.println(place.toString());
            System.out.println("==================================================");

        }
    }



    // Returns the place with the passed id
    public static Place findPlace(String id) throws Exception {
        Place place = ALL_PLACES.get(id);
        // TO BE CHANGED
        if (place != null)
            return place;
        throw new Exception("Place doesn't exist."); // throwing an exception if no place found
    }


    // Filter methods

    // if you pass zero it will not work
    public static void filterPlacesCountry(String country) {      // filtering depending on the  country
        Place value = null;

        if(country.equals("0"))
            return;

        for (String id : ALL_PLACES.keySet()) {
            try {
                value = findPlace(id);
            }catch (Exception e){
                e.toString();
            }
            if (!(value.location.getCountry().equals(country)))
                tmpPlaces.remove(id);
        }
    }


    // if you pass zero it will not work
    public static void filterPlacesCity(String city) {      // filtering depending on the city
        Place value = null;

        if(city.equals("0"))
            return;

        for (String id : ALL_PLACES.keySet()) {
            try {
                value = findPlace(id);
            }catch (Exception e){
                e.toString();
            }
            if (!(value.location.getCity().equals(city) ))
                tmpPlaces.remove(id);
        }


    }


    // if you pass zero it will not work
    public static void filterPlacesPrice(float Min_price, float Max_price) {        // filtering depending on the Price

        Place value = null;

        if(Min_price == 0 && Max_price == 0)
            return;

        for (String id : ALL_PLACES.keySet()) {
            try {
                value = findPlace(id);
            }catch (Exception e){
                e.toString();
            }
            if (!(value.getPrice() >= Min_price && value.getPrice() <= Max_price))
                tmpPlaces.remove(id);
        }

    }

    // if you pass zero it will not work
    public static void filterPlacesNumOfBedrooms(int numOfRooms) {                 // filtering depending on number of bedrooms


        Place value = null;

        if(numOfRooms == 0)
            return;

        for (String id : ALL_PLACES.keySet()) {
            try {
                value = findPlace(id);
            }catch (Exception e){
                e.toString();
            }
            if (!(value.getNumOfBedrooms() == numOfRooms))
                tmpPlaces.remove(id);
        }

    }

    // if you pass zero it will not work
    public static void filterPlacesNumOfBeds(int numOfBeds) {                 // filtering depending on number of beds


        Place value = null;


        if(numOfBeds == 0)
            return;

        for (String id : ALL_PLACES.keySet()) {
            try {
                value = findPlace(id);
            }catch (Exception e){
                e.toString();
            }
            if (!(value.getNumOfBeds() == numOfBeds))
                tmpPlaces.remove(id);
        }
    }

    // if you pass zero it will not work
    public static void filterPlacesNumOfBathRooms(int numOfBathrooms) {                 // filtering depending on number of bathrooms

        Place value = null;


        if(numOfBathrooms == 0)
            return;


        for (String id : ALL_PLACES.keySet()) {
            try {
                value = findPlace(id);
            }catch (Exception e){
                e.toString();
            }
            if (!(value.getNumOfBathrooms() == numOfBathrooms))
                tmpPlaces.remove(id);
        }
    }


    // public static void filterPlacesRentalDuration( ) ;




// ---- METHODS -----

    public void inputInterface() {
        System.out.println("--- [ Place Details ] ---");
        System.out.println();

        System.out.print("Type: ");
        setPlaceType(input.nextLine());

        System.out.print("Area: ");
        setArea(input.nextInt());
        input.nextLine();


        System.out.print("Enter place number of rooms: ");
        setNumOfBedrooms(input.nextInt());
        input.nextLine();

        System.out.print("Enter place number of beds: ");
        setNumOfBeds(input.nextInt());
        input.nextLine();

        System.out.print("Enter place number of bathrooms: ");
        setNumOfBathrooms(input.nextInt());
        input.nextLine();

        System.out.print("Enter price: ");
        setPrice(input.nextInt());
        input.nextLine();

        System.out.print("Enter Country: ");
        this.location.setCountry(input.nextLine());

        System.out.print("Enter City: ");
        this.location.setCity(input.nextLine());

    }

    public void edit() {

        if (this.isReserved) {
            System.out.println("You cannot edit/delete a reserved place.");
            return;
        }

        System.out.println("---- [ Edit Place ] ----");

        int choice;
        do {

            System.out.println("[1] Price");
            System.out.println("[2] Rental Duration");
            System.out.println("[3] Rules");
            System.out.println("[4] Description");
            System.out.println("[5] Delete Place");
            System.out.println("[0] Back");

            System.out.print("> ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: {

                    System.out.print("Enter new price, or '0' to return: ");
                    int nPrice = input.nextInt();
                    input.nextLine();

                    if (nPrice == 0)
                        continue;


                    setPrice(nPrice);
                }
                break;

                case 2: {

                    System.out.println("Enter new Rental Duration, or '0' to return: ");
                    int newRentalDuration;
                    while (true) {
                        newRentalDuration = input.nextInt();
                        input.nextLine();

                        if (this.rentalDuration == newRentalDuration) {
                            System.out.println("You entered the same rental duration, try again!");
                            continue;
                        }

                        if (newRentalDuration == 0)
                            continue;


                        setRentalDuration(newRentalDuration);
                        break;
                    }
                }
                break;

                case 3: {

                    System.out.print("Maximum Guests: ");
                    setMaximumGuests(input.nextInt());
                    input.nextLine();

                    System.out.print("Pets Allowed (yes / ENTER): ");
                    String flag = input.nextLine();
                    if (flag.equals("yes")) {
                        setPetsAllowed(true);
                    }
                    else {
                        setPetsAllowed(false);
                    }


                    System.out.print("SmokeFree (yes / ENTER): ");
                    flag = input.nextLine();
                    if (flag.equals("yes")) {
                        setSmokeFree(true);
                    }
                    else {
                        setSmokeFree(false);
                    }
                }
                break;

                case 4: {

                    System.out.print("New Description: ");
                    String newDes = input.nextLine();

                    setDescription(newDes);
                }
                break;

                case 5: {
                    System.out.print("\nInput order to delete your account write \'DELETE PLACE\' without quotes\n> ");

                    while (true) {

                        String choice1 = input.nextLine();
                        if(!choice1.equals("DELETE PLACE")) {
                            System.out.print("Incorrect, try again.\n> ");
                            continue;
                        }

                        System.out.println("Place has been deleted successfully");
                        System.out.println();
                        host.deleteHostedPlace(this.placeID);
                        return;
                    }
                }
            }

        } while (choice > 0);
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

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(int numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
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

    public Contract getContract() {
        return place_contract;
    }

    public void setContract(Contract contract) {
        this.place_contract = contract;
    }


}
