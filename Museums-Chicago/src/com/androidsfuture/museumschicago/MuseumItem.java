package com.androidsfuture.museumschicago;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MuseumItem extends Activity implements OnClickListener{
	
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
    public final String TABLE_NAME = "museums";
    
    private static final int ACTIVITY_EDIT=1;

	private SQLiteDatabase mDb;
	private DataBaseHelper mDbHelper;
	private Long mRowId;
	
	private TextView itemName;
	private TextView itemAddress;
	private TextView itemPhone;
	private TextView itemEmail;
	private TextView itemHours;
	private TextView itemAdmission;
	private TextView itemRental;
	private TextView itemIntern;
	private TextView webViewLink;
	private TextView itemCollections;
	private TextView itemEvents;
	private TextView itemMembership;
	private TextView itemParking;
	private TextView itemWebsite;
	private Button tabButton;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem);
        
        mDbHelper = new DataBaseHelper(this);
        mDb = mDbHelper.getWritableDatabase();
        
        //Get fields to be updated
        itemName = (TextView)findViewById(R.id.ListItem_Name);
        itemAddress = (TextView)findViewById(R.id.ListItem_Address);
        itemPhone = (TextView)findViewById(R.id.ListItem_Phone);
        itemEmail = (TextView)findViewById(R.id.ListItem_Email);
        itemHours = (TextView)findViewById(R.id.ListItem_Hours);
        itemAdmission = (TextView)findViewById(R.id.ListItem_Admission);
        itemRental = (TextView)findViewById(R.id.ListItem_Rental);
        itemIntern = (TextView)findViewById(R.id.ListItem_Intern);
        itemCollections = (TextView)findViewById(R.id.ListItem_Collection);
        itemEvents = (TextView)findViewById(R.id.ListItem_Events);
        itemMembership = (TextView)findViewById(R.id.ListItem_Membership);
        itemParking = (TextView)findViewById(R.id.ListItem_Parking);
        itemWebsite = (TextView)findViewById(R.id.ListItem_Website);
        tabButton = (Button)findViewById(R.id.TabsButton);
        	
        //Create clickable links
        itemAddress.setOnClickListener(this);
        itemPhone.setOnClickListener(this);
        itemEmail.setOnClickListener(this);
        itemWebsite.setOnClickListener(this);
        tabButton.setOnClickListener(this);
        
        webViewLink = (TextView)findViewById(R.id.ListItem_WebViewLink);
        	webViewLink.setOnClickListener(this);
        
       
        
        mRowId = savedInstanceState != null ? savedInstanceState.getLong(DataBaseHelper.KEY_ROWID_01) 
				: null;
        
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();            
			mRowId = extras != null ? extras.getLong(DataBaseHelper.KEY_ROWID_01) 
									: null;
		}
		
		populateFields();
		
    }
    
    
    private void populateFields() {
        if (mRowId != null) {
            Cursor note = fetchItems(mRowId);
            startManagingCursor(note);
            itemName.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_MUSEUM)));
            itemAddress.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_ADDRESS)));
            itemPhone.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_PHONE)));
            itemEmail.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_CONTACT)));
            itemHours.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_HOURS)));
            itemAdmission.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_ADMISSION)));
            itemRental.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_RENTAL)));
            itemIntern.setText(note.getString(
                    note.getColumnIndexOrThrow(DataBaseHelper.KEY_INTERN)));
            itemCollections.setText(note.getString(
            		note.getColumnIndexOrThrow(DataBaseHelper.KEY_COLLECTIONS)));
            itemEvents.setText(note.getString(
            		note.getColumnIndexOrThrow(DataBaseHelper.KEY_EVENTS)));
            itemMembership.setText(note.getString(
            		note.getColumnIndexOrThrow(DataBaseHelper.KEY_MEMBERSHIP)));
            itemParking.setText(note.getString(
            		note.getColumnIndexOrThrow(DataBaseHelper.KEY_PARKING)));
            itemWebsite.setText(note.getString(
            		note.getColumnIndexOrThrow(DataBaseHelper.KEY_WEBSITE)));
        }
    }
    
    public Cursor fetchItems(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, TABLE_NAME, new String[] {KEY_ROWID_01,
                		KEY_MUSEUM, KEY_ADDRESS, KEY_PHONE, KEY_CONTACT, 
                		KEY_HOURS, KEY_ADMISSION, KEY_RENTAL, KEY_INTERN,
                		KEY_COLLECTIONS, KEY_EVENTS, KEY_MEMBERSHIP, KEY_PARKING, KEY_WEBSITE}, KEY_ROWID_01 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(DataBaseHelper.KEY_ROWID_01, mRowId);
    }
 
    
    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }
    
       


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		
		case R.id.ListItem_Address:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?daddr=" + itemAddress.getText().toString())));
			break;
				
		case R.id.ListItem_Phone:
			startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + itemPhone.getText().toString())));
			break;
			
		case R.id.ListItem_Email:
			String[] Me = new String[] {itemEmail.getText().toString()};
			Intent sendTo = new Intent(android.content.Intent.ACTION_SEND);
				sendTo.putExtra(android.content.Intent.EXTRA_EMAIL, Me);
				sendTo.setType("text/plain");
				startActivity(Intent.createChooser(sendTo, null));
		    break;
		    
		case R.id.ListItem_Website:
			Intent webSite = new Intent(Intent.ACTION_VIEW);
			webSite.setData(Uri.parse(itemWebsite.getText().toString()));
			startActivity(webSite);
			break;
			
		case R.id.TabsButton:
			
			String Collections = itemCollections.getText().toString();
			String Events = itemEvents.getText().toString();
			String Membership = itemMembership.getText().toString();
			String Parking = itemParking.getText().toString();
			Intent i = (new Intent(this, Tabs.class));
				i.putExtra("collURL", Collections );
				i.putExtra("eventsURL", Events);
				i.putExtra("memberURL", Membership);
				i.putExtra("parkURL", Parking);
			startActivity(i);			
			break;
		}
		
		
	}
}
