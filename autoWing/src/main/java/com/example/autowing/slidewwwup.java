package com.example.autowing;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author android_wbin
 *
 */
public class slidewwwup extends Fragment{
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        return inflater.inflate(R.layout.slidewwwup, container, false);    
	    } 
	 public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	        Button button = (Button) getActivity().findViewById(R.id.linear_clearwaybtn);  
	        button.setOnClickListener(new OnClickListener() {  
	        	@Override  
	        	public void onClick(View v) {  
	                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();  
	              
	            	        	}  
	        	
	        	});  
	    }  
	  public void onDestroy() {
	  		super.onDestroy();
	  		//¹Ø±Õ¶¨Î»Í¼²ã
	  		
	  	}
		public void onResume() {
	    	super.onResume(); 
	    
		}
}
	

	
