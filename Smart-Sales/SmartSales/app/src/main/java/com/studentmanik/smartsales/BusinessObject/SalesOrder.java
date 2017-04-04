package com.studentmanik.smartsales.BusinessObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ritu on 12/10/2016.
 */
public class SalesOrder implements Parcelable {
  String localSoId;
    Outlet outlet;
    String orderDate;
    String orderTimeStamp;
    SalesOrderLineList salesOrderLineList;

    public SalesOrder() {
    }

    protected SalesOrder(Parcel in) {
        localSoId = in.readString();
        outlet = in.readParcelable(Outlet.class.getClassLoader());
        orderDate = in.readString();
        orderTimeStamp = in.readString();
        salesOrderLineList = in.readParcelable(SalesOrderLineList.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(localSoId);
        dest.writeParcelable(outlet, flags);
        dest.writeString(orderDate);
        dest.writeString(orderTimeStamp);
        dest.writeParcelable(salesOrderLineList, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SalesOrder> CREATOR = new Creator<SalesOrder>() {
        @Override
        public SalesOrder createFromParcel(Parcel in) {
            return new SalesOrder(in);
        }

        @Override
        public SalesOrder[] newArray(int size) {
            return new SalesOrder[size];
        }
    };

    public Outlet getOutlet() {
        return outlet;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    public SalesOrderLineList getSalesOrderLineList() {
        return salesOrderLineList;
    }

    public void setSalesOrderLineList(SalesOrderLineList salesOrderLineList) {
        this.salesOrderLineList = salesOrderLineList;
    }

    public String getLocalSoId() {
        return localSoId;
    }

    public void setLocalSoId(String localSoId) {
        this.localSoId = localSoId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTimeStamp() {
        return orderTimeStamp;
    }

    public void setOrderTimeStamp(String orderTimeStamp) {
        this.orderTimeStamp = orderTimeStamp;
    }

}
