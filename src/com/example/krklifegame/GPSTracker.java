package com.example.krklifegame;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class GPSTracker extends Service implements LocationListener{
	 private final Context mContext;
	 
	    // flag for GPS status
	    boolean isGPSEnabled = false;
	 
	    // flag for network status
	    boolean isNetworkEnabled = false;
	 
	    boolean canGetLocation = false;
	 
	    Location location; // location
	    double latitude; // latitude
	    double longitude; // longitude
	 
	    // The minimum distance to change Updates in meters
	    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters
	 
	    // The minimum time between updates in milliseconds
	    private static final long MIN_TIME_BW_UPDATES = 1000; // * 60 * 1; // 1 minute
	 
	    // Declaring a Location Manager
	    protected LocationManager locationManager;
	 
	    public GPSTracker(Context context) {
	        this.mContext = context;
	        getLocation();
	    }
	
	public Location getLocation() {
		try{
			locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
			//getting GPS status
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			System.out.println("isGPSENABLED: " + isGPSEnabled);
			System.out.println("isNetowrkEnabled: " + isGPSEnabled);
			if(!isGPSEnabled && !isNetworkEnabled) {
				System.out.println("Wszystkjo powyloczane");
				//no network provider is enabled
			} else {
				this.canGetLocation = true;
				// first get location from Network Provider
				if (isNetworkEnabled) {
					System.out.println("siecWlaczona");
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if(location != null){
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
				//if GPS enabled get lat/long using GPS Services
				if(isGPSEnabled){
					System.out.println("gps wlaczony");
					if (location == null){
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if(locationManager != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return location;
	}
	
	public double getLatitude() {
		if(location != null){
			latitude = location.getLatitude();
		}
		return latitude;
	}
	
	public double getLongitude() {
		if(location != null){
			longitude = location.getLongitude();
		}
		return longitude;
	}
	
	//function to check if best network provider @return boolean
	public boolean canGetLocation(){
		return this.canGetLocation;
	}
	
	//function to show settings alert dialog
	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
		//dialog title
		alertDialog.setTitle("GPS is settings");
		//dialog message
		alertDialog.setMessage("GPS is not enabled. Do youe want to go to settings menu?");
		//setting icon to DIalog
		//alertDialog.setIcon(R.drawable.delete);
		
		//On pressing Settings button
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) { 
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mContext.startActivity(intent);
			}
		});
		
		//on pressing cancel button
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();				
			}
		});		
		//showing alert Message
		alertDialog.show();		
	}
	
	//Stop using GPS listener
	public void stopUsingGPS(){
		if(locationManager != null){
			locationManager.removeUpdates(GPSTracker.this);
		}
	}
	
//	@Override
//	protected void onResume() {
//		super.onResume();
//		setUpLocationClientIfNeeded();
//		location.connect();
//	}
//	
//	@Override // 
//	protected void onPause() {
//		super.onPause();
//		if (location != null) {
//            location.disconnect();
//        }		
//	}
//	
//	@Override
//	public void onConnected(Bundle arg0) {
// 		location.requestLocationUpdates( REQUEST, this);  // LocationListener	
//		
//	}
	
	@Override
	public void onLocationChanged(Location location) {
//		latitude = location.getLatitude();
//        longitude = location.getLongitude();
//        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + "\nLong: " , Toast.LENGTH_SHORT).show();		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
