import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/*
    Created By  خطاب
*/
public class Dashboard {



    //Methods----
    public static float getMaleTypePrecentage(){

        float male_prectentage = (Account.Male_Counter/Account.AccountCnt)*100f ;
        return male_prectentage;
    }

    public static float getFemaleTypePrecentage(){

    float female_prectentage = (Account.Female_Counter/Account.AccountCnt)*100f ;
        return female_prectentage ;
    }



    public static void viewMostHostedPlacesCities(){

    }



    public String toString() {

        String finalShape = "";

        finalShape += "prectentage_of_Male " + getMaleTypePrecentage() + " \n";
        finalShape += "prectentage_of_Female " + getFemaleTypePrecentage() + " \n";

        return finalShape;
    } //end of tostring fun Dashboard





}
