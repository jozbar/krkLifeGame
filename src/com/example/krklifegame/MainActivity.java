package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void goToGameOptionsPanel(View v) {
		Intent intent = new Intent(this, GameOptionsPanel.class);
		startActivity(intent);

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
}
