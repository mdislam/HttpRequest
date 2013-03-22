package com.httpreq.mesba;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public TextView txt1, txt2 = null;
		
	ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		
		AsyncTaskExample asyncTask = new AsyncTaskExample();
        asyncTask.execute("http://siivouspaiva.com/data.php?query=load");
        
		// We get the ListView component from the layout
	    lv = (ListView) findViewById(R.id.listView);
	     
	    lv.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id) {
	 
	              String rowId = mylist.get(position).get("id");
	              String name = mylist.get(position).get("name");
	              String address = mylist.get(position).get("address");
	              String description = mylist.get(position).get("description");
					
	              String start_hour = mylist.get(position).get("start_hour");
	              String start_minute = mylist.get(position).get("start_minute");
	              String end_hour = mylist.get(position).get("end_hour");
	              String end_minute = mylist.get(position).get("end_minute");					
					
	              String u = mylist.get(position).get("u");
	              String v = mylist.get(position).get("v");
					
	              String tags = mylist.get(position).get("tags");
	              String link = mylist.get(position).get("link");
	              String modified = mylist.get(position).get("modified");
	        	  
	 
	              // Launching new Activity on selecting single List Item
	              Intent i = new Intent(getApplicationContext(), ListData.class);
	              // sending data to new activity	              
	              //Here I am passing all values to other activity
	              i.putExtra("id",  rowId);
	              i.putExtra("name", name);
	              i.putExtra("address", address);			        
	              i.putExtra("description", description);
			        
	              i.putExtra("u", u);
	              i.putExtra("v", v);
			       
	              i.putExtra("start_hour", start_hour);
	              i.putExtra("start_minute", start_minute);
	              i.putExtra("end_hour", end_hour);
	              i.putExtra("end_minute", end_minute);
			        
	              i.putExtra("tags", tags);
	              i.putExtra("link", link);
	              i.putExtra("modified", modified);
	              
			      startActivity(i);	 
	          }
	        });
	    
	}
	
	////////////////////////////////////////////////////////////////////////
	private class AsyncTaskExample extends AsyncTask<String, Void, String> {
		
		private ProgressDialog Dialog;
        String response = "";
        
        @Override
        protected void onPreExecute() {
               Dialog = new ProgressDialog(MainActivity.this);
               Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
               Dialog.setMessage("Loading Data from server...");
               Dialog.setCancelable(false);
               Dialog.show();       
        }
        
		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub
			
			try
	    	{
	    		HttpClient hc = new DefaultHttpClient();
	    		HttpPost post = new HttpPost(urls[0]);
	    		
	    		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        
	    		//HttpPost parameters
		        nameValuePairs.add(new BasicNameValuePair("um", "60"));
		        nameValuePairs.add(new BasicNameValuePair("uM", "61"));
		        nameValuePairs.add(new BasicNameValuePair("vm", "24"));
		        nameValuePairs.add(new BasicNameValuePair("vM", "25"));
		        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	    		HttpResponse rp = hc.execute(post);

	    		if(rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
	    		{
	    			response = EntityUtils.toString(rp.getEntity());
	    		}
	    		else
	    		{
	    			Toast.makeText(getApplicationContext(), "Failed!!", Toast.LENGTH_LONG).show();
	    		}
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}  
			
			return response;
		}
		
		@Override
        protected void onPostExecute(String result) {
			
			Dialog.dismiss();
			Dialog = null;                

			String jsonStr = result;
			
			try {			
				JSONArray jsonArray = new JSONArray(jsonStr); 
				for (int i = 0; i < jsonArray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					JSONObject objJson = jsonArray.getJSONObject(i);
		
					// here you can get id,name,address,start_hour,start_minute,end_hour,end_minute,
					// description,tags,link,u,v,modified
					int id = objJson.getInt("id");
					String name =objJson.getString("name");
					String address = objJson.getString("address");
					String description = objJson.getString("description");
					
					String start_hour = objJson.getString("start_hour");
					String start_minute = objJson.getString("start_minute");
					String end_hour = objJson.getString("end_hour");
					String end_minute = objJson.getString("end_minute");					
					
					String u = objJson.getString("u");
					String v = objJson.getString("v");
					
					String tags = objJson.getString("tags");
					String link = objJson.getString("link");
					String modified = objJson.getString("modified");
					
					//add all properties to the hash map
					map.put("id",  String.valueOf(id));
			        map.put("name", name);
			        map.put("address", address);			        
			        map.put("description", description);
			        
			        map.put("u", u);
			        map.put("v", v);
			        
			        map.put("start_hour", start_hour);
			        map.put("start_minute", start_minute);
			        map.put("end_hour", end_hour);
			        map.put("end_minute", end_minute);
			        
			        map.put("tags", tags);
			        map.put("link", link);
			        map.put("modified", modified);
			        
			        mylist.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SimpleAdapter simpleAdpt = new SimpleAdapter(MainActivity.this, mylist, android.R.layout.simple_list_item_2, new String[] {"name", "address"}, new int[] {android.R.id.text1, android.R.id.text2});
			
			//Using custom ListView
			//SimpleAdapter simpleAdpt = new SimpleAdapter(MainActivity.this, mylist, R.layout.list_item, new String[] {"id","name", "address"}, new int[] {R.id.text1, R.id.text2, R.id.text3});
				    	 
		    lv.setAdapter(simpleAdpt);   
        }
	}
}


