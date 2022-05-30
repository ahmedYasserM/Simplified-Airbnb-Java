import java.io.IOException;
import java.util.Map;


public class Main {


    public static void main(String[] args) throws IOException  {
        DataFiles.readPlaces();
        DataFiles.readAccounts();
        for (Place place: Place.getAllPlaces().values()) 
            DataFiles.readHosts(place.getPlaceID());
        
        Pages.home_page();
    }
   
}



