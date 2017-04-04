package com.studentmanik.smartsales.BusinessObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ritu on 12/10/2016.
 */
public class SalesOrderLine implements Parcelable{

    SKU sku;
    int quantityOrder;
    double totalSalesPrice;


    public SalesOrderLine() {
    }


    protected SalesOrderLine(Parcel in) {

        sku = in.readParcelable(SKU.class.getClassLoader());
        quantityOrder = in.readInt();
        totalSalesPrice = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(sku, flags);
        dest.writeInt(quantityOrder);
        dest.writeDouble(totalSalesPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SalesOrderLine> CREATOR = new Creator<SalesOrderLine>() {
        @Override
        public SalesOrderLine createFromParcel(Parcel in) {
            return new SalesOrderLine(in);
        }

        @Override
        public SalesOrderLine[] newArray(int size) {
            return new SalesOrderLine[size];
        }
    };


    public SKU getSku() {
        return sku;
    }

    public void setSku(SKU sku) {
        this.sku = sku;
    }
    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public double getTotalSalesPrice() {
        return totalSalesPrice;
    }

    public void setTotalSalesPrice(double totalSalesPrice) {
        this.totalSalesPrice = totalSalesPrice;
    }
}
