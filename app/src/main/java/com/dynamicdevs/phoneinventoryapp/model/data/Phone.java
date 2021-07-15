package com.dynamicdevs.phoneinventoryapp.model.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable {

    protected Phone(Parcel in) {
        PhoneID = in.readInt();
        model = in.readString();
        price = in.readDouble();
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PhoneID);
        dest.writeString(model);
        dest.writeDouble(price);
    }

    public enum Manufacturer{
        APPLE,
        SAMSUNG,
        GOOGLE,
        HUAWEI,
        VIVO,
        LG,
        MOTOROLA,
    }

    private int PhoneID;
    private Manufacturer manufacturer;
    private String model;
    private double price;

    public Phone(int phoneID, Manufacturer manufacturer, String model, double price){
        this.PhoneID = phoneID;
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }
    public Phone(Manufacturer manufacturer, String model, double price){
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }

    public int getPhoneID() {
        return PhoneID;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }
}
