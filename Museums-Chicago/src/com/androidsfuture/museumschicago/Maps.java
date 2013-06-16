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

public class Maps<PointLocation> extends MapActivity {
    /** Called when the activity is first created. */
	List<Overlay> mapOverlays;
	Drawable drawable;
	MuseumItemizedOverlay itemizedOverlay;
	Context mContext;
	MyLocationOverlay me=null;
	MapView mapView;
	private static final float SQ2 = 1.414213562373095f;
	
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
        
        GeoPoint point02 = new GeoPoint((int) (41.891432*1E6), (int) (-87.608097*1E6));
        OverlayItem overlayitem02 = new OverlayItem(point02, 
        		"Chicago Childrens Museum", "700 East Grand Avenue, Chicago, IL 60611");
        
        GeoPoint point03 = new GeoPoint((int) (41.87421*1E6), (int) (-87.62448*1E6));
        OverlayItem overlayitem03 = new OverlayItem(point03, 
        		"Museum of Contemporary Photography", "600 S. Michigan Ave, Chicago, IL 60605");
        
        GeoPoint point04 = new GeoPoint((int) (41.894373*1E6), (int) (-87.63278*1E6));
        OverlayItem overlayitem04 = new OverlayItem(point04, 
        		"Museum of Broadcast Communication", "676 North LaSalle St., Suite 424, Chicago, IL 60654");
        
        GeoPoint point05 = new GeoPoint((int) (41.857718*1E6), (int) (-87.621212*1E6));
        OverlayItem overlayitem05 = new OverlayItem(point05, 
        		"Glessner House Museum", "1800 S. Prairie Avenue, Chicago, IL 60616");
        
        GeoPoint point06 = new GeoPoint((int) (41.899699*1E6), (int) (-87.660985*1E6));
        OverlayItem overlayitem06 = new OverlayItem(point06, 
        		"Polish Museum of America", "984 N. Milwaukee Avenue, Chicago, IL 60642");
        
        /*
        GeoPoint point07 = new GeoPoint((int) (38.927241*1E6), (int) (-77.0148641E6));
        OverlayItem overlayitem07 = new OverlayItem(point07, 
        		"Old Lighthouse Museum", "1 Washington Park Marina, Michigan City, IN 46360");
        */
        
        GeoPoint point08 = new GeoPoint((int) (41.894861*1E6), (int) (-87.683628*1E6));
        OverlayItem overlayitem08 = new OverlayItem(point08, 
        		"Ukrainian National Museum", "2249 W. Superior St, Chicago, Illinois 60612");
        
        GeoPoint point09 = new GeoPoint((int) (41.879197*1E6), (int) (-87.647274*1E6));
        OverlayItem overlayitem09 = new OverlayItem(point09, 
        		"Hellenic Museum & Cultural Center", "801 W. Adams Street, 4th Floor, Chicago, IL,60607");
        
        GeoPoint point10 = new GeoPoint((int) (41.851289*1E6), (int) (-87.633484*1E6));
        OverlayItem overlayitem10 = new OverlayItem(point10, 
        		"Chinese-American Museum of Chicago", "238 West 23rd Street, Chicago, IL 60616");
        
        GeoPoint point11 = new GeoPoint((int) (41.705374*1E6), (int) (-87.603077*1E6));
        OverlayItem overlayitem11 = new OverlayItem(point11, 
        		"A. Philip Randolph Pullman Porter Museum", "10406 S. Maryland Ave, Chicago, IL 60628");
        
        GeoPoint point12 = new GeoPoint((int) (41.792446*1E6), (int) (-87.58035*1E6));
        OverlayItem overlayitem12 = new OverlayItem(point12, 
        		"Museum of Science & Industry", "57th Street and Lake Shore Drive, Chicago, IL 60637");
        
        GeoPoint point13 = new GeoPoint((int) (41.874043*1E6), (int) (-87.624346*1E6));
        OverlayItem overlayitem13 = new OverlayItem(point13, 
        		"Spertus Institute of Jewish Studies Museum", "610 S. Michigan Avenue, Chicago, IL 60605");
        
        GeoPoint point14 = new GeoPoint((int) (41.879585*1E6), (int) (-87.623713*1E6));
        OverlayItem overlayitem14 = new OverlayItem(point14, 
        		"The Art Institute of Chicago", "111 South Michigan Avenue, Chicago, Illinois, 60603");
        
        GeoPoint point15 = new GeoPoint((int) (41.865198*1E6), (int) (-87.616985*1E6));
        OverlayItem overlayitem15 = new OverlayItem(point15, 
        		"The Field Museum", "1400 S. Lake Shore Dr, Chicago, IL 60605");
        
        GeoPoint point16 = new GeoPoint((int) (41.896042*1E6), (int) (-87.684953*1E6));
        OverlayItem overlayitem16 = new OverlayItem(point16, 
        		"Ukrainian Institute of Modern Art", "2320 W Chicago Ave, Chicago, IL 60622");
        
        GeoPoint point17 = new GeoPoint((int) (41.853569*1E6), (int) (-87.62404*1E6));
        OverlayItem overlayitem17 = new OverlayItem(point17, 
        		"Blues Heaven Foundation", "2120 S. Michigan Ave, Chicago, IL 60616");
        
        GeoPoint point18 = new GeoPoint((int) (41.979618*1E6), (int) (-87.669797*1E6));
        OverlayItem overlayitem18 = new OverlayItem(point18, 
        		"Edgewater Historical Society", "5358 N. Ashland Ave, Chicago, IL 60640");
        
        GeoPoint point19 = new GeoPoint((int) (41.86632*1E6), (int) (-87.613821*1E6));
        OverlayItem overlayitem19 = new OverlayItem(point19, 
        		"Shedd Aquarium", "1200 S. Lake Shore Drive, Chicago, IL 60605");
        
        GeoPoint point20 = new GeoPoint((int) (41.856073*1E6), (int) (-87.67298*1E6));
        OverlayItem overlayitem20 = new OverlayItem(point20, 
        		"National Museum of Mexican Art", "1852 West 19th Street, Chicago, IL 60608");
        
        GeoPoint point21 = new GeoPoint((int) (41.866142*1E6), (int) (-87.619594*1E6));
        OverlayItem overlayitem21 = new OverlayItem(point21, 
        		"Adler Planetarium", "1300 South Lake Shore Drive, Chicago, IL 60605");
        
        GeoPoint point22 = new GeoPoint((int) (41.791589*1E6), (int) (-87.60715*1E6));
        OverlayItem overlayitem22 = new OverlayItem(point22, 
        		"DuSable Museum of African American History", "740 East 56th Place, Chicago, IL 60637");
        
        GeoPoint point23 = new GeoPoint((int) (41.926805*1E6), (int) (-87.635138*1E6));
        OverlayItem overlayitem23 = new OverlayItem(point23, 
        		"The Notebaert Nature Museum", "2430 N. Cannon Dr., Chicago, IL 60614");
        
        GeoPoint point24 = new GeoPoint((int) (41.789217*1E6), (int) (-87.597792*1E6));
        OverlayItem overlayitem24 = new OverlayItem(point24, 
        		"Oriental Institute Museum", "1155 East 58th Street, Chicago, IL 60637");
        
        GeoPoint point25 = new GeoPoint((int) (41.976669*1E6), (int) (-87.668301*1E6));
        OverlayItem overlayitem25 = new OverlayItem(point25, 
        		"Swedish American Museum Center", "5211 N. Clark St., Chicago, IL 60640");
        
        GeoPoint point26 = new GeoPoint((int) (41.910356*1E6), (int) (-87.626217*1E6));
        OverlayItem overlayitem26 = new OverlayItem(point26, 
        		"International Museum of Surgical Science", "1524 N. Lake Shore Dr., Chicago, IL 60610");
        
        GeoPoint point27 = new GeoPoint((int) (41.857099*1E6), (int) (-87.625101*1E6));
        OverlayItem overlayitem27 = new OverlayItem(point27, 
        		"National Vietnam Veterans Art Museum", "1801 S. Indiana Ave., Chicago, IL 60616");
        
        GeoPoint point28 = new GeoPoint((int) (41.793736*1E6), (int) (-87.599959*1E6));
        OverlayItem overlayitem28 = new OverlayItem(point28, 
        		"David and Alfred Smart Museum of Art", "5550 S. Greenwood Ave., Chicago, IL 60637");
        
        GeoPoint point29 = new GeoPoint((int) (41.960618*1E6), (int) (-87.673498*1E6));
        OverlayItem overlayitem29 = new OverlayItem(point29, 
        		"Architectural Artifacts, Inc", "4325 N. Ravenswood, Chicago IL 60613");
        
        GeoPoint point30 = new GeoPoint((int) (41.871942*1E6), (int) (-87.647144*1E6));
        OverlayItem overlayitem30 = new OverlayItem(point30, 
        		"Jane Addams' Hull-House Museum", "800 S. Halsted (M/C 051), Chicago, IL 60607");
        
        GeoPoint point31 = new GeoPoint((int) (41.971121*1E6), (int) (-87.777911*1E6));
        OverlayItem overlayitem31 = new OverlayItem(point31, 
        		"Polish Museum of America", "984 N. Milwaukee Avenue, Chicago, IL 60642-4101");
        
        
        
        itemizedOverlay.addOverlay(overlayitem01);
        itemizedOverlay.addOverlay(overlayitem02);
        itemizedOverlay.addOverlay(overlayitem03);
        itemizedOverlay.addOverlay(overlayitem04);
        itemizedOverlay.addOverlay(overlayitem05);
        itemizedOverlay.addOverlay(overlayitem06);
       // itemizedOverlay.addOverlay(overlayitem07);
        itemizedOverlay.addOverlay(overlayitem08);
        itemizedOverlay.addOverlay(overlayitem09);
        itemizedOverlay.addOverlay(overlayitem10);
        itemizedOverlay.addOverlay(overlayitem11);
        itemizedOverlay.addOverlay(overlayitem12);
        itemizedOverlay.addOverlay(overlayitem13);
        itemizedOverlay.addOverlay(overlayitem14);
        itemizedOverlay.addOverlay(overlayitem15);
        itemizedOverlay.addOverlay(overlayitem16);
        itemizedOverlay.addOverlay(overlayitem17);
        itemizedOverlay.addOverlay(overlayitem18);
        itemizedOverlay.addOverlay(overlayitem19);
        itemizedOverlay.addOverlay(overlayitem20);
        itemizedOverlay.addOverlay(overlayitem21);
        itemizedOverlay.addOverlay(overlayitem22);
        itemizedOverlay.addOverlay(overlayitem23);
        itemizedOverlay.addOverlay(overlayitem24);
        itemizedOverlay.addOverlay(overlayitem25);
        itemizedOverlay.addOverlay(overlayitem26);
        itemizedOverlay.addOverlay(overlayitem27);
        itemizedOverlay.addOverlay(overlayitem28);
        itemizedOverlay.addOverlay(overlayitem29);
        itemizedOverlay.addOverlay(overlayitem30);
        itemizedOverlay.addOverlay(overlayitem31);
        
        mapOverlays.add(itemizedOverlay);
        
        MapController mapControl = mapView.getController();
        mapControl.setCenter(itemizedOverlay.getCenter());
        mapControl.zoomToSpan(itemizedOverlay.getLatSpanE6(), itemizedOverlay.getLonSpanE6());
        //mapControl.setZoom(21);
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
    		
    	case R.id.Map_Menu_Me:
    		
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
    	  AlertDialog.Builder dialog = new AlertDialog.Builder(Maps.this);
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