package com.studentmanik.smartsales;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studentmanik.smartsales.BusinessObject.Outlet;

import java.util.List;

/**
 * Created by Ritu on 12/13/2016.
 */
public class OutletListAdapter extends ArrayAdapter<Outlet> {
    public OutletListAdapter(Context context, List<Outlet> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.outlet_list_item, parent, false);
        }

        Outlet selectedOutlet = getItem(position);
        TextView txtOutletName = (TextView) convertView.findViewById(R.id.txtOutletName);
        TextView txtOutletAddress = (TextView) convertView.findViewById(R.id.txtOutletAddress);
        TextView txtOutletCount = (TextView) convertView.findViewById(R.id.txtOutletCount);
        LinearLayout lvbg = (LinearLayout) convertView.findViewById(R.id.lvbg);
        txtOutletName.setText(selectedOutlet.getName());
        txtOutletAddress.setText(selectedOutlet.getAddress());
        if (selectedOutlet.getVisited()==1){
            lvbg.setBackgroundColor(Color.GREEN);
        }else {
            lvbg.setBackgroundColor( Color.parseColor("#FFFFFF"));

        }
        txtOutletCount.setText(position + 1 + "");
        txtOutletName.setTag(selectedOutlet);
        return convertView;
    }


}
