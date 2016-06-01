package com.example.volleydemo;

import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;

public class VolleyRequest {

	public static StringRequest stringRequest;
	public static Context context;
	
	public static void requestGet(Context context,String url,String tag,VolleyInterface vif)
	{
		MyApplication.getHttpQueues().cancelAll(tag);
		stringRequest = new StringRequest(Method.GET,url, vif.loadingListener(), vif.errorListener());
		stringRequest.setTag(tag);
		MyApplication.getHttpQueues().add(stringRequest);
		MyApplication.getHttpQueues().start();
	}
	
	public static void requestPost(Context Context,String url,String tag,final Map<String,String> params,VolleyInterface vif)
	{
		MyApplication.getHttpQueues().cancelAll(tag);
		stringRequest = new StringRequest(Method.POST, url, vif.loadingListener(), vif.errorListener())
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				return params;
			}
		};
		stringRequest.setTag(tag);
		MyApplication.getHttpQueues().add(stringRequest);
		MyApplication.getHttpQueues().start();
	}
	
}
