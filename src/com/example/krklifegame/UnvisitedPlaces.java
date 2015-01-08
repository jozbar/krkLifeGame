package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UnvisitedPlaces extends Activity {
	
	Button btnShowAllPlaces;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unvisited_places);
		
		btnShowAllPlaces = (Button) findViewById(R.id.btnShowAllPlaces);
		
	}

	public void showPlaceMap(View v) {
		Intent intent = new Intent(this, Map.class);
		startActivity(intent);
	}
	
	public void showAllPlacesMap(View v) {
		System.out.println("Jestem w intent!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Intent intent = new Intent(this, Map.class);
		startActivity(intent);
	}
}
