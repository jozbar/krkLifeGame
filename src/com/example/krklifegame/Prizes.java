package com.example.krklifegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Prizes extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prizes);
		
		howManyVisitedPlaces();
	}

	private void howManyVisitedPlaces() {
		DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());
		String sql = "SELECT COUNT(Visited) FROM Places WHERE Visited=1";
		int count = dbHelper.getCount(sql);
		Toast.makeText(this, "Ilosc: " + count, Toast.LENGTH_LONG).show();		
	}

	public void showPlaceOnMap(View v) {
		Intent intent = new Intent(this, Map.class);
		startActivity(intent);
	}
}
