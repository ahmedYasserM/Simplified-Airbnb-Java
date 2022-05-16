

public class Main {
    public static void main(String[] args){

        Place p = new Place();
        p.setArea(34);
        System.out.println(Place.PLACES.size());
        Place.removePlace(p);
        System.out.println(Place.PLACES.size());

// EX 1
        /*Account a = new Account("malvister", "dkmf2340m32",
                "011239348", "7/4/2002");
        a.setFirstName("Suhail");
        a.setLastName("Mahmoud");

        Place p = new Place();
        p.setArea(50);
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
        p.setPrice(3800);
        p.setRentalDuration(20);
        System.out.println(p.toString());*/


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
