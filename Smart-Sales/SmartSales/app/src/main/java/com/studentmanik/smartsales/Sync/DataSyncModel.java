package com.studentmanik.smartsales.Sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.studentmanik.smartsales.SyncAdapter.DataContract;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ritu on 9/8/2016.
 */
public class DataSyncModel {
    ContentResolver contentResolver;

    public DataSyncModel(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public HashMap<String, String> getUniqueColumn(Uri uri, String uniqueColumnName, String condition) {
        HashMap<String, String> uniqueColumn = new HashMap<>();
        String[] projection = {
                uniqueColumnName
        };
        Cursor cursor = contentResolver.query(uri, projection, condition, null, null);
        if (cursor.moveToFirst()) {
            do {
                uniqueColumn.put(cursor.getString(0), cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return uniqueColumn;
    }

    public ArrayList<ArrayList<NameValuePair>> getUpData(Uri uri, String condition) {
        String[] projection = {"*"};
        Cursor cursor = contentResolver.query(uri, projection, condition, null, null);
        ArrayList<ArrayList<NameValuePair>> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ArrayList<NameValuePair> dataColumn = new ArrayList<NameValuePair>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    dataColumn.add(new BasicNameValuePair(cursor.getColumnName(i), cursor.getString(i)));
                }
                data.add(dataColumn);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public ArrayList<ArrayList<NameValuePair>> getSalesOrder() {
        ArrayList<ArrayList<NameValuePair>> salesOrderList = new ArrayList<>();
        String[] projection = {
                DataContract.SalesOrderEntry.LOCAL_SO_ID,
                DataContract.SalesOrderEntry.ORDER_AMOUNT,
                DataContract.SalesOrderEntry.OUTLET_ID,
                DataContract.SalesOrderEntry.ORDER_TIME,
                DataContract.SalesOrderEntry.ORDER_TIME_STAMP,
                DataContract.SalesOrderEntry.SR_ID,
                DataContract.SalesOrderEntry.DISTANCE,
        };
        Cursor cursor = contentResolver.query(DataContract.SalesOrderEntry.CONTENT_URI, projection, DataContract.SalesOrderEntry.IS_SYNC + "='0'", null, null);
        if (cursor.moveToFirst()) {
            do {
                ArrayList<NameValuePair> aSalesOrder = new ArrayList<NameValuePair>();
                aSalesOrder.add(new BasicNameValuePair(DataContract.SalesOrderEntry.LOCAL_SO_ID, cursor.getString(0)));
                aSalesOrder.add(new BasicNameValuePair(DataContract.SalesOrderEntry.ORDER_AMOUNT, cursor.getString(1)));
                aSalesOrder.add(new BasicNameValuePair(DataContract.SalesOrderEntry.OUTLET_ID, cursor.getString(2)));
                aSalesOrder.add(new BasicNameValuePair(DataContract.SalesOrderEntry.ORDER_TIME, cursor.getString(3)));
                aSalesOrder.add(new BasicNameValuePair(DataContract.SalesOrderEntry.ORDER_TIME_STAMP, cursor.getString(4)));
                aSalesOrder.add(new BasicNameValuePair(DataContract.SalesOrderEntry.SR_ID, cursor.getString(5)));
                aSalesOrder.add(new BasicNameValuePair(DataContract.SalesOrderEntry.DISTANCE, cursor.getString(6)));
                aSalesOrder.add(new BasicNameValuePair("SalesOrderLine", getSalesOrderLines(cursor.getString(0)).toString()));
                salesOrderList.add(aSalesOrder);
            } while (cursor.moveToNext());
        }

        return salesOrderList;
    }

    private JSONArray getSalesOrderLines(String soId) {
        JSONArray dataList = new JSONArray();
        String[] projection = {
                DataContract.SalesOrderLineEntry.SKU_ID,
                DataContract.SalesOrderLineEntry.QUANTITY,
                DataContract.SalesOrderLineEntry.UNIT_PRICE,
                DataContract.SalesOrderLineEntry.TOTAL_PRICE
        };
        Cursor cursor = contentResolver.query(DataContract.SalesOrderLineEntry.CONTENT_URI, projection, DataContract.SalesOrderLineEntry.LOCAL_SO_ID + "='"+soId+"'", null, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String,String> aSalesOrderLine = new HashMap<>();
                aSalesOrderLine.put(DataContract.SalesOrderLineEntry.SKU_ID, cursor.getString(0));
                aSalesOrderLine.put(DataContract.SalesOrderLineEntry.QUANTITY, cursor.getString(1));
                aSalesOrderLine.put(DataContract.SalesOrderLineEntry.UNIT_PRICE, cursor.getString(2));
                aSalesOrderLine.put(DataContract.SalesOrderLineEntry.TOTAL_PRICE, cursor.getString(3));
                JSONObject jsonObject=new JSONObject(aSalesOrderLine);
                dataList.put(jsonObject);
            } while (cursor.moveToNext());
        }

        return dataList;
    }
    public void updateSynced(Uri uri, String dataResult, DataSync dataSync) {
        ContentValues values = new ContentValues();
        values.put(dataSync.getUpdateColumn(), 1);
        contentResolver.update(uri, values, dataSync.getUniqueColumn() + "='" + dataResult + "'", null);
    }
    public void updateSynced(String columnId) {
        Log.e("server_columnId", columnId);
        ContentValues values = new ContentValues();
        values.put(DataContract.SalesOrderEntry.IS_SYNC, 1);
        contentResolver.update(DataContract.SalesOrderEntry.CONTENT_URI, values, DataContract.SalesOrderEntry.LOCAL_SO_ID + "='" + columnId + "'", null);
    }


}
