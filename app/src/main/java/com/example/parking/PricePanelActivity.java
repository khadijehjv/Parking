package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PricePanelActivity extends AppCompatActivity {
    private Button saveBtn;
    private Button cancelBtn;
    private EditText price_editTx;
    private EditText hour_editTx;
    private DBHelper parking;
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
                if (parking.isPriceTableEmpty()) {
                    parking.insertPrice(Integer.parseInt(price), Integer.parseInt(hour));
                }
                else{
                    parking.updatePriceAndHour(Integer.parseInt(price), Integer.parseInt(hour),parking.getPriceAndHour().getPrice(),parking.getPriceAndHour().getHour());
                }
                setResult(RESULT_OK);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }
    private void initialId(){
        saveBtn=findViewById(R.id.pricePanel_save_btn);
        cancelBtn=findViewById(R.id.pricePanel_cancel_btn);
        price_editTx=findViewById(R.id.pricePanel_price_editText);
        hour_editTx=findViewById(R.id.pricePanel_hour_editText);
        parking=new DBHelper(this);
    }
}