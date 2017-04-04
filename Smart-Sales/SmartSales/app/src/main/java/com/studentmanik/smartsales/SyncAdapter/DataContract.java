package com.studentmanik.smartsales.SyncAdapter;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ritu on 6/23/2016.
 */
public class DataContract {
    public static final String AUTHORITY = "com.studentmanik.smartsales.SyncAdapter";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_SKU = "tbl_sku";
    public static final String PATH_OUTLET = "tbl_outlet";
    public static final String PATH_NEW_OUTLET = "tbl_new_outlet";
    public static final String PATH_SALES_ORDER = "tbl_sales_order";
    public static final String PATH_SALES_ORDER_LINE = "tbl_sales_order_line";


    public static Uri getURI(String tableName) {
        return BASE_CONTENT_URI.buildUpon().appendPath(tableName).build();
    }


    public static class SKUEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SKU).build();
        public static final String TABLE_NAME = "tbl_sku";
        public static final String COLUMN_ID = "column_id";
        public static final String SKU_ID = "sku_id";
        public static final String SKU_NAME = "sku_name";
        public static final String PRICE = "price";
        public static final String TOKEN = "token";

    }
    public static class OutletEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_OUTLET).build();
        public static final String TABLE_NAME = "tbl_outlet";
        public static final String COLUMN_ID = "column_id";
        public static final String OUTLET_ID = "outlet_id";
        public static final String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String OWNER = "owner";
        public static final String MOBILE = "mobile";
        public static final String SR_ID = "sr_id";
        public static final String TOKEN = "token";
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String VERIFIED = "verified";
        public static final String IS_VISIT= "is_visit";
        public static final String IS_SYNC= "is_sync";
    }
    public static class NewOutletEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NEW_OUTLET).build();
        public static final String TABLE_NAME = "tbl_new_outlet";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String OWNER = "owner";
        public static final String MOBILE = "mobile";
        public static final String SR_ID = "sr_id";
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String IS_SYNC= "is_sync";
    }
    public static class SalesOrderEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SALES_ORDER).build();
        public static final String TABLE_NAME = "tbl_sales_order";
        public static final String ID = "id";
        public static final String ORDER_TIME = "order_time";
        public static final String ORDER_TIME_STAMP = "order_time_stamp";
        public static final String LOCAL_SO_ID = "local_so_id";
        public static final String OUTLET_ID = "outlet_id";
        public static final String SR_ID = "sr_id";
        public static final String DISTANCE = "distance";
        public static final String ORDER_AMOUNT ="order_amount";
        public static final String IS_SYNC= "is_sync";
    }
    public static class SalesOrderLineEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SALES_ORDER_LINE).build();
        public static final String TABLE_NAME = "tbl_sales_order_line";
        public static final String LOCAL_SO_ID = "local_so_id";
        public static final String ID = "id";
        public static final String SKU_ID = "sku_id";
        public static final String QUANTITY = "quantity";
        public static final String UNIT_PRICE = "unit_price";
        public static final String TOTAL_PRICE = "total_price";

    }


}
