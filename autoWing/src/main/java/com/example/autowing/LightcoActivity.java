package com.example.autowing;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.example.view.SildingFinishLayout;
public class LightcoActivity extends Activity implements OnCheckedChangeListener  {
   
	public Button  buttonbacklight;//����
	static SeekBar SeekBarlight = null;
	private Handler hangler=new Handler();
	private SwitchButton mLigfrontSwitch;
	private SwitchButton mLigturnaroundSwitch;
	
	private SwitchButton mLigBackSwitch;
	  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_ledset); 
	  
	  SildingFinishLayout mSildingFinishLayoutlight;
//	  ActionBar mActionBar=getActionBar();
//	  mActionBar.setHomeButtonEnabled(true);
//	  mActionBar.setDisplayHomeAsUpEnabled(true);
//	  mActionBar.setDisplayShowHomeEnabled(false);  
//	  mActionBar.setDisplayShowCustomEnabled(true);
//	  mActionBar.setDisplayShowTitleEnabled(true); 
//	  mActionBar.setTitle("�ƹ�����");
//	  mActionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
	  
	  
	  //======setCustomActionBarlight();
	  //�����Զ���ķ��ؼ�ͼ��  
	  SeekBarlight = (SeekBar) findViewById(R.id.seekbarscrlight);
	 

	  SeekBarlight.setMax(255);
      try {
    	  SeekBarlight.setProgress(Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS));
	  } catch (SettingNotFoundException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	  }
      SeekBarlight.setOnSeekBarChangeListener(onSeekbar);
	  //=========================���ص�������==========================//
//	  buttonbacklight.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//                Intent intent = getIntent();
//                LightcoActivity.this.setResult(0, intent);
//                LightcoActivity.this.finish();
//                overridePendingTransition(0, R.anim.base_slide_right_out);	
//          }
//      }); 
	  initUI();
//	  mSildingFinishLayoutlight = (SildingFinishLayout)findViewById(R.id.sildingFinishLayoutlight);  
//	  mSildingFinishLayoutlight.setOnSildingFinishListener(new OnSildingFinishListener() {  
//                   @Override  
//                   public void onSildingFinish() {  
//                	   lightcontrol.this.finish();  
//                   }  
//               });  
//	  mSildingFinishLayoutlight.setTouchView(mSildingFinishLayoutlight);
	  mLigturnaroundSwitch.setChecked(false);
	  
	  mLigfrontSwitch.setChecked(false);
	 
	  mLigBackSwitch.setChecked(false);
  }
//	  private void setCustomActionBarlight() {  
//		    ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);  
//		    View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbarlight, null);  
//		    ActionBar actionBar = getActionBar();  
//		    actionBar.setCustomView(mActionBarView, lp);  
//		    actionBar.setDisplayShowCustomEnabled(true);  
//		    actionBar.setDisplayShowHomeEnabled(false);  
//		    actionBar.setDisplayShowTitleEnabled(false);  
//		    actionBar.setDisplayHomeAsUpEnabled(true);
//		    actionBar.setHomeButtonEnabled(true);
//		    actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_CUSTOM);  
//		}  
	  private void initUI() {
		  	mLigfrontSwitch = (SwitchButton)findViewById(R.id.light_front_switch);
		  	mLigturnaroundSwitch = (SwitchButton)findViewById(R.id.light_turnaround_switch);
		  	mLigBackSwitch = (SwitchButton)findViewById(R.id.light_back_switch);
		  	mLigfrontSwitch.setOnCheckedChangeListener(this);
			mLigturnaroundSwitch.setOnCheckedChangeListener(this);
			mLigBackSwitch.setOnCheckedChangeListener(this);
		}
	  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.light_front_switch:
				if (isChecked) {
//					MainActivity.bigledcontrolflagtwo = 1;
//					MainActivity.Screenflagchange = true;
					Toast.makeText(this, "ǰ�ӵƴ�", Toast.LENGTH_SHORT).show();
				}else {
//					MainActivity.bigledcontrolflagtwo = 0;
//					MainActivity.Screenflagchange = true;
					Toast.makeText(this, "ǰ�ӵƹر�", Toast.LENGTH_SHORT).show();
					
				}
				break;
			case R.id.light_turnaround_switch:
				if (isChecked) {
//					MainActivity.bigledcontrolflagone = 1;
//					MainActivity.Screenflagchange = true;
					Toast.makeText(this, "ת��ƴ�", Toast.LENGTH_SHORT).show();
					
				}else {
//					MainActivity.bigledcontrolflagone = 0;
//					MainActivity.Screenflagchange = true;
					Toast.makeText(this, "ת��ƹر�", Toast.LENGTH_SHORT).show();
					
				}			
				break;
			case R.id.light_back_switch:
				if (isChecked) {
//					MainActivity.bigledcontrolflagthree = 1;
//					MainActivity.Screenflagchange = true;
					Toast.makeText(this, "���ӵƴ�", Toast.LENGTH_SHORT).show();
				}else {
//					MainActivity.bigledcontrolflagthree = 0;
//					MainActivity.Screenflagchange = true;
					Toast.makeText(this, "���ӵƹر�", Toast.LENGTH_SHORT).show();
					
				}
				break;
			default:
				break;
			}
			
		}
	  public boolean onOptionsItemSelected(MenuItem item)
	  {
	      // TODO Auto-generated method stub
	      if(item.getItemId() == android.R.id.home)
	      {
	    	
	          finish();
	          overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	          return true;
	      }
	      return super.onOptionsItemSelected(item);
	  }
  OnSeekBarChangeListener  onSeekbar=new OnSeekBarChangeListener() {
		
		@Override
		//���α��ƶ�ֹͣ��ʱ����ø÷���
		public void onStopTrackingTouch(SeekBar seekBar) {
		//���ñ��Ϊ��Ҫˢ��
			//flag=true;
	   //����ʱ�Ϳ�ʼ�Զ����¸��϶���
			//refresh();
		}
		@Override
		//���α꿪ʼ�ƶ�ʱ���ø÷���
		public void onStartTrackingTouch(SeekBar seekBar) {
		//ֹͣˢ��
		//	flag=false;
			
		}
		
		@Override
		//���������α걻�ı���߽������ı�ʱ���ø÷���
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
//			MainActivity.Screenflagchange = true;
//			MainActivity.tmpInt = SeekBarlight.getProgress() ;
//			if(MainActivity.Screenflagchange)
//			{
//				
//	 			//������С��40ʱ�����ó�40����ֹ̫�ڿ������ĺ����
//			    if (MainActivity.tmpInt < 40) {
//			    	MainActivity.tmpInt = 40;
//	 			}
//	 			if ((0 <= MainActivity.tmpInt) && (MainActivity.tmpInt < 85)) {
//	 				MainActivity.ledcontrolflag = 0;
//	 			}
//	 			if ((85 <= MainActivity.tmpInt) && (MainActivity.tmpInt < 170)) {
//	 				MainActivity.ledcontrolflag = 1;
//	 			}
//	 			if ((170 <= MainActivity.tmpInt) && (MainActivity.tmpInt <= 255)) {
//	 				MainActivity.ledcontrolflag = 2;
//	 			}
//			}
	 			//���ݵ�ǰ���ȸı�����

		}
		
	};
	private void refresh() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//�����Ȳ���1000���͸���status
				while( SeekBarlight.getProgress()<100)
				{
//					try {
//						//��ͣ1��
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
					//��һ��Runnable������ӵ���Ϣ���е��У�
					//���ҵ�ִ�е��ö���ʱִ��run()����
					hangler.post(new Runnable() {
						
						@Override
						public void run() {
							// �������ý�������ǰ��ֵ
							//SeekBar.setProgress(SeekBar.getProgress()+1);
							
						}
					});
				}
			}
		}).start();
     }
	public void onBackPressed() {  
	    super.onBackPressed(); 
	    LightcoActivity.this.finish();  
	    overridePendingTransition(0, R.anim.base_slide_right_out);  
	} 

}
