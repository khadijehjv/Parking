package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ResultActivity extends AppCompatActivity {
    private TextView price;
    private DBHelper parking;
    private Button okBtn;
    private String carPlate;
    private String timeStamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initial();

        carPlate=getIntent().getStringExtra("plate");

//        price.setText(calPrice(DateDifference.findDifference("2022.08.23.10.00.00","2022.08.23.11.00.00")));
        price.setText(
                calPrice
                        (DateDifference.findDifference(
                                parking.getArrivalDateAndTime(carPlate)
                                ,timeStamp))+" تومان");

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    public void initial(){
        price=findViewById(R.id.result_textv_price);
        parking=new DBHelper(this);
        okBtn=findViewById(R.id.resultAct_btn_ok);
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }
    public String calPrice(long hour){
        PricePanel pricePanel=parking.getPriceAndHour();
        int fixedPrice=pricePanel.getPrice();
        int fixedHour=pricePanel.getHour();
        long resultPrice=(hour*fixedPrice)/fixedHour;
        return String.valueOf(resultPrice);
    }
}