package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Points extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_points);
	}
	
	public void goToPrizes(View v) {
		Intent intent = new Intent(this, Prizes.class);
		startActivity(intent);
	}

	public void buy(){
		
	}
}
