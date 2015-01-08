package com.example.krklifegame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	private static String DB_PATH = "";
	private static String DB_NAME = "myDatabase";
	
	private static String TAG = "DataBaseHelper";
//	private static final String TABLE_PLACES = "Places";
//	//name of columns
//	
//	private static final String PLACES_COLUMN_ID = "_id";
//	private static final String PLACES_COLUMN_NAME = "Name";
//	private static final String PLACES_COLUMN_DESCRIPTION = "Description";
//	private static final String PLACES_COLUMN_LATIDUTE = "Latidute";
//	private static final String PLACES_COLUMN_LONGTIDUTE = "Longtidute";
//	private static final String PLACES_COLUMN_PHOTO = "Photo";
//	private static final String PLACES_COLUMN_VISITED = "Visited";
	
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		DB_PATH = "/data/data/com.example.krklifegame/databases/";
		this.myContext = context;
	}

	/**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
    	
    	if(!dbExist){ 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
        	this.close();
        	
        	try {
    			copyDataBase();
    			Log.e(TAG, "createDatabase database created"); 
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    //Check that the database exists here: /data/data/your package/databases/Da Name 
    private boolean checkDataBase() 
    { 
        File dbFile = new File(DB_PATH + DB_NAME); 
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists()); 
        return dbFile.exists(); 
    } 

 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME); 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;    	
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);    	
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public boolean openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    	//SQLiteDatabase db = this.getReadableDatabase();    	
    	System.out.println("IN ---------------- openDataBase2-----");
    	return myDataBase != null;
 
    }
    
    @Override
	public synchronized void close() {
    	    if(myDataBase != null)
    		    myDataBase.close();
    	    super.close();
	}
    
//			public Cursor getData(int id){
//				SQLiteDatabase db = this.getReadableDatabase();
//				Cursor res = db.rawQuery("select * from contacts where id="+id+"", null);
//				return res;
//			}
    
//				PlacesAdapter getPlace (String placeName){
//					SQLiteDatabase db = this.getReadableDatabase();
//					//String myQuery = "SELECT * FROM " + DB_NAME + "WHERE " + PLACES_COLUMN_NAME + "=" + placeName;
//					String[] columns = { PLACES_COLUMN_ID, PLACES_COLUMN_NAME, PLACES_COLUMN_DESCRIPTION, PLACES_COLUMN_LATIDUTE, PLACES_COLUMN_LONGTIDUTE};
//					String where = PLACES_COLUMN_NAME+"="+ placeName ;
//					Cursor cursor = db.query(TABLE_PLACES, columns, where, null, null, null, null, null);
//				//	db.rawQuery("SELECT * FROM myDatabase.sqlite_master WHERE type='table'", null); 
//			//		SELECT * FROM myDatabase.sqlite_master WHERE type='table';
//					if (cursor != null)
//						cursor.moveToFirst();
//					
//					PlacesAdapter myPlace = new PlacesAdapter (Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));		
//					return myPlace;
//									
//				}
	

 
    
//		    public Cursor query(String table,String[] columns, String selection,String[] selectionArgs,String groupBy,String having,String orderBy){
//		        return myDataBase.query("PLACES", null, null, null, null, null, null); 
//		    }
    
    
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}

 
        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.	    }


}
