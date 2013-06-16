package com.androidsfuture.museumschicago;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



public class Membership extends Activity {
    private ProgressDialog pd;
    private WebView browse;
    public String webURL;
    public String webURLEmpty = "";
    
    	// Need handler for callbacks to the UI thread
	 	final Handler mHandler = new Handler();
	 	
	 	// Create runnable for posting
	 	final Runnable mUpdateResults = new Runnable(){
	 		public void run(){
	 			updateResultsInUi();
	 		}
	 	};
	 	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        browse = new WebView(Membership.this);
	 	setContentView(browse);
	 	
	 	Bundle extras = getIntent().getExtras(); 
	 	if(extras !=null){
	 		webURL = extras.getString("memberLink");
	 		
	 	}
	 	
	 	if (webURL.equals(webURLEmpty)){
	 		Toast.makeText(this, "There is no page to display", Toast.LENGTH_SHORT).show();
	 	}else{
	 		pd = ProgressDialog.show(this, webURL, "Loading page. Please wait....", true, true);	 	
	 	 	startLongRunningOperation();
	 		
	 	}
	 	
	 
}
    public void startLongRunningOperation (){
    	
    	// Fire off a thread to do some work that we shouldn't do directly in the UI thread
    	Thread t = new Thread(){
    		public void run(){
    			doSomethingExpensive();
    			mHandler.post(mUpdateResults);
    		}
    	};
    	t.start();
    }
    
    private void updateResultsInUi(){
    	
    	// Back in the UI thread -- update our UI elements based on the data in mResults
    	
    	//pd.dismiss();
    	
    }
    
    private void doSomethingExpensive(){
    	browse.setWebViewClient(new MyWebViewClient());
    	browse.getSettings().setJavaScriptEnabled(true);
    	browse.getSettings().setBuiltInZoomControls(true);
    	browse.loadUrl(webURL);
    	
    }
    
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        
        public void onPageFinished(WebView view, String url){
        	pd.dismiss();
        	
        }
    }
    
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  // We do nothing here. We're only handling this to keep orientation
	  // or keyboard hiding from causing the WebView activity to restart.
	}
}
