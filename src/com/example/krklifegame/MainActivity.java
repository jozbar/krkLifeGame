package com.example.krklifegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		preferences = this.getSharedPreferences("com.example.krklifegame", Context.MODE_PRIVATE);
	}

	public void goToGameOptionsPanel(View v) {
		boolean isGameChoosen = preferences.getBoolean("gameIsChoosen", false);
		if (isGameChoosen){
			Intent intent = new Intent(this, GameMenu.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, GameOptionsPanel.class);
			startActivity(intent);
		}

	}

	public void goToAbout(View v) {
		Intent intent = new Intent(this, About.class);
		startActivity(intent);
	}
	
	public void goToPrizes(View v) {
		Intent intent = new Intent(this, Prizes.class);
		startActivity(intent);
	}
	
	public void goToMaps(View v) {
		Intent intent = new Intent(this,Map.class);
		startActivity(intent);
	}
	
	public void reset(View v) {
		SharedPreferences.Editor preferencesEditor = preferences.edit();		
	    preferencesEditor.commit();
	    
	    DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
	    dbHelper.zerosVisited();
	    Toast.makeText(this, "Zresetowano ustawienia", Toast.LENGTH_LONG).show();
	    
	}
}
