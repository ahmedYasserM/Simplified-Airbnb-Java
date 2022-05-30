import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public abstract class DataFiles {

    public static void readAccounts() {
        try {
            File folder_path = new File("Accounts");
            File[] listOfFiles = folder_path.listFiles();
            for (File file : listOfFiles) {
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);

                Account account = new Account();
                
                account.setFirstName(br.readLine().replace("fn: ", ""));
                account.setLastName(br.readLine().replace("ln: ", ""));            
                account.setUserName(br.readLine().replace("un: ", ""));
                account.setPassword(br.readLine().replace("ps: ", ""));
                account.setPhoneNumber(br.readLine().replace("ph: ", ""));
                account.setDateOfBirth(new Date(br.readLine().replace("dd: ", "")
                , br.readLine().replace("mm: ", "")
                , br.readLine().replace("yy: ", "")));
                Place place = Place.getAllPlaces().get(br.readLine().replace("rp: ", "")); 

                if (place != null) 
                    account.reservePlace(place);

                for (int i = 0; i < 3; i++) { 
                    String placeID = br.readLine();
                    if (placeID.length() == 8) continue;
                    account.hostPlace(Place.getAllPlaces().get(placeID.replace("hp: ", ""))); 
                }

                Account.getAllAccounts().put(account.getUserName(), account);

                fileReader.close();
                br.close();
            }
        } catch(Exception e) {
            System.out.println("Error in reading Accounts.");
            e.printStackTrace();
        }
    }

    public static void readPlaces() {
        try {
            File folder_path = new File("Places");
            File[] listOfFiles = folder_path.listFiles();
            for (File file : listOfFiles) {
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);

                Place place = new Place(0);
                br.readLine();
                // place.setHost(Account.getAllAccounts().get(br.readLine().replace("ht: ", "")));
                place.setPlaceType(br.readLine().replace("pt: ", ""));
                place.setPlaceID(br.readLine().replace("id: ", ""));
                place.setArea(Integer.parseInt(br.readLine().replace("ar: ", "")));
                place.setNumOfRooms(Integer.parseInt(br.readLine().replace("#r: ", "")));
                place.setRentalDuration(Integer.parseInt(br.readLine().replace("rd: ", "")));
                place.setPrice(Integer.parseInt(br.readLine().replace("pr: ", "")));
                place.setReserved(Boolean.parseBoolean(br.readLine().replace("rs: ", "")));
                place.setLocation(new Location(br.readLine().replace("LC: ", ""),
                    br.readLine().replace("Lc: ", ""),
                    br.readLine().replace("Lt: ", ""),
                    br.readLine().replace("Ls: ", ""),
                    br.readLine().replace("Lb: ", "")));
                place.setMaximumGuests(Integer.parseInt(br.readLine().replace("Rg: ", "")));
                place.setPetsAllowed(Boolean.parseBoolean(br.readLine().replace("Rp: ", "")));
                place.setSmokeFree(Boolean.parseBoolean(br.readLine().replace("Rs: ", "")));
                place.setDescription(br.readLine().replace("ds: ", ""));

                Place.getAllPlaces().put(place.getPlaceID(), place);

                fileReader.close();
                br.close();
            }
        } catch(Exception e) {
            System.out.println("Error in reading Places.");
            e.printStackTrace();
        }
    }

    public static void readHosts(String placeID) {
        try {
                
            Place place = Place.getAllPlaces().get(placeID);

            File file = new File("Places/" + placeID + ".txt");

            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            place.setHost(Account.getAllAccounts().get(br.readLine().replace("ht: ", "")));

            fileReader.close();
            br.close();
        
        } catch(Exception e) {
            System.out.println("Error in reading Hosts.");
            e.printStackTrace();
        }
    }

    public static void writeAccount(Account account) {
        File file = new File("Accounts/" + account.getUserName() + ".txt");
        try {

            file.createNewFile();

            FileWriter fileWriter = new FileWriter("Accounts/" + account.getUserName() + ".txt");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write("fn: " + account.getFirstName() + '\n');
            bw.write("ln: " + account.getLastName() + '\n');
            bw.write("un: " + account.getUserName() + '\n');
            bw.write("ps: " + account.getPassword() + '\n');
            bw.write("ph: " + account.getPhoneNumber() + '\n');
            bw.write("dd: " + account.getDateOfBirth().getDay() + '\n');
            bw.write("mm: " + account.getDateOfBirth().getMonth() + '\n');
            bw.write("yy: " + account.getDateOfBirth().getYear() + '\n');
            bw.write("rp: " + String.valueOf(account.getReservedPlace()) + '\n');

            for (int i = 0; i < 3; i++) {
                if (i >= account.getHostedPlaces().size()) {
                    bw.write("hp: " + "null" + '\n');
                    continue;
                } 
                bw.write("hp: " + account.getHostedPlaces().elementAt(i).getPlaceID());
            }
            

            bw.flush();
            bw.close();
            
        } catch (IOException e) {
            System.out.println("Error occurred in writing account");
            e.printStackTrace();
        }
    }

    public static void writePlace(Place place) {
        File file = new File("Places/" + place.getPlaceID() + ".txt");
        try {

            file.createNewFile();

            FileWriter fileWriter = new FileWriter("Places/" + place.getPlaceID() + ".txt");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write("ht: " + place.getHost().getUserName() + '\n');
            bw.write("pt: " + place.getPlaceType() + '\n');
            bw.write("id: " + place.getPlaceID() + '\n');
            bw.write("ar: " + place.getArea() + '\n');
            bw.write("#r: " + place.getNumOfRooms() + '\n');
            bw.write("rd: " + place.getRentalDuration() + '\n');
            bw.write("pr: " + place.getPrice() + '\n');
            bw.write("rs: " + String.valueOf(place.isReserved()) + '\n');
            bw.write("LC: " + place.getLocation().getCountry() + '\n');
            bw.write("Lc: " + place.getLocation().getCity() + '\n');
            bw.write("Lt: " + place.getLocation().getTown() + '\n');
            bw.write("Ls: " + place.getLocation().getStreet() + '\n');
            bw.write("Lb: " + place.getLocation().getBuildingNumber() + '\n');
            bw.write("Rg: " + String.valueOf(place.getMaximumGuests()) + '\n');
            bw.write("Rp: " + String.valueOf(place.arePetsAllowed()) + '\n');
            bw.write("Rs: " + String.valueOf(place.isSmokeFree()) + '\n');
            bw.write("ds: " + place.getDescription() + '\n');

            bw.flush();
            bw.close();
            
        } catch (IOException e) {
            System.out.println("Error occurred in writing account");
            e.printStackTrace();
        }
    } 

    public static void editFile(String fileName, String typeDef, String oldValue, String newValue) {
        List<String> lines = new ArrayList<String>();
        String line_in_lines = null;


        try {
            
            if (typeDef.equals("un")) {
                File file = new File(fileName + ".txt");
                file.renameTo(new File("Accounts/" + newValue + ".txt"));
                fileName = String.copyValueOf(newValue.toCharArray());
            }

            oldValue = typeDef + ": " + oldValue;
            newValue = typeDef + ": " + newValue;

            File file = new File(fileName + ".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            Boolean oneChangeOnly = false;
            while ((line_in_lines = br.readLine()) != null) {
                if (line_in_lines.contains(oldValue) && !oneChangeOnly) {
                    line_in_lines = line_in_lines.replace(oldValue, newValue);
                    oneChangeOnly = true;
                } 
                
                lines.add(line_in_lines + '\n');
            }

            fileReader.close();
            br.close();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            for(String line : lines)
                bw.write(line);

            bw.flush();
            bw.close();

        } catch (Exception e) {
            System.out.println("Error occurred in editing file.");
            e.printStackTrace();
        }
    }

    public static void eraseAccount(String userName) {
        File file = new File("Accounts/" + userName + ".txt");
        file.delete();
    }

    public static void erasePlace(String id) {
        File file = new File("Places/" + id + ".txt");
        file.delete();
    }
    
}
