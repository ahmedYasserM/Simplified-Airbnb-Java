import java.io.IOException;
import java.util.Map;


public class Main {

    /* this function is just for test data */
    public static void init() {
        Account acc1, acc2, acc3, acc4;

        acc1 = new Account();
        acc1.setFirstName("suhail");
        acc1.setLastName("Mahmoud");
        acc1.setGender("m");
        acc1.setUserName("malv");
        acc1.setPassword("malv");
        acc1.setPhoneNumber("0112383874834");
        acc1.setDateOfBirth(new Date("7", "4", "1995"));
        Account.getAllAccounts().put(acc1.getUserName(), acc1);

        acc2 = new Account();
        acc2.setFirstName("Mohammed");
        acc2.setLastName("Hesham");
        acc2.setGender("m");
        acc2.setUserName("moh");
        acc2.setPassword("moh");
        acc2.setPhoneNumber("011233243");
        acc2.setDateOfBirth(new Date("10", "10", "2008"));
        Account.getAllAccounts().put(acc2.getUserName(), acc2);

        acc3 = new Account();
        acc3.setFirstName("Abdo");
        acc3.setLastName("Khattab");
        acc3.setGender("f");
        acc3.setUserName("khattab");
        acc3.setPassword("khattab");
        acc3.setPhoneNumber("0123233844");
        acc3.setDateOfBirth(new Date("20", "4", "2010"));
        Account.getAllAccounts().put(acc3.getUserName(), acc3);

        acc4 = new Account();
        acc4.setFirstName("Mohammed");
        acc4.setLastName("Nossier");
        acc4.setGender("m");
        acc4.setUserName("m.nossier");
        acc4.setPassword("123456");
        acc4.setPhoneNumber("01183749373");
        acc4.setDateOfBirth(new Date("29", "10", "1998"));
        Account.getAllAccounts().put(acc4.getUserName(), acc4);


        Place p1, p2, p3, p4;

        p1 = new Place();
        p1.setPlaceType("villa");
        p1.setArea(500);
        p1.setHost(acc1);
        acc1.hostPlace(p1);
        p1.setNumOfBedrooms(8);
        p1.setNumOfBeds(8);
        p1.setNumOfBathrooms(4);
        p1.setLocation(new Location("egypt", "cairo", "katameya", "mahmodeya", "25"));
        p1.setMaximumGuests(10);
        p1.setPetsAllowed(true);
        p1.setSmokeFree(true);
        p1.setDescription("Good Place");
        Place.getAllPlaces().put(p1.getPlaceID(), p1);

        p2 = new Place();
        p2.setPlaceType("flat");
        p2.setArea(80);
        p2.setHost(acc2);
        acc2.hostPlace(p2);
        p2.setNumOfBedrooms(2);
        p2.setNumOfBeds(1);
        p2.setNumOfBathrooms(1);
        p2.setLocation(new Location("egypt", "fayoom", "abo-ghet", "el-teraa", "2b"));
        p2.setMaximumGuests(2);
        p2.setPetsAllowed(false);
        p2.setSmokeFree(false);
        p2.setDescription("Good Place");
        Place.getAllPlaces().put(p2.getPlaceID(), p2);


        p3 = new Place();
        p3.setPlaceType("flat");
        p3.setArea(120);
        p3.setHost(acc3);
        acc3.hostPlace(p3);
        p3.setNumOfBedrooms(3);
        p3.setNumOfBeds(2);
        p3.setNumOfBathrooms(1);
        p3.setLocation(new Location("egypt", "giza", "haram", "", "25"));
        p3.setMaximumGuests(3);
        p3.setPetsAllowed(true);
        p3.setSmokeFree(true);
        p3.setDescription("Good Place");
        Place.getAllPlaces().put(p3.getPlaceID(), p3);

        p4 = new Place();
        p4.setPlaceType("villa");
        p4.setArea(800);
        p4.setHost(acc4);
        acc4.hostPlace(p4);
        p4.setNumOfBedrooms(12);
        p4.setNumOfBeds(10);
        p4.setNumOfBathrooms(4);
        p4.setLocation(new Location("egypt", "cairo", "fifth-settlement", "el-golf", "100"));
        p4.setMaximumGuests(3);
        p4.setPetsAllowed(true);
        p4.setSmokeFree(false);
        p4.setDescription("Good Place");
        Place.getAllPlaces().put(p4.getPlaceID(), p4);
    }

    public static void main(String[] args) throws IOException  {

        init();
        Pages.home_page();
    }

}



