package com.example.autowing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;
/**
 * ×óÓÒ»¬¶¯demo
 * @author xzw
 *
 */
public class flightmodel extends Activity implements OnGestureListener {
	
	
	private ViewFlipper viewFlipper;
	private GestureDetector detector; //ÊÖÊÆ¼ì²â
	
	Animation leftInAnimation;
	Animation leftOutAnimation;
	Animation rightInAnimation;
	Animation rightOutAnimation;
	Button buttonviewbacked;
	Button buttonviewjixinged;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.viewfilerflight);
       // buttonviewjixinged= (Button) findViewById(R.id.buttonviewjixinga);
        buttonviewbacked= (Button) findViewById(R.id.buttonviewback);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        detector = new GestureDetector(this);
        //»æÖÆ¾ØĞÎ±ß¿ò
        GradientDrawable drawable = new GradientDrawable();  
        drawable.setShape(GradientDrawable.RECTANGLE); // »­¿ò  RECTANGLE 
        drawable.setStroke(1, Color.rgb(144,144,144)); // ±ß¿ò´ÖÏ¸¼°ÑÕÉ«  
        drawable.setCornerRadius(5.0f); // ±ß¿òÔ²½Ç°ë¾¶
        //drawable.setColor(0x00000000); // ±ß¿òÄÚ²¿ÑÕÉ«  
        buttonviewbacked.setBackgroundDrawable(drawable);  
        
        //ÍùviewFlipperÌí¼ÓView
        viewFlipper.addView(getImageView(R.drawable.si_cha_ni));
        viewFlipper.addView(getImageView(R.drawable.si_cha_shun));
        viewFlipper.addView(getImageView(R.drawable.si_shi_ni));
        viewFlipper.addView(getImageView(R.drawable.si_shi_shun));
        viewFlipper.addView(getImageView(R.drawable.liu_cha_ni));
        viewFlipper.addView(getImageView(R.drawable.liu_cha_shun));
        viewFlipper.addView(getImageView(R.drawable.liu_shi_ni));
        viewFlipper.addView(getImageView(R.drawable.liu_shi_shun));
        viewFlipper.addView(getImageView(R.drawable.ba_x_ni));
        viewFlipper.addView(getImageView(R.drawable.ba_x_shun));
        viewFlipper.addView(getImageView(R.drawable.ba_shi_ni));
        viewFlipper.addView(getImageView(R.drawable.ba_shi_shun));
        viewFlipper.addView(getImageView(R.drawable.gong_x_ni));
        viewFlipper.addView(getImageView(R.drawable.gong_x_shun));
        viewFlipper.getDisplayedChild();
        //¶¯»­Ğ§¹û
    	leftInAnimation = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
		leftOutAnimation = AnimationUtils.loadAnimation(this, R.anim.push_left_out);
		rightInAnimation = AnimationUtils.loadAnimation(this, R.anim.push_right_in);
		rightOutAnimation = AnimationUtils.loadAnimation(this, R.anim.push_right_out);
		
		buttonviewbacked.setOnClickListener(new View.OnClickListener() {
	          
	          @Override
	          public void onClick(View v) {
	        	  
	        	  FlightModelActivity.Index_FlightQuad = (short)( viewFlipper.getDisplayedChild() + 1);
	        	  switch(FlightModelActivity.Index_FlightQuad)
	        	  {
	        	  case 1:
	        		  FlightModelActivity.textViewmodelseted.setText("ËÄÖá²æ×ÖË³");
	        		  break;
	        	  case 2:
	        		  FlightModelActivity.textViewmodelseted.setText("ËÄÖá²æ×ÖÄæ");
	        		  break;
	        	  case 3:
	        		  FlightModelActivity.textViewmodelseted.setText("ËÄÖáÊ®×ÖË³");
	        		  break;
	        	  case 4:
	        		  FlightModelActivity.textViewmodelseted.setText("ËÄÖáÊ®×ÖÄæ");
	        		  break;
	        	  case 5:
	        		  FlightModelActivity.textViewmodelseted.setText("ÁùÖá²æ×ÖË³");
	        		  break;
	        	  case 6:
	        		  FlightModelActivity.textViewmodelseted.setText("ÁùÖá²æ×ÖÄæ");
	        		  break;
	        	  case 7:
	        		  FlightModelActivity.textViewmodelseted.setText("ÁùÖáÊ®×ÖË³");
	        		  break;
	        	  case 8:
	        		  FlightModelActivity.textViewmodelseted.setText("ÁùÖáÊ®×ÖÄæ");
	        		  break;
	        	  case 9:
	        		  FlightModelActivity.textViewmodelseted.setText("°ËÖá²æ×ÖË³");
	        		  break;
	        	  case 10:
	        		  FlightModelActivity.textViewmodelseted.setText("°ËÖá²æ×ÖÄæ");
	        		  break;
	        	  case 11:
	        		  FlightModelActivity.textViewmodelseted.setText("°ËÖáÊ®×ÖË³");
	        		  break;
	        	  case 12:
	        		  FlightModelActivity.textViewmodelseted.setText("°ËÖáÊ®×ÖÄæ");
	        		  break;
	        	  case 13:
	        		  FlightModelActivity.textViewmodelseted.setText("¹²Öá²æ×ÖË³");
	        		  break;
	        	  case 14:
	        		  FlightModelActivity.textViewmodelseted.setText("¹²Öá²æ×ÖÄæ");
	        		  break;
	        	  
	        	  }
	        	  flightmodel.this.finish();
	        	  MainActivity.viewPager.setCurrentItem(4,false);
	          }
	      });
		
    }

    private ImageView getImageView(int id){
    	ImageView imageView = new ImageView(this);
    	imageView.setImageResource(id);
    	return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
     
    	return this.detector.onTouchEvent(event); //touchÊÂ¼ş½»¸øÊÖÊÆ´¦Àí¡£
    }
    
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
//		Log.i(TAG, "e1="+e1.getX()+" e2="+e2.getX()+" e1-e2="+(e1.getX()-e2.getX()));
		
	
		
		if((e1.getX()-e2.getX()>50) && (Math.abs(velocityX) > 20) ){
			viewFlipper.setInAnimation(leftInAnimation);
			viewFlipper.setOutAnimation(leftOutAnimation);
		    viewFlipper.showNext();//ÏòÓÒ»¬¶¯
		    switch(viewFlipper.getDisplayedChild())
      	  {
      	  case 0:
      		  buttonviewbacked.setText("   ËÄÖá²æ×ÖË³   ");
      		  break;
      	  case 1:
      		  buttonviewbacked.setText("   ËÄÖá²æ×ÖÄæ   ");
      		  break;
      	  case 2:
      		  buttonviewbacked.setText("   ËÄÖáÊ®×ÖË³   ");
      		  break;
      	  case 3:
      		  buttonviewbacked.setText("   ËÄÖáÊ®×ÖÄæ   ");
      		  break;
      	  case 4:
      		  buttonviewbacked.setText("   ÁùÖá²æ×ÖË³   ");
      		  break;
      	  case 5:
      		  buttonviewbacked.setText("   ÁùÖá²æ×ÖÄæ   ");
      		  break;
      	  case 6:
      		  buttonviewbacked.setText("   ÁùÖáÊ®×ÖË³   ");
      		  break;
      	  case 7:
      		  buttonviewbacked.setText("   ÁùÖáÊ®×ÖÄæ   ");
      		  break;
      	  case 8:
      		  buttonviewbacked.setText("   °ËÖá²æ×ÖË³   ");
      		  break;
      	  case 9:
      		  buttonviewbacked.setText("   °ËÖá²æ×ÖÄæ   ");
      		  break;
      	  case 10:
      		  buttonviewbacked.setText("   °ËÖáÊ®×ÖË³   ");
      		  break;
      	  case 11:
      		  buttonviewbacked.setText("   °ËÖáÊ®×ÖÄæ   ");
      		  break;
      	  case 12:
      		  buttonviewbacked.setText("   ¹²Öá²æ×ÖË³   ");
      		  break;
      	  case 13:
      		  buttonviewbacked.setText("   ¹²Öá²æ×ÖÄæ   ");
      		  break;
      
      		  
      	  }
		    return true;
		}else if((e2.getX()-e1.getX()>50) && ( Math.abs(velocityX) > 20)){
			viewFlipper.setInAnimation(rightInAnimation);
			viewFlipper.setOutAnimation(rightOutAnimation);
			viewFlipper.showPrevious();//Ïò×ó»¬¶¯
			switch(viewFlipper.getDisplayedChild())
      	  {
      	  case 0:
      		  buttonviewbacked.setText("   ËÄÖá²æ×ÖË³   ");
      		  break;
      	  case 1:
      		  buttonviewbacked.setText("   ËÄÖá²æ×ÖÄæ   ");
      		  break;
      	  case 2:
      		  buttonviewbacked.setText("   ËÄÖáÊ®×ÖË³   ");
      		  break;
      	  case 3:
      		  buttonviewbacked.setText("   ËÄÖáÊ®×ÖÄæ   ");
      		  break;
      	  case 4:
      		  buttonviewbacked.setText("   ÁùÖá²æ×ÖË³   ");
      		  break;
      	  case 5:
      		  buttonviewbacked.setText("   ÁùÖá²æ×ÖÄæ   ");
      		  break;
      	  case 6:
      		  buttonviewbacked.setText("   ÁùÖáÊ®×ÖË³   ");
      		  break;
      	  case 7:
      		  buttonviewbacked.setText("   ÁùÖáÊ®×ÖÄæ   ");
      		  break;
      	  case 8:
      		  buttonviewbacked.setText("   °ËÖá²æ×ÖË³   ");
      		  break;
      	  case 9:
      		  buttonviewbacked.setText("   °ËÖá²æ×ÖÄæ   ");
      		  break;
      	  case 10:
      		  buttonviewbacked.setText("   °ËÖáÊ®×ÖË³   ");
      		  break;
      	  case 11:
      		  buttonviewbacked.setText("   °ËÖáÊ®×ÖÄæ   ");
      		  break;
      	  case 12:
      		  buttonviewbacked.setText("   ¹²Öá²æ×ÖË³   ");
      		  break;
      	  case 13:
      		  buttonviewbacked.setText("   ¹²Öá²æ×ÖÄæ   ");
      		  break;
      	  }
			return true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		int i;
		
		i = viewFlipper.getDisplayedChild();
		
	}
	public void onDoubleClick(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
    
    
}
