package com.androidsfuture.museumschicago;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TabHost;

public class Tabs extends TabActivity {
	public String collWeb;
	public String eventsWeb;
	public String memberWeb;
	public String parkWeb;
	
	
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.tabs);
    
	 	Bundle extras = getIntent().getExtras(); 
	 	if(extras !=null){
	 		collWeb = extras.getString("collURL");
	 		eventsWeb = extras.getString("eventsURL");
	 		memberWeb = extras.getString("memberURL");
	 		parkWeb = extras.getString("parkURL");
	 		
	 	}else{
	 		this.finish();
	 	}
	 	
	 	String Collections = collWeb;
		String Events = eventsWeb;
		String Membership = memberWeb;
		String Parking = parkWeb;
		
			  TabHost mTabHost = getTabHost();
			    
			    mTabHost.addTab(mTabHost.newTabSpec("tab_collections").setIndicator("Collections", getResources().getDrawable(R.drawable.containers_32))
			    		.setContent(new Intent(this,Collections.class)
			    		.putExtra("collLink",Collections)));
			    mTabHost.addTab(mTabHost.newTabSpec("tab_events").setIndicator("Events", getResources().getDrawable(R.drawable.gold_music_noteote_32))
			    		.setContent(new Intent(this,Events.class)
			    		.putExtra("eventsLink", Events)));
			    mTabHost.addTab(mTabHost.newTabSpec("tab_membership").setIndicator("Membership", getResources().getDrawable(R.drawable.money_32))
			    		.setContent(new Intent(this,Membership.class)
			    		.putExtra("memberLink", Membership)));
			    mTabHost.addTab(mTabHost.newTabSpec("tab_parking").setIndicator("Parking", getResources().getDrawable(R.drawable.classic_truck_yellow_48))
			    		.setContent(new Intent(this,Parking.class)
			    		.putExtra("parkLink", Parking)));
			   			   			    
			  mTabHost.setCurrentTab(0);
			    
		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  // We do nothing here. We're only handling this to keep orientation
	  // or keyboard hiding from causing the WebView activity to restart.
	}

}
