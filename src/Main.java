import java.io.IOException;
import java.util.Map;


public class Main {


    public static void main(String[] args) throws IOException  {
        DataFiles.readPlaces();
        DataFiles.readAccounts();
        for (Map.Entry<String, Place> it: Place.getAllPlaces().entrySet()) {
            DataFiles.readHosts(it.getValue().getPlaceID());
        }
        Pages.home_page();
    }
   
}



