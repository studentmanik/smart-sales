package com.studentmanik.smartsales;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.studentmanik.smartsales.BusinessObject.SKU;
import com.studentmanik.smartsales.BusinessObject.SalesOrderLine;
import com.studentmanik.smartsales.BusinessObject.SalesOrderLineList;
import com.studentmanik.smartsales.Model.SKUModel;

public class SKUListActivity extends AppCompatActivity {
    SalesOrderLineList salesOrderLineList;
    SKUListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skulist);
        setTitle("SKU List");
        SKUModel skuModel = new SKUModel(getContentResolver());

        if (getIntent().getExtras() != null) {
            salesOrderLineList = getIntent().getExtras().getParcelable("SalesOrderLineList");
        }
        adapter = new SKUListAdapter(getApplicationContext(), skuModel.getSKUList().getSkuArray(), salesOrderLineList);
        ListView lvSKUList = (ListView) findViewById(R.id.lvSKUList);
        lvSKUList.setAdapter(adapter);

        lvSKUList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final SKU selectedSKU = (SKU) view.findViewById(R.id.txtSKUName).getTag();
                LayoutInflater li = LayoutInflater.from(SKUListActivity.this);
                View promptsView = li.inflate(R.layout.input_box, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        SKUListActivity.this);
                alertDialogBuilder.setView(promptsView);
                final EditText etInputQuentity = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (!etInputQuentity.getText().toString().isEmpty()) {
                                            selectedSKU.setInCart(1);
                                            int qty = Integer.parseInt(etInputQuentity.getText().toString());
                                            SalesOrderLine salesOrderLine = new SalesOrderLine();
                                            salesOrderLine.setSku(selectedSKU);
                                            salesOrderLine.setTotalSalesPrice(selectedSKU.getPrice() * qty);
                                            salesOrderLine.setQuantityOrder(qty);
                                            salesOrderLineList.addSalesOrderLine(salesOrderLine);
                                            adapter.notifyDataSetChanged();
                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addBack:
                Bundle conData = new Bundle();
                Intent intent = new Intent();
                //if (salesOrderLineList.getSalesOrderLineArray().size() > 0) {
                    conData.putParcelable("salesOrderLineList", salesOrderLineList);
                    intent.putExtras(conData);
                    setResult(RESULT_OK, intent);
              /*  } else {
                    setResult(RESULT_CANCELED, intent);
                }*/
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
