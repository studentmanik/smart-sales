package com.studentmanik.smartsales.BusinessObject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Ritu on 12/10/2016.
 */
public class SKUList implements Parcelable {
    ArrayList<SKU> skuArray=new ArrayList<>();

    public SKUList() {
    }

    protected SKUList(Parcel in) {
        skuArray = in.createTypedArrayList(SKU.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(skuArray);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SKUList> CREATOR = new Creator<SKUList>() {
        @Override
        public SKUList createFromParcel(Parcel in) {
            return new SKUList(in);
        }

        @Override
        public SKUList[] newArray(int size) {
            return new SKUList[size];
        }
    };

    public ArrayList<SKU> getSkuArray() {
        return skuArray;
    }

    public void setSkuArray(ArrayList<SKU> skuArray) {
        this.skuArray = skuArray;
    }


    public void addSku(SKU sku) {
        this.skuArray.add(sku);
    }









}
