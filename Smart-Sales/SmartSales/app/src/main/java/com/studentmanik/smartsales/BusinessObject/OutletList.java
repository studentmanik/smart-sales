package com.studentmanik.smartsales.BusinessObject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Ritu on 12/10/2016.
 */
public class OutletList implements Parcelable {
    ArrayList<Outlet> outletArray=new ArrayList<> ();

    public OutletList() {
    }

    protected OutletList(Parcel in) {
        outletArray = in.createTypedArrayList(Outlet.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(outletArray);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OutletList> CREATOR = new Creator<OutletList>() {
        @Override
        public OutletList createFromParcel(Parcel in) {
            return new OutletList(in);
        }

        @Override
        public OutletList[] newArray(int size) {
            return new OutletList[size];
        }
    };

    public ArrayList<Outlet> getOutletArray() {
        return outletArray;
    }

    public void setOutletArray(ArrayList<Outlet> outletArray) {
        this.outletArray = outletArray;
    }
    public void addOutlet(Outlet outlet) {
        this.outletArray.add(outlet);
    }


}
