 package com.androidsfuture.museumschicago;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsArt<PointLocation> extends MapActivity {
    /** Called when the activity is first created. */
	List<Overlay> mapOverlays;
	Drawable drawable;
	MuseumItemizedOverlay itemizedOverlay;
	Context mContext;
	MapView mapView;
	MyLocationOverlay me=null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.museum_48);
        itemizedOverlay = new MuseumItemizedOverlay(drawable);
        
        me=new MyLocationOverlay(this, mapView);
        
        me.runOnFirstFix(new Runnable() { public void run() {
            mapView.getController().animateTo(me.getMyLocation());
        }});
        
    	mapView.getOverlays().add(me);
        
    	GeoPoint point01 = new GeoPoint((int) (41.911558*1E6), (int) (-87.63168*1E6));
        OverlayItem overlayitem01 = new OverlayItem(point01, 
        		"Chicago History Museum", "1601 North Clark Street, Chicago, IL 60614");
        
        GeoPoint point03 = new GeoPoint((int) (41.87421*1E6), (int) (-87.62448*1E6));
        OverlayItem overlayitem03 = new OverlayItem(point03, 
        		"Museum of Contemporary Photography", "600 S. Michigan Ave, Chicago, IL 60605");
        
        GeoPoint point14 = new GeoPoint((int) (41.879585*1E6), (int) (-87.623713*1E6));
        OverlayItem overlayitem14 = new OverlayItem(point14, 
        		"The Art Institute of Chicago", "111 South Michigan Avenue, Chicago, Illinois, 60603");
        
        GeoPoint point16 = new GeoPoint((int) (41.896042*1E6), (int) (-87.684953*1E6));
        OverlayItem overlayitem16 = new OverlayItem(point16, 
        		"Ukrainian Institute of Modern Art", "2320 W Chicago Ave, Chicago, IL 60622");
        
        GeoPoint point17 = new GeoPoint((int) (41.853569*1E6), (int) (-87.62404*1E6));
        OverlayItem overlayitem17 = new OverlayItem(point17, 
        		"Blues Heaven Foundation", "2120 S. Michigan Ave, Chicago, IL 60616");
        
        GeoPoint point20 = new GeoPoint((int) (41.856073*1E6), (int) (-87.67298*1E6));
        OverlayItem overlayitem20 = new OverlayItem(point20, 
        		"National Museum of Mexican Art", "1852 West 19th Street, Chicago, IL 60608");
        
        GeoPoint point27 = new GeoPoint((int) (41.857099*1E6), (int) (-87.625101*1E6));
        OverlayItem overlayitem27 = new OverlayItem(point27, 
        		"National Vietnam Veterans Art Museum", "1801 S. Indiana Ave., Chicago, IL 60616");
        
        GeoPoint point28 = new GeoPoint((int) (41.793736*1E6), (int) (-87.599959*1E6));
        OverlayItem overlayitem28 = new OverlayItem(point28, 
        		"David and Alfred Smart Museum of Art", "5550 S. Greenwood Ave., Chicago, IL 60637");
        
        GeoPoint point29 = new GeoPoint((int) (41.960618*1E6), (int) (-87.673498*1E6));
        OverlayItem overlayitem29 = new OverlayItem(point29, 
        		"Architectural Artifacts, Inc", "4325 N. Ravenswood, Chicago IL 60613");
        
        GeoPoint point31 = new GeoPoint((int) (41.971121*1E6), (int) (-87.777911*1E6));
        OverlayItem overlayitem31 = new OverlayItem(point31, 
        		"Polish Museum of America", "984 N. Milwaukee Avenue, Chicago, IL 60642-4101");
        
        
        
        
        itemizedOverlay.addOverlay(overlayitem01);
        itemizedOverlay.addOverlay(overlayitem03);
        itemizedOverlay.addOverlay(overlayitem14);
        itemizedOverlay.addOverlay(overlayitem16);
        itemizedOverlay.addOverlay(overlayitem17);
        itemizedOverlay.addOverlay(overlayitem20);
        itemizedOverlay.addOverlay(overlayitem27);
        itemizedOverlay.addOverlay(overlayitem28);
        itemizedOverlay.addOverlay(overlayitem29);
        itemizedOverlay.addOverlay(overlayitem31);
        
        
        mapOverlays.add(itemizedOverlay);
        
		MapController mapControl = mapView.getController();
        mapControl.setCenter(itemizedOverlay.getCenter());
        mapControl.zoomToSpan(itemizedOverlay.getLatSpanE6(), itemizedOverlay.getLonSpanE6());
    }
    
    @Override
	public void onResume() {
		super.onResume();
		me.enableMyLocation();
		me.enableCompass();
	}		

	@Override
	public void onPause() {
		super.onPause();
		me.disableMyLocation();
		me.disableCompass();
	}
    

    protected boolean isRouteDisplayed() {
        return false;
    }
	    
	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);
        return true;
    }
	  
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        
        case R.id.Sat_View:
        	mapView.setSatellite(true);
            mapView.setStreetView(true);
            return true;
            
        case R.id.Map_View:
        	mapView.setSatellite(false);
            mapView.setStreetView(false);
            return true;
        
        case R.id.Map_Menu_All:
        	
    		startActivity(new Intent(this, Maps.class));
    		Toast.makeText(this, R.string.toast_all, Toast.LENGTH_LONG).show();
    		this.finish();
    		return true;
    		
    	case R.id.Map_Menu_Free:
    		
    		startActivity(new Intent(this, MapsFree.class));
    		Toast.makeText(this, R.string.toast_free, Toast.LENGTH_LONG).show();
    		this.finish();
    		return true;
    		
    	case R.id.Map_Menu_Art:
    		
    		startActivity(new Intent(this, MapsArt.class));
    		Toast.makeText(this, R.string.toast_art, Toast.LENGTH_LONG).show();
    		this.finish();
    		return true;
    		
    	case R.id.Map_Menu_Mansion:
    		
    		startActivity(new Intent(this, MapsMansion.class));
    		Toast.makeText(this, R.string.toast_mansion, Toast.LENGTH_LONG).show();
    		this.finish();
    		return true;
    		
    	case R.id.Map_Menu_History:
    	
    		startActivity(new Intent(this, MapsHistory.class));
    		Toast.makeText(this, R.string.toast_history, Toast.LENGTH_LONG).show();
    		this.finish();
    		return true;
    		
    	case R.id.Map_Menu_Science:
    	
    		startActivity(new Intent(this, MapsScience.class));
    		Toast.makeText(this, R.string.toast_science, Toast.LENGTH_LONG).show();
    		this.finish();
    		return true;
    		
        default:
            return super.onOptionsItemSelected(item);
        }
    }   
	    
	    
    public class MuseumItemizedOverlay extends ItemizedOverlay {

    	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
    	
    	public MuseumItemizedOverlay(Drawable defaultMarker) {
    		super(boundCenterBottom(defaultMarker));
    		
    		// TODO Auto-generated constructor stub
    	}
    	
    	public void addOverlay(OverlayItem overlay) {
    	    mOverlays.add(overlay);
    	    populate();
    	}

    	@Override
    	protected OverlayItem createItem(int i) {
    		// TODO Auto-generated method stub
    		return mOverlays.get(i);
    	}

    	@Override
    	public int size() {
    		// TODO Auto-generated method stub
    		return mOverlays.size();
    	}
    	
    	@Override
    	protected boolean onTap(int index) {
    	
    	
    	  final OverlayItem item = mOverlays.get(index);
    	  AlertDialog.Builder dialog = new AlertDialog.Builder(MapsArt.this);
    	  dialog.setTitle(item.getTitle());
    	  dialog.setMessage(item.getSnippet());
    	  
    	  dialog.setNegativeButton("Navigate", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?daddr=" + item.getSnippet())));
				
			}
		});
    	  
    	  dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				
			}
		});
    	  dialog.show();
    	 
    	  return true;
    	}

    }
    
    @Override
    public void onConfigurationChanged(Configuration  newConfig) {
      super.onConfigurationChanged(newConfig);
    

    }

}