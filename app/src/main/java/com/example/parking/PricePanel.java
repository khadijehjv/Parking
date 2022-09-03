package com.example.parking;

public class PricePanel {
    private int price;
    private int hour;
    private String registerDate;
    private String registerTime;
    PricePanel(int price,int hour,String registerDate,String registerTime){
        setPrice(price);
        setHour(hour);
        setRegisterDate(registerDate);
        setRegisterTime(registerTime);

    }
    PricePanel(int price,int hour){
        setPrice(price);
        setHour(hour);
    }
    PricePanel(){

    }
    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
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
