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

public class GPSTracker extends Service implements LocationListener{

	private final Context mContext;
	
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;
	
	Location location;
	double latitude;
	double longitude;
	
	private static final long MIN_DISTANCE_FOR_UPDATES = 10; // 10 meters
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 *1; // 1 minute
	
	protected LocationManager locationManager;
	
	public GPSTracker(Context context) {
		this.mContext = context;
		getLocation();
	}
	
	public Location getLocation() {
		try{
			locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
			//getting GPS status
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
			if(!isGPSEnabled && !isNetworkEnabled) {
				//no network provider is enabled
			} else {
				this.canGetLocation = true;
				// first get location from Network Provider
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_FOR_UPDATES, this);
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
					if (location == null){
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_FOR_UPDATES, this);
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
	
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
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
