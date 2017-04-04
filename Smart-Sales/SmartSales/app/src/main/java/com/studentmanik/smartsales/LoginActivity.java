package com.studentmanik.smartsales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.studentmanik.smartsales.SyncAdapter.SyncUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText etUserName,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                String url = getString(R.string.web_app)+"/mobile_api/login.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("login_response",response);
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject user = jsonArray.getJSONObject(0);
                                    int userId = user.getInt("user_id");
                                    String fullName = user.getString("full_name");
                                    Toast.makeText(LoginActivity.this, "WellCome "+fullName, Toast.LENGTH_SHORT).show();

                                    SharedPreferences sharedpreferences = getSharedPreferences("userSession", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putInt("srId", userId);
                                    editor.commit();

                                    SyncUtils.CreateSyncAccount(LoginActivity.this);

                                } catch (JSONException e) {
                                    // e.printStackTrace();
                                }
                                if (!response.equalsIgnoreCase("[]")){
                                    Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(LoginActivity.this, "User Name or Password Wrong", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("login_error",error.toString());
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("user_name",etUserName.getText().toString().trim());
                        params.put("password",etPassword.getText().toString().trim());
                        return params;
                    }

                };
                queue.add(stringRequest);


            }

        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
