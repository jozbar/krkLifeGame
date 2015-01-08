package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends Activity {

	long placeId;
	SQLiteDatabase db;
	Double latitude, longitude;
	String name;
	
	private GoogleMap googleMap;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_map);
	      
	      Intent intent = getIntent();
	      placeId = intent.getLongExtra("id", 0);
	      
	      try {
				initializeMap();
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	   
	private void initializeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().
					findFragmentById(R.id.map)).getMap();
		}
		  googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		  googleMap.setMyLocationEnabled(true);
		  setCoordinates(placeId);
		  LatLng latLng = new LatLng(latitude, longitude);		  
		  
		  int strokeColor = 0xffff0000; //red outline
		  int shadeColor = 0x44ff0000; //opaque red fill
		  CircleOptions circleOptions = new CircleOptions().center(latLng).radius(35).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(3);
		  googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng , 14.0f) );	
		  googleMap.addMarker(new MarkerOptions().position(latLng).title(name));
		  googleMap.addCircle(circleOptions);
	}

	private void setCoordinates(long _id) {
		try{  	
	    	DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
	    	db = dbHelper.getWritableDatabase();
	    	String sql ="SELECT Name, Latidute, Longtidute FROM Places WHERE _id = " + _id;
	    	Cursor mCur  = db.rawQuery(sql, null);
	    	
	    	if (mCur != null){
	    		do{
	    			name = PlacesAdapter.getColumnValue(mCur, "Name");	    			
	    			latitude = PlacesAdapter.getColumnDoubleValue(mCur, "Latidute");
	    			longitude = PlacesAdapter.getColumnDoubleValue(mCur, "Longtidute");	    				    		
	    		} while (mCur.moveToNext());
	    	}
    	}catch(SQLException se){
    		Log.e(getClass().getSimpleName(), "Could not create or Open the Database");
    	}finally{
    		if (db != null){
    			db.close();
    		}
    	}		
	}
	   
	   
}
