package com.indies.kayoner.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.indies.kayoner.dataprovider.KayonerService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class BasicPostAsyncTask extends AsyncTask<NameValuePair, Void, String>{
	
	String url;
	public OnBasicPostAsyncResult listener;
	Context context;
	SharedPreferences pref;
	Editor editor;
	String sessionId;
	ProgressDialog prd;
	
	public BasicPostAsyncTask(String urls, OnBasicPostAsyncResult listener, Context context) 
	{
		this.url = urls;
		this.listener = listener;
		this.context = context;
		pref = this.context.getSharedPreferences("KayonerApp", 0);
		pref.getString("sessionId", "");
	}
	
	@Override
	protected void onPreExecute() 
	{
		// TODO Auto-generated method stub
		super.onPreExecute();
		prd = new ProgressDialog(context);
		prd.setTitle("Loading");
		prd.setMessage("Your reply is being posted");
		prd.show();
	}


	@Override
	protected String doInBackground(NameValuePair... arguments) {
		// TODO Auto-generated method stub
		DefaultHttpClient httpclient = new DefaultHttpClient();
		CookieStore cs = httpclient.getCookieStore();
		Log.w("session value", KayonerService.getPersistentCookie(context).getName() + "hello");
		cs.addCookie(KayonerService.getPersistentCookie(context));
		httpclient.setCookieStore(cs);
		Log.e("url", url);
		HttpPost httppost = new HttpPost(url);
		ArrayList<BasicNameValuePair> reqArguments = new ArrayList<BasicNameValuePair>();
		for (int i = 0; i < arguments.length; i++) 
		{
			Log.e(arguments[i].getName(), arguments[i].getValue());
			BasicNameValuePair argument = new BasicNameValuePair(arguments[i].getName(), arguments[i].getValue());
			reqArguments.add(argument);
		}

		try
		{
			httppost.setEntity(new UrlEncodedFormEntity(reqArguments));
			HttpResponse response = httpclient.execute(httppost);
			if (response == null) 
			{
				throw new NullPointerException("Response From URL is null");
			}
			//if (response.getStatusLine().getStatusCode() == 200)
			else 
			{
				Log.e("here","right here");
				HttpEntity httpentity = response.getEntity();
				InputStream input = httpentity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				StringBuilder sb = new StringBuilder();
				String ch = null;
				while ((ch = br.readLine()) != null) 
				{
					sb.append(ch);
				}
				input.close();
				char[] utf8 = null;
				StringBuilder properString = new StringBuilder("");
				utf8 = sb.toString().toCharArray();
				for (int i = 0; i < utf8.length; i++) 
				{
					if ((int) utf8[i] < 65000) 
					{
						properString.append(utf8[i]);
					}
				}
				Log.e("pro", properString.toString());
				return properString.toString();
			}
			//return response.getStatusLine().getStatusCode() + "";
		}
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
			return "error";
		}
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
			return "error";
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			return "error";
		}
		catch (NullPointerException e) 
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	public interface OnBasicPostAsyncResult 
	{
		void OnBasicPostAsynResult(String result);
	}
	
	protected void onPostExecute(String result) 
	{
		try
		{
			if (!result.equals("error")) 
			{
				Log.e("result", result + "hello");
				if (listener != null) 
				{
					listener.OnBasicPostAsynResult(result);
					prd.dismiss();
				}
			}
			else
			{
				prd.dismiss();
				Toast.makeText(context, "Message not submitted", Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e)
			{
				e.printStackTrace();
			
			}
	}


}
