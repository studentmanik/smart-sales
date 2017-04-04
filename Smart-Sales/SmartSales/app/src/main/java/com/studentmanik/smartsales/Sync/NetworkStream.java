package com.studentmanik.smartsales.Sync;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Ritu on 8/11/2016.
 */
public class NetworkStream {
    public NetworkStream() {
    }

    public String getStream(String url, int method, List<NameValuePair> params) {
        String response = "";
        try {
            DefaultHttpClient e = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
            if (method == 2) {
                HttpPost httpGet = new HttpPost(url);
                if (params != null) {
                    httpGet.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = e.execute(httpGet);
            } else if (method == 1) {
                if (params != null) {
                    String httpGet1 = URLEncodedUtils.format(params, "utf-8");
                    url = url + "?" + httpGet1;
                }

                HttpGet httpGet2 = new HttpGet(url);
                httpResponse = e.execute(httpGet2);
            } else if (method == 3) {
                HttpPut httpPut = new HttpPut(url);
                if (params != null) {
                    httpPut.setEntity(new UrlEncodedFormEntity(params));
                }
                httpResponse = e.execute(httpPut);
            }
            if (httpResponse != null) {
                httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
            }
        } catch (UnsupportedEncodingException var8) {
            // var8.printStackTrace();
        } catch (ClientProtocolException var9) {
            // var9.printStackTrace();
        } catch (IOException var10) {
            //  var10.printStackTrace();
        }
        return response;
    }



}
