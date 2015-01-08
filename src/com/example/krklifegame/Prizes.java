package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Prizes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prizes);
	}

	public void showPlaceOnMap(View v) {
		Intent intent = new Intent(this, Map.class);
		startActivity(intent);
	}
}
