package com.example.jsontutorial.AsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by RushitKapadia on 13-Jan-15.
 */
public class BasicAsyncTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private OnAsyncResult listener;
    private String url;
    private ProgressDialog prd;

    public BasicAsyncTask(Context context, OnAsyncResult listener, String url){
        this.context = context;
        this.listener = listener;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        prd = new ProgressDialog(context);
        prd.setMessage("Please wait while fetching data");
        prd.setTitle("Loading");
        prd.show();
    }

    @Override
    protected void onPostExecute(String result) {
        if(!result.equals("error")){
            if(listener != null){
                listener.onAsynResult(result);
                prd.dismiss();
            }
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        Log.e("url", url + "");
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
