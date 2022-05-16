//
// Created by Hesham.
//


//Date class to use it in date of arrival,leaving and booking in Class contract

public class Date {

    //attributes

    private String day;
    private String month;
    private String  year;


    // methods

    //default constructor:  day=xx month=xx   year=xxxx
    Date()
    {
        day = "xx";
        month = "xx";
        year = "xxxx";

    }


    Date(String  day, String  month, String year)
    {
        this.day = day;
        this.month = month;
        this.year = year;

    }





    //method to copy obj date attributes to our Date object in class contract we will use it in contract constructer
    void copyAnotherDate(Date passed)
    {
        day = passed.day;
        month = passed.month;
        year = passed.year;
    } // end of copyAnotherDate function

    //method to return data in string in form of dd/mm/yyyy   ex->   25/4/2022;
    String GetDate()
    {
        String finalShape = "";
        finalShape += day;
        finalShape += '/';
        finalShape += month;
        finalShape += '/';
        finalShape += year;

        return finalShape;
    } // end of copyAnotherDate function

    // setters

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }


    // getters

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }


}
