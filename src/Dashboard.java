/*
    Created By  خطاب
*/
    public class Dashboard {


        //Methods----
        public static float getMaleTypePercentage() {

            float male_prectentage = (Account.Male_Counter / Account.AccountCnt) * 100f;
            return male_prectentage;
        }

        public static float getFemaleTypePercentage() {

            float female_prectentage = (Account.Female_Counter / Account.AccountCnt) * 100f;
            return female_prectentage;
        }


        public static void viewMostHostedPlacesCities() {

        }


        public String toString() {

            String finalShape = "";

             // finalShape += "Percentage_of_Male " + getMaleTypePrecentage() + " \n";
            // finalShape += "Percentage_of_Female " + getFemaleTypePrecentage() + " \n";

            return finalShape;
        } //end of toString fun Dashboard

    }






