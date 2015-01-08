package com.example.krklifegame;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VisitedPlaces extends ListActivity {

	TextView tvName;
	PlacesAdapter mDbHelper = new PlacesAdapter(this);	
	private ArrayList<String> results = new ArrayList<String>();
	SQLiteDatabase db;
//-------------------------------------------------------------------			

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDbHelper.createDatabase();
		mDbHelper.open();

//--------------------------------------------------------------
		getPlaceName();
		displayResultList();
//--------------------------------------------------------------
	}
	
	public void displayResultList() {
		TextView tvNew = new TextView(this);
		tvNew.setText("This data is retrieved from database");
		getListView().addHeaderView(tvNew);
		
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, results));
		getListView().setTextFilterEnabled(true);
		final ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {					 
		
//		Place place = (Place)(lv.getItemAtPosition(position));
//		
//		int placeId = place.getID();
		
		
		PlacesAdapter currentPlace = (PlacesAdapter)(lv.getItemAtPosition(position));
		
		
//		DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
//    	db = dbHelper.getWritableDatabase();
//    	String sql ="SELECT _id, Name, Visited FROM Places WHERE Visited=1"; // WHERE Visited=0
//    	Cursor mCur  = db.rawQuery(sql, null);
//		Cursor myCursor = db.
		 
//		String selectedFromList = (lv.getItemAtPosition(position)).toString();
//		String sql = "SELECT _id FROM Places WHERE Name=" + selectedFromList;
//		int myId = myPlace.getId(sql);
		Toast.makeText(getApplicationContext(),"Click ListItem: " + id + ", " + position + ", " , Toast.LENGTH_LONG).show();				 				 
		goToPlaceDescriptions(view, id);
				 			
			}
		});
		
	}	

// pobieranie z bazy
	
    public void getPlaceName(){
    	try{  	
	    	DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
	    	db = dbHelper.getWritableDatabase();
	    	String sql ="SELECT _id, Name, Visited FROM Places WHERE Visited=1"; // WHERE Visited=0
	    	Cursor mCur  = db.rawQuery(sql, null);
	    	
	    	if (mCur != null){
	    		do{
	    			String str = PlacesAdapter.getColumnValue(mCur, "Name");
	    			results.add(str);
	    			
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
	
	public void goToPlaceDescriptions(View v, long id) {
		Intent intent = new Intent(this, PlaceDescriptions.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}
}
