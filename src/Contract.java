import java.util.HashMap;

public class Contract {

    // --- MEMBER ---

    private Date dateOfBooking;
    private Date dateOfArrival;
    private Date dateOfLeaving;
    private Account host;
    private Account customer;
    private int price;

// --- CONSTRUCTORS ---

    public Contract() {}

    public Contract(Date dateOfBooking, Date dateOfArrival, Date dateOfLeaving,
                    int price, Account customer, Account host) {
        this.dateOfBooking = dateOfBooking;
        this.dateOfArrival = dateOfArrival;
        this.dateOfLeaving = dateOfLeaving;
        this.host = host;
        this.customer = customer;
        this.price = price;
    }

// --- METHODS ---

    // printing all the info of the host except the password
    public void printHostInfo() {
        System.out.println(host.toString());
    }

    // printing all the info of the customer except the password
    public void printCustomerInfo() {
        System.out.println(customer.toString());
    }

    // PRINT CLASS DATA
    @Override
    public String toString() {

        String finalShape = "--- Host Info --- \n";
        finalShape += host.toString();
        finalShape += '\n';

        finalShape += "--- Customer Info --- \n";
        finalShape += customer.toString();
        finalShape += '\n';

        finalShape += "Price: " + price + '$' + '\n';
        finalShape += "Date of Booking: " + dateOfBooking.toString() + '\n';
        finalShape += "Date of Arrival: " + dateOfArrival.toString() + '\n';
        finalShape += "Date of Leaving: " + dateOfLeaving.toString() + '\n';

        return finalShape;
    }


// --- SETTERS & GETTERS ---
    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Date getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(Date dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }


    public Account getHost() {
        return host;
    }

    public void setHost(Account host) {
        this.host = host;
    }

    public Account getCustomer() {
        return customer;
    }

    public void setCustomer(Account customer) {
        this.customer = customer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
