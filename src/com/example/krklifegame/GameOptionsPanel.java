package com.example.krklifegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameOptionsPanel extends Activity {

	SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_options_panel);
		
		preferences = this.getSharedPreferences("com.example.krklifegame", Context.MODE_PRIVATE);
		
		SharedPreferences.Editor preferencesEditor = preferences.edit();		
	    preferencesEditor.putBoolean("gameIsChoosen", true);
	    preferencesEditor.commit();
		
	}

	public void goToGameMenuWithOneDayOption(View v) {
		Toast.makeText(this, "Wybra�e� opcj� jednodniow�", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithTwoDayOption(View v) {
		Toast.makeText(this, "Wybra�e� opcj� dwudniow�", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithThreeDayOption(View v) {
		Toast.makeText(this, "Wybra�e� opcj� trzydniow�", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithWeekOption(View v) {
		Toast.makeText(this, "Wybra�e� opcj� tygodniow�", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
	
	public void goToGameMenuWithoutTimeRestrictions(View v) {
		Toast.makeText(this, "Wybra�e� opcj� bez restrykcji czasowych", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, GameMenu.class);
		startActivity(intent);
	}
}
