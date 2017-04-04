package com.studentmanik.smartsales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studentmanik.smartsales.BusinessObject.SalesOrder;
import com.studentmanik.smartsales.BusinessObject.SalesOrderLine;

import java.util.List;

/**
 * Created by ritu on 12/15/2016.
 */
public class OrderedSKUListAdapter extends ArrayAdapter<SalesOrderLine> {
    SalesOrder salesOrder;
    public OrderedSKUListAdapter(Context context, List<SalesOrderLine> objects, SalesOrder salesOrder) {
        super(context, 0, objects);
        this.salesOrder=salesOrder;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_sku_list_item,parent,false);
        }
        final SalesOrderLine salesOrderLine = getItem(position);
        TextView txtSKUCount =(TextView)convertView.findViewById(R.id.txtSKUCount);
        TextView txtQty =(TextView)convertView.findViewById(R.id.txtQty);
        TextView txtSKUName =(TextView)convertView.findViewById(R.id.txtSKUName);
        TextView txtTotalPrice =(TextView)convertView.findViewById(R.id.txtTotalPrice);
        ImageView ivDrop =(ImageView)convertView.findViewById(R.id.ivDrop);

        ivDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesOrder.getSalesOrderLineList().getSalesOrderLineArray().remove(position);
                notifyDataSetChanged();
            }
        });
        txtSKUCount.setText(position+1+"");
        txtTotalPrice.setText(salesOrderLine.getSku().getPrice()*salesOrderLine.getQuantityOrder()+"");
        txtSKUName.setText(salesOrderLine.getSku().getName());
        txtSKUName.setTag(salesOrderLine);
        txtQty.setText(salesOrderLine.getQuantityOrder()+"");
        return convertView;
    }

}
