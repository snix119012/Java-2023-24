package me.aneta;

public class LeapYear {
    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public static void main(String[] args)
    {
        int year = 2024;
        boolean isLeap = isLeapYear(year);
        if (isLeap) {
            System.out.println(year + " is leap year.");
        } else {
            System.out.println(year + " is not leap year");
        }
    }
}