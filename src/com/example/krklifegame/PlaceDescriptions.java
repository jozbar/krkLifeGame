package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PlaceDescriptions extends Activity {

	SQLiteDatabase db;
	TextView tvPlaceName, tvDescription;
	long placeId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_descriptions);
	
		tvPlaceName = (TextView) findViewById(R.id.tvPlaceName);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		Intent intent = getIntent();
		placeId = intent.getLongExtra("id", 0);
		
		setValuesToTable(placeId);
	}
	
    public void setValuesToTable(long _id){
    	try{  	
	    	DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
	    	db = dbHelper.getWritableDatabase();
	    	String sql ="SELECT Name, Description FROM Places WHERE _id = " + _id;
	    	Cursor mCur  = db.rawQuery(sql, null);
	    	
	    	if (mCur != null){
	    		do{
	    			String str = PlacesAdapter.getColumnValue(mCur, "Name");
	    			String str2 = PlacesAdapter.getColumnValue(mCur, "Description");
	    			tvPlaceName.setText(str);
	    			tvDescription.setText(str2);
	    			
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
    
	public void goToQuiz(View v) {
		Intent intent = new Intent(this, Map.class);
		intent.putExtra("id", placeId);
		startActivity(intent);
	}
	
}
