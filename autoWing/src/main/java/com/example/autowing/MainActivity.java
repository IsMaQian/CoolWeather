package com.example.autowing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.example.EventBus.Accelerated_EventBus;
import com.example.EventBus.GPS_EventBus;
import com.example.EventBus.Geomagnetism_EventBus;
import com.example.EventBus.IMU_EventBus;
import com.example.EventBus.Motor_EventBus;
import com.example.EventBus.PID_Eventbus;
import com.example.EventBus.Remote_EventBus;
import com.example.EventBus.Speed_EventBus;
import com.example.EventBus.StartClose_EventBus;
import com.example.EventBus.Status_EventBus;
import com.example.EventBus.WayPoint_EventBus;
import com.example.EventBus.XBEE_EventBus;
import com.example.library.NavigationTabBar;
import com.example.offline.OfflineMapActivity;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity implements LocationSource{
	private Animation showAction, hideAction;


	MapView mMapView = null;//定义一个地图的图层
	boolean isFirstLoc = true;// 是否首次定位  
	private AMap aMap;//定义一个地图对象
	StringBuffer buffer = new StringBuffer();
	LinearLayout layoutpoint;
	private Button button_choosepoint;
	private Button button_clearpoint;
	private Button button_saveproject;
	private Button button_chooseproject;
	private Button button_waypointplan;
	private LatLng Pointcurrent;
	private LatLng Pointcurrent_drag;
	String state;
    double[] Lng = new double[500];
	double[] Lat = new double[500];
	 //点击屏幕次数
    int ScreenClick =-1;
    public int title_name_count = -1;//第几个点
    MarkerOptions markerOptions;
    Marker pointmarker;
    //以前的定位点  
    LatLng LatLngpre = null;//路点发生变化的时候
    LatLng latlngpre;//设置路点的时候
    //现在的点
    LatLng LatLngnow;//路点发生变化的时候
    LatLng latlngnow;//设置路点的时候
    Polyline polyline;
    boolean ispointsetenable;//是否可以拖动
    boolean ispointset;//是否可以点击marker
    String waypointfile="";
    String FILENAME="";//保存路点数据的文件名
    Time time;
    int year ; 
	int month ; 
	int day ; 
	int minute ; 
	int hour; 
	int sec ; 
	String district;
	String streetNum;
	String street;
	static String cityName;
	static String province;
	//定位及图层按钮
	Button btn_location,btn_mappattern;
	private PopupMenu mappatMenu;
	boolean ischangemapChecked;
	
//===================================生成航迹==================================================//
	double User_Distance = 0.0;
	double Field_Distance_onetotwo = 0.0;
	double Field_Distance_zerotothree = 0.0;
   	double Field_Distance_twotothree = 0.0;
   	double[] AY_lat = new double[800];
   	double[] AY_lon = new double[800];
   	double[] AYmarker_lat = new double[800];
   	double[] AYmarker_lon = new double[800];
    double[] make_road_lat = new double[800];
    double[] make_road_lon = new double[800];
	double[] make_crosspoint1_lat = new double[800];
	double[] make_crosspoint1_lon = new double[800];
	double[] make_crosspoint2_lat = new double[800];
	double[] make_crosspoint2_lon = new double[800];
	double[] make_crosspoint3_lat = new double[800];
    double[] make_crosspoint3_lon = new double[800];
    double[] make_crosspoint4_lat = new double[800];
    double[] make_crosspoint4_lon = new double[800];	 
    EditText ed_radius; 
    private Button Btn_connect;
    int All_Field_Point = 0;//所有的路点个数
    double Absolute_Height = 0.0;//设置绝对高度初始为0.0
//===================================生成航迹==================================================//	 
    double[] WayPoint_Lat = new double[800];
    double[] WayPoint_Lon = new double[800];
    int Which_Data_Bag = 1;
    int[] Waypoint_TakePic_Flag = new int[800];
    int PlanSend_Flag = 0;
    int Which_Road_Point = 0;
    int WayPoint_NumS = 0;//载入路点的总个数
    int First_Send_Flag = 0; 
    int AutoWing_RoadPoint_Flag = 0;// 路点发送标志位
    double AltitudeSend_Value = 0;
    double YawSend_Value = 0;
	//声明AMapLocationClient类对象  
    public AMapLocationClient mLocationClient = null;  
    /** 
     * 声明定位回调监听器 
     */  
    public AMapLocationListener mLocationListener = new AMapLocationListener() {  
        @Override  
        public void onLocationChanged(AMapLocation amapLocation) {  
            if (amapLocation != null) {  
                if (amapLocation.getErrorCode() == 0) {  
                    //定位成功回调信息，设置相关消息  
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表  
                    amapLocation.getLatitude();//获取纬度  
                    amapLocation.getLongitude();//获取经度  
                    amapLocation.getAccuracy();//获取精度信息  
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    Date date = new Date(amapLocation.getTime());  
                    df.format(date);//定位时间  
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。  
                    amapLocation.getCountry();//国家信息  
                    amapLocation.getProvince();//省信息  
                    amapLocation.getCity();//城市信息  
                    amapLocation.getDistrict();//城区信息  
                    amapLocation.getStreet();//街道信息  
                    amapLocation.getStreetNum();//街道门牌号信息  
                    amapLocation.getCityCode();//城市编码  
                    amapLocation.getAdCode();//地区编码  
                    amapLocation.getAoiName();//获取当前定位点的AOI信息  
                    lat = amapLocation.getLatitude();  
                    lon = amapLocation.getLongitude();  
                    Log.v("pcw", "lat : " + lat + " lon : " + lon);  
                    Log.v("pcw", "Country : " + amapLocation.getCountry() + " province : " + amapLocation.getProvince() + " City : " + amapLocation.getCity() + " District : " + amapLocation.getDistrict());  
  
	                cityName=amapLocation.getCity();
	                province=amapLocation.getProvince();
	                district=amapLocation.getDistrict();
	                street=amapLocation.getStreet();
	                streetNum=amapLocation.getStreetNum();
                    // 设置当前地图显示为当前位置  
                    // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                    if (isFirstLoc) {
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 19));  
                    MarkerOptions markerOptions = new MarkerOptions();  
                    markerOptions.position(new LatLng(lat, lon));  
                    //markerOptions.title("当前位置");  
                    markerOptions.visible(true);  
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location_marker));  
                    markerOptions.icon(bitmapDescriptor);  
                    //aMap.clear();
                    aMap.addMarker(markerOptions);
                    isFirstLoc = false;
                    // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                        //获取定位信息
                        buffer.append(amapLocation.getCountry() + ""
                                + amapLocation.getProvince() + ""
                                + amapLocation.getCity() + ""
                                + amapLocation.getProvince() + ""
                                + amapLocation.getDistrict() + ""
                                + amapLocation.getStreet() + ""
                                + amapLocation.getStreetNum());
                        Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                        buffer.delete(0, buffer.length());
                    }else{

                        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 19));  
                        MarkerOptions markerOptions = new MarkerOptions();  
                        markerOptions.position(new LatLng(lat, lon));  
                        lat = lat + 1;
                        lon = lon + 1;
                        //markerOptions.title("当前位置");  
                        markerOptions.visible(true);  
                        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location_marker));  
                        markerOptions.icon(bitmapDescriptor);  
                        //aMap.clear();
                        aMap.addMarker(markerOptions);  
                        isFirstLoc = false;
                        // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                            //获取定位信息
                            buffer.append(amapLocation.getCountry() + ""
                                    + amapLocation.getProvince() + ""
                                    + amapLocation.getCity() + ""
                                    + amapLocation.getProvince() + ""
                                    + amapLocation.getDistrict() + ""
                                    + amapLocation.getStreet() + ""
                                    + amapLocation.getStreetNum());
                            Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                            buffer.delete(0, buffer.length());
                        
                    }
                } else {  
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。  
                    Log.e("AmapError", "location Error, ErrCode:"  
                            + amapLocation.getErrorCode() + ", errInfo:"  
                            + amapLocation.getErrorInfo());  
                    
                    Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                    
                }  
            }  
        }  
    }; 

    //声明mLocationOption对象  
    public AMapLocationClientOption mLocationOption = null;  
    private double lat;  
    private double lon;  
	LinearLayout rightMenu;//右侧菜单
	LinearLayout title_linearlayout;
	
	
	private Button WayBtn;
	//侧面菜单
	static ViewPager viewPager;
	static NavigationTabBar ntbSample;
	
	private View conentView;
	private PopupWindow popupWindow;
	private LinearLayout rightLayout;
	private DrawerLayout drawerLayout;
	LocalActivityManager manager = null;
	
	OnLocationChangedListener mListener;
	 SettingActivity  settingactivity =new SettingActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        findViewById();
        initWindow();//状态栏
        //注册EventBus事件
        EventBus.getDefault().register(this,"IMU_EventBus",IMU_EventBus.class);
        EventBus.getDefault().register(this,"Accelerated_Event",Accelerated_EventBus.class);
        EventBus.getDefault().register(this,"Geomagnetism_EventBus",Geomagnetism_EventBus.class);
        EventBus.getDefault().register(this,"Remote_EventBus",Remote_EventBus.class);
        EventBus.getDefault().register(this,"StartClose_EventBus",StartClose_EventBus.class);
        EventBus.getDefault().register(this,"WayPoint_EventBus",WayPoint_EventBus.class);
        EventBus.getDefault().register(this,"Motor_EventBus",Motor_EventBus.class);
        EventBus.getDefault().register(this,"Speed_EventBus",Speed_EventBus.class);
        EventBus.getDefault().register(this,"GPS_EventBus",GPS_EventBus.class);
		EventBus.getDefault().register(this, "Status_EventBus",Status_EventBus.class);
		EventBus.getDefault().register(this, "XBEE_EventBus",XBEE_EventBus.class);
		EventBus.getDefault().register(this, "PID_Eventbus", PID_Eventbus.class);
		// 获取手机当前时间
		time = new Time();
		time.setToNow();
		year = time.year;
		month = time.month;
		day = time.monthDay;
		minute = time.minute;
		hour = time.hour;
		sec = time.second;
		ispointsetenable = false;
		ispointset = false;
        // 初始化隐藏路点设置
        layoutpoint = (LinearLayout)findViewById(R.id.Linear_setpoint);
        layoutpoint.setVisibility(View.GONE);
        
        manager = new LocalActivityManager(MainActivity.this , true);
        manager.dispatchCreate(savedInstanceState);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.amapView);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，
        //实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        //初始化定位  
        mLocationClient = new AMapLocationClient(getApplicationContext());  
        //设置定位回调监听  
        mLocationClient.setLocationListener(mLocationListener);  
        //初始化地图变量
		if (aMap == null) {
			aMap = mMapView.getMap();
			aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 设置普通地图模式
			// aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式
			// 初始化定位参数
			mLocationOption = new AMapLocationClientOption();
			// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
			mLocationOption
					.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
			// 设置是否返回地址信息（默认返回地址信息）
			mLocationOption.setNeedAddress(true);
			// 设置是否只定位一次,默认为false
			mLocationOption.setOnceLocation(true);
			// 设置是否强制刷新WIFI，默认为强制刷新
			mLocationOption.setWifiActiveScan(true);
			// 设置是否允许模拟位置,默认为false，不允许模拟位置
			mLocationOption.setMockEnable(false);
			// 设置定位间隔,单位毫秒,默认为2000ms
			mLocationOption.setInterval(2000);
			// 给定位客户端对象设置定位参数
			mLocationClient.setLocationOption(mLocationOption);
			// 启动定位
			mLocationClient.startLocation();
		}
        ischangemapChecked = false;
        aMap.setOnMapClickListener(new OnMapClickListener() {	
			@Override
		public void onMapClick(LatLng point) {
				// TODO Auto-generated method stub
				if(ispointset){
				Pointcurrent=point;
				ScreenClick++;
				updateMapState();
				}
			}
		  });	
     // 这里是TranslateAnimation动画
        showAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
         // 这里是ScaleAnimation动画
        //showAction = new ScaleAnimation(
        //    1.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        showAction.setDuration(500);

         // 这里是TranslateAnimation动画        
        hideAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
         // 这里是ScaleAnimation动画
        //hideAction = new ScaleAnimation(
        //        1.0f, 1.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        hideAction.setDuration(500);

    }
  //状态栏
    private void initWindow(){
	      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
	    	//透明导航栏
	    	getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	      	getWindow().getDecorView().setSystemUiVisibility(10); 
	      }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void findViewById(){
    	Btn_connect = (Button)findViewById(R.id.btn_connect);
    	Btn_connect.setOnClickListener(connectlistener);
    	ed_radius= (EditText) findViewById(R.id.ed_radius);
    	
    	rightMenu = (LinearLayout)findViewById(R.id.llyout_menu);
    	rightMenu.setOnClickListener(menuListener);
    	
    	title_linearlayout = (LinearLayout)findViewById(R.id.title_linearlayout);
    	//title_linearlayout.setOnClickListener(menuListener);
    	
    	btn_location = (Button)findViewById(R.id.btn_location);
    	btn_location.setOnClickListener(locationListener);
    	
    	btn_mappattern = (Button)findViewById(R.id.btn_mappattern);
    	btn_mappattern.setOnClickListener(mappatternListener);
    	
    	WayBtn = (Button)findViewById(R.id.btn_wasMenu);
    	WayBtn.setOnClickListener(wasMenuListener);

    	button_choosepoint = (Button)findViewById(R.id.choose_waypoint);
    	button_choosepoint.setOnClickListener(choosewaypoint);
    	
    	button_clearpoint = (Button)findViewById(R.id.clear_waypoint);
    	button_clearpoint.setOnClickListener(clearwaypoint);
    	
    	button_saveproject = (Button)findViewById(R.id.save_project);
    	button_saveproject.setOnClickListener(savepointproject);
    	
    	button_chooseproject = (Button)findViewById(R.id.choose_project);
    	button_chooseproject.setOnClickListener(Sendwaypoint);
    	
     	button_waypointplan = (Button)findViewById(R.id.waypoint_planset);
     	button_waypointplan.setOnClickListener(waypointplanset);
    	
    	drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);	
    	
    	
    }
	private String strRoll;//滚转角
	private String strPitch;//俯仰角
	private String strYaw;//航向角
	private String strRollRate;//陀螺仪Rollrate
	private String strPichRate;//陀螺仪Pitchrate
	private String strYawRate;//陀螺仪Yawrate
	 /**
     * 接到EventBus数据
     * @param event
     * IMU数据
     */
    public void IMU_EventBus(IMU_EventBus IMUevent) {
//		String msg = "收到了消息：" + event.getMsg();
//		Log.d("harvic", msg);
//		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		
		strRoll = String.valueOf(IMUevent.getIRoll());
		strPitch = String.valueOf(IMUevent.getIPitch());
		strYaw = String.valueOf(IMUevent.getIYaw());
		strRollRate = String.valueOf(IMUevent.getIRollRate());
		strPichRate = String.valueOf(IMUevent.getIPitchRate());
		strYawRate = String.valueOf(IMUevent.getIYawRate());
	}
    
    private String strAccX;
	private String strAccY;
	private String strAccZ;
    /**
     * 加速度数据
     * @param event
     */
    public void Accelerated_Event(Accelerated_EventBus Accevent) {
    	strAccX = String.valueOf(Accevent.geX());
    	strAccY = String.valueOf(Accevent.geY());
    	strAccZ = String.valueOf(Accevent.geZ());
    }
    
    private String strGeoX;
	private String strGeoY;
	private String strGeoZ;
    /**
     * 地磁数据
     * @param event
     */
    public void Geomagnetism_EventBus(Geomagnetism_EventBus Geoevent) {
    	strGeoX = String.valueOf(Geoevent.getX());
    	strGeoY = String.valueOf(Geoevent.getY());
    	strGeoZ = String.valueOf(Geoevent.getZ());
    }
    
    private String strReRoll;
	private String strRePitch;
	private String strReYaw;	
	private String strReThrottle;
    /**
     * 遥控器数据
     * @param event
     */
    public void Remote_EventBus(Remote_EventBus Remoteevent) {
    	strReRoll = String.valueOf(Remoteevent.getRoll());
    	strRePitch = String.valueOf(Remoteevent.getPitch());
    	strReYaw = String.valueOf(Remoteevent.getYaw());
    	strReThrottle = String.valueOf(Remoteevent.getThrottle());
    }
    
    private String strSW1;
	private String strSW2;
	private String strSW3;
	private String strSW4;
    /**
     * 开关数据
     * @param event
     */
    public void StartClose_EventBus(StartClose_EventBus Startevent) {
    	strSW1 = String.valueOf(Startevent.getSW1());
    	strSW2 = String.valueOf(Startevent.getSW2());
    	strSW3 = String.valueOf(Startevent.getSW3());
    	strSW4 = String.valueOf(Startevent.getSW4());
    }
    
    private String strWayID;
	private String strWayCount;
	private String strWayIndex;
    /**
     * 路点数据
     * @param event
     */
    public void WayPoint_EventBus(WayPoint_EventBus Wayevent) {
    	strWayID = String.valueOf(Wayevent.getWayID());
    	strWayCount = String.valueOf(Wayevent.getWayCount());
    	strWayIndex = String.valueOf(Wayevent.getWayIndex());
    }
    
    private String strMotor_Front;
	private String strMotor_Back;
	private String strMotor_Left;
	private String strMotor_Right;
	private String strMotor_X;
	private String strMotor_Y;
    /**
     * 电机数据
     * @param event
     */
    public void Motor_EventBus(Motor_EventBus Motorevent) {
    	strMotor_Front = String.valueOf(Motorevent.getMotor_Front());
    	strMotor_Back = String.valueOf(Motorevent.getMotor_Back());
    	strMotor_Left = String.valueOf(Motorevent.getMotor_Left());
    	strMotor_Right = String.valueOf(Motorevent.getMotor_Right());
    	strMotor_X = String.valueOf(Motorevent.getMotor_X());
    	strMotor_Y = String.valueOf(Motorevent.getMotor_Y());
    }
    
    private String strLateral;
	private String strLongitudinal;
	private String strUpdown;
    /**
     * 速度数据
     * @param event
     */
    public void Speed_EventBus(Speed_EventBus Speedevent) {
    	strLateral = String.valueOf(Speedevent.getLateral());
    	strLongitudinal = String.valueOf(Speedevent.getLongitudinal());
    	strUpdown = String.valueOf(Speedevent.getUpdown());
    }
    
    private String strLatitude;
	private String strLongitude;
	private String strStarCount;
	private String strHight;
	private String strBaraAlt;
	private String strGpsDop;
	private String strHeading ;
    /**
     * GPS数据
     * @param event
     */
    public void GPS_EventBus(GPS_EventBus GPSevent) {
    	strLatitude = String.valueOf(GPSevent.getLatitude());
    	strLongitude = String.valueOf(GPSevent.getLongitude());
    	strStarCount = String.valueOf(GPSevent.getStarCount());
    	strHight = String.valueOf(GPSevent.getHight());
    	strBaraAlt = String.valueOf(GPSevent.getBaraAlt());
    	strGpsDop = String.valueOf(GPSevent.getGpsDop());
    	strHeading = String.valueOf(GPSevent.getHeading());
    }
    
    private String strFlyStatus;
	private String strVoltage;
	private String strSendFlag;
	private String strSensorStatus;
	private String strCommStatus;
	private String strPhotoFlag;
    /**
     * 状态数据
     * @param event
     */
    public void Status_EventBus(Status_EventBus Statusevent) {
    	strFlyStatus = String.valueOf(Statusevent.getFlyStatus());
    	strVoltage = String.valueOf(Statusevent.getVoltage());
    	//strSendFlag = String.valueOf(Statusevent.getSendFlag());
    	strSensorStatus = String.valueOf(Statusevent.getSensorStatus());
    	//strCommStatus = String.valueOf(Statusevent.getCommStatus());
    	//strPhotoFlag = String.valueOf(Statusevent.getPhotoFlag());
    	}
    
    private String strXBEE;
    /**
     * XBEE数据
     * @param event
     */
    public void XBEE_EventBus(XBEE_EventBus XBEEevent) {
    	strXBEE = String.valueOf(XBEEevent.getXBEE());
    	}
	/**PID数据*/
    private String strGyro_RP_KP;
    private String strGyro_RP_KI;
    private String strGyro_RP_KD;
    private String strGyro_Y_KP;
    private String strGyro_Y_KI;
    private String strGyro_Y_KD;
    private String strProp_RP_KP;
    private String strProp_RP_KI;
    private String strProp_Y_KP;
    private String strProp_Y_KI;
    /**
     * PID数据
     * @param event
     */
    public void PID_Eventbus(PID_Eventbus PIDEevent) {
    	strGyro_RP_KP = String.valueOf(PIDEevent.getGyro_RP_KP());
    	strGyro_RP_KI = String.valueOf(PIDEevent.getGyro_RP_KI());
    	strGyro_RP_KD = String.valueOf(PIDEevent.getGyro_RP_KD());
    	strGyro_Y_KP = String.valueOf(PIDEevent.getGyro_Y_KP());
    	strGyro_Y_KI = String.valueOf(PIDEevent.getGyro_Y_KI());
    	strGyro_Y_KD = String.valueOf(PIDEevent.getGyro_Y_KD());
    	strProp_RP_KP = String.valueOf(PIDEevent.getProp_RP_KP());
    	strProp_RP_KI = String.valueOf(PIDEevent.getProp_RP_KI());
    	strProp_Y_KP = String.valueOf(PIDEevent.getProp_Y_KP());
    	strProp_Y_KI = String.valueOf(PIDEevent.getProp_Y_KI());
    	}
    
    /**
	 * 飞控参数事件
	 */
	private OnClickListener feikongListener = new OnClickListener() {
		 @Override
		public void onClick(View v) {
//			 FeikongMenu = new PopupMenu(MainActivity.this, feikongBtn);
//			 FeikongMenu.getMenuInflater()
//			.inflate(R.menu.file_menu, FeikongMenu.getMenu());
//	        
//			 FeikongMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//				public boolean onMenuItemClick(MenuItem item) {
//					return false;
//				}
//			});
//			 FeikongMenu.show()
			 
			 drawerLayout.openDrawer(Gravity.RIGHT);//调出侧边间菜单抽屉
			 initNavigationTabBar();//加载Tab页面
			 ntbSample.setViewPager(viewPager, 0);
		}
		
	 };

	
	/**
	 * 设备参数事件
	 */
	private OnClickListener equipListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
//			EquipMenu = new PopupMenu(MainActivity.this, equipBtn);
//			EquipMenu.getMenuInflater()
//			.inflate(R.menu.file_menu, EquipMenu.getMenu());
//			
//			EquipMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//				@Override
//				public boolean onMenuItemClick(MenuItem item) {
//					return false;
//				}
//			});
//			EquipMenu.show();
//			 AlertDialog builder=new AlertDialog.Builder(MainActivity.this)
//			 .setMessage(strRoll+"\n"+strPitch+"\n"+strYaw+"\n"
//					 +strRollRate+"\n"+strPichRate+"\n"+strYawRate)
//			 .show();  
		}
		
	 };

	 
	/**
	 * 飞行模式
	 */
	private OnClickListener flightListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//显示飞机飞行模式
			drawerLayout.openDrawer(Gravity.RIGHT);//调出侧边间菜单抽屉
			initNavigationTabBar();//加载Tab页面
			ntbSample.setViewPager(viewPager, 1);
			
		}
		
	 };

	 
	/**
	 * 电池显示
	 */
	private OnClickListener batteryListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//显示飞机电池信息
			drawerLayout.openDrawer(Gravity.RIGHT);//调出侧边间菜单抽屉
			initNavigationTabBar();//加载Tab页面
			ntbSample.setViewPager(viewPager, 2);
		}
		
	 };
	 
	/**
	 * 卫星显示
	 */
	private OnClickListener startsListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//显示卫星个数
			drawerLayout.openDrawer(Gravity.RIGHT);//调出侧边间菜单抽屉
			initNavigationTabBar();//加载Tab页面
			ntbSample.setViewPager(viewPager, 3);
		}
		
	 };
	 /**
	  	 * 手动定位
	  	 */
	  	private OnClickListener locationListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				 //初始化定位  
		        mLocationClient = new AMapLocationClient(getApplicationContext());  
		        //设置定位回调监听  
		        mLocationClient.setLocationListener(mLocationListener);  
		        //初始化地图变量
				if (aMap != null) {
					aMap = mMapView.getMap();
					aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 设置普通地图模式
					// aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式
					// 初始化定位参数
					mLocationOption = new AMapLocationClientOption();
					// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
					mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
					// 设置是否返回地址信息（默认返回地址信息）
					mLocationOption.setNeedAddress(true);
					// 设置是否只定位一次,默认为false
					mLocationOption.setOnceLocation(true);
					// 设置是否强制刷新WIFI，默认为强制刷新
					mLocationOption.setWifiActiveScan(true);
					// 设置是否允许模拟位置,默认为false，不允许模拟位置
					mLocationOption.setMockEnable(false);
					// 设置定位间隔,单位毫秒,默认为2000ms
					mLocationOption.setInterval(2000);
					// 给定位客户端对象设置定位参数
					mLocationClient.setLocationOption(mLocationOption);
					// 启动定位
					mLocationClient.startLocation();
				}
			}
	  		
	  	};
	  	
	  	/**
	  	 * 切换地图图层
	  	 */
	  	private OnClickListener mappatternListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
					if(ischangemapChecked)
					{
						// 设置使用普通地图
						ischangemapChecked = false;
						aMap.setMapType(AMap.MAP_TYPE_NORMAL);
					}
					else
					{
						// 设置使用卫星地图
						ischangemapChecked = true;
						aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
					}
			}	
	  	};
	  	/**
		 * 路点设置及半径
		 */
		private OnClickListener wasMenuListener = new OnClickListener() {
			@Override
			public void onClick(View view) {
			if(layoutpoint.getVisibility()==View.VISIBLE){
				layoutpoint.setVisibility(View.GONE);
				ispointset = false;

//				title_linearlayout.setVisibility(view.VISIBLE);
//				Animation animation = new TranslateAnimation(0, 0, -400, 0);// 平移动画
//				animation.setFillAfter(true);// 动画终止时停留在最后一帧，不然会回到没有执行前的状态
//				animation.setDuration(100);// 动画持续时间0.2秒
//				title_linearlayout.startAnimation(animation);// 是用ImageView来显示动画的
//				title_linearlayout.setVisibility(view.VISIBLE);
				 title_linearlayout.startAnimation(showAction);
				 title_linearlayout.setVisibility(View.VISIBLE);
				
			
			 }else{
				 ispointset = true;	
				 layoutpoint.setVisibility(View.VISIBLE);
//	            	Animation animation = new TranslateAnimation(0,0,0,-400);//平移动画
//	            	animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
//	            	animation.setDuration(100);//动画持续时间0.2秒
//	            	title_linearlayout.startAnimation(animation);//是用ImageView来显示动画的
//	            	title_linearlayout.setVisibility(view.GONE);
					title_linearlayout.startAnimation(hideAction);
					title_linearlayout.setVisibility(View.GONE);
	   			
			 }
			}
		};
		
		 /**
		 * 选择路点
		 */ 
		private OnClickListener choosewaypoint = new OnClickListener() {  
            
            @Override  
            public void onClick(View v) {  
            	
            	Toast.makeText(MainActivity.this, "选择路点",  
	                    Toast.LENGTH_SHORT).show(); 
            	
//				aMap.setOnMapClickListener(new OnMapClickListener() {
//					
//					@Override
//					public void onMapClick(LatLng point) {
//						// TODO Auto-generated method stub
//						if(ispointsetenable){
//						button_choosepoint.setEnabled(false);
//						Pointcurrent=point;
//						ScreenClick++;
//						updateMapState();
//						}
//					}
//				  });
            	
            } 
        };  
		 /**
		 * 清除路点
		 */ 
        private OnClickListener clearwaypoint = new OnClickListener() {  
              
            @Override  
            public void onClick(View v) {  
            	ScreenClick=-1;
				title_name_count=-1;
				//input.setText("");
				ispointsetenable = false;
				aMap.clear();
            }  
        };  
		 /**
		 * 保存方案
		 */   
        private OnClickListener savepointproject = new OnClickListener() {  
            
            @Override  
            public void onClick(View v) {  
				// TODO Auto-generated method stub
          
				waypointfile="飞行任务";
				FILENAME= 
						  district+
						  street+
						  streetNum+
						  ".txt";
				
				List<LatLng> pts1 = new ArrayList<LatLng>();
				if(ScreenClick>0){
				for(int i=0;i<=ScreenClick;i++){
					LatLng newlatlng1=new LatLng(Lat[i],Lng[i]);
					pts1.add(newlatlng1);
					writepointtosd(pts1.get(i).toString());//将坐标点写入SD卡
				}
				}
			
            }  
        };  
		 /**
		 * 选择方案  
		 */  
        private OnClickListener Sendwaypoint = new OnClickListener() {  
              
            @Override  
            public void onClick(View v) {  
            	Toast.makeText(MainActivity.this, "路点上传",  
	                    Toast.LENGTH_SHORT).show(); 
            	
            }  
        }; 
   	 /**
   		 * 生成方案  
   		 */  
           private OnClickListener waypointplanset = new OnClickListener() {  
                 
               @Override  
               public void onClick(View v) {  
            	   
            	   
            	   if (TextUtils.isEmpty(ed_radius.getText()))//设置喷洒半径的限制条件
                   { 
       				Toast.makeText(MainActivity.this, "喷洒半径不能为空值",  
       	                    Toast.LENGTH_SHORT).show();
                       return;
                   }
                   else
                   {
                       User_Distance = Double.parseDouble(ed_radius.getText().toString());
                   }
                   if (User_Distance < 0)
                   {
                   	Toast.makeText(MainActivity.this, "喷洒半径不能为负",  
       	                    Toast.LENGTH_SHORT).show(); 
                       return;
                   }
                   ispointsetenable = true;
               		Waypoint_Plan();
               }  
           };        
    	private void updateMapState() {
    		// TODO Auto-generated method stub
    		if(Pointcurrent==null){
    			state="";
    		}else{
    			state=String.format("%f %f", Pointcurrent.latitude,Pointcurrent.longitude);
    			Lng[ScreenClick] = Pointcurrent.longitude;
    			Lat[ScreenClick] = Pointcurrent.latitude;
    			title_name_count++;
    			String title_name = null;
    			title_name = String.format("%d",title_name_count );
    			//input.setText(state);//显示当前点击的点的经纬度
    			//定义Marker坐标点
    			LatLng point=Pointcurrent;//将当前点击的点的经纬度传到point
    			markerOptions=new MarkerOptions()
    			.position(point)
    			.title(title_name)
    			//.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
    			.draggable(true);
    			pointmarker=aMap.addMarker(markerOptions);
    			pointmarker.setZIndex(3);
    			latlngpre=new LatLng(Lat[ScreenClick],Lng[ScreenClick]);
    			latlngnow=new LatLng(Lat[ScreenClick-1],Lng[ScreenClick-1]);   			
    			if(ScreenClick>=1){
    				drawlineonMap( latlngpre , latlngnow ); 
    			}
    		}
    		//对marker进行拖拽
    		aMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
    			
    			@Override
    			public void onMarkerDragStart(Marker marker) {
    				// TODO Auto-generated method stub
    			}
    			
    			@Override
    			public void onMarkerDragEnd(Marker marker) {
    				// TODO Auto-generated method stub
    			}
    			
    			@Override
    			public void onMarkerDrag(Marker marker) {
//    				polyline.remove();
//    				
    				Pointcurrent_drag=marker.getPosition();
    			    String markerid=marker.getTitle();
    			    int markerId=Integer.parseInt(markerid);
    			    
    			    Lng[markerId] = Pointcurrent_drag.longitude;
    			    Lat[markerId] = Pointcurrent_drag.latitude;
    			    if(ispointsetenable){
    			    	Waypoint_Plan();
    			    }
    			}
    		});
    	}
    	private void drawlineonMap(LatLng oldData, LatLng newData) {
            // TODO Auto-generated method stub
          aMap.addPolyline((new PolylineOptions()) 
          .add(oldData, newData) 
          .width(8)
          .zIndex(3)
          .geodesic(true)
          .setDottedLine(true)//设置虚线
          .color(Color.rgb(104,34,139)));
    }
    	private void drawmarkerandpolyline() {//setup
    		// TODO Auto-generated method stub
    		aMap.clear();
    		List<LatLng> pts = new ArrayList<LatLng>(); 
    		for(int i=0;i<ScreenClick;i++){
    		LatLng newlatlng1=new LatLng(Lat[i],Lng[i]);
    		pts.add(newlatlng1);
    		String title_name = String.format("%d",i );
    		MarkerOptions markerOptions=new MarkerOptions()
    		             .position(newlatlng1)
    		             .draggable(true)
    		             .title(title_name);
    		             //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
    		aMap.addMarker(markerOptions);
    		  }
    		PolylineOptions polylineOptions=new PolylineOptions()
    		             .addAll(pts) 
                         .width(5)
                         .zIndex(3)
                         .visible(true)
                         .geodesic(true)
                         .color(Color.rgb(104,34,139));
    		polyline=aMap.addPolyline(polylineOptions);
    		CameraUpdate cu=CameraUpdateFactory.changeLatLng(new LatLng(Lat[ScreenClick],Lng[ScreenClick]));
    		aMap.moveCamera(cu);
    	}
    	private void drawwaypiontmarkerandpolyline() {
    		aMap.clear();
    		List<LatLng> waypointstation = new ArrayList<LatLng>(); 
    		for(int m=0;m<4;m++){
        		LatLng newlatlngmarker = new LatLng(Lat[m],Lng[m]);
        		String title_name = String.format("%d",m );
        		MarkerOptions markerOptions=new MarkerOptions()
                .position(newlatlngmarker)
                .draggable(true)
                .title(title_name);
        		aMap.addMarker(markerOptions);
        		//pointmarker.setObject(markerOptions);
        		
        	}
    		for(int i=0;i<All_Field_Point;i++){
    			LatLng newlatlng1=new LatLng(AY_lat[i],AY_lon[i]);	
    			waypointstation.add(newlatlng1);
    		}
    		PolylineOptions polylineOptions=new PolylineOptions()
    		             .addAll(waypointstation) 
                         .width(5)
                         .zIndex(3)
                         .visible(true)
                         .setDottedLine(true)//设置虚线
                         .geodesic(true)
                         .color(Color.rgb(34, 255, 211));
    		polyline=aMap.addPolyline(polylineOptions);
    		
//    		CameraUpdate cu=CameraUpdateFactory.changeLatLng(new LatLng(Lat[All_Field_Point],Lng[All_Field_Point]));
//    		aMap.moveCamera(cu);
    		
    	}
    	//删除指定Marker
    	private void clearMarkers() {
    	        //获取地图上所有Marker
    	        List<Marker> mapScreenMarkers = aMap.getMapScreenMarkers();
    	        for (int i = 0; i < mapScreenMarkers.size(); i++) {
    	            Marker marker = mapScreenMarkers.get(i);
    	            if (marker.getObject() == (marker.getTitle()) ) {
    	                marker.remove();//移除当前Marker
    	            }
    	        }
    	       // aMap.invalidate();//刷新地图
    	}
    	///============================================///
        ///   	    	               航迹生成                                                       ///
    	///============================================///
    	private void Waypoint_Plan(){
    		int BIG_NUM = 0;
    		int Horizontal_interval_num = 0;
    		int cross_flag = 0;
	
    		double OnetoTwo_Lat_Part = 0.0;//1-2两点之间纬度差值
    		double OnetoTwo_Lon_Part = 0.0;//1-2两点之间经度差值
    		double ZerotoThree_Lat_Part = 0.0;//0-3两点之间纬度差值
    		double ZerotoThree_Lon_Part = 0.0;//0-3两点之间经度差值
    		double TwotoThree_Lat_Part = 0.0;//2-3两点之间纬度差值
    		double TwotoThree_Lon_Part = 0.0;//2-3两点之间经度差值
    		int ZEROTOTHREE_NUM = 0;//0-3之间的路点个数
    		int ONETOTWO_NUM = 0;//1-2之间的路点个数
    		int TWOTOTHREE_NUM = 0;//2-3之间的路点个数
    		All_Field_Point = 0;//所有的路点个数
	
			if (TextUtils.isEmpty(ed_radius.getText()))//设置喷洒半径的限制条件
            {
				Toast.makeText(MainActivity.this, "喷洒半径不能为空值",  
	                    Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {//.getText().toString()Convert.ToDouble
                User_Distance = Double.parseDouble(ed_radius.getText().toString());
            }
            if (User_Distance < 0)
            {
            	Toast.makeText(MainActivity.this, "喷洒半径不能为负",  
	                    Toast.LENGTH_SHORT).show(); 
                return;
            }
            //if (Double.parseDouble(strHight) !=0)
            {
                Absolute_Height = 5;//Double.parseDouble(strHight);
            }
        
            for (int waypointnum = 0; waypointnum < 4; waypointnum++)//将区域四个顶点的经纬度记录到一个数组中
            {
                AY_lat[waypointnum] = Lat[waypointnum];
                AY_lon[waypointnum] = Lng[waypointnum];
            }

///////////////////////确保按顺时针的方向生成轨迹防止出现Z型的轨迹///////////////////////////
		
	double sTemp_lon = 0;//临时经度
    double sTemp_lat = 0;//临时纬度
	double sTemp_lon_one = (AY_lon[0] - AY_lon[1]);
    double sTemp_lat_one = (AY_lat[0] - AY_lat[1]);
    double sTemp_lon_two = (AY_lon[3] - AY_lon[2]);
    double sTemp_lat_two = (AY_lat[3] - AY_lat[2]);
    double Clockwise_flag = (sTemp_lon_one) * (sTemp_lon_two) + (sTemp_lat_one) * (sTemp_lat_two);//是否按照顺时针方向
    if (Clockwise_flag < 0)//如果没有按照顺时针方向将2和3对调位置
    {
            sTemp_lon = AY_lon[2];
            sTemp_lat = AY_lat[2];
            AY_lon[2] = AY_lon[3];
            AY_lat[2] = AY_lat[3];
            AY_lon[3] = sTemp_lon;
            AY_lat[3] = sTemp_lat;      
	}	
		Field_Distance_onetotwo = Land_Distance(AY_lat[1], AY_lon[1], AY_lat[2], AY_lon[2]);////计算1-2的长度
        Field_Distance_zerotothree = Land_Distance(AY_lat[0], AY_lon[0], AY_lat[3], AY_lon[3]);////计算0-3的长度
        Field_Distance_twotothree = Land_Distance(AY_lat[2], AY_lon[2], AY_lat[3], AY_lon[3]);///计算2-3的长度
		ONETOTWO_NUM = (int)(Field_Distance_onetotwo * 1000 / User_Distance);//计算1-2之间的路点个数
        ZEROTOTHREE_NUM = (int)(Field_Distance_zerotothree * 1000 / User_Distance);//计算0-3之间的路点个数
        TWOTOTHREE_NUM = Math.abs(ONETOTWO_NUM - ZEROTOTHREE_NUM);//计算2-3之间的路点个数
		OnetoTwo_Lat_Part = (AY_lat[2] - AY_lat[1]) / (float)ONETOTWO_NUM;//1-2两点之间纬度差值
        OnetoTwo_Lon_Part = (AY_lon[2] - AY_lon[1]) / (float)ONETOTWO_NUM;//1-2两点之间经度差值
        ZerotoThree_Lat_Part = (AY_lat[3] - AY_lat[0]) / (float)ZEROTOTHREE_NUM;//0-3两点之间经度差值
        ZerotoThree_Lon_Part = (AY_lon[3] - AY_lon[0]) / (float)ZEROTOTHREE_NUM;//0-3两点之间经度差值
		if (TWOTOTHREE_NUM == 0)//2-3之间的路点个数需要判断条件
            {
                TwotoThree_Lat_Part = 0;
                TwotoThree_Lon_Part = 0;
            }
        else
            {
                TwotoThree_Lat_Part = (AY_lat[3] - AY_lat[2]) / (float)TWOTOTHREE_NUM;
                TwotoThree_Lon_Part = (AY_lon[3] - AY_lon[2]) / (float)TWOTOTHREE_NUM;
            }
 //////////////////////////////////////////下行线过渡0--3///////////////////////////////////////////////////////////
			
			make_crosspoint1_lat[0] = AY_lat[0];
			make_crosspoint1_lon[0] = AY_lon[0];
			for (int i = 0; i < (ZEROTOTHREE_NUM - 1); i++)
            {
				//计算0--3上除了第一个点和第三个点之外的所有的拐角点
                make_crosspoint1_lat[i + 1] = make_crosspoint1_lat[i] + ZerotoThree_Lat_Part;//当前点与前一个点和后一个点差为一个经度差值
                make_crosspoint1_lon[i + 1] = make_crosspoint1_lon[i] + ZerotoThree_Lon_Part;//当前点与前一个点和后一个点差为一个纬度差值
            }
            make_crosspoint1_lat[ZEROTOTHREE_NUM] = AY_lat[3];
            make_crosspoint1_lon[ZEROTOTHREE_NUM] = AY_lon[3];
//////////////////////////////////////////上行线过渡1--2///////////////////////////////////////////////////////////			
            make_crosspoint2_lat[0] = AY_lat[1];
            make_crosspoint2_lon[0] = AY_lon[1];
            for (int i = 0; i < (ONETOTWO_NUM - 1); i++)
            {
				//计算1--2上除了第一个点和第三个点之外的所有的拐角点
            make_crosspoint2_lat[i + 1] = make_crosspoint2_lat[i] + OnetoTwo_Lat_Part;//当前点与前一个点和后一个点差为一个经度差值
            make_crosspoint2_lon[i + 1] = make_crosspoint2_lon[i] + OnetoTwo_Lon_Part;//当前点与前一个点和后一个点差为一个纬度差值
            }
            make_crosspoint2_lat[ONETOTWO_NUM] = AY_lat[2];
            make_crosspoint2_lon[ONETOTWO_NUM] = AY_lon[2];		
///////////////////////////////////斜边线过渡2--3///////////////////////////////////////////////////////////////////			
            if (ONETOTWO_NUM >= ZEROTOTHREE_NUM)
            {
                make_crosspoint3_lat[0] = AY_lat[2];
                make_crosspoint3_lon[0] = AY_lon[2];
                for (int i = 0; i < TWOTOTHREE_NUM - 2; i++)
                {
                    make_crosspoint3_lat[i + 1] = make_crosspoint3_lat[i] + TwotoThree_Lat_Part;
                    make_crosspoint3_lon[i + 1] = make_crosspoint3_lon[i] + TwotoThree_Lon_Part;
                }
                if (TWOTOTHREE_NUM >= 1)
                {
                    make_crosspoint3_lat[TWOTOTHREE_NUM - 1] = AY_lat[3] - TwotoThree_Lat_Part;
                    make_crosspoint3_lon[TWOTOTHREE_NUM - 1] = AY_lon[3] - TwotoThree_Lon_Part;
                }
            }
            else
            {
                make_crosspoint3_lat[0] = AY_lat[2] + TwotoThree_Lat_Part;
                make_crosspoint3_lon[0] = AY_lon[2] + TwotoThree_Lon_Part;
                for (int i = 0; i < TWOTOTHREE_NUM - 2; i++)
                {
                    make_crosspoint3_lat[i + 1] = make_crosspoint3_lat[i] + TwotoThree_Lat_Part;
                    make_crosspoint3_lon[i + 1] = make_crosspoint3_lon[i] + TwotoThree_Lon_Part;
                }
                if (TWOTOTHREE_NUM >= 1)
                {
                    make_crosspoint3_lat[TWOTOTHREE_NUM - 1] = AY_lat[3];
                    make_crosspoint3_lon[TWOTOTHREE_NUM - 1] = AY_lon[3];
                }
            }

            if (ONETOTWO_NUM >= ZEROTOTHREE_NUM)
            {
                for (int j = 0; j < TWOTOTHREE_NUM; j++)
                {
                    make_crosspoint1_lat[ZEROTOTHREE_NUM + j + 1] = make_crosspoint3_lat[TWOTOTHREE_NUM - 1 - j];
                    make_crosspoint1_lon[ZEROTOTHREE_NUM + j + 1] = make_crosspoint3_lon[TWOTOTHREE_NUM - 1 - j];
                }
                BIG_NUM = ONETOTWO_NUM;
            }
            else
            {
                BIG_NUM = ZEROTOTHREE_NUM;
                for (int j = 0; j < TWOTOTHREE_NUM; j++)
                {
                    make_crosspoint2_lat[ONETOTWO_NUM + j + 1] = make_crosspoint3_lat[j];
                    make_crosspoint2_lon[ONETOTWO_NUM + j + 1] = make_crosspoint3_lon[j];
                }
            }
///////////////////////////////////斜边线过渡2--3///////////////////////////////////////////////////////////////////
            for (int j = 0; j <= BIG_NUM; j++)
            {
                if (cross_flag == 0)
                {
                    make_road_lat[All_Field_Point] = make_crosspoint1_lat[j];
                    make_road_lon[All_Field_Point] = make_crosspoint1_lon[j];
                    All_Field_Point = All_Field_Point + 1;
                    make_road_lat[All_Field_Point] = make_crosspoint2_lat[j];
                    make_road_lon[All_Field_Point] = make_crosspoint2_lon[j];
                    All_Field_Point = All_Field_Point + 1;
                    cross_flag = 1;
                }
                else if (cross_flag == 1)
                {
                    make_road_lat[All_Field_Point] = make_crosspoint2_lat[j];
                    make_road_lon[All_Field_Point] = make_crosspoint2_lon[j];
                    All_Field_Point = All_Field_Point + 1;
                    make_road_lat[All_Field_Point] = make_crosspoint1_lat[j];
                    make_road_lon[All_Field_Point] = make_crosspoint1_lon[j];
                    All_Field_Point = All_Field_Point + 1;
                    cross_flag = 0;
                }
            }			
            
///////////////////////////绘制生成的路点及轨迹/////////////////////////////////////////////////////////////////////
            for (int j = 0; j < All_Field_Point; j++)
            {
                AY_lat[j]= make_road_lat[j];
                AY_lon[j] = make_road_lon[j];
                WayPoint_Lat[j] = make_road_lat[j];
                WayPoint_Lon[j] = make_road_lat[j];
                Waypoint_TakePic_Flag[j] = 1;//默认生成的所有点都是拍照的
            }
            drawwaypiontmarkerandpolyline();

}
private double Land_Distance(double lat1, double lng1, double lat2, double lng2)
        {
	        double radLat1 = radian(lat1); 
	        double radLat2 = radian(lat2); 
	   
	        double a = radLat1 - radLat2; 
	        double b = radian(lng1) - radian(lng2); 
	        double dst=0.0;
            dst = 2 *(Math.asin((Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)))));
            dst = dst * 6378.137; 
	        dst=Math.round(dst * 10000.0) / 10000.0; 
	        return dst;
        }
private double radian(double d)
        {
            return d * 3.1415926 / 180.0;   //角度1? = π / 180 
        }    	
    	
    	
    	
///============================================///
///   	    	               路点发送                                                       ///
///============================================///

private void Send_WayPoint()
{
     if (WayPoint_NumS < 1)
     {
         AutoWing_RoadPoint_Flag = 0;
         Toast.makeText(MainActivity.this, "请载入路点",  
                 Toast.LENGTH_SHORT).show();
         return;
     }
     
     AltitudeSend_Value = Absolute_Height;//发送高度
    if(First_Send_Flag ==0)
    {	// WayPoint_Lat[0], WayPoint_Lon[0], 
    	//AltitudeSend_Value, YawSend_Value, 
    	//WayPoint_NumS, Which_Road_Point, Constants.User_TrueFlightPath_Distance, 
    	//Constants.User_TrueTakePic_Distance, Which_Data_Bag, SetCameraAngleSend_Flag
        Which_Road_Point = 0x01;
        SendRefData(PlanSend_Flag, Waypoint_TakePic_Flag[0], WayPoint_Lat[0], WayPoint_Lon[0], AltitudeSend_Value, YawSend_Value, WayPoint_NumS, Which_Road_Point, 5, 20, Which_Data_Bag, 20);
        First_Send_Flag = Integer.parseInt(strWayIndex);// AllAerocraftData.AutoWingWhichMarker;
    }
    else if(Integer.parseInt(strWayIndex)==Which_Road_Point && ((Which_Road_Point+1)<=WayPoint_NumS))
    {
        Which_Road_Point++;
        SendRefData(PlanSend_Flag, Waypoint_TakePic_Flag[Which_Road_Point - 1], WayPoint_Lat[Which_Road_Point - 1], WayPoint_Lon[Which_Road_Point - 1], AltitudeSend_Value, YawSend_Value, WayPoint_NumS, Which_Road_Point, 10, 10, Which_Data_Bag, 10);
    }
    else if (Integer.parseInt(strWayIndex) == (Which_Road_Point - 1) && (Which_Road_Point) <= WayPoint_NumS)
    {
        SendRefData(PlanSend_Flag, Waypoint_TakePic_Flag[Which_Road_Point - 1], WayPoint_Lat[Which_Road_Point - 1], WayPoint_Lon[Which_Road_Point - 1], AltitudeSend_Value, YawSend_Value, WayPoint_NumS, Which_Road_Point, 10,10, Which_Data_Bag, 10);
    }
    if(Integer.parseInt(strWayIndex)==WayPoint_NumS && Integer.parseInt(strWayIndex)==Which_Data_Bag)
    {
        Which_Road_Point = 0;
        First_Send_Flag = 0;
        AutoWing_RoadPoint_Flag = 0;
        Which_Data_Bag = Integer.parseInt(strWayID)+1;
        Toast.makeText(MainActivity.this, "路点发送成功",  
                Toast.LENGTH_SHORT).show();
    }
}

/// <summary>
/// /发送路点的相关指令
/// </summary>
/// <param name="Plan_Flag"></param>
/// <param name="TakePhoto_Flag"></param>
/// <param name="Lat_Value"></param>
/// <param name="Lon_Value"></param>
/// <param name="Height_Value"></param>
/// <param name="Yaw_Value"></param>
/// <param name="Road_Num"></param>
/// <param name="Which_Road"></param>
/// <param name="Vel_Flag"></param>
/// <param name="Vel_Value"></param>
/// <param name="WhichData_Bag"></param>
/// <param name="SetCameraAngle_Flag"></param>
public void SendRefData(int Plan_Flag, int TakePhoto_Flag, double Lat_Value
    , double Lon_Value, double Height_Value, double Yaw_Value, int Road_Num,int Which_Road
    ,double Vel_Flag,double Vel_Value,int WhichData_Bag,int SetCameraAngle_Flag)
{
    if (SettingActivity.isConnecting == false)
    {
        return;
    }
    short STemp;
    short SBcc = 0;
    byte[] TxData = new byte[35];
    byte[] LatData = new byte[8];
    byte[] LonData = new byte[8];
    TxData[0] = (byte) 0xAA;
    TxData[1] = (byte) 0x55; 
    TxData[2] = (byte)TakePhoto_Flag;
    TxData[3] = (byte)Plan_Flag;
    TxData[4] = (byte)WhichData_Bag;
    TxData[5] = (byte)Road_Num;
    TxData[6] = (byte)Which_Road;
    LatData = DoubleToArray(Lat_Value);
    TxData[7] = LatData[0];
    TxData[8] = LatData[1]; 
    TxData[9] = LatData[2];
    TxData[10] = LatData[3];
    TxData[11] = LatData[4];
    TxData[12] = LatData[5];
    TxData[13] = LatData[6];
    TxData[14] = LatData[7];
    LonData = DoubleToArray(Lon_Value);
    TxData[15] = LonData[0];
    TxData[16] = LonData[1]; 
    TxData[17] = LonData[2];
    TxData[18] = LonData[3];
    TxData[19] = LonData[4];
    TxData[20] = LonData[5];
    TxData[21] = LonData[6];
    TxData[22] = LonData[7];
    /*****************************高度****************/
    STemp = (short)(Height_Value + 100.0);
    TxData[23] = (byte)((STemp & 0xFF00) >> 8);
    TxData[24] = (byte)(STemp & 0x00FF);
    /*****************************偏航****************/
    STemp = (short)((Yaw_Value + 10.0)*100.0);
    TxData[25] = (byte)((STemp & 0xFF00) >> 8);
    TxData[26] = (byte)(STemp & 0x00FF);
    /***************************速度标志位***********/
    STemp = (short)(Vel_Flag*10.0 + 100.0);
    TxData[27] = (byte)((STemp & 0xFF00) >> 8);
    TxData[28] = (byte)(STemp & 0x00FF);
    /*************************速度********************/
    STemp = (short)(Vel_Value * 10.0 + 100.0);
    TxData[29] = (byte)((STemp & 0xFF00) >> 8);
    TxData[30] = (byte)(STemp & 0x00FF);
    /***********************机头定向******************/
    TxData[31] = (byte)SetCameraAngle_Flag;
    for (int i = 2; i <= 31; i++)
    {
        SBcc += (short)((short)TxData[i] & 0x00FF);
    }
    TxData[32] = (byte)((SBcc >> 8) & 0x00FF);
    TxData[33] = (byte)(SBcc & 0x00FF);
    //Write(TxData, 0, 34);
    try {
		SettingActivity.mPrintWriterClient.write(TxData);
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
static byte[] DoubleToArray(double Value)  
{  
    long accum = Double.doubleToRawLongBits(Value);  
    byte[] byteRet = new byte[8];  
    byteRet[0] = (byte)(accum & 0xFF);  
    byteRet[1] = (byte)((accum>>8) & 0xFF);  
    byteRet[2] = (byte)((accum>>16) & 0xFF);  
    byteRet[3] = (byte)((accum>>24) & 0xFF);  
    byteRet[4] = (byte)((accum>>32) & 0xFF);  
    byteRet[5] = (byte)((accum>>40) & 0xFF);  
    byteRet[6] = (byte)((accum>>48) & 0xFF);  
    byteRet[7] = (byte)((accum>>56) & 0xFF);  
    return byteRet;  
}  
    	private void writepointtosd(String content){

    	    if (Environment.getExternalStorageState().equals(
    	        Environment.MEDIA_MOUNTED)) { // 如果sdcard存在
    	      File file = new File(Environment.getExternalStorageDirectory()
    	          .toString()
    	          +File.separator
    	          +"地面站"
    	          + File.separator
    	          + waypointfile
    	          + File.separator
    	          + FILENAME); // 定义File类对象
    	      if (!file.getParentFile().exists()) { // 父文件夹不存在
    	        file.getParentFile().mkdirs(); // 创建文件夹
    	      }
    	      PrintStream out = null; // 打印流对象用于输出
    	      try {
    	        out = new PrintStream(new FileOutputStream(file,true)); // 追加文件
    	        out.println(content);
    	      } catch (Exception e) {
    	        e.printStackTrace();
    	      } finally {
    	        if (out != null) {
    	          out.close(); // 关闭打印流
    	          Toast.makeText(MainActivity.this, "保存完成", Toast.LENGTH_SHORT).show();
    	        }
    	      }
    	    } else { // SDCard不存在，使用Toast提示用户
    	      Toast.makeText(this, "保存失败，SD卡不存在！", Toast.LENGTH_LONG).show();
    	    }
    	  
    	}
    	/**
    	 * Socket 连接
    	 *  王斌
    	 */
    	
    	private OnClickListener connectlistener = new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			
    			settingactivity.StartConnect();
    			Toast.makeText(getApplicationContext(), "已连接IP", Toast.LENGTH_LONG).show();
    		}
    	};
    /**
	 * 侧面菜单显示
	 */
	private OnClickListener menuListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
//			if (title_linearlayout.getVisibility() == View.GONE) {
//			      animateOpen(title_linearlayout);
//			      animationIvOpen();
//			    } else {
//			      animateClose(title_linearlayout);
//			      animationIvClose();
//			    }
			drawerLayout.openDrawer(Gravity.RIGHT);//调出侧边间菜单抽屉
			initNavigationTabBar();//加载Tab页面
		}
	};
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	} 
	/**
	 * Pager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter{
		List<View> list =  new ArrayList<View>();
		public MyPagerAdapter(ArrayList<View> list) {
			this.list = list;
		}
		@Override
		public void destroyItem(ViewGroup container, int position,
				Object object) {
			ViewPager pViewPager = ((ViewPager) container);
			pViewPager.removeView(list.get(position));
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			ViewPager pViewPager = ((ViewPager) arg0);
			pViewPager.addView(list.get(arg1));
			return list.get(arg1);
		}
		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}
		@Override
		public Parcelable saveState() {
			return null;
		}
		@Override
		public void startUpdate(View arg0) {
		}
	}
	 /** 
     * 设置添加屏幕的背景透明度 
     * @param bgAlpha 
     */  
    public void backgroundAlpha(float bgAlpha)  
    {  
        WindowManager.LayoutParams lp = getWindow().getAttributes();  
        lp.alpha = bgAlpha; //0.0-1.0  
        getWindow().setAttributes(lp);  
    }  
	private void initNavigationTabBar() {
		
		  //设置背景半透明  
        backgroundAlpha(0.9f);  
		viewPager = (ViewPager) findViewById(R.id.vp_vertical_ntb);
		ntbSample = (NavigationTabBar) findViewById(R.id.ntb_vertical);
		final ArrayList<View> list = new ArrayList<View>();
	 	Intent intent = new Intent(this,StateActivity.class);
	 	list.add(getView("A", intent));
	 	
	 	Intent intent2 = new Intent(this,IndexSetActivity.class);
	 	list.add(getView("B", intent2));
	 	
	 	Intent intent3 = new Intent(this,NewAddActivity.class);
	 	list.add(getView("C", intent3));
	 	
	 	//======================================================//
	 	//           用于内嵌使用的Activity                         //                  //
	 	//                                                      //
	 	//======================================================//

	 	Intent intent4 = new Intent(this,WorkIndexActivity.class);
	 	list.add(getView("D", intent4)); 
	 	
	 	Intent intent5 = new Intent(this,FlightModelActivity.class);
	 	list.add(getView("E", intent5));
	 	
	 	Intent intent6 = new Intent(this,LightcoActivity.class);
	 	list.add(getView("F", intent6));
	 	
		Intent intent7 = new Intent(this,OfflineMapActivity.class);
	 	list.add(getView("G", intent7));
		viewPager.setAdapter(new MyPagerAdapter(list));
		viewPager.setCurrentItem(0);

		final int bgColor = Color.parseColor("#343434");  
	 	final ArrayList<NavigationTabBar.Model> models = new ArrayList();
	        models.add(
	       
	        		new NavigationTabBar.Model.Builder(
	                        getResources().getDrawable(R.drawable.ic_eighth), bgColor
	                ).build()
	        );
	        models.add(
	                new NavigationTabBar.Model.Builder(
	                        getResources().getDrawable(R.drawable.ic_fifth),
	                        bgColor)
	                .build()
	        );
	        models.add(
	                new NavigationTabBar.Model.Builder(
	                        getResources().getDrawable(R.drawable.ic_first), bgColor
	                ).build()
	        );
//	        models.add(
//	                new NavigationTabBar.Model.Builder(
//	                        getResources().getDrawable(R.drawable.ic_fourth), bgColor
//	                ).build()
//	        );
//	        models.add(
//	                new NavigationTabBar.Model.Builder(
//	                        getResources().getDrawable(R.drawable.ic_second), bgColor
//	                ).build()
//	        );
//	        models.add(
//	                new NavigationTabBar.Model.Builder(
//	                        getResources().getDrawable(R.drawable.ic_first), bgColor
//	                ).build()
//	        );
	        ntbSample.setModels(models);
//	        ntbSample.setModelIndex(4, true);
	        ntbSample.setViewPager(viewPager, 0);
	}
//	  private ValueAnimator createDropAnimator(final View v, int start, int end) {
//		    ValueAnimator animator = ValueAnimator.ofInt(start, end);
//		    animator.addUpdateListener(new AnimatorUpdateListener() {
//		 
//		      @Override
//		      public void onAnimationUpdate(ValueAnimator arg0) {
//		        Object value = arg0.getAnimatedValue();
//		        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
//		        layoutParams.height = (Integer) value;
//		        v.setLayoutParams(layoutParams);
//		 
//		      }
//		    });
//		    return animator;
//		  }
//	  private void animateOpen(View v) {
//		    v.setVisibility(View.VISIBLE);
//		    ValueAnimator animator = createDropAnimator(v, 0,
//		        mHiddenViewMeasuredHeight);
//		    animator.start();
//		 
//		  }
//	  private void animateClose(final View view) {
//		    int origHeight = view.getHeight();
//		    ValueAnimator animator = createDropAnimator(view, origHeight, 0);
//		    animator.addListener(new AnimatorListenerAdapter() {
//		      public void onAnimationEnd(Animator animation) {
//		        view.setVisibility(View.GONE);
//		      }
//		 
//		    });
//		    animator.start();
//		  }
//
//	  private void animationIvOpen() {
//		    RotateAnimation animation = new RotateAnimation(0, 0,
//		        Animation.ABSOLUTE, 0.5f, Animation.ABSOLUTE,
//		        0.5f);
//		    animation.setFillAfter(true);
//		    animation.setDuration(1);
//		    title_linearlayout.startAnimation(animation);
//		  }
//		 
//		  private void animationIvClose() {
//
//		       
//		  }

	private void openMenuDialog() {
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View menuView = inflater.inflate(R.layout.activity_vertical_ntb, null);
		// 创建AlertDialog
		final AlertDialog menuDialog = new AlertDialog.Builder(this).create();
		menuDialog.setView(menuView);
		menuDialog.show();
		final NavigationTabBar ntbSample = (NavigationTabBar) findViewById(R.id.ntb_vertical);
        final ArrayList<NavigationTabBar.Model> models3 = new ArrayList();
        models3.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_seventh), Color.RED
                ).build()
        );
        models3.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_seventh), Color.RED
                ).build()
        );
        models3.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_seventh), Color.RED
                ).build()
        );
        ntbSample.setModels(models3);
        ntbSample.setModelIndex(1, true);
	}
 
  
    /** 
     * 方法必须重写 
     */  
    @Override  
    protected void onPause() {  
        super.onPause();  
        mMapView.onPause();  
    }  
    /** 
     * 方法必须重写 
     */  
    @Override  
    protected void onSaveInstanceState(Bundle outState) {  
        super.onSaveInstanceState(outState);  
        mMapView.onSaveInstanceState(outState);  
    }  
    protected void onDestroy() {
  		super.onDestroy();
  		//关闭定位图层
  		mMapView.onDestroy();
  	        //mLocationClient.stopLocation();//停止定位
  	        mLocationClient.onDestroy();//销毁定位客户端。
  	        //销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象。
  		
  		EventBus.getDefault().unregister(this);
  	}
	protected void onResume() {
    	super.onResume(); 
    	/** 设置为横屏*/
		 if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
		  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		 }   
		 // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
	 	mMapView.onResume();
	}
	@Override
	public void activate(OnLocationChangedListener arg0) {
		// TODO 自动生成的方法存根
		 mListener = arg0;
	}
	@Override
	public void deactivate() {
		// TODO 自动生成的方法存根
		mListener = null;	
	}
	
}
