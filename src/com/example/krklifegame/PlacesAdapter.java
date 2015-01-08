package com.example.krklifegame;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class PlacesAdapter{
	
	protected static final String TAG = "DataAdapter"; 
	private ArrayList<String> results = new ArrayList<String>(); 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private DataBaseHelper mDbHelper; 
 
    public PlacesAdapter(Context context){ 
        this.mContext = context; 
        mDbHelper = new DataBaseHelper(mContext); 
    } 
 
    public PlacesAdapter createDatabase() throws SQLException{ 
        try{ 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException){ 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
 
    public PlacesAdapter open() throws SQLException{ 
        try{ 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            mDb = mDbHelper.getReadableDatabase(); 
        }  
        catch (SQLException mSQLException){ 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
 
    public void close(){ 
        mDbHelper.close(); 
    }
    
    public int getId(String sql){
    	Cursor mCur = mDb.rawQuery(sql, null);
    	
    	if(mCur != null){
    		mCur.moveToNext();
    	}
    	return mCur.getInt(mCur.getColumnIndex("_id"));
    }
    
//    public Cursor getPlacesData(){ 
//        try { 
//            String sql ="SELECT _id, Name, Description, Visited FROM Places"; 
//
//            Cursor mCur = mDb.rawQuery(sql, null); 
//            if (mCur!=null) { 
//               mCur.moveToNext(); 
//            } 
//            return mCur; 
//        } 
//        catch (SQLException mSQLException){ 
//            Log.e(TAG, "getPlacesData >>"+ mSQLException.toString()); 
//            throw mSQLException; 
//        } 
//    }
    
	public static String getColumnValue(Cursor cur, String ColumnName) {
		try {
			return cur.getString(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return "";
		}
	}
	
	public static Double getColumnDoubleValue(Cursor cur, String ColumnName) {
		try {
			return cur.getDouble(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return (double) 0;
		}
	}
    
//    public void getAll(){
//    	String sql ="SELECT _id, Longtidute FROM Places WHERE _id > 10";
//    	Cursor mCur  = mDb.rawQuery(sql, null);
//    	
//    	
//    	if (mCur != null){
//    		do{
//    			String placeName = mCur.getString(mCur.getColumnIndex("Name"));
//    			String longtidute = mCur.getString(mCur.getColumnIndex("Longtidute"));
//    			Double latidute = mCur.getDouble(mCur.getColumnIndex("Latidute"));
//    			results.add("Name: " + placeName + ", Longtidute: " + longtidute);
//    		} while (mCur.moveToNext());
//    	}
//    	
//    }

    
    
//    public List<Place> getAllPlaces(){
//    	List<Place> placeList = new ArrayList<Place>();
//    	String selectQuery = "SELECT * FROM Places";
//    	mDb = mDbHelper.getReadableDatabase();
//    	Cursor mCur = mDb.rawQuery(selectQuery, null);
//    	
//    	 if (mCur.moveToFirst()) {
//             do {
//                 Place place = new Place();
//                 place.setID(Integer.parseInt(place.getString(0)));
//                 place.setName(place.getString(1));
//                 place.setPhoneNumber(place.getString(2));
//
//                 String name = place.getString(1) +"\n"+ place.getString(2);
//                 AndroidSQLiteTutorialActivity.ArrayofName.add(name);
//                 // Adding contact to list
//                 placeList.add(place);
//             } while (mCur.moveToNext());
//         }
//    	
//    	return placeList;
//    }

//	int _id;
//	String _placeName;
//	String _placeDescription;
//	double _latidute, _longtidute;
//	
//	public PlacesAdapter(int id, String placeName, String placeDescription, double latidute, double longtidute){
//		this._id = id;
//		this._placeName = placeName;
//		this._placeDescription = placeDescription;
//		this._latidute = latidute;
//		this._longtidute = longtidute;
//	}
//	
//	public PlacesAdapter(String placeName, String placeDescription, double latidute, double longtidute){
//		this._placeName = placeName;
//		this._placeDescription = placeDescription;
//		this._latidute = latidute;
//		this._longtidute = longtidute;
//	}
//	
//	public int getID(){
//		return this._id;
//	}
//	
//	public void setID(int id){
//		this._id = id;
//	}
//	
//	public String getPlaceName(){
//		return this._placeName;
//	}
//	
//	public void setPlaceName(String placeName){
//		this._placeName = placeName;
//	}
//	
//	public double getLatidute(){
//		return this._latidute;
//	}
//	
//	public void setLatidute(double latidute){
//		this._latidute = latidute;
//	}
//	
//	public double getLongtidute(){
//		return this._longtidute;
//	}
//
//	public void setLongtidute(double longtidute){
//		this._longtidute = longtidute;
//	}
//	
//	public String toString(){
//		return getPlaceName();
//				//"LISTA: " + getID() + " " + getPlaceName()  + "( " + getLatidute() + ", " + getLongtidute() + " )";
		
}
