package com.example.krklifegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends Activity implements
	LocationListener,
	GooglePlayServicesClient.ConnectionCallbacks,
	GooglePlayServicesClient.OnConnectionFailedListener {

	int placeId;
	SQLiteDatabase db;
	Double latitude, longitude;
	String name;
	Button trackGPS;
	TextView tvTest;
	GPSTracker gps;
	LocationClient currentLocation;
	
	private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(1000)        
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	
	private GoogleMap googleMap;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_map);
	      
	      Intent intent = getIntent();
	      placeId = intent.getIntExtra("id", 0);
	      tvTest = (TextView) findViewById(R.id.textView1);
	      trackGPS = (Button) findViewById(R.id.btnTrack);
	      LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	      
	      try {
				initializeMap();
			} catch (Exception e) {
				e.printStackTrace();
			}
	      	      
	      trackGPS.setOnClickListener(new View.OnClickListener() {
			  public void onClick(View arg0) {        
			      gps = new GPSTracker(Map.this);
			      if(gps.canGetLocation()){
				    	System.out.println("jestem w GPSTracking");
						double mylatitude = gps.latitude;
						double mylongitude = gps.longitude;
						tvTest.setText("latidute: " + mylatitude + ", longitude: " + mylongitude);
						Toast.makeText(getApplicationContext(), "lat: " + mylatitude + ",lon: " + mylongitude , Toast.LENGTH_LONG).show();
		  		 } else {
			  			gps.showSettingsAlert();
		  		 }
			  }
		  });	      		  	      
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
	
	private void setUpLocationClientIfNeeded() {
        if (currentLocation == null) {
	        currentLocation = new LocationClient(
                getApplicationContext(),
                this,  // ConnectionCallbacks
                this); // OnConnectionFailedListener
        }
    }
	
//	public void runGPSBackground(View view){
//		GPSTracker myGps = new GPSTracker(this);
//		if(myGps.canGetLocation()){
//			System.out.println("jestem w GPSTracking");
//			double mylatitude = myGps.latitude;
//			double mylongitude = myGps.longitude;
//			tvTest.setText("latidute: " + mylatitude + ", longitude: " + mylongitude);
//			Toast.makeText(getApplicationContext(), "lat: " + mylatitude + ",lon: " + mylongitude , Toast.LENGTH_LONG).show();
//		}else{
//			myGps.showSettingsAlert();
//		}
//	}
	 
	@Override
	protected void onResume() {
		super.onResume();
		setUpLocationClientIfNeeded();
		currentLocation.connect();
	}
	
	@Override // 
	protected void onPause() {
		super.onPause();
		if (currentLocation != null) {
            currentLocation.disconnect();
        }		
	}
	
	@Override
	public void onConnected(Bundle arg0) {
 		currentLocation.requestLocationUpdates( REQUEST, this);  // LocationListener	
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(Location location) {
		String coordinates = String.valueOf(location.getLatitude()) + ", "
				+ String.valueOf(location.getLongitude());
		
		tvTest.setText("Aktualna pozycja: " + coordinates); 
		
//		if (targetLocation != null){
//			distanceInMeters = location.distanceTo(targetLocation);		
			
			// jesli dystans mniejszy niz 1 kilometr to wlacz alarm
	//		if (distanceInMeters < 100){
	//			//mp.start();
	//		}
			
	//		System.out.println("distance in meters: " + distanceInMeters);
	//		tvCoordinates.setText(String.valueOf(distanceInMeters));
	}
		
}
	
//	public void stopGPS(View view){
//		GPSTracker mygps = new GPSTracker(this);
//		System.out.println("wyloczony");
//	}
//}

	
	
	
	


