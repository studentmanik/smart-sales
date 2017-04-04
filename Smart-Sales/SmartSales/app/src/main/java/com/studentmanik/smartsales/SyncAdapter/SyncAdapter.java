package com.studentmanik.smartsales.SyncAdapter;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SyncResult;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.studentmanik.smartsales.R;
import com.studentmanik.smartsales.Sync.DataObject;
import com.studentmanik.smartsales.Sync.DataSync;
import com.studentmanik.smartsales.Sync.DataSyncModel;
import com.studentmanik.smartsales.Sync.JsonParser;
import com.studentmanik.smartsales.Sync.NetworkStream;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    int srId = 0;
    private final ContentResolver mContentResolver;
    private String application_url;
    SharedPreferences sharedpreferences;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        this.mContentResolver = context.getContentResolver();
        application_url= getContext().getString(R.string.web_app);
        sharedpreferences= context.getSharedPreferences("userSession", Context.MODE_PRIVATE);
        srId = sharedpreferences.getInt("srId", 0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        this.mContentResolver = context.getContentResolver();
        sharedpreferences= context.getSharedPreferences("userSession", Context.MODE_PRIVATE);
        srId = sharedpreferences.getInt("srId", 0);
    }

    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.e("SyncAdapter", "Beginning network synchronization");
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("sr_id", srId+""));
        ArrayList<DataSync> dataSyncs = dataUpServices();
        DataSyncModel dataSyncModel = new DataSyncModel(mContentResolver);
        for (DataSync dataSync : dataSyncs) {
            Uri uri = DataContract.getURI(dataSync.getTableName());
            Log.e("RequestURI", uri.toString());
            String url = application_url + dataSync.getServiceUrl();
            Log.e("RequestURL", url);
            String condition = dataSync.getUpdateColumn() + "='0'";
            for (ArrayList<NameValuePair> nameValuePair : dataSyncModel.getUpData(uri, condition)) {
                int httpMethod = 1;
                httpMethod = Integer.parseInt(dataSync.getHttpMethod());
                Log.e("RequestData", nameValuePair.toString());
                String dataPut = new NetworkStream().getStream(url, httpMethod, nameValuePair);
                String columnId = JsonParser.ifValidJSONGetStatus(dataPut);
                Log.e("RequestResult", dataPut);
                if (columnId != null) {
                    dataSyncModel.updateSynced(uri, columnId, dataSync);
                }
            }
        }


        ArrayList<DataSync> downServices2 = syncMasterServices();
        for (DataSync dataRule : downServices2) {
            Log.e("RequestUrl", dataRule.getServiceUrl());
            String resultData = new NetworkStream().getStream(application_url + dataRule.getServiceUrl(), Integer.parseInt(dataRule.getHttpMethod()), params1);
            Log.e("RequestedData", resultData);
            ArrayList<String> insertTableList = JsonParser.ifValidJSONGetTable(resultData);
            if (insertTableList != null) {
                for (String insertTable : insertTableList) {
                    Log.e("insertTable", insertTable);
                    Uri uri = DataContract.getURI(insertTable);
                    Log.e("RequestedURI", uri.toString());
                    HashMap<String, String> tableData = new DataSyncModel(mContentResolver).getUniqueColumn(uri, "token", null);
                    HashMap<String, DataObject> resultContentValuesList = JsonParser.getTokenAndValues(resultData, insertTable);
                    for (String key : tableData.keySet()) {
                        if (!resultContentValuesList.containsKey(key)) {
                            mContentResolver.delete(uri, "token=" + key, null);
                        }
                    }
                    for (String key : resultContentValuesList.keySet()) {
                        if (!tableData.containsKey(key)) {
                            DataObject dataObject = resultContentValuesList.get(key);
                            mContentResolver.delete(uri, "column_id=" + dataObject.getColumnId(), null);
                            Uri insertUri = mContentResolver.insert(uri, dataObject.getContentValues());
                            Log.d(insertTable, insertUri.getPath());
                        } else {
                            Log.e(insertTable + key, ":Already Exist");
                        }
                    }
                }
            }
        }
        DataSyncModel syncModel = new DataSyncModel(mContentResolver);
        String urlData = application_url + "mobile_api/sync_sales_order.php";
        Log.e("Order up Url", urlData);
        for (ArrayList<NameValuePair> valus : syncModel.getSalesOrder()) {
            Log.e("data_up", valus.toString());
            String dataPut = new NetworkStream().getStream(urlData, 2, valus);
            Log.e("data_put", dataPut);
            String dataResult = JsonParser.ifValidJSONGetStatus(dataPut);
            if (dataResult != null) {
                syncModel.updateSynced(dataResult);
            }

        }
        Log.e("SyncAdapter", "Network synchronization complete");
    }

    public ArrayList<DataSync> syncMasterServices() {
        ArrayList<DataSync> dataService = new ArrayList();
        AssetManager assetManager = getContext().getAssets();
        try {
            InputStream e = assetManager.open("sync_master_data.xml");
            InputSource inStream = new InputSource(e);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inStream);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("services");
            for (int temp = 0; temp < nList.getLength(); ++temp) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == 1) {
                    Element eElement = (Element) nNode;
                    String url = eElement.getAttribute("url").toString();
                    String httpMethod = eElement.getAttribute("http_method").toString();
                    DataSync dataSync = new DataSync(url, httpMethod);
                    dataService.add(dataSync);
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }
        return dataService;
    }

    public ArrayList<DataSync> dataUpServices() {
        ArrayList<DataSync> dataService = new ArrayList();
        AssetManager assetManager = getContext().getAssets();
        try {
            InputStream e = assetManager.open("sync_data_up.xml");
            InputSource inStream = new InputSource(e);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inStream);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("services");
            for (int temp = 0; temp < nList.getLength(); ++temp) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == 1) {
                    Element eElement = (Element) nNode;
                    String table_name = eElement.getAttribute("table_name").toString();
                    String update_column = eElement.getAttribute("update_column").toString();
                    String url = eElement.getAttribute("url").toString();
                    String unique_column = eElement.getAttribute("unique_column").toString();
                    String http_method = eElement.getAttribute("http_method").toString();
                    DataSync dataSync = new DataSync(url, http_method, table_name, unique_column, update_column);
                    dataService.add(dataSync);
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }
        return dataService;
    }
}
