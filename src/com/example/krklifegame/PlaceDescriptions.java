package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceDescriptions extends Activity {

	SQLiteDatabase db;
	TextView tvPlaceName, tvDescription;	
	ImageView ivPhoto;	
	int placeId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_descriptions);
	
		tvPlaceName = (TextView) findViewById(R.id.tvPlaceName);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		ivPhoto = (ImageView) findViewById(R.id.imageView1);
		Intent intent = getIntent();
		placeId = intent.getIntExtra("id", 0);
		getPlace(placeId);
	}

	public void getPlace(int id){
		DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
		String sql ="SELECT Name, Description FROM Places WHERE _id=?" ;
    	Place myPlace = dbHelper.getMyPlace(sql, id);    	
    	tvPlaceName.setText(myPlace.getPlaceName());
    	tvDescription.setText(myPlace.getPlaceDescription());
    	tvDescription.setMovementMethod(new ScrollingMovementMethod());
    	String getPlace = "a" + id;

    	ivPhoto.setImageResource(getResources().getIdentifier(getPlace, "drawable", getPackageName()));
	}
	
	public void goToMap(View v) {
		Intent intent = new Intent(this, Map.class);
		intent.putExtra("id", placeId);
		startActivity(intent);
	}
}
