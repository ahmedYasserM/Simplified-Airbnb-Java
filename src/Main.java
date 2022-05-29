import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException  {
        DataFiles.readPlaces();
        DataFiles.readAccounts();
        Pages.home_page();
    }
   
}



