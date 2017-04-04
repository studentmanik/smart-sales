package com.studentmanik.smartsales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studentmanik.smartsales.BusinessObject.Outlet;
import com.studentmanik.smartsales.Model.OutletModel;

public class OutletListActivity extends AppCompatActivity {
    OutletModel outletModel;
    OutletListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_list);
        setTitle("Outlet List");
        outletModel =new OutletModel(getContentResolver());
        adapter=new OutletListAdapter(getApplicationContext(),outletModel.getOutletList().getOutletArray());
        ListView lvOutletList = (ListView) findViewById(R.id.lvOutletList);
        lvOutletList.setAdapter(adapter);
        lvOutletList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Outlet selectedOutlet =(Outlet) view.findViewById(R.id.txtOutletName).getTag();
                Intent intent=new Intent(getApplicationContext(),OutletDetaileActivity.class);
                intent.putExtra("selectedOutlet",selectedOutlet);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        outletModel.getOutletList();
        adapter.notifyDataSetChanged();
    }
}
