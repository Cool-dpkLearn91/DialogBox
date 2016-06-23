package com.example.devil.customdialogbox;

/**
 * Created by Devil on 22-06-2016.
 */
public class Items {

    public String itemName;
    public int icon;

    Items(){

    }

    public Items(String itemName, int icon) {
        this.itemName = itemName;
        this.icon = icon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
