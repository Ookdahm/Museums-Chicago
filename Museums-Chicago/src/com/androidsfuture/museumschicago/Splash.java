package com.androidsfuture.museumschicago;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.LayoutAnimationController;

public class Splash extends Activity {
	
	final Handler mHandler = new Handler();
	public LayoutAnimationController lc;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        mHandler.postDelayed(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				try {
					
					
				} catch (Exception e){
					
				}finally{
					finish();
					
					startActivity(new Intent (Splash.this, Main.class));
					
				}
			} 
        	
	}, 1000);
	}
	
}