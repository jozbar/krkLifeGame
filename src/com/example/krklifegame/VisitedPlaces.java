package com.example.krklifegame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class VisitedPlaces extends ListActivity {

	private ArrayList<String> results = new ArrayList<String>();
	List<Place> placesList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DataBaseHelper dbHelper = new DataBaseHelper(this);
		
		try {
			dbHelper.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbHelper.openDataBase();

		fillTheList();
		displayResultList();
	}
	
	public void fillTheList(){
		DataBaseHelper dbHelper = new DataBaseHelper(this.getApplicationContext());		
		
		String sql = "SELECT _id, Name, Description, Latidute, Longtidute FROM Places WHERE Visited=1";
		placesList = dbHelper.getAllPlaces(sql);
		
		for (Place place : placesList){
			String pName = place.getPlaceName();
			results.add(pName);
			System.out.println("W nowym oknie: " + pName);
		}
	}	
	
	public void displayResultList() {
		TextView tvNew = new TextView(this);
		final ListView lv = getListView();
		lv.addHeaderView(tvNew);
		lv.setTextFilterEnabled(true);
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, results));
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {	
				position -= 1;	
		
				Place mPlace = placesList.get(position);
				int mID = mPlace.getID();			 				 
						
				Intent intent = new Intent(VisitedPlaces.this, PlaceDescriptions.class);
				intent.putExtra("id", mID);
				startActivity(intent);				 			
			}
		});		
	}
	
	
}
