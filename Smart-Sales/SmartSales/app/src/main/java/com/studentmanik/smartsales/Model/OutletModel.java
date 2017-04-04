package com.studentmanik.smartsales.Model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.studentmanik.smartsales.BusinessObject.Outlet;
import com.studentmanik.smartsales.BusinessObject.OutletList;
import com.studentmanik.smartsales.SyncAdapter.DataContract;

/**
 * Created by Ritu on 12/10/2016.
 */
public class OutletModel {
    ContentResolver contentResolver;
    OutletList outletList = new OutletList();
    public OutletModel(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public OutletList getOutletList() {
        outletList.getOutletArray().clear();
        String[] projecion = {"DISTINCT " + DataContract.OutletEntry.OUTLET_ID,
                DataContract.OutletEntry.NAME,
                DataContract.OutletEntry.ADDRESS,
                DataContract.OutletEntry.OWNER,
                DataContract.OutletEntry.MOBILE,
                DataContract.OutletEntry.SR_ID,
                DataContract.OutletEntry.LAT,
                DataContract.OutletEntry.LON,
                DataContract.OutletEntry.VERIFIED,
                DataContract.OutletEntry.IS_VISIT,
        };
        Cursor cursor = contentResolver.query(DataContract.OutletEntry.CONTENT_URI, projecion, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Outlet outlet = new Outlet();
                outlet.setId(cursor.getInt(0));
                outlet.setName(cursor.getString(1));
                outlet.setAddress(cursor.getString(2));
                outlet.setOwnerName(cursor.getString(3));
                outlet.setMobile(cursor.getString(4));
                outlet.setSrId(cursor.getInt(5));
                outlet.setLat(cursor.getDouble(6));
                outlet.setLon(cursor.getDouble(7));
                outlet.setVerified(cursor.getInt(8));
                outlet.setVisited(cursor.getInt(9));
                outletList.addOutlet(outlet);
            } while (cursor.moveToNext());
        }
        return outletList;
    }

    public void saveOutlet(Outlet outlet) {
        ContentValues values = new ContentValues();
        values.put(DataContract.NewOutletEntry.NAME, outlet.getName());
        values.put(DataContract.NewOutletEntry.ADDRESS, outlet.getAddress());
        values.put(DataContract.NewOutletEntry.OWNER, outlet.getOwnerName());
        values.put(DataContract.NewOutletEntry.MOBILE, outlet.getMobile());
        values.put(DataContract.NewOutletEntry.SR_ID, outlet.getSrId());
        values.put(DataContract.NewOutletEntry.LAT, outlet.getLat());
        values.put(DataContract.NewOutletEntry.LON, outlet.getLon());
        values.put(DataContract.NewOutletEntry.IS_SYNC, 0);
        contentResolver.insert(DataContract.NewOutletEntry.CONTENT_URI, values);
    }


    public void verifyOutlet(Outlet outlet) {
        ContentValues values = new ContentValues();
        values.put(DataContract.OutletEntry.LAT, outlet.getLat());
        values.put(DataContract.OutletEntry.LON, outlet.getLon());
        values.put(DataContract.OutletEntry.IS_SYNC, 0);
        contentResolver.update(DataContract.OutletEntry.CONTENT_URI, values, DataContract.OutletEntry.OUTLET_ID + "=" + outlet.getId(), null);
    }

    public void outletVisited(Outlet outlet) {
        ContentValues values = new ContentValues();
        values.put(DataContract.OutletEntry.IS_VISIT, 1);
        contentResolver.update(DataContract.OutletEntry.CONTENT_URI, values, DataContract.OutletEntry.OUTLET_ID + "=" + outlet.getId(), null);

    }
}
