package com.studentmanik.smartsales.Sync;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Ritu on 8/11/2016.
 */
public class JsonParser {
    public static ArrayList<String> ifValidJSONGetTable(String dataJson) {
        ArrayList<String> tableName = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(dataJson);
            if (!data.has("status")) {
                for (int i = 0; i < data.length(); i++) {
                    tableName.add(data.names().getString(i));
                }
            } else {
                Log.e("RequestedResult", dataJson);
            }
        } catch (JSONException ex) {
            Log.e("not Vailid JSON", dataJson);
            tableName = null;
        }
        return tableName;
    }
    public static String ifValidJSONGetImageName(String dataJson) {
        String imageName =null;
        try {
            JSONObject data = new JSONObject(dataJson);
            imageName = data.getString("image_name");
        } catch (JSONException ex) {
            Log.e("not Vailid JSON", dataJson);
            imageName = null;
        }
        return imageName;
    }

    public static String ifValidJSONGetStatus(String dataJson) {
        String columnId = null;
        try {
            JSONObject data = new JSONObject(dataJson);
            columnId = data.getString("status");
        } catch (JSONException ex) {
            Log.e("not Vailid JSON", dataJson);
            columnId = null;
        }
        return columnId;
    }



    public static HashMap<String, ContentValues> getColIdAndValues(String dataString, String tableName) {
        HashMap<String, ContentValues> valueList = new HashMap<>();
        if (dataString != null) {
            try {
                JSONObject data = new JSONObject(dataString);
                String action_name = data.getJSONObject(tableName).getString("action");
                Log.e("Json Obtain Actio : ", action_name);
                JSONArray dataArray = data.getJSONObject(tableName).getJSONArray("data");
                Log.v("Data Row Length : ", " : " + dataArray.length());
                for (int i = 0; i < dataArray.length(); ++i) {
                    JSONObject dataRows = dataArray.getJSONObject(i);
                    Iterator iterator = dataRows.keys();
                    String column_id = dataArray.getJSONObject(i).getString("column_id");

                    ContentValues mNewValues = new ContentValues();
                    for (int k = 0; iterator.hasNext(); k++) {
                        String single_column_name = (String) iterator.next();
                        String column_val = dataRows.getString(single_column_name).toString();
                        mNewValues.put(single_column_name, column_val);
                    }
                    valueList.put(column_id , mNewValues);
                }

            } catch (JSONException var21) {
                var21.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn\'t get any data from the url");
        }
        return valueList;
    }

    public static HashMap<String, DataObject> getTokenAndValues(String dataString, String tableName) {
        HashMap<String, DataObject> valueList = new HashMap<>();
        if (dataString != null) {
            try {
                JSONObject data = new JSONObject(dataString);
                JSONArray dataArray = data.getJSONObject(tableName).getJSONArray("data");
                Log.v("Data Row Length : ", " : " + dataArray.length());
                for (int i = 0; i < dataArray.length(); ++i) {
                    JSONObject dataRows = dataArray.getJSONObject(i);
                    Iterator iterator = dataRows.keys();
                    String column_id = dataArray.getJSONObject(i).getString("column_id");
                    String token = dataArray.getJSONObject(i).getString("token");
                    ContentValues mNewValues = new ContentValues();
                    for (int k = 0; iterator.hasNext(); k++) {
                        String single_column_name = (String) iterator.next();
                        String column_val = dataRows.getString(single_column_name).toString();
                        mNewValues.put(single_column_name, column_val);
                    }
                    DataObject dataObject=new DataObject(column_id,mNewValues);
                    valueList.put(token, dataObject);
                }

            } catch (JSONException var21) {
                var21.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn\'t get any data from the url");
        }
        return valueList;
    }

    public static String ifValidJSONGetfileName(String updateData) {

        String apkversion = "null";
        try {
            JSONObject data = new JSONObject(updateData);
            apkversion = data.getString("fileName");
        } catch (JSONException ex) {
            Log.e("not Vailid JSON", updateData);
            apkversion = "null";
        }
        return apkversion;
    }
}
