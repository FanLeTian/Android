package com.example.volleydemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //volley_Get();
        volley_Post();
    }

    private void volley_Post() {
		// TODO Auto-generated method stub
		String url="";
		HashMap<String,String> hashMap = new HashMap<String, String>();
		hashMap.put("phone", "132214214");
		hashMap.put("key","2e842948");
		
		JSONObject object = new JSONObject(hashMap);
		
		JsonObjectRequest objectRequest = new JsonObjectRequest(Method.POST,url, object, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
			}
		});
		objectRequest.setTag("abcPost");
		MyApplication.getHttpQueues().add(objectRequest);
		StringRequest request = new StringRequest(Method.POST,url,new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
			}
		},new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String,String> map = new HashMap<String, String>();
				map.put("phone", "1214");
				map.put("key", "dad");
				return map;
			}
		};
		request.setTag("abcPost");
		MyApplication.getHttpQueues().add(request);
	}

	private void volley_Get() {
		// TODO Auto-generated method stub
    	String url="";
//		StringRequest request = new StringRequest(Method.GET, url, new Listener<String>() {
//
//			@Override
//			public void onResponse(String response) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//			}
//		}, new Response.ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//			}
//		});
//		request.setTag("abcGet");
//		MyApplication.getHttpQueues().add(request);
//    	JsonObjectRequest request = new JsonObjectRequest(Method.GET,url,null,new Listener<JSONObject>() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//			}
//		}, new Response.ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//			}
//		});
//    	request.setTag("abcGet");
//    	MyApplication.getHttpQueues().add(request);
    	VolleyRequest.requestGet(this, url, "abcGet", new VolleyInterface(this,VolleyInterface.mListener,VolleyInterface.mErrorListener) {
			@Override
			public void onMySuccess(String reslut) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, reslut, Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onMyError(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		MyApplication.getHttpQueues().cancelAll("abcGet");
	}
	
}
