package org.LeapYear;

public class Main {
    public static void main(String[] args) {

        LeapYear myObject = new LeapYear();
        int year = 2024;
        boolean isLeap = LeapYear.isLeapYear(year);
        if (isLeap) {
            System.out.println(year + " is leap year.");
        } else {
            System.out.println(year + " is not leap year");
        }


    }
}