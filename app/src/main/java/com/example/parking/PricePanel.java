package com.example.parking;

public class PricePanel {
    private int price;
    private int hour;
    PricePanel(){

    }
    PricePanel(int price,int hour){
        setPrice(price);
        setHour(hour);
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getHour() {
        return hour;
    }
}
