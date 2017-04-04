package com.studentmanik.smartsales;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.studentmanik.smartsales.BusinessObject.Outlet;
import com.studentmanik.smartsales.BusinessObject.SalesOrder;
import com.studentmanik.smartsales.BusinessObject.SalesOrderLine;
import com.studentmanik.smartsales.BusinessObject.SalesOrderLineList;
import com.studentmanik.smartsales.Model.OutletModel;
import com.studentmanik.smartsales.Model.SalesOrderModel;
import com.studentmanik.smartsales.SyncAdapter.SyncUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OutletDetaileActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    Outlet selectedOutlet;
    SalesOrder salesOrder;
    OrderedSKUListAdapter orderedSKUListAdapter;
    TextView txtDistance;
    double lat=0;
    double lon=0;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_detaile);

        setTitle("Outlet Details");
        if (getIntent().getExtras() != null) {
            selectedOutlet = getIntent().getExtras().getParcelable("selectedOutlet");
        }
        salesOrder = new SalesOrder();
        salesOrder.setOutlet(selectedOutlet);
        salesOrder.setLocalSoId(selectedOutlet.getId() + "_" + new SimpleDateFormat("yy-MM-dd-HH:mm:ss").format(new Date()));
        salesOrder.setOrderTimeStamp(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
        salesOrder.setOrderDate(new SimpleDateFormat("yy-MM-dd").format(new Date()));
        salesOrder.setSalesOrderLineList(new SalesOrderLineList());
        orderedSKUListAdapter = new OrderedSKUListAdapter(getApplicationContext(), salesOrder.getSalesOrderLineList().getSalesOrderLineArray(),salesOrder);
        TextView txtOutletName = (TextView) findViewById(R.id.txtOutletName);
        TextView txtOutletAddress = (TextView) findViewById(R.id.txtOutletAddress);
        TextView txtOwnerName = (TextView) findViewById(R.id.txtOwnerName);
        TextView txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtDistance = (TextView) findViewById(R.id.txtDistance);
        ListView lvSKUList = (ListView) findViewById(R.id.lvSKUList);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        lvSKUList.setAdapter(orderedSKUListAdapter);

        txtOutletName.setText(selectedOutlet.getName());
        txtOutletAddress.setText(selectedOutlet.getAddress());
        txtOwnerName.setText(selectedOutlet.getOwnerName());
        txtMobile.setText(selectedOutlet.getMobile());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalesOrderModel salesOrderModel=new SalesOrderModel(getContentResolver());
                OutletModel outletModel=new OutletModel(getContentResolver());
                salesOrderModel.saveSalesOrder(salesOrder);
                if (selectedOutlet.getVerified()==0){
                    selectedOutlet.setLat(lat);
                    selectedOutlet.setLon(lon);
                    outletModel.verifyOutlet(selectedOutlet);
                }
                outletModel.outletVisited(selectedOutlet);

                Toast.makeText(OutletDetaileActivity.this, "Order Saved", Toast.LENGTH_SHORT).show();
                SyncUtils.TriggerRefresh();
                finish();
            }
        });
        if (!isLocationEnabled(getApplicationContext())) {
            showSettingsAlert();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 12:
                if (resultCode == RESULT_OK && data!=null) {
                  SalesOrderLineList salesOrderLineList= data.getParcelableExtra("salesOrderLineList");
                    salesOrder.getSalesOrderLineList().getSalesOrderLineArray().clear();
                    for (SalesOrderLine salesOrderLines:salesOrderLineList.getSalesOrderLineArray()   ) {
                        salesOrder.getSalesOrderLineList().addSalesOrderLine(salesOrderLines);
                        orderedSKUListAdapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_plus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addPlus:
                Intent intent = new Intent(getApplicationContext(), SKUListActivity.class);
                intent.putExtra("SalesOrderLineList",salesOrder.getSalesOrderLineList());
                startActivityForResult(intent,12);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }
    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }

    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    private void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS Settings");
        alertDialog.setMessage("This app wants to turn on your device’s GPS:");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                createLocationRequest();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        return gps_enabled;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lon=location.getLongitude();
        txtDistance.setText(selectedOutlet.genareteDistance(location)+"");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
