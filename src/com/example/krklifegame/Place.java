package com.example.krklifegame;

public class Place {

	int _id;
	String _placeName;
	String _placeDescription;
	double _latidute, _longtidute;
	int _visited;
	
	public Place(){}
	
	public Place(int id, String placeName, String placeDescription, double latidute, double longtidute, int visited){
		this._id = id;
		this._placeName = placeName;
		this._placeDescription = placeDescription;
		this._latidute = latidute;
		this._longtidute = longtidute;
		this._visited = visited;
	}
	
	public Place(String placeName, String placeDescription, double latidute, double longtidute, int visited){
		this._placeName = placeName;
		this._placeDescription = placeDescription;
		this._latidute = latidute;
		this._longtidute = longtidute;
		this._visited = visited;
	}
	
	public int getID(){
		return this._id;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public String getPlaceName(){
		return this._placeName;
	}
	
	public void setPlaceName(String placeName){
		this._placeName = placeName;
	}
	
	public void setPlaceDescription(String placeDescription){
		this._placeDescription = placeDescription;
	}
	
	public String getPlaceDescription(){
		return this._placeDescription;
	}
	
	public double getLatidute(){
		return this._latidute;
	}
	
	public void setLatidute(double latidute){
		this._latidute = latidute;
	}
	
	public double getLongtidute(){
		return this._longtidute;
	}

	public void setLongtidute(double longtidute){
		this._longtidute = longtidute;
	}
	
	public int getVisited(){
		return this._visited;
	}
	public void setVisited(int visited){
		this._visited = visited;
	}
	
	public String toString(){
		return getPlaceName();
				//"LISTA: " + getID() + " " + getPlaceName()  + "( " + getLatidute() + ", " + getLongtidute() + " )";
	}
}
