package com.studentmanik.smartsales.Sync;

import android.content.ContentValues;

/**
 * Created by Ritu on 9/22/2016.
 */
public class DataObject {
   private String columnId;
   private ContentValues contentValues;

    public DataObject(String columnId, ContentValues contentValues) {
        this.columnId = columnId;
        this.contentValues = contentValues;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public ContentValues getContentValues() {
        return contentValues;
    }

    public void setContentValues(ContentValues contentValues) {
        this.contentValues = contentValues;
    }
}
