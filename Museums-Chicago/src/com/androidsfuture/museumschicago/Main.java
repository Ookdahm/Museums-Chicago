package com.androidsfuture.museumschicago;


import java.io.IOException;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.admob.android.ads.AdView;


public class Main extends ListActivity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private AdView mAd;
	private DataBaseHelper myDbHelper;
	private Cursor mMuseumCursor;
	private static final int ACTIVITY_EDIT=1;
	
    public final String TABLE_NAME = "museums";
    public static final String KEY_MUSEUM = "museum";
    public static final String KEY_TYPE = "type";
	
    private static String[] FROM_SEARCH = {DataBaseHelper.KEY_ROWID_01, KEY_MUSEUM, DataBaseHelper.KEY_TYPE, DataBaseHelper.KEY_ADDRESS, DataBaseHelper.KEY_ADMISSION};
	private static String[] FROM_FILL = {DataBaseHelper.KEY_ROWID_01,DataBaseHelper.KEY_MUSEUM, DataBaseHelper.KEY_ADDRESS, DataBaseHelper.KEY_ADMISSION};
	private static int[] TO = {R.id._id,R.id.MuseumName, R.id.MuseumAddress, R.id.MuseumType};
	
	private static int keyCodeDown = 0;
	private static int keyCodeUp = 1;
	
	public TextView ListAll;
	public TextView ListFree;
	public TextView ListMansions;
	public TextView ListHistory;
	public TextView ListArt;
	public TextView ListScience;
	public TextView SearchText;
	public EditText searchBox;
	public String SearchBox;
	public String EmailSubject;
	
	final Handler mHandler = new Handler();
	

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*AdManager.setTestDevices( new String[] {
        		AdManager.TEST_EMULATOR, // Android emulator
        		"18E18844C940F82CECD94945D57EB9FC", // My T-Mobile G1 Test Phone
        		} ); */
        
        
        
        myDbHelper = new DataBaseHelper(this);
 
        try {
 
        	myDbHelper.createDataBase();
 
        	} catch (IOException ioe) {
 
        		throw new Error("Unable to create database");
 
        	}
 
        		try {
 
        			myDbHelper.openDataBase();
 
        		}catch(SQLException sqle){
 
        			throw sqle;
 
        		}
 
     
                   
        Typeface copperPlateTypeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/COPRGTB.TTF");
        
        
        	
        TextView TitleText = (TextView)this.findViewById(R.id.TitleText);
        	TitleText.setTypeface(copperPlateTypeFaceBold);
        	
        ListAll = (TextView)this.findViewById(R.id.ListAll);
        	ListAll.setOnClickListener(this);
        	ListAll.setTypeface(copperPlateTypeFaceBold);
        	        	
        ListFree = (TextView)this.findViewById(R.id.ListFree);
        	ListFree.setOnClickListener(this);
        	ListFree.setTypeface(copperPlateTypeFaceBold);
        	
        ListMansions = (TextView)this.findViewById(R.id.ListMansions);
        	ListMansions.setOnClickListener(this);
        	ListMansions.setTypeface(copperPlateTypeFaceBold);
        	
        ListHistory = (TextView)this.findViewById(R.id.ListHistory);
         	ListHistory.setOnClickListener(this);
         	ListHistory.setTypeface(copperPlateTypeFaceBold);
        	
        ListArt = (TextView)this.findViewById(R.id.ListArt);
        	ListArt.setOnClickListener(this);
        	ListArt.setTypeface(copperPlateTypeFaceBold);
        	
        ListScience = (TextView)this.findViewById(R.id.ListScience);
        	ListScience.setOnClickListener(this);
        	ListScience.setTypeface(copperPlateTypeFaceBold);
        	
        SearchText = (TextView)this.findViewById(R.id.SearchText);
        	SearchText.setOnClickListener(this);
        	SearchText.setTypeface(copperPlateTypeFaceBold); 

    }
    


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
    	case R.id.ListAll:
    		Toast.makeText(this, "Displaying ALL entries", Toast.LENGTH_LONG).show();
    		fillAll();
    			    		    		
		break;

		case  R.id.ListMansions:
			Toast.makeText(this, "Displaying all MANSION entries", Toast.LENGTH_LONG).show();
			fillMansions();
			
			break;
		
		case R.id.ListArt:
			Toast.makeText(this, "Displaying all ART museums/galleries", Toast.LENGTH_LONG).show();
			fillArt();
			
			break;
			
		case R.id.ListFree:
			Toast.makeText(this, "Displaying all FREE entries", Toast.LENGTH_LONG).show();
			fillFree();
			
			break;
			
		case R.id.ListHistory:
			Toast.makeText(this, "Displaying all HISTORY museums", Toast.LENGTH_LONG).show();
			fillHistory();
			
			break;
			
		case R.id.ListScience:
			Toast.makeText(this, "Displaying all SCIENCE museums", Toast.LENGTH_LONG).show();
			fillScience();
			
			break;

			
		}
	
	}
	


    private Cursor fillAll() {
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listAll();
        startManagingCursor(mMuseumCursor);

        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);
        myDbHelper.close();
		return mMuseumCursor;
		
		
    }

    private Cursor fillMansions(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listMansions();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter); 
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillArt(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listArt();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter); 
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillFree(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listFree();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillHistory(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listHistory();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);  
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillScience(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listScience();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);
        myDbHelper.close();
    	return mMuseumCursor;
    }
    private Cursor fillIntern(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listIntern();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillRental(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listRental();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillSunday(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listSunday();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillMonday(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listMonday();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillTuesday(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listTuesday();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillWednesday(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listWednesday();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillThursday(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listThursday();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillFriday(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listFriday();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
    
    private Cursor fillSaturday(){
    	myDbHelper.openDataBase();
    	mMuseumCursor = myDbHelper.listSaturday();
        startManagingCursor(mMuseumCursor);
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listall, mMuseumCursor, FROM_FILL, TO);
        setListAdapter(adapter);   
        myDbHelper.close();
    	return mMuseumCursor;
    }
          
    //After user selects museum, show details of the establishment
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
               
        Intent i = new Intent(this, MuseumItem.class);
        i.putExtra(DataBaseHelper.KEY_ROWID_01, id);
        startActivityForResult(i, ACTIVITY_EDIT);
       
    }
	
    //Create menu items
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
		return true;	
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	
    	case R.id.MC_Menu:
    		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://museum-community.blogspot.com")));
    		return true;
 
        	
    	case R.id.Maps_All:
    		startActivity(new Intent(this, Maps.class));
    		Toast.makeText(this, R.string.toast_more_options, Toast.LENGTH_LONG).show();
    		return true;
    		
    	case R.id.Maps_Free:
    		startActivity(new Intent(this, MapsFree.class));
    		Toast.makeText(this, R.string.toast_more_options, Toast.LENGTH_LONG).show();
    		return true;
    		
    	case R.id.Maps_Art:
    		startActivity(new Intent(this, MapsArt.class));
    		Toast.makeText(this, R.string.toast_more_options, Toast.LENGTH_LONG).show();
    		return true;
    		
    	case R.id.Maps_Mansion:
    		startActivity(new Intent(this, MapsMansion.class));
    		Toast.makeText(this, R.string.toast_more_options, Toast.LENGTH_LONG).show();
    		return true;
    		
    	case R.id.Maps_History:
    		startActivity(new Intent(this, MapsHistory.class));
    		Toast.makeText(this, R.string.toast_more_options, Toast.LENGTH_LONG).show();
    		return true;
    		
    	case R.id.Maps_Science:
    		startActivity(new Intent(this, MapsScience.class));
    		Toast.makeText(this, R.string.toast_more_options, Toast.LENGTH_LONG).show();
    		return true;
    		

    		
    	case R.id.CityPass:
    		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.citypass.com/chicago")));
    		return true;
    		
    	case R.id.Menu_Apps:
    		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://search?q=pub:%22Androids%20Future%22")));
    		return true;
    	
    		
    	case R.id.Tell_A_Friend:
    		startActivity(new Intent(android.content.Intent.ACTION_SEND)
    					.putExtra(android.content.Intent.EXTRA_SUBJECT,"Museums in Chicago")
    					.putExtra(android.content.Intent.EXTRA_TEXT, "Here's an interesting app I use called " +
    						"Museums In Chicago. I think you'll really like it.\n\n" +
    						"This website helps making decisions of where to go easier. It's updated every Monday. " +
    						"And you can access it directly from the app:\n\n" +
							"http://museum-community.blogspot.com")
    					.addCategory(Intent.CATEGORY_DEFAULT)
    					.setType("text/plain"));
    		return true;
    		
    	case R.id.Contact_Us:
    		String[] Me = new String[] {"androidsfuture@gmail.com"};
    		startActivity(new Intent(android.content.Intent.ACTION_SEND)
				.putExtra(android.content.Intent.EXTRA_EMAIL, Me)
				.addCategory(Intent.CATEGORY_DEFAULT)
				.setType("text/plain"));
    		return true;
    		
    	case R.id.Search_Intern:
    		Toast.makeText(this, "Displaying all museums that have INTERNS", Toast.LENGTH_LONG).show();
    		fillIntern();
    		return true;
    		
    	case R.id.Search_Rental:
    		Toast.makeText(this, "Displaying all museums that allow RENTALS", Toast.LENGTH_LONG).show();
    		fillRental();
    		return true;
    		
    	case R.id.sunday:
    		Toast.makeText(this, "Displaying all museums open SUNDAY", Toast.LENGTH_LONG).show();
    		fillSunday();
    		return true;
    		
    	case R.id.monday:
    		Toast.makeText(this, "Displaying all museums open MONDAY", Toast.LENGTH_LONG).show();
    		fillMonday();
    		return true;
    		
    	case R.id.tuesday:
    		Toast.makeText(this, "Displaying all museums open TUESDAY", Toast.LENGTH_LONG).show();
    		fillTuesday();
    		return true;
    		
    	case R.id.wednesday:
    		Toast.makeText(this, "Displaying all museums open WEDNESDAY", Toast.LENGTH_LONG).show();
    		fillWednesday();
    		return true;
    		
    	case R.id.thursday:
    		Toast.makeText(this, "Displaying all museums open THURSDAY", Toast.LENGTH_LONG).show();
    		fillThursday();
    		return true;
    		
    	case R.id.friday:
    		Toast.makeText(this, "Displaying all museums open FRIDAY", Toast.LENGTH_LONG).show();
    		fillFriday();
    		return true;
    		
    	case R.id.saturday:
    		Toast.makeText(this, "Displaying all museums open SATURDAY", Toast.LENGTH_LONG).show();
    		fillSaturday();
    		return true;
    	}
		return false;
    }
    	
}
	
	