package com.example.parking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PriceTableAdapter extends ArrayAdapter<PricePanel> {
    private Context context;
    private List<PricePanel> pricesList;
    public PriceTableAdapter(@NonNull Context context, @NonNull List<PricePanel> prices) {
        super(context, R.layout.price_table, prices);
        this.context=context;
        this.pricesList=prices;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.price_table,parent,false);
        TextView price=convertView.findViewById(R.id.priceTable_tv_price);
        TextView hour=convertView.findViewById(R.id.priceTable_tv_hour);
        TextView date=convertView.findViewById(R.id.priceTable_tv_date);
        TextView time=convertView.findViewById(R.id.priceTable_tv_time);
        PricePanel pricePanel=pricesList.get(position);
        price.setText(pricePanel.getPrice());
        hour.setText(pricePanel.getHour());
        date.setText(pricePanel.getRegisterDate());
        time.setText(pricePanel.getRegisterTime());
        return convertView;
    }
}
