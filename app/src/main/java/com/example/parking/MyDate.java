package com.example.parking;


public class MyDate {
    public static String getYear(String date){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<4;i++) {
            stringBuilder.append(date.charAt(i));
        }
        String yearStr = stringBuilder.toString();
        return yearStr;
    }
    public static String getYear(int date){
        int year = date/10000;
        return Integer.toString(year);
    }

    public static String getMonth(String date){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=5;i<7;i++) {
            stringBuilder.append(date.charAt(i));
        }
        String monthStr = stringBuilder.toString();
        return monthStr;
    }
    public static String getMonth(int date){
        int num=date/100;
        return Integer.toString(num%100);
    }
    public static String getDay(String date){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=8;i<10;i++) {
            stringBuilder.append(date.charAt(i));
        }
        String dayStr = stringBuilder.toString();
        return dayStr;
    }
    public static String getDay(int date){
        return Integer.toString(date%100);
    }
    public static String getHour(String date){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=11;i<13;i++) {
            stringBuilder.append(date.charAt(i));
        }
        String monthStr = stringBuilder.toString();
        return monthStr;
    }
    public static String getHour(int time){
        return Integer.toString(time/100);
    }

    public static String getMin(String time){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=14;i<16;i++) {
            stringBuilder.append(time.charAt(i));
        }
        String monthStr = stringBuilder.toString();
        return monthStr;
    }
    public static String getMin(int time){
        return Integer.toString(time%100);
    }


    public static String getStrDateAndTime(int date, int time){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(MyDate.getYear(date));
        stringBuilder.append(".");
        stringBuilder.append(MyDate.getMonth(date));
        stringBuilder.append(".");
        stringBuilder.append(MyDate.getDay(date));
        stringBuilder.append(".");
        stringBuilder.append(MyDate.getHour(time));
        stringBuilder.append(".");
        stringBuilder.append(MyDate.getMin(time));
        stringBuilder.append(".");
        stringBuilder.append("00");
        String dateAndTime = stringBuilder.toString();
        return dateAndTime;
    }
}
