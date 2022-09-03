package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class PricePanelActivity extends AppCompatActivity {
    private Button saveBtn;
    private ImageView cancelImg;
    private EditText price_editTx;
    private EditText hour_editTx;
    private DBHelper parking;
    private String timeStamp;
    private Button priceHistory_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_panel);
        initialId();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price = price_editTx.getText().toString();
                String hour = hour_editTx.getText().toString();
                parking.insertPrice(Integer.parseInt(price), Integer.parseInt(hour),timeStamp);


                setResult(RESULT_OK);
                finish();
            }
        });
        cancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
//        priceHistory_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(PricePanelActivity.this,PriceTableActivity.class);
//                startActivity(intent);
//            }
//        });

    }
    private void initialId(){
        saveBtn=findViewById(R.id.pricePanel_save_btn);
        cancelImg=findViewById(R.id.pricePanel_cancel_img);
        price_editTx=findViewById(R.id.pricePanel_price_editText);
        hour_editTx=findViewById(R.id.pricePanel_hour_editText);
//        priceHistory_btn = findViewById(R.id.pricePanel_btn_priceHistory);
        parking=new DBHelper(this);
        price_editTx.setText(Integer.toString(parking.getPriceAndHour().getPrice()));
        hour_editTx.setText(Integer.toString(parking.getPriceAndHour().getHour()));
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

    }
}