package com.studentmanik.smartsales.Model;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.studentmanik.smartsales.BusinessObject.SalesOrder;
import com.studentmanik.smartsales.BusinessObject.SalesOrderLine;
import com.studentmanik.smartsales.SyncAdapter.DataContract;

/**
 * Created by Manik on 1/10/2017.
 */
public class SalesOrderModel {
    ContentResolver contentResolver;
    public SalesOrderModel(ContentResolver contentResolver) {
        this.contentResolver=contentResolver;
    }
    public void saveSalesOrder(SalesOrder salesOrder){
        ContentValues values = new ContentValues();
        values.put(DataContract.SalesOrderEntry.LOCAL_SO_ID, salesOrder.getLocalSoId());
        values.put(DataContract.SalesOrderEntry.ORDER_TIME, salesOrder.getOrderDate());
        values.put(DataContract.SalesOrderEntry.ORDER_TIME_STAMP, salesOrder.getOrderTimeStamp());
        values.put(DataContract.SalesOrderEntry.OUTLET_ID, salesOrder.getOutlet().getId());
        values.put(DataContract.SalesOrderEntry.SR_ID, salesOrder.getOutlet().getSrId());
        values.put(DataContract.SalesOrderEntry.ORDER_AMOUNT, salesOrder.getSalesOrderLineList().totalOrderAmount());
        values.put(DataContract.SalesOrderEntry.DISTANCE, salesOrder.getOutlet().getDistance());
        values.put(DataContract.SalesOrderEntry.IS_SYNC, 0);
        contentResolver.insert(DataContract.SalesOrderEntry.CONTENT_URI, values);
        saveSalesOrderLine(salesOrder);
    }
    private void saveSalesOrderLine(SalesOrder salesOrder){
        for (SalesOrderLine salesOrderLine:salesOrder.getSalesOrderLineList().getSalesOrderLineArray() ) {
            ContentValues values = new ContentValues();
            values.put(DataContract.SalesOrderLineEntry.LOCAL_SO_ID, salesOrder.getLocalSoId());
            values.put(DataContract.SalesOrderLineEntry.QUANTITY, salesOrderLine.getQuantityOrder());
            values.put(DataContract.SalesOrderLineEntry.UNIT_PRICE, salesOrderLine.getSku().getPrice());
            values.put(DataContract.SalesOrderLineEntry.SKU_ID, salesOrderLine.getSku().getId());
            values.put(DataContract.SalesOrderLineEntry.TOTAL_PRICE, salesOrderLine.getTotalSalesPrice());
            contentResolver.insert(DataContract.SalesOrderLineEntry.CONTENT_URI, values);


        }

    }

}
