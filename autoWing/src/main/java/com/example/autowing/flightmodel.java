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
 * ���һ���demo
 * @author xzw
 *
 */
public class flightmodel extends Activity implements OnGestureListener {
	
	
	private ViewFlipper viewFlipper;
	private GestureDetector detector; //���Ƽ��
	
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
        //���ƾ��α߿�
        GradientDrawable drawable = new GradientDrawable();  
        drawable.setShape(GradientDrawable.RECTANGLE); // ����  RECTANGLE 
        drawable.setStroke(1, Color.rgb(144,144,144)); // �߿��ϸ����ɫ  
        drawable.setCornerRadius(5.0f); // �߿�Բ�ǰ뾶
        //drawable.setColor(0x00000000); // �߿��ڲ���ɫ  
        buttonviewbacked.setBackgroundDrawable(drawable);  
        
        //��viewFlipper���View
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
        //����Ч��
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
	        		  FlightModelActivity.textViewmodelseted.setText("�������˳");
	        		  break;
	        	  case 2:
	        		  FlightModelActivity.textViewmodelseted.setText("���������");
	        		  break;
	        	  case 3:
	        		  FlightModelActivity.textViewmodelseted.setText("����ʮ��˳");
	        		  break;
	        	  case 4:
	        		  FlightModelActivity.textViewmodelseted.setText("����ʮ����");
	        		  break;
	        	  case 5:
	        		  FlightModelActivity.textViewmodelseted.setText("�������˳");
	        		  break;
	        	  case 6:
	        		  FlightModelActivity.textViewmodelseted.setText("���������");
	        		  break;
	        	  case 7:
	        		  FlightModelActivity.textViewmodelseted.setText("����ʮ��˳");
	        		  break;
	        	  case 8:
	        		  FlightModelActivity.textViewmodelseted.setText("����ʮ����");
	        		  break;
	        	  case 9:
	        		  FlightModelActivity.textViewmodelseted.setText("�������˳");
	        		  break;
	        	  case 10:
	        		  FlightModelActivity.textViewmodelseted.setText("���������");
	        		  break;
	        	  case 11:
	        		  FlightModelActivity.textViewmodelseted.setText("����ʮ��˳");
	        		  break;
	        	  case 12:
	        		  FlightModelActivity.textViewmodelseted.setText("����ʮ����");
	        		  break;
	        	  case 13:
	        		  FlightModelActivity.textViewmodelseted.setText("�������˳");
	        		  break;
	        	  case 14:
	        		  FlightModelActivity.textViewmodelseted.setText("���������");
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
     
    	return this.detector.onTouchEvent(event); //touch�¼��������ƴ���
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
		    viewFlipper.showNext();//���һ���
		    switch(viewFlipper.getDisplayedChild())
      	  {
      	  case 0:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 1:
      		  buttonviewbacked.setText("   ���������   ");
      		  break;
      	  case 2:
      		  buttonviewbacked.setText("   ����ʮ��˳   ");
      		  break;
      	  case 3:
      		  buttonviewbacked.setText("   ����ʮ����   ");
      		  break;
      	  case 4:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 5:
      		  buttonviewbacked.setText("   ���������   ");
      		  break;
      	  case 6:
      		  buttonviewbacked.setText("   ����ʮ��˳   ");
      		  break;
      	  case 7:
      		  buttonviewbacked.setText("   ����ʮ����   ");
      		  break;
      	  case 8:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 9:
      		  buttonviewbacked.setText("   ���������   ");
      		  break;
      	  case 10:
      		  buttonviewbacked.setText("   ����ʮ��˳   ");
      		  break;
      	  case 11:
      		  buttonviewbacked.setText("   ����ʮ����   ");
      		  break;
      	  case 12:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 13:
      		  buttonviewbacked.setText("   ���������   ");
      		  break;
      
      		  
      	  }
		    return true;
		}else if((e2.getX()-e1.getX()>50) && ( Math.abs(velocityX) > 20)){
			viewFlipper.setInAnimation(rightInAnimation);
			viewFlipper.setOutAnimation(rightOutAnimation);
			viewFlipper.showPrevious();//���󻬶�
			switch(viewFlipper.getDisplayedChild())
      	  {
      	  case 0:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 1:
      		  buttonviewbacked.setText("   ���������   ");
      		  break;
      	  case 2:
      		  buttonviewbacked.setText("   ����ʮ��˳   ");
      		  break;
      	  case 3:
      		  buttonviewbacked.setText("   ����ʮ����   ");
      		  break;
      	  case 4:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 5:
      		  buttonviewbacked.setText("   ���������   ");
      		  break;
      	  case 6:
      		  buttonviewbacked.setText("   ����ʮ��˳   ");
      		  break;
      	  case 7:
      		  buttonviewbacked.setText("   ����ʮ����   ");
      		  break;
      	  case 8:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 9:
      		  buttonviewbacked.setText("   ���������   ");
      		  break;
      	  case 10:
      		  buttonviewbacked.setText("   ����ʮ��˳   ");
      		  break;
      	  case 11:
      		  buttonviewbacked.setText("   ����ʮ����   ");
      		  break;
      	  case 12:
      		  buttonviewbacked.setText("   �������˳   ");
      		  break;
      	  case 13:
      		  buttonviewbacked.setText("   ���������   ");
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
