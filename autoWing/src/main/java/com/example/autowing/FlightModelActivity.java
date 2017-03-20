package com.example.autowing;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.view.SildingFinishLayout;
/**
 * 机型设置
 * @author android_wbin
 *
 */
public class FlightModelActivity extends Activity{
	    static short Index_FlightQuad;//几旋翼
		static short Index_YKQType;//遥控器类型
		static short Index_BatteryType;//电池类型
		static short Index_huizhong;//遥控器回中
		static short Index_daisu;//怠速设置
		static TextView textViewmodelseted;//机型选择显示
		static TextView textViewykqseted; //遥控器选择显示
		static TextView textViewvolseted; //电池选择显示
		int DataSelect_Quade;
		private NumberSeekBar pb;
		
		public static int mjixing_type = 0;
		public static int myaokongqi_type = 0;
		public static int mdaisuzhi = 0;
		public static int mdianci_type = 0;
		public static int mhuizhong = 0;
		Spinner spinnerhuizhong;
		Button  buttonbackmodel;
	    Button  buttonindexset;
	    Button  buttonmodelselected;
	    Button  buttonmodelindexget;
	    Button  buttondianchi;
	    Button  buttonykqselected;
	    Button  VesionType;
	    public static ImageView animationmodel;
		protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_flightmodel);
		  //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		  //setCustomActionBarmodel(); 

		  //声明spinner对象  
		  SildingFinishLayout mSildingFinishLayoutmodel;
          textViewykqseted = (TextView) findViewById(R.id.textViewykqset);
          textViewvolseted = (TextView) findViewById(R.id.textViewdianchiset); 
          textViewmodelseted = (TextView) findViewById(R.id.textViewmodelset);
		
		  buttonindexset= (Button) findViewById(R.id.buttonindexset);
		  buttonmodelselected = (Button) findViewById(R.id.buttonmodelselect);
		  buttonmodelindexget = (Button) findViewById(R.id.buttongetjx_btn);
		  buttonykqselected = (Button) findViewById(R.id.buttonykqselect);
		  buttondianchi = (Button) findViewById(R.id.buttondianchi);
		  VesionType = (Button) findViewById(R.id.vesiontype);
		  spinnerhuizhong=(Spinner) findViewById(R.id.spinnerhuizhong);
		  pb = (NumberSeekBar)findViewById(R.id.bar0);
		 // animationmodel = (ImageView) findViewById(R.id.animationmodel);
		 // animationmodel.setVisibility(View.INVISIBLE);
		  init();
	      start();
		     int i = 0;
		      while(i<5)
		      {
		    	  i++;
		    	 // buttonmodelindexget.callOnClick();
		      }
				
		  final String arrjixing[]=new String[]{  
	                "四旋翼叉字顺时针",  
	                "四旋翼叉字逆时针",  
	                "四旋翼十字顺时针",  
	                "四旋翼十字逆时针",
	                
	                "六旋翼V字顺时针",  
	                "六旋翼V字逆时针",  
	                "六旋翼一字顺时针",  
	                "六旋翼一字逆时针", 
	                
	                "八旋翼叉字顺时针",  
	                "八旋翼叉字逆时针",  
	                "八旋翼十字顺时针",  
	                "八旋翼十字逆时针",
	                
	                "共轴叉字顺时针",  
	                "共轴叉字逆时针"  
	        };  
		  final String arrdianchi[]=new String[]{  
				  "2S",
				  "3S",
	              "4S",  
	              "6S" 
	        };  
		  final String arryaokongqi[]=new String[]{  
	                "14SG", 
	                "其他" 
	        }; 
		  final String arrhuizhong[]=new String[]{  
	                "是", 
	                "否" 
	              
	        }; 
		  //==============================遥控回中=====================================//
		  ArrayAdapter<String> arrayAdapterhuizhong = new ArrayAdapter<String>(this, R.layout.spinner_item, arrhuizhong);  
		  //设置样式
		 // arrayAdapterhuizhong.setDropDownViewResource(R.layout.dropdown_stytle);
	      //加载适配器
		  
		  spinnerhuizhong.setAdapter(arrayAdapterhuizhong);
		  
		  spinnerhuizhong.setSelection(0,true);
		 //spinner设置监听器  
		   
//		  buttonbackmodel.setOnClickListener(new View.OnClickListener() {
//	          
//	          @Override
//	          public void onClick(View v) {
//	                Intent intent = getIntent();
////	                modeltype.this.setResult(0, intent);
////	                modeltype.this.finish();
//	                overridePendingTransition(0, R.anim.base_slide_right_out);
//	               
//	          }
//	      });
		  //============================机型设置==============================//
		  buttonmodelselected.setOnClickListener(new View.OnClickListener() {
	          
	          @Override
	          public void onClick(View v) {

	        		Intent wneIntent = new Intent(FlightModelActivity.this, flightmodel.class);	
					startActivity(wneIntent);
					overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);		
	          }
	      });
		  //============================遥控器设置==============================//
		  buttonykqselected.setOnClickListener(new View.OnClickListener() {
	          
	          @Override
	          public void onClick(View v) {

	        		Intent yneIntent = new Intent(FlightModelActivity.this, flightykqset.class);	
					startActivity(yneIntent);
					overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);		
	          }
	      });
		  //============================电池设置==============================//
		  buttondianchi.setOnClickListener(new View.OnClickListener() {
	          
	          @Override
	          public void onClick(View v) {

	        		Intent wneIntent = new Intent(FlightModelActivity.this, flightdcset.class);	
					startActivityForResult(wneIntent, 3);
					overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);		
	          }
	      });
		  //============================版本信息==============================//
		  VesionType.setOnClickListener(new View.OnClickListener() {
	          
	          @Override
	          public void onClick(View v) {

	        		Intent wneIntent = new Intent(FlightModelActivity.this, flightvesion.class);	
					startActivityForResult(wneIntent, 3);
					overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);		
	          }
	      });  
		  spinnerhuizhong.setOnItemSelectedListener(new OnItemSelectedListener() {
			    @Override
			    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			    	@SuppressWarnings("unused")
					String cardNumber = arrhuizhong[arg2];
			    	switch(arg2)
			    	{
			    	case 0:
			    		Index_huizhong = 0;//遥控器回中
			    		break;
			    	case 1:
			    		Index_huizhong = 1;//遥控器不回中
			    		break;
			    	}
			    }
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO 自动生成的方法存根
				}
			});
		  buttonindexset.setOnClickListener(new View.OnClickListener() {     
	          @Override
	          public void onClick(View v) {
	        	  if(true){//MainActivity.isConnecting&&MainActivity.isDataConnecting
	        		  //animationmodel.setVisibility(View.VISIBLE);
	        		  DataSelect_Quade =  (((short)Index_FlightQuad & 0x00ff)<<8)  
	        			  |(((short)Index_huizhong& 0x0001)<<7) 
	        			  | (((short)Index_BatteryType& 0x0003)<<1)  
	        			  | (((short)Index_YKQType) & 0x0001);//数组要进行打包
	        	  
	        	  SendQudeSet(DataSelect_Quade,Index_daisu);
	        	  //MainActivity.flightmodelflag = true;
	        	  }else if(true){
	        		  
	        		  showMessagemodel("请确保通信设备连接正常!!!");
	        	  }else{
	        		  showMessagemodel("请确保数据连接正常!!!");
	        	  }
	          }
	      });
//		  buttonmodelindexget.setOnClickListener(new View.OnClickListener() {     
//	          @Override
//	          public void onClick(View v) {
//	        	if(true){
//	        		try {
//	        		  if(MainActivity.FrameTypename!=null && MainActivity.daisuzhi!=null){
//	      				mdaisuzhi = (Integer.parseInt(MainActivity.daisuzhi));
//	      				mjixing_type = (Integer.parseInt(MainActivity.FrameTypename)>>8) & 0x000f;
//	      				myaokongqi_type =(Integer.parseInt(MainActivity.FrameTypename)) & 0x0001;
//	      				mdianci_type = (Integer.parseInt(MainActivity.FrameTypename)>>1) & 0x0003;
//	      				mhuizhong =(Integer.parseInt(MainActivity.FrameTypename)>>7) & 0x0001;
//	      				pb.setProgress(mdaisuzhi*4);
//	      				spinnerhuizhong.setSelection(mhuizhong,true);
//	      				Index_huizhong =(short)mhuizhong;
//	      				switch(mdianci_type){
//	      				case 0:
//	      					textViewvolseted.setText("6S");
//	      					Index_BatteryType = 0;
//	      					break;
//	      				case 1:
//	      					textViewvolseted.setText("12S");
//	      					Index_BatteryType = 1;
//	      					break;
//	      				case 2:
//	      					textViewvolseted.setText("4S");
//	      					Index_BatteryType = 2;
//	      					break;
//	      				case 3:
//	      					textViewvolseted.setText("3S");
//	      					Index_BatteryType = 3;
//	      					break;
//	      				}
//	      				switch(myaokongqi_type){
//	      				case 0:
//	      					textViewykqseted.setText("14SG");
//	      					Index_YKQType = 0;
//	      					break;
//	      				case 1:
//	      					textViewykqseted.setText("其他");
//	      					Index_YKQType = 1;
//	      					break;
//	      		
//	      				
//	      				}
//	      				switch(mjixing_type){
//	      				
//	      				case 1:
//	      					textViewmodelseted.setText("四轴叉字顺");
//	      					Index_FlightQuad = 1;
//	      					break;
//	      				case 2:
//	      					textViewmodelseted.setText("四轴叉字逆");
//	      					Index_FlightQuad = 2;
//	      					break;
//	      				case 3:
//	      					textViewmodelseted.setText("四轴十字顺");
//	      					Index_FlightQuad = 3;
//	      					break;
//	      				case 4:
//	      					textViewmodelseted.setText("四轴十字逆");
//	      					Index_FlightQuad = 4;
//	      					break;
//	      				case 5:
//	      					textViewmodelseted.setText("六轴叉字顺");
//	      					Index_FlightQuad = 5;
//	      					break;
//	      				case 6:
//	      					textViewmodelseted.setText("六轴叉字逆");
//	      					Index_FlightQuad = 6;
//	      					break;
//	      				case 7:
//	      					textViewmodelseted.setText("六轴十字顺");
//	      					Index_FlightQuad = 7;
//	      					break;
//	      				case 8:
//	      					textViewmodelseted.setText("六轴十字逆");
//	      					Index_FlightQuad = 8;
//	      					break;
//	      				case 9:
//	      					textViewmodelseted.setText("八轴叉字顺");
//	      					Index_FlightQuad = 9;
//	      					break;
//	      				case 10:
//	      					textViewmodelseted.setText("八轴叉字逆");
//	      					Index_FlightQuad = 10;
//	      					break;
//	      				case 11:
//	      					textViewmodelseted.setText("八轴十字顺");
//	      					Index_FlightQuad = 11;
//	      					break;
//	      				case 12:
//	      					textViewmodelseted.setText("八轴十字逆");
//	      					Index_FlightQuad = 12;
//	      					break;
//	      				case 13:
//	      					textViewmodelseted.setText("共轴叉字顺");
//	      					Index_FlightQuad = 13;
//	      					break;
//	      				case 14:
//	      					textViewmodelseted.setText("共轴叉字逆");
//	      					Index_FlightQuad = 14;
//	      					break;
//	      					}
//	      				}
//	        		}catch (Exception e) {	
//	        			System.out.println("...");
//	  				}
//	        	 } else if(MainActivity.isDataConnecting == false||MainActivity.isConnecting==false){
//	        		  
//	        		  showMessagemodel("请确保通信设备连接正常!!!");
//	        	  }
//	          }
//	      });
//		  if(MainActivity.FrameTypename!=null && MainActivity.daisuzhi!=null){
//				mdaisuzhi = (Integer.parseInt(MainActivity.daisuzhi));
//				mjixing_type = (Integer.parseInt(MainActivity.FrameTypename)>>8) & 0x000f;
//				myaokongqi_type =(Integer.parseInt(MainActivity.FrameTypename)) & 0x0001;
//				mdianci_type = (Integer.parseInt(MainActivity.FrameTypename)>>1) & 0x0003;
//				mhuizhong =(Integer.parseInt(MainActivity.FrameTypename)>>7) & 0x0001;
//				pb.setProgress(mdaisuzhi*4);
//				spinnerhuizhong.setSelection(mhuizhong,true);
//				Index_huizhong =(short)mhuizhong;
//				switch(mdianci_type){
//				case 0:
//					textViewvolseted.setText("6S");
//					Index_BatteryType = 0;
//					break;
//				case 1:
//					textViewvolseted.setText("12S");
//					Index_BatteryType = 1;
//					break;
//				case 2:
//					textViewvolseted.setText("4S");
//					Index_BatteryType = 2;
//					break;
//				case 3:
//					textViewvolseted.setText("3S");
//					Index_BatteryType = 3;
//					break;
//				}
//				switch(myaokongqi_type){
//				case 0:
//					textViewykqseted.setText("14SG");
//					Index_YKQType = 0;
//					break;
//				case 1:
//					textViewykqseted.setText("其他");
//					Index_YKQType = 1;
//					break;
//			
//				
//				}
//				switch(mjixing_type){
//  				
//  				case 1:
//  					textViewmodelseted.setText("四轴叉字顺");
//  					Index_FlightQuad = 1;
//  					break;
//  				case 2:
//  					textViewmodelseted.setText("四轴叉字逆");
//  					Index_FlightQuad = 2;
//  					break;
//  				case 3:
//  					textViewmodelseted.setText("四轴十字顺");
//  					Index_FlightQuad = 3;
//  					break;
//  				case 4:
//  					textViewmodelseted.setText("四轴十字逆");
//  					Index_FlightQuad = 4;
//  					break;
//  				case 5:
//  					textViewmodelseted.setText("六轴叉字顺");
//  					Index_FlightQuad = 5;
//  					break;
//  				case 6:
//  					textViewmodelseted.setText("六轴叉字逆");
//  					Index_FlightQuad = 6;
//  					break;
//  				case 7:
//  					textViewmodelseted.setText("六轴十字顺");
//  					Index_FlightQuad = 7;
//  					break;
//  				case 8:
//  					textViewmodelseted.setText("六轴十字逆");
//  					Index_FlightQuad = 8;
//  					break;
//  				case 9:
//  					textViewmodelseted.setText("八轴叉字顺");
//  					Index_FlightQuad = 9;
//  					break;
//  				case 10:
//  					textViewmodelseted.setText("八轴叉字逆");
//  					Index_FlightQuad = 10;
//  					break;
//  				case 11:
//  					textViewmodelseted.setText("八轴十字顺");
//  					Index_FlightQuad = 11;
//  					break;
//  				case 12:
//  					textViewmodelseted.setText("八轴十字逆");
//  					Index_FlightQuad = 12;
//  					break;
//  				case 13:
//  					textViewmodelseted.setText("共轴叉字顺");
//  					Index_FlightQuad = 13;
//  					break;
//  				case 14:
//  					textViewmodelseted.setText("共轴叉字逆");
//  					Index_FlightQuad = 14;
//  					break;
//  					}
//				}
	  }
		 private void setCustomActionBarmodel() {  
			    ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);  
			    View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbarmodel, null);  
			    ActionBar actionBar = getActionBar();  
			    actionBar.setCustomView(mActionBarView, lp);  
			    actionBar.setDisplayShowCustomEnabled(true);  
			    actionBar.setDisplayShowHomeEnabled(false);  
			    actionBar.setDisplayShowTitleEnabled(false);  
			    actionBar.setDisplayHomeAsUpEnabled(true);
			   // actionBar.setHomeButtonEnabled(true);
			    actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_CUSTOM);  
			}  
	  void SendQudeSet(int DataSelect_QUADE,int Indexdaisu)//添加一个怠速值   以参数的形式5--25   遥控器是否回中 回中：0 ，不会中 ：1
		{
			int sTemp;
			sTemp=DataSelect_QUADE;
			byte[] TxData = new byte[10];
			TxData[0] = (byte)0x3A;
			TxData[1] = (byte)0xA3;
			TxData[2] = (byte)0x0A;
			TxData[3] = (byte)0x0C;
			TxData[4] = (byte)((sTemp&0xFF00)>>8);
			TxData[5]=  (byte)(sTemp&0x00FF);
			sTemp = Indexdaisu;
			TxData[6] = (byte)(sTemp&0x00FF);
			TxData[7] = (byte)0x00;
			CalcBcc(TxData,7);
			//MainActivity.flightmodel = TxData.clone();
		}
	  public static void CalcBcc(byte[] TxData, int nByte)
	  { // 计算校验和
	  	 short sBcc = 0; 	
	  	for(int i=2;i<=nByte;i++) 
	    {
	  		 if(TxData[i] < 0)
	  		 {
	  			TxData[i] = (byte)(TxData[i] + 256);
	  			sBcc += (TxData[i] & 0x00FF);
	  		 }
	  		 else
	  		 sBcc += (TxData[i] & 0x00FF);
	  	}
	  	TxData[nByte+1] = (byte)((sBcc >> 8) & 0x00FF);
	  	TxData[nByte+2] = (byte)(sBcc & 0x00FF);	  		
	  	return;
	  }
	  private void init() {
	        pb.setTextSize(20);// 璁剧疆瀛椾綋澶у皬
	        pb.setTextColor(Color.WHITE);// 棰滆壊
	        pb.setMyPadding(10, 10, 10, 10);// 璁剧疆padding 璋冪敤setpadding浼氭棤鏁�
	        pb.setImagePadding(0, 1);// 鍙互涓嶈缃�
	        pb.setTextPadding(0, 0);// 鍙互涓嶈缃�
	
	    }
	    
	    private void start() {
	        TimerTask tt = new TimerTask() {
	            public void run() {
	             
	            }
	        };
	        Timer timer = new Timer();
	        timer.schedule(tt, 1000, 2000);
	    }
	    private void showMessagemodel(String msg) {
	        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

	    } 
	    public void onBackPressed() {  
	        super.onBackPressed();  
	        //modeltype.this.finish(); 
	        overridePendingTransition(0, R.anim.base_slide_right_out);  
	    } 
	}