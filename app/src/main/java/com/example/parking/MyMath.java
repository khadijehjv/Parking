package com.example.parking;

public class MyMath {
    public static int getDate(int yyyy,int mm,int dd){
        return (yyyy*100+mm)*100+dd;
    }
    public static int getTime(int hh,int mm){
        return hh*100+mm;
    }
}
