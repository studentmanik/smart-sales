package com.studentmanik.smartsales.BusinessObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ritu on 12/10/2016.
 */
public class SKU implements Parcelable {
    int id;
    String name;
    double price;
    int inCart;

    public SKU() {
    }

    public SKU(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    protected SKU(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        inCart = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(inCart);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SKU> CREATOR = new Creator<SKU>() {
        @Override
        public SKU createFromParcel(Parcel in) {
            return new SKU(in);
        }

        @Override
        public SKU[] newArray(int size) {
            return new SKU[size];
        }
    };

    public int getInCart() {
        return inCart;
    }

    public void setInCart(int inCart) {
        this.inCart = inCart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
