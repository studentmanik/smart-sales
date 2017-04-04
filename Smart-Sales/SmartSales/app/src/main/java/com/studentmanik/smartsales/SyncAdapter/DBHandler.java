package com.studentmanik.smartsales.SyncAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ritu on 12/23/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "smart_sales.db";
    private static  int DATABASE_VERSION = 4;
    Context context;
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SKU_TABLE = "CREATE TABLE " +
                DataContract.SKUEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.SKUEntry.COLUMN_ID  + " INTEGER," +
                DataContract.SKUEntry.SKU_ID  + " INTEGER," +
                DataContract.SKUEntry.SKU_NAME + " TEXT," +
                DataContract.SKUEntry.PRICE + " TEXT," +
                DataContract.SKUEntry.TOKEN + " TEXT )";
        String CREATE_OUTLET_TABLE = "CREATE TABLE " +
                DataContract.OutletEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.OutletEntry.COLUMN_ID  + " INTEGER," +
                DataContract.OutletEntry.OUTLET_ID  + " INTEGER," +
                DataContract.OutletEntry.NAME + " TEXT," +
                DataContract.OutletEntry.ADDRESS + " TEXT," +
                DataContract.OutletEntry.OWNER + " TEXT," +
                DataContract.OutletEntry.MOBILE + " TEXT," +
                DataContract.OutletEntry.LAT + " TEXT," +
                DataContract.OutletEntry.LON + " TEXT," +
                DataContract.OutletEntry.VERIFIED + " TEXT," +
                DataContract.OutletEntry.IS_SYNC + " INTEGER," +
                DataContract.OutletEntry.IS_VISIT + " TEXT," +
                DataContract.OutletEntry.SR_ID + " INTEGER," +
                DataContract.OutletEntry.TOKEN + " TEXT )";
        String CREATE_NEW_OUTLET_TABLE = "CREATE TABLE " +
                DataContract.NewOutletEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.NewOutletEntry.NAME + " TEXT," +
                DataContract.NewOutletEntry.ADDRESS + " TEXT," +
                DataContract.NewOutletEntry.OWNER + " TEXT," +
                DataContract.NewOutletEntry.MOBILE + " TEXT," +
                DataContract.NewOutletEntry.LAT + " TEXT," +
                DataContract.NewOutletEntry.LON + " TEXT," +
                DataContract.NewOutletEntry.SR_ID + " INTEGER," +
                DataContract.NewOutletEntry.IS_SYNC + " INTEGER  )";
        String CREATE_SALES_ORDER = "CREATE TABLE " +
                DataContract.SalesOrderEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.SalesOrderEntry.LOCAL_SO_ID + " TEXT," +
                DataContract.SalesOrderEntry.ORDER_TIME + " TEXT," +
                DataContract.SalesOrderEntry.ORDER_TIME_STAMP + " TEXT," +
                DataContract.SalesOrderEntry.OUTLET_ID + " INTEGER," +
                DataContract.SalesOrderEntry.DISTANCE + " TEXT," +
                DataContract.SalesOrderEntry.SR_ID + " INTEGER," +
                DataContract.SalesOrderEntry.ORDER_AMOUNT + " TEXT," +
                DataContract.SalesOrderEntry.IS_SYNC + " INTEGER  )";
        String CREATE_SALES_ORDER_LINE = "CREATE TABLE " +
                DataContract.SalesOrderLineEntry.TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DataContract.SalesOrderLineEntry.LOCAL_SO_ID + " TEXT," +
                DataContract.SalesOrderLineEntry.SKU_ID + " INTEGER," +
                DataContract.SalesOrderLineEntry.QUANTITY + " INTEGER," +
                DataContract.SalesOrderLineEntry.UNIT_PRICE + " TEXT," +
                DataContract.SalesOrderLineEntry.TOTAL_PRICE + " TEXT  )";

        db.execSQL(CREATE_SKU_TABLE);
        db.execSQL(CREATE_OUTLET_TABLE);
        db.execSQL(CREATE_NEW_OUTLET_TABLE);
        db.execSQL(CREATE_SALES_ORDER);
        db.execSQL(CREATE_SALES_ORDER_LINE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.SKUEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.OutletEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.NewOutletEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.SalesOrderEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.SalesOrderLineEntry.TABLE_NAME);
        onCreate(db);
    }



}
