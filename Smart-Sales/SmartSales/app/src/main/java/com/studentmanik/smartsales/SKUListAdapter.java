package com.studentmanik.smartsales;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studentmanik.smartsales.BusinessObject.SKU;
import com.studentmanik.smartsales.BusinessObject.SalesOrderLineList;

import java.util.List;

/**
 * Created by ritu on 12/15/2016.
 */
public class SKUListAdapter extends ArrayAdapter<SKU> {
    SalesOrderLineList salesOrderLineList1;
    public SKUListAdapter(Context context, List<SKU> objects, SalesOrderLineList salesOrderLineList1) {
        super(context, 0, objects);
        this.salesOrderLineList1=salesOrderLineList1;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sku_list_item,parent,false);
        }
        SKU selectedSKU = getItem(position);
        TextView txtSKUCount =(TextView)convertView.findViewById(R.id.txtSKUCount);
        TextView txtSKUName =(TextView)convertView.findViewById(R.id.txtSKUName);
        TextView txtSKUPrice =(TextView)convertView.findViewById(R.id.txtSKUPrice);
        LinearLayout lvbg = (LinearLayout) convertView.findViewById(R.id.lvbg);
        if (salesOrderLineList1.isInCart(selectedSKU)){
            lvbg.setBackgroundColor(Color.GREEN);
        }else {
            lvbg.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        txtSKUCount.setText(position+1+"");
        txtSKUPrice.setText(selectedSKU.getPrice()+"");
        txtSKUName.setText(selectedSKU.getName());
        txtSKUName.setTag(selectedSKU);
        return convertView;
    }

}
