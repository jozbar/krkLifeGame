package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends Activity {

	int placeId;
	SQLiteDatabase db;
	Double latitude, longitude;
	String name;
	
	private GoogleMap googleMap;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_map);
	      
	      Intent intent = getIntent();
	      placeId = intent.getIntExtra("id", 0);
	      
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
		  getPlace(placeId);
		  LatLng latLng = new LatLng(latitude, longitude);		  
		  
		  int strokeColor = 0xffff0000; //red outline
		  int shadeColor = 0x44ff0000; //opaque red fill
		  CircleOptions circleOptions = new CircleOptions().center(latLng).radius(35).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(3);
		  googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng , 14.0f) );	
		  googleMap.addMarker(new MarkerOptions().position(latLng).title(name));
		  googleMap.addCircle(circleOptions);
	}

	
	public void getPlace(int id){
		DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
		String sql ="SELECT Name, Latidute, Longtidute FROM Places WHERE _id=?" ;
    	Place myPlace = dbHelper.getMyPlace(sql, id);    
    	
    	name = myPlace.getPlaceName();
    	latitude = myPlace.getLatidute();
    	longitude = myPlace.getLongtidute();
    	
	}
	
	   
	   
}
