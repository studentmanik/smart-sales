package com.studentmanik.smartsales.BusinessObject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Ritu on 12/10/2016.
 */
public class SalesOrderLineList implements Parcelable {

    ArrayList<SalesOrderLine> salesOrderLineArray=new ArrayList<>();

    public SalesOrderLineList() {
    }

    protected SalesOrderLineList(Parcel in) {
        salesOrderLineArray = in.createTypedArrayList(SalesOrderLine.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(salesOrderLineArray);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SalesOrderLineList> CREATOR = new Creator<SalesOrderLineList>() {
        @Override
        public SalesOrderLineList createFromParcel(Parcel in) {
            return new SalesOrderLineList(in);
        }

        @Override
        public SalesOrderLineList[] newArray(int size) {
            return new SalesOrderLineList[size];
        }
    };

    public ArrayList<SalesOrderLine> getSalesOrderLineArray() {
        return salesOrderLineArray;
    }

    public void setSalesOrderLineArray(ArrayList<SalesOrderLine> salesOrderLineArray) {
        this.salesOrderLineArray = salesOrderLineArray;
    }

    public void addSalesOrderLine(SalesOrderLine salesOrderLine) {
        this.salesOrderLineArray.add(salesOrderLine);
    }
    public double totalOrderAmount(){
        double total=0;
        for (SalesOrderLine salesOrderLine:this.salesOrderLineArray         ) {
            total+=salesOrderLine.getTotalSalesPrice();
        }
        return total;
    }
    public boolean isInCart(SKU sku){
        boolean result=false;
        for (SalesOrderLine  s:this.salesOrderLineArray             ) {
            if (s.getSku().getId()==sku.getId()){
                result=true;
            }
        }
        return result;
    }
    public void removePromoSku(SalesOrderLine salesOrderLine) {
        Iterator<SalesOrderLine> orderLineArray = salesOrderLineArray.iterator();
        while (orderLineArray.hasNext()) {
            SalesOrderLine orderLine = orderLineArray.next();
            if (salesOrderLine.getSku().getId()==orderLine.getSku().getId()){

                orderLineArray.remove();

            }

        }

    }
}
