package com.example.devil.customdialogbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Devil on 22-06-2016.
 */
public class ItemAdapter extends ArrayAdapter<Items> {


    public static ArrayList<Items> itemsList;

    static class ItemHolder {
        TextView textView;
        ImageView imageView;
    }


    public ItemAdapter(Context context, ArrayList<Items> items) {
        super(context, R.layout.custom_list_row, items);
        this.itemsList = items;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Items item = getItem(position);

        ItemHolder itemHolder = null;

        if (convertView == null) {
            itemHolder = new ItemHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_list_row, parent, false);
            itemHolder.textView = (TextView) convertView.findViewById(R.id.activity_text);
            itemHolder.imageView = (ImageView) convertView.findViewById(R.id.ivImage);

            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }

        itemHolder.imageView.setImageResource(item.getIcon());
        itemHolder.textView.setText(item.getItemName());

        return convertView;
    }
}
