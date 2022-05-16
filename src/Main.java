

public class Main {
    public static void main(String[] args) throws Exception {


// EX 1
        Account a = new Account("malvister", "dkmf2340m32",
                "011239348", "7/4/2002");
        a.setFirstName("Suhail");
        a.setLastName("Mahmoud");

        Place p0 = new Place();
        p0.setArea(50);
        p0.setPlaceType("Private Room");
        p0.setHost(a);
        p0.setDescription("clean and elegant room in good spot of the town with a pretty good view");
        p0.setLocation(new Location("Egypt", "Cairo",
                "Nasr City", "Abbas el akkad", "40"));
        p0.setMaximumGuests(2);
        p0.setPetsAllowed(true);
        p0.setSmokeFree(false);
        p0.setNumOfRooms(1);
        p0.setReserved(false);
        p0.setPrice(3800);
        p0.setRentalDuration(20);

        Place p = new Place();
        p.setArea(5220);
        p.setPlaceType("Private Room");
        p.setHost(a);
        p.setDescription("clean and elegant room in good spot of the town with a pretty good view");
        p.setLocation(new Location("Egypt", "Cairo",
                "Nasr City", "Abbas el akkad", "40"));
        p.setMaximumGuests(2);
        p.setPetsAllowed(true);
        p.setSmokeFree(false);
        p.setNumOfRooms(1);
        p.setReserved(false);
        p.setPrice(1000000);
        p.setRentalDuration(20);
        int n = p0.getPlaceID();
        int m = p.getPlaceID();
        System.out.println(p0.getPlaceID());
        System.out.println(p.getPlaceID());
        try {
            System.out.println(Place.findPlace(8923).getArea());
        } catch (Exception e) {
            System.out.println(e);
        }

// EX 2
        /*Account a = new Account("malvister", "dkmf2340m32",
                "011239348", "7/4/2002");
        a.setFirstName("Suhail");
        a.setLastName("Mahmoud");

        Account b = new Account("wolffe", "m340003m3",
                "012383473", "29/3/1993");
        b.setFirstName("Ahmed");
        b.setLastName("Gamal");
        Contract c = new Contract();
        c.setHost(a);
        c.setCustomer(b);
        c.setDateOfBooking(new Date("16", "5", "2022"));
        c.setDateOfArrival(new Date("20", "5", "2022"));
        c.setDateOfLeaving(new Date("28", "5", "2022"));
        c.setPrice(3800);
        System.out.println(c.toString());*/
    }
}
