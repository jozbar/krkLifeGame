package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameOptionsPanel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_options_panel);
	}

	public void goToGameMenuWithOneDayOption(View v) {
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithTwoDayOption(View v) {
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithThreeDayOption(View v) {
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithWeekOption(View v) {
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithoutTimeRestrictions(View v) {
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
}
