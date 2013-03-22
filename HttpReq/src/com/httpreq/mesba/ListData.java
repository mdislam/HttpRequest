package com.httpreq.mesba;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ListData extends Activity {
	List<Map<String, String>> planetsList = new ArrayList<Map<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        this.setContentView(R.layout.details_layout);
 
        TextView nameTxt = (TextView) findViewById(R.id.name_label);
        TextView addressTxt = (TextView) findViewById(R.id.address_label);
        TextView detailsTxt = (TextView) findViewById(R.id.details_label);
        
        TextView operatingTxt = (TextView) findViewById(R.id.operating_label);
        
        TextView latitudeTxt = (TextView) findViewById(R.id.latitude_label);
        TextView longitudeTxt = (TextView) findViewById(R.id.longitude_label);
        
        TextView otherTxt = (TextView) findViewById(R.id.other_label);
 
        Intent i = getIntent();
        // getting attached intent data
        String rowId = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        String address = i.getStringExtra("address");
        String description = i.getStringExtra("description");
			
        String start_hour = i.getStringExtra("start_hour");
        String start_minute = i.getStringExtra("start_minute");
        String end_hour = i.getStringExtra("end_hour");
        String end_minute = i.getStringExtra("end_minute");					
			
        String u = i.getStringExtra("u");
        String v = i.getStringExtra("v");
			
        String tags = i.getStringExtra("tags");
        String link = i.getStringExtra("link");
        String modified = i.getStringExtra("modified");
        // displaying selected product name
        
        nameTxt.setText(name);
        addressTxt.setText(address);
        detailsTxt.setText(description);
        
        operatingTxt.setText("From " + start_hour + ":" + start_minute + " To " + end_hour + ":" + end_minute);
        
        latitudeTxt.setText(u);
        longitudeTxt.setText(v);
        
        otherTxt.setText("Tags: " + tags + "; link: " + link + "; modified: " + modified);
	}
}
