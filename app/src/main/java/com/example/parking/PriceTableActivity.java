package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PriceTableActivity extends AppCompatActivity {
    private ListView listView;
    private List<PricePanel> prices=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_table);
        init();
        PriceTableAdapter priceTableAdapter=new PriceTableAdapter(this,prices);
        listView.setAdapter(priceTableAdapter);
    }
    public void init(){
        listView=findViewById(R.id.priceTable_listView);
        prices.add(new PricePanel(1000,1,"dhg","dhcfg"));
        prices.add(new PricePanel(1000,1,"dhg","dhcfg"));
        prices.add(new PricePanel(1000,1,"dhg","dhcfg"));
        prices.add(new PricePanel(1000,1,"dhg","dhcfg"));
        prices.add(new PricePanel(1000,1,"dhg","dhcfg"));
    }
}