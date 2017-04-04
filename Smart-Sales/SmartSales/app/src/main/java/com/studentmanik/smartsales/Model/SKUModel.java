package com.studentmanik.smartsales.Model;

import android.content.ContentResolver;
import android.database.Cursor;

import com.studentmanik.smartsales.BusinessObject.SKU;
import com.studentmanik.smartsales.BusinessObject.SKUList;
import com.studentmanik.smartsales.SyncAdapter.DataContract;

/**
 * Created by ritu on 12/15/2016.
 */
public class SKUModel {
    ContentResolver contentResolver;
    public SKUModel(ContentResolver contentResolver) {
        this.contentResolver=contentResolver;
    }
    public SKUList getSKUList() {
        SKUList skuList = new SKUList();

        String [] projecion={"DISTINCT "+ DataContract.SKUEntry.SKU_ID,
                DataContract.SKUEntry.SKU_NAME,
                DataContract.SKUEntry.PRICE,

        };
        Cursor cursor= contentResolver.query(DataContract.SKUEntry.CONTENT_URI,projecion,null,null,null);
        if (cursor.moveToFirst()) {
            do{
                SKU sku = new SKU();
                sku.setId(cursor.getInt(0));
                sku.setName(cursor.getString(1));
                sku.setPrice(cursor.getDouble(2));
                skuList.addSku(sku);
            } while (cursor.moveToNext());
        }

               return skuList;
    }
}
