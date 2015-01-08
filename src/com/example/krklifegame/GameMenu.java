package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_menu);
	}

	public void goToUnvisitedPlaces(View v) {
		Intent intent = new Intent(this, UnvisitedPlaces.class);
		startActivity(intent);
	}
	
	public void goToVisitedPlaces(View v) {
		Intent intent = new Intent(this, VisitedPlaces.class);
		startActivity(intent);
	}
	
	public void goToPoints(View v) {
		Intent intent = new Intent(this, Points.class);
		startActivity(intent);
	}
}
