package com.androidsfuture.museumschicago;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.EditText;

public class DataBaseHelper extends SQLiteOpenHelper{
	 
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.androidsfuture.museumschicago/databases/";
    private static String DB_NAME = "museumsChicago02";
    private static int DB_VERSION = 1;
    
    private SQLiteDatabase myDataBase; 
    private final Context myContext;
    
    public static final String KEY_ROWID_01 = "_id";
    public static final String KEY_MUSEUM = "museum";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_HOURS = "hours";
    public static final String KEY_ADMISSION = "admission";
    public static final String KEY_CONTACT = "contact";
    public static final String KEY_COLLECTIONS = "collections";
    public static final String KEY_EVENTS = "events";
    public static final String KEY_TYPE = "type";
    public static final String KEY_MEMBERSHIP = "membership";
    public static final String KEY_DONATE = "donate";
    public static final String KEY_PRESS = "press";
    public static final String KEY_INTERN = "intern";
    public static final String KEY_RENTAL = "rental";
    public static final String KEY_PARKING = "parking";
    public static final String KEY_VOLUNTEER = "volunteer";
    public static final String KEY_WEBSITE = "website";
    public static final String KEY_OPEN = "open";
    
    public final String TABLE_NAME = "museums";
    
    public EditText search;
    
    private static String[] FROM_FILL = {KEY_ROWID_01, KEY_MUSEUM, KEY_ADDRESS, KEY_ADMISSION};
    private static String[] FROM_INTERN = {KEY_ROWID_01, KEY_MUSEUM, KEY_ADDRESS, KEY_INTERN, KEY_ADMISSION};
    private static String[] FROM_MANSIONS = {KEY_ROWID_01, KEY_MUSEUM, KEY_ADDRESS, KEY_ADMISSION, KEY_TYPE};
	private static int[] TO = {R.id._id, R.id.MuseumName, R.id.MuseumAddress, R.id.MuseumType};
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
        
       
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
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
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
 
        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
 
	public Cursor listAll(){
		
		return myDataBase.query(TABLE_NAME, FROM_FILL, null, null, KEY_MUSEUM, null, KEY_MUSEUM);
		//return myDataBase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
	} 
	
	public Cursor listMansions(){
		
		return myDataBase.query(TABLE_NAME, FROM_MANSIONS, KEY_TYPE + " like '%mansion%'", null, KEY_MUSEUM, null, KEY_MUSEUM);
	}

	public Cursor listArt(){
		
		return myDataBase.query(TABLE_NAME, FROM_MANSIONS, KEY_TYPE + " like '%art%'", null, KEY_MUSEUM, null, KEY_MUSEUM);
	}
	
	public Cursor listFree(){
		
		return myDataBase.query(TABLE_NAME, FROM_MANSIONS, KEY_TYPE + " like '%free%'", null, KEY_MUSEUM, null, KEY_MUSEUM);
	}
	
	public Cursor listHistory(){
		
		return myDataBase.query(TABLE_NAME, FROM_MANSIONS, KEY_TYPE + " like '%history%'", null, KEY_MUSEUM, null, KEY_MUSEUM);
	}
	
	public Cursor listScience(){
		
		return myDataBase.query(TABLE_NAME, FROM_MANSIONS, KEY_TYPE + " like '%science%'", null, KEY_MUSEUM, null, KEY_MUSEUM);
	}
public Cursor listIntern(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_INTERN + " like '%Yes%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listRental(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_RENTAL + " like '%Yes%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listSunday(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_OPEN + " like '%sunday%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listMonday(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_OPEN + " like '%monday%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listTuesday(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_OPEN + " like '%tuesday%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listWednesday(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_OPEN + " like '%wednesday%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listThursday(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_OPEN + " like '%thursday%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listFriday(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_OPEN + " like '%friday%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
	
	public Cursor listSaturday(){
		
		return myDataBase.query(TABLE_NAME, FROM_INTERN, KEY_OPEN + " like '%saturday%' ", null, KEY_MUSEUM, null, KEY_MUSEUM);
		
	}
		
}