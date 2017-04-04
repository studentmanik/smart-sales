package com.studentmanik.smartsales.SyncAdapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;


/**
 * Created by Ritu on 6/23/2016.
 */
public class DataProvider extends ContentProvider {
    DBHandler db;
    static final int SKU = 102;
    static final int OUTLET = 103;
    static final int NEW_OUTLET = 104;
    static final int SALES_ORDER = 105;
    static final int SALES_ORDER_LINE = 106;

    private static final UriMatcher sURIMatcher = buildUriMatcher();
    @Override
    public boolean onCreate() {
        db = new DBHandler(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String groupBy = null;
        String limit = null;
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case SKU:
                queryBuilder.setTables(DataContract.SKUEntry.TABLE_NAME);
                break;
            case OUTLET:
                queryBuilder.setTables(DataContract.OutletEntry.TABLE_NAME);
                break;
            case  NEW_OUTLET:
                queryBuilder.setTables(DataContract.NewOutletEntry.TABLE_NAME);
                break;
            case  SALES_ORDER:
                queryBuilder.setTables(DataContract.SalesOrderEntry.TABLE_NAME);
                break;
            case  SALES_ORDER_LINE:
                queryBuilder.setTables(DataContract.SalesOrderLineEntry.TABLE_NAME);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI");
        }
        Cursor cursor = queryBuilder.query(db.getReadableDatabase(), projection, selection, selectionArgs, groupBy, null, sortOrder, limit);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase writableDb = db.getWritableDatabase();
        long id;
        switch (sURIMatcher.match(uri)) {
            case SKU:
                id = writableDb.insert(DataContract.SKUEntry.TABLE_NAME, null, values);
                break;
            case OUTLET:
                id = writableDb.insert(DataContract.OutletEntry.TABLE_NAME, null, values);
                break;
            case NEW_OUTLET:
                id = writableDb.insert(DataContract.NewOutletEntry.TABLE_NAME, null, values);
                break;
            case SALES_ORDER:
                id = writableDb.insert(DataContract.SalesOrderEntry.TABLE_NAME, null, values);
                break;
            case SALES_ORDER_LINE:
                id = writableDb.insert(DataContract.SalesOrderLineEntry.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(uri.getPath() + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase writableDb = db.getWritableDatabase();
        switch (sURIMatcher.match(uri)) {
            case SKU:
                count = writableDb.delete(DataContract.SKUEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case OUTLET:
                count = writableDb.delete(DataContract.OutletEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case NEW_OUTLET:
                count = writableDb.delete(DataContract.NewOutletEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case SALES_ORDER:
                count = writableDb.delete(DataContract.SalesOrderEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case SALES_ORDER_LINE:
                count = writableDb.delete(DataContract.SalesOrderLineEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase writableDb = db.getWritableDatabase();
        switch (sURIMatcher.match(uri)) {
            case SKU:
                count = writableDb.update(DataContract.SKUEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case OUTLET:
                count = writableDb.update(DataContract.OutletEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case NEW_OUTLET:
                count = writableDb.update(DataContract.NewOutletEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case SALES_ORDER:
                count = writableDb.update(DataContract.SalesOrderEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case SALES_ORDER_LINE:
                count = writableDb.update(DataContract.SalesOrderLineEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataContract.AUTHORITY;
        matcher.addURI(authority, DataContract.PATH_SKU, SKU);
        matcher.addURI(authority, DataContract.PATH_OUTLET, OUTLET);
        matcher.addURI(authority, DataContract.PATH_NEW_OUTLET, NEW_OUTLET);
        matcher.addURI(authority, DataContract.PATH_SALES_ORDER, SALES_ORDER);
        matcher.addURI(authority, DataContract.PATH_SALES_ORDER_LINE, SALES_ORDER_LINE);
        return matcher;
    }
}
