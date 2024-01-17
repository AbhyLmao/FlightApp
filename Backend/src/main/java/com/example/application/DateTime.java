package com.example.application;

import java.util.HashMap;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private HashMap<Integer, Integer> numberOfDaysInMonth = new HashMap<Integer, Integer>(); // used for checking for valid days in month

    public DateTime(int y, int m, int d, int hr, int min, int sec) {
        numberOfDaysInMonth.put(1, 31);
        numberOfDaysInMonth.put(2, 29); // There can be 29 days in a leap year
        numberOfDaysInMonth.put(3, 31);
        numberOfDaysInMonth.put(4, 30);
        numberOfDaysInMonth.put(5, 31);
        numberOfDaysInMonth.put(6, 30);
        numberOfDaysInMonth.put(7, 31);
        numberOfDaysInMonth.put(8, 31);
        numberOfDaysInMonth.put(9, 30);
        numberOfDaysInMonth.put(10, 31);
        numberOfDaysInMonth.put(11, 30);
        numberOfDaysInMonth.put(12, 31);

        if (y < 0 || y > 9999) {
            throw new IllegalArgumentException("Year cannot be a negative value or greater than 9999");
        }
        if (m < 1 || m > 12) {
            throw new IllegalArgumentException("Invalid month");
        }
        if (d < 1 || d > numberOfDaysInMonth.get(m)) {
            throw new IllegalArgumentException("Invalid days for that month");
        }
        if ((y % 4 != 0) && (m==2) && (d>28)) {
            throw new IllegalArgumentException("February 29th is only on a leap year");
        }
        if (hr < 0 || hr > 23) {
            throw new IllegalArgumentException("Invalid hours");
        }
        if (min < 0 || min > 59) {
            throw new IllegalArgumentException("Invalid minutes");
        }
        if (sec < 0 || sec > 59) {
            throw new IllegalArgumentException("Invalid seconds");
        }
        this.year = y;
        this.month = m;
        this.day = d;
        this.hour = hr;
        this.minute = min;
        this.second = sec;
    } // end of constructor

    public int getYear() { return this.year; }
    public int getMonth() { return this.month; }
    public int getDay() { return this.day; }
    public int getHour() { return this.hour; }
    public int getMinute() { return this.minute; }
    public int getSecond() { return this.second; }


    public void setYear(int y) {
        if (y < 0 || y > 9999) {
            throw new IllegalArgumentException("Year cannot be a negative value");
        }
        if ((y % 4 != 0) && (this.month==2) && (this.day>28)) {
            throw new IllegalArgumentException("February 29th is only on a leap year");
        }
        this.year = y;
    }

    public void setMonthAndDay(int m, int d) {
        if (m < 1 || m > 12) {
            throw new IllegalArgumentException("Invalid month");
        }
        if (d < 1 || d > numberOfDaysInMonth.get(m)) {
            throw new IllegalArgumentException("Invalid days for that month");
        }
        if ((this.year % 4 != 0) && (m==2) && (d>28)) {
            throw new IllegalArgumentException("February 29th is only on a leap year");
        }
        this.month = m;
        this.day = d;
    }

    public void setHour(int hr) {
        if (hr < 0 || hr > 23) {    
            throw new IllegalArgumentException("Invalid hours");
        }
        this.hour = hr;
    }

    public void setMinute(int min) {
        if (min < 0 || min > 59) {
            throw new IllegalArgumentException("Invalid minutes");
        }
        this.minute = min;
    }

    public void setSecond(int sec) {
        if (sec < 0 || sec > 59) {
            throw new IllegalArgumentException("Invalid seconds");
        }
        this.second = sec;
    }

    public String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append(this.year);
        tempString.append("-");
        if (this.month < 10) {
            tempString.append("0");
        }
        tempString.append(this.month);
        tempString.append("-");
        if (this.day < 10) {
            tempString.append("0");
        }
        tempString.append(this.day);
        tempString.append(" ");
        if (this.hour < 10) {
            tempString.append("0");
        }
        tempString.append(this.hour);
        tempString.append(":");
        if (this.minute < 10) {
            tempString.append("0");
        }
        tempString.append(this.minute);
        tempString.append(":");

        if (this.second < 10) {
            tempString.append("0");
        }
        tempString.append(this.second);
        return tempString.toString();
    }
}