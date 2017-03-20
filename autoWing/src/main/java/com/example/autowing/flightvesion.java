package com.example.autowing;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.view.SildingFinishLayout;

public  class flightvesion extends Activity {
	
	public static boolean getframetype = false;
	static TextView apvesionhard ;
    static TextView apvesionsoft;
    static TextView apvesionid;
	static TextView imuvesionhard ;
    static TextView imuvesionsoft;
    static TextView imuvesionid;
	static TextView ledvesionhard ;
    static TextView ledvesionsoft;
    static TextView ledvesionid;
	static TextView hubvesionhard ;
    static TextView hubvesionsoft;
    static TextView hubvesionid;
	static TextView gpsvesionhard ;
    static TextView gpsvesionsoft;
    static TextView gpsvesionid;
	static TextView magvesionhard ;
    static TextView magvesionsoft;
    static TextView magvesionid;
    static Button buttonvesionback;

    
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.viewvesion);
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        SildingFinishLayout mSildingFinishLayoutvesion;
	        ScrollView mScrollViewvesion = (ScrollView) findViewById(R.id.scrollViewvesion);
	        apvesionhard =(TextView)findViewById(R.id.textViewaphard);
	        apvesionsoft =(TextView)findViewById(R.id.textViewapsoft);
	        apvesionid =(TextView)findViewById(R.id.textViewapid);
	        imuvesionhard =(TextView)findViewById(R.id.textViewimuhard);
	        imuvesionsoft =(TextView)findViewById(R.id.textViewimusoft);
	        imuvesionid =(TextView)findViewById(R.id.textViewimuid);
	        ledvesionhard =(TextView)findViewById(R.id.textViewledhard);
	        ledvesionsoft =(TextView)findViewById(R.id.textViewledsoft);
	        ledvesionid =(TextView)findViewById(R.id.textViewledid);
	        hubvesionhard =(TextView)findViewById(R.id.textViewhubhard);
	        hubvesionsoft =(TextView)findViewById(R.id.textViewhubsoft);
	        hubvesionid =(TextView)findViewById(R.id.textViewhubid);
	        gpsvesionhard =(TextView)findViewById(R.id.textViewgpshard);
	        gpsvesionsoft =(TextView)findViewById(R.id.textViewgpssoft);
	        gpsvesionid =(TextView)findViewById(R.id.textViewgpsid);
	        magvesionhard =(TextView)findViewById(R.id.textViewmaghard);
	        magvesionsoft =(TextView)findViewById(R.id.textViewmagsoft);
	        magvesionid =(TextView)findViewById(R.id.textViewmagid);
	        buttonvesionback = (Button)findViewById(R.id.buttonvesionback);
	        //setCustomActionBarvesion();  
	        buttonvesionback.setOnClickListener(new View.OnClickListener() {
	          
	            @Override
	            public void onClick(View v) {   
	  				int i= 0;
	  				boolean timer = true;
	  				while(timer){
	  				i++;
	  			    if(i>10){
	  				timer = false;
	  				 getframetype = false;
	                  Intent intent = getIntent();
	                  flightvesion.this.setResult(0, intent);
	                  flightvesion.this.finish();	
	                  overridePendingTransition(0, R.anim.base_slide_right_out);
	  				//overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	  					    }
	  				}
	            }
	        });
	        getframetype = true;
//	        mSildingFinishLayoutvesion = (SildingFinishLayout)findViewById(R.id.sildingFinishLayoutvesion);  
//	        mSildingFinishLayoutvesion.setOnSildingFinishListener(new OnSildingFinishListener() {  
//		                   @Override  
//		                   public void onSildingFinish() { 
//		                	   getframetype = false;
//		                	   flightvesion.this.finish();  
//		                   }  
//		               });  
//	        mSildingFinishLayoutvesion.setTouchView(mScrollViewvesion);
	        }
//	 private void setCustomActionBarvesion() {  
//		    ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);  
//		    View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbarvesion, null);  
//		    ActionBar actionBar = getActionBar();  
//		    actionBar.setCustomView(mActionBarView, lp);  
//		    actionBar.setDisplayShowCustomEnabled(true);  
//		    actionBar.setDisplayShowHomeEnabled(false);  
//		    actionBar.setDisplayShowTitleEnabled(false);  
//		    actionBar.setDisplayHomeAsUpEnabled(true);
//		    actionBar.setHomeButtonEnabled(true);
//		    actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_CUSTOM);  
//		}  
	 public void onBackPressed() {
			super.onBackPressed();
			 getframetype = false;
			 flightvesion.this.finish(); 
			overridePendingTransition(0, R.anim.base_slide_right_out);
		}

}
