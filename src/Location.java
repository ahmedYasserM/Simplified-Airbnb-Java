import java.util.Scanner;

public class Location {

    // --- MEMBERS ---
    private String country;
    private String city;
    private String town;
    private String street;
    private String buildingNumber;


// --- CONSTRUCTORS ---

    public Location() {
        country = "N/A";
        city = "N/A";
        town = "N/A";
        street = "N/A";
        buildingNumber = "N/A";
    }

    public Location(String country, String city, String town, String street, String buildingNumber) {
        this.country = country;
        this.city = city;
        this.town = town;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

// --- METHODS ---

    // printing the location in one line comma format, i.e: 25 Abbas el akkad, Nasr City, Cairo, Egypt.
    // PRINT CLASS DATA
    @Override
    public String toString() {
        String finalShape = "";
        finalShape += buildingNumber + ' ';
        finalShape += street + ", ";
        finalShape += town + ", ";
        finalShape += city + ", ";
        finalShape += country + '.';
        return finalShape;
    }

    public void inputInterface() {
        Scanner input = new Scanner(System.in);

        System.out.print("Country: ");
        setCountry(input.nextLine());

        System.out.print("City: ");
        setCity(input.nextLine());

        System.out.print("Town: ");
        setTown(input.nextLine());

        System.out.print("Street: ");
        setStreet(input.nextLine());

        System.out.print("Building Number: ");
        setBuildingNumber(input.nextLine());
    }

    // copy another location to our location ->>will be useful in constructor in class Contract in passing arguments  :)
    public void copyAnotherLocation(Location location) {
        country = location.country;
        city = location.city;
        street = location.street;
        buildingNumber = location.buildingNumber;
    }

    // --- SETTERS & GETTERS ---
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}