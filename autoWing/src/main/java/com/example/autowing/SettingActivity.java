package com.example.autowing;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SelectionKey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.adapter.SearchAutoAdapter;
import com.example.buff.GetIntData;
import com.example.buff.PackageType.packageType;
import com.example.data.AcceleratedSpeedData;
import com.example.data.GPSData;
import com.example.data.GeomagnetismData;
import com.example.data.IMUData;
import com.example.data.MotorData;
import com.example.data.PIDData;
import com.example.data.RemoteControlData;
import com.example.data.SpeedData;
import com.example.data.StartCloseData;
import com.example.data.StatusData;
import com.example.data.WayPointData;
import com.example.data.XBEEData;
import com.example.offline.OfflineMapActivity;

import de.greenrobot.event.EventBus;

/**
 * 设置页面
 * 
 * @author android_wbin
 * 
 */
public class SettingActivity extends Activity {

	private String Tag = "SettingActivity";
	private Button StartBtn, SendBtn, Disp_sendBtn, Disp_recevierBtn;
	private Button offlinemap;
	private EditText ed_IP, ed_CMT;
	private TextView Imu_log, Acc_log, Geo_log, Re_log, SW_log, Way_log,
			Motor_log, Speed_log, GPS_log, State_log, XBEE_log;
	private TextView SearchBtn;
	private Spinner selectRoadSp;

	double longtitude = 0;
	double latitude = 0;

	int Lpoint = 0;
	int Click = 0;

	String IPStr;// 输入的请求地址
	String Client_message;// 发送消息
	String Client_log;// 显示发送消息
	String Receive_log;// 显示接收消息
	String textlog;

	static int newIPSegment = 0;// 无符号

	/** IMU数据 */
	private String strRoll;// 滚转角
	private String strPitch;// 俯仰角
	private String strYaw;// 航向角
	private String strRollRate;// 陀螺仪Rollrate
	private String strPichRate;// 陀螺仪Pitchrate
	private String strYawRate;// 陀螺仪Yawrate

	/** 加速度数据 */
	private String strAccX;
	private String strAccY;
	private String strAccZ;

	/** 地磁数据 */
	private String strGeoX;
	private String strGeoY;
	private String strGeoZ;

	/** 遥控器数据 */
	private String strReRoll;
	private String strRePitch;
	private String strReYaw;
	private String strReThrottle;

	/** 开关数据 */
	private String strSW1;
	private String strSW2;
	private String strSW3;
	private String strSW4;

	/** 路点数据 */
	private String strWayID;
	private String strWayCount;
	private String strWayIndex;

	/** 电机数据 */
	private String strMotor_Front;
	private String strMotor_Back;
	private String strMotor_Left;
	private String strMotor_Right;
	private String strMotor_X;
	private String strMotor_Y;

	/** 速度数据 */
	private String strLateral;
	private String strLongitudinal;
	private String strUpdown;

	/** GPS数据 */
	private String strLatitude;
	private String strLongitude;
	private String strStarCount;
	private String strHight;
	static String strDop;
	static String strBaraAlt;
	static String strHeading;

	/** 状态数据 */
	private String strFlyStatus;
	private String strVoltage;
	private String strSendFlag;
	private String strSensorStatus;
	private String strCommStatus;
	private String strPhotoFlag;

	/** XBEE通断检测数据 */
	private String strXBEE;

	/** PID数据 */
	public static String strGyro_RP_KP = "0";
	public static String strGyro_RP_KI = "0";
	public static String strGyro_RP_KD = "0";
	public static String strGyro_Y_KP = "0";
	public static String strGyro_Y_KI = "0";
	public static String strGyro_Y_KD = "0";
	public static String strProp_RP_KP = "0";
	public static String strProp_RP_KI = "0";
	public static String strProp_Y_KP = "0";
	public static String strProp_Y_KI = "0";
	public int PID_Send_Flg = 0;// PID参数发送标志
	/** 接收消息 */
	String StrIMU;
	String Strgeoma;
	String Strremote;
	String Strstart;
	String StrwayPoint;
	String Strmotor;
	String Strspeed;
	String Strgps;
	String Strstatus;
	String Strxbee;

	/** 发送消息 */
	String sIP;
	String sPort;
	int port;
	int start = 0;

	/** 发送请求参数 */
	public static boolean isConnecting = false;
	private Socket mSocketClient = null;
	static BufferedOutputStream mPrintWriterClient = null;
	static BufferedInputStream mBufferedReaderClient = null;
	Thread mThreadClient;

	/**
	 * IP地址历史输入
	 */
	InputMethodManager manager;
	public static final String SEARCH_HISTORY = "search_history";
	private SearchAutoAdapter mSearchAutoAdapter;
	// private ListView mAutoList;
	private SharedPreferences IPsharep;
	/** 接收请求参数 */

	int bodylen = -1;
	SelectionKey sk;
	static final int MESSAGE_LENGTH_HEAD = 2;// 缓冲包

	packageType dataType;
	GetIntData getInt;

	/** IMU数据 */
	IMUData IMU;
	/** 加速度数据 */
	AcceleratedSpeedData accspeed;
	/** 地磁数据 */
	GeomagnetismData geoData;
	/** 遥控器数据 */
	RemoteControlData remoteData;
	/** 开关数据 */
	StartCloseData closeData;
	/** 路点数据 */
	WayPointData wayPointData;
	/** 电机数据 */
	MotorData motorData;
	/** 速度数据 */
	SpeedData speedData;
	/** GPS数据 */
	GPSData gpsData;
	/** 状态数据 */
	StatusData statusData;
	/** XBEE通断检测数据 */
	XBEEData xbeeData;

	PIDData piddata;

	static byte[] m_nsenddefaultdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_setting);

		m_nsenddefaultdata = new byte[7];
		// IPsharep = getSharedPreferences(SEARCH_HISTORY, 0);
		// IPsharep.edit().clear().commit();
		// mSearchAutoAdapter = new SearchAutoAdapter(SettingActivity.this, -1);

		initWindow();
		findViewById();
		// isIPValid();

	}

	// 状态栏
	private void initWindow() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	private void findViewById() {
		// StartBtn = (Button)findViewById(R.id.btn_connect);
//		StartBtn = (Button) findViewById(R.id.StartConnect);
//		SendBtn = (Button) findViewById(R.id.SendButtonClient);
//
//		offlinemap = (Button) findViewById(R.id.display_send_msg_btn);
//		Disp_recevierBtn = (Button) findViewById(R.id.display_receive_msg_btn);
//		//ed_IP = (EditText) findViewById(R.id.IPText);
//
//		ed_CMT = (EditText) findViewById(R.id.clientMessageText);
//		Imu_log = (TextView) findViewById(R.id.log1);
//		Acc_log = (TextView) findViewById(R.id.log2);
//		Geo_log = (TextView) findViewById(R.id.log3);
//		Re_log = (TextView) findViewById(R.id.log4);
//		SW_log = (TextView) findViewById(R.id.log5);
//		Way_log = (TextView) findViewById(R.id.log6);
//		Motor_log = (TextView) findViewById(R.id.log7);
//		Speed_log = (TextView) findViewById(R.id.log8);
//		GPS_log = (TextView) findViewById(R.id.log9);
//		State_log = (TextView) findViewById(R.id.log10);
//		XBEE_log = (TextView) findViewById(R.id.log11);

		//IPStr = ed_IP.getText().toString(); // 获取写入的IP值

		/**
		 * 添加的监听函数
		 */
//		StartBtn.setOnClickListener(StartConnectListener);
//		SendBtn.setOnClickListener(SendClientListener);
//
//		offlinemap.setOnClickListener(OfflinemapListener);
//		Disp_recevierBtn.setOnClickListener(Disp_recevierListener);

	}

	public boolean isIPValid() {

		sIP = "192.168.2.1";// IPStr.substring(0, start);//获取IP地址
		sPort = "8000";// IPStr.substring(start + 1);//获取串口号
		port = Integer.parseInt(sPort);

		return true;
	}

	@Override
	public boolean onSearchRequested() {
		startSearch("onNewIntent", false, null, false);
		return true;
	}

	/**
	 * 
	 * 保存搜索记录 public void saveSearchHistory() { String longhistory =
	 * IPsharep.getString(SEARCH_HISTORY, ""); String[] tmpHistory =
	 * longhistory.split(","); ArrayList<String> history = new
	 * ArrayList<String>( Arrays.asList(tmpHistory)); if (history.size() > 0) {
	 * int i; for (i = 0; i < history.size(); i++) { if
	 * (IPStr.equals(history.get(i))) { history.remove(i); break; } }
	 * history.add(0, IPStr); } if (history.size() > 0) { StringBuilder sb = new
	 * StringBuilder(); for (int i = 0; i < history.size(); i++) {
	 * sb.append(history.get(i) + ","); }
	 * IPsharep.edit().putString(SEARCH_HISTORY, sb.toString()).commit(); } else
	 * { IPsharep.edit().putString(SEARCH_HISTORY, IPStr + ",").commit(); } }
	 */

	/**
	 * 创建链接
	 */
	public void StartConnect() {
		if (isConnecting) {
			isConnecting = false;
			try {
				if (mSocketClient != null) {
					mSocketClient.close();
					mSocketClient = null;
					mBufferedReaderClient.close();
					mBufferedReaderClient = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			mThreadClient.interrupt();
			//StartBtn.setText("已断开");
			handler.removeCallbacks(mThreadClient);
		} else {
			isConnecting = true;
			//StartBtn.setText("已连接");
			// 开启一个与服务器创建线程
			mThreadClient = new Thread(new StartRunnable());
			mThreadClient.start();

		}
	}

	private OnClickListener StartConnectListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (isConnecting) {
				isConnecting = false;
				try {
					if (mSocketClient != null) {
						mSocketClient.close();
						mSocketClient = null;
						mBufferedReaderClient.close();
						mBufferedReaderClient = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				mThreadClient.interrupt();
				//StartBtn.setText("已断开");
				// StartBtn.setEnabled(false);
				// ed_IP.setEnabled(false);
				handler.removeCallbacks(mThreadClient);
			} else {
				isConnecting = true;

				//StartBtn.setText("已连接");
				// StartBtn.setEnabled(true);
				// ed_IP.setEnabled(true);
				// 开启一个与服务器创建线程
				mThreadClient = new Thread(new StartRunnable());
				mThreadClient.start();

			}
		}
	};

	/**
	 * 与服务器对接线程
	 * 
	 * @author android_wbin
	 */
	class StartRunnable implements Runnable {
		@Override
		public void run() {
			if (SettingActivity.this.isIPValid()) {
				try {
					// 连接服务器
					mSocketClient = new Socket(sIP, port); // portnum
					// 取得输入、输出流
					mBufferedReaderClient = new BufferedInputStream(
							mSocketClient.getInputStream());
					mPrintWriterClient = new BufferedOutputStream(
							mSocketClient.getOutputStream());
					// handler.post(r)。r是要执行的任务代码。意思就是说r的代码实际是在UI线程执行的。
					// 可以写更新UI的代码。（工作线程是不能更新UI的）
					handler.post(receiver);
					System.out.println("<<<<<<<<<<<<<<<<<<<已连接IP");
					new Thread(new WriteData()).start();
					Looper.prepare();
					
//					Toast.makeText(null, "已连接IP",
//							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} catch (Exception e) {
					Log.e(Tag, e.toString() + e.getMessage());
					Looper.prepare();
//					Toast.makeText(null, "连接IP异常",
//							Toast.LENGTH_SHORT).show();
					Looper.loop();
					return;
				}

			}
		}
	};

	class WriteData implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isConnecting) {
				try {

					SendDefaultData((byte) 0x00);
					mPrintWriterClient.write(m_nsenddefaultdata);

				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}

	int clientCheckAnd;// 客户端校验和
	int serverCheckAnd;// 服务端校验和
	// 11条数据 对应每个数据包的长度
	int dataPacketSize;
	int count = 0;
	// 接受到的数据
	byte[] charBuffer = new byte[153];
	// 数组的下标
	int dataIndex;
	// 数据头的3个字节
	byte[] dataHeaderBytes = new byte[5];
	// 总的数据
	byte[] allData = new byte[256];
	// 数据大小
	int nSize = allData.length;
	byte[] m_byBuff;
	boolean dataFlag = false;

	/**
	 * 获取到接收的数据内容 转译
	 */
	private final Runnable receiver = new Runnable() {
		@Override
		public void run() {
			new Thread() {
				public void run() {
					try {
						while (isConnecting) {
							if ((count = mBufferedReaderClient.read(charBuffer)) > 0) {
								// for (byte ipSegment : charBuffer) {
								// newIPSegment = (ipSegment < 0) ? 256 +
								// ipSegment : ipSegment;
								// System.out.println(">>>>>>>>>>>>>>>>>>>>接到数据");
								//
								// break;
								// }

								for (int i = 0; i < count; i++) {
									byte bytes = charBuffer[i];
									// System.out.println("bytes1="+bytes);
									if (dataFlag) {
										allData[dataIndex++] = bytes;
										if (dataIndex >= dataPacketSize) {
											dataFlag = false;
											// 将获得的负数数据转成正数并赋给ss数组
											for (int x = 0; x < dataPacketSize; x++) {
												if (allData[x] < 0) {
													allData[x] = (byte) (allData[x] + 256);
												} else {
													allData[x] = (byte) allData[x];
												}
											}
											clientCheckAnd = 0;
											serverCheckAnd = 0;
											// 计算校验和
											for (int y = 2; y < dataPacketSize - 2; y++) {
												clientCheckAnd += ((short) +allData[y] & 0x00FF);
											}
											System.out
													.println("clientCheckAnd="
															+ clientCheckAnd);
											serverCheckAnd = (short) (allData[dataPacketSize - 2] << 8 | (short) allData[dataPacketSize - 1] & 0x00FF);
											System.out
													.println("serverCheckAnd="
															+ serverCheckAnd);

											if (clientCheckAnd == serverCheckAnd) {
												dataType = packageType.PtNon;
												// 取ID
												switch (allData[4]) {
												case 1:
													dataType = packageType.PtIMU;
													IMU = new IMUData(allData,
															5);// 5-16
													strRoll = String
															.valueOf(IMU
																	.getIRoll());
													strPitch = String
															.valueOf(IMU
																	.getIPitch());
													strYaw = String.valueOf(IMU
															.getIYaw());
													strRollRate = String
															.valueOf(IMU
																	.getIRollRate());
													strPichRate = String
															.valueOf(IMU
																	.getIPitchRate());
													strYawRate = String
															.valueOf(IMU
																	.getIYawRate());

													dataType = packageType.PtAcceleratedSpeed;
													accspeed = new AcceleratedSpeedData(
															allData, 17);// 17-22

													strAccX = String
															.valueOf(accspeed
																	.getX());
													strAccY = String
															.valueOf(accspeed
																	.getY());
													strAccZ = String
															.valueOf(accspeed
																	.getZ());

													dataType = packageType.PtGeomagnetism;
													geoData = new GeomagnetismData(
															allData, 23);// 23-28

													strGeoX = String
															.valueOf(geoData
																	.getX());
													strGeoY = String
															.valueOf(geoData
																	.getY());
													strGeoZ = String
															.valueOf(geoData
																	.getZ());

													dataType = packageType.PtRemoteControl;
													remoteData = new RemoteControlData(
															allData, 29);// 17-36
													strReRoll = String
															.valueOf(remoteData
																	.getRoll());
													strRePitch = String
															.valueOf(remoteData
																	.getPitch());
													strReYaw = String
															.valueOf(remoteData
																	.getYaw());
													strReThrottle = String
															.valueOf(remoteData
																	.getThrottle());

													dataType = packageType.PtStartClose;
													closeData = new StartCloseData(
															allData, 37);// 37-44
													strSW1 = String
															.valueOf(closeData
																	.getSW1());
													strSW2 = String
															.valueOf(closeData
																	.getSW2());
													strSW3 = String
															.valueOf(closeData
																	.getSW3());
													strSW4 = String
															.valueOf(closeData
																	.getSW4());

													dataType = packageType.PtMotor;
													motorData = new MotorData(
															allData, 45);// 45-56
													strMotor_Front = String
															.valueOf(motorData
																	.getMotor_Front());
													strMotor_Back = String
															.valueOf(motorData
																	.getMotor_Back());
													strMotor_Left = String
															.valueOf(motorData
																	.getMotor_Left());
													strMotor_Right = String
															.valueOf(motorData
																	.getMotor_Right());
													strMotor_X = String
															.valueOf(motorData
																	.getMotor_X());
													strMotor_Y = String
															.valueOf(motorData
																	.getMotor_Y());

													dataType = packageType.PtSpeed;
													speedData = new SpeedData(
															allData, 57);// 57-62
													strLateral = String
															.valueOf(speedData
																	.getLateral());
													strLongitudinal = String
															.valueOf(speedData
																	.getLongitudinal());
													strUpdown = String
															.valueOf(speedData
																	.getUpdown());

													dataType = packageType.PtGPS;
													gpsData = new GPSData(
															allData, 63);// 63-90
													strLatitude = String
															.valueOf(gpsData
																	.getLatitude());
													strLongitude = String
															.valueOf(gpsData
																	.getLongitude());
													strStarCount = String
															.valueOf(gpsData
																	.getStarCount());
													strHight = String
															.valueOf(gpsData
																	.getHight());
													strDop = String
															.valueOf(gpsData
																	.getGpsDop());
													strBaraAlt = String
															.valueOf(gpsData
																	.getBaraAlt());
													strHeading = String
															.valueOf(gpsData
																	.getHeading());

													dataType = packageType.PtStatus;
													statusData = new StatusData(
															allData, 91);// 91-94
													strFlyStatus = String
															.valueOf(statusData
																	.getFlyStatus());
													strVoltage = String
															.valueOf(statusData
																	.getVoltage());
													// strSendFlag =
													// String.valueOf(statusData.getSendFlag());
													strSensorStatus = String
															.valueOf(statusData
																	.getSensorStatus());
													// strCommStatus =
													// String.valueOf(statusData.getCommStatus());
													// strPhotoFlag =
													// String.valueOf(statusData.getPhotoFlag());

													dataType = packageType.PtSpeed;
													xbeeData = new XBEEData(
															allData, 97);// 97-99
													strXBEE = String
															.valueOf(xbeeData
																	.getXBEE());
													handler.sendEmptyMessage(1);
													break;
												case 2:
													wayPointData = new WayPointData(
															allData, 22);// 37-44
													strWayID = String
															.valueOf(wayPointData
																	.getWayID());
													strWayCount = String
															.valueOf(wayPointData
																	.getWayCount());
													strWayIndex = String
															.valueOf(wayPointData
																	.getWayIndex());
													handler.sendEmptyMessage(2);
													break;
												case 4:
													piddata = new PIDData(
															allData, 5);
													PID_Send_Flg = (allData[3] & 0x0000001f);// 反馈flag
													strGyro_RP_KP = String
															.valueOf(piddata
																	.getIGyro_RP_KP());
													strGyro_RP_KI = String
															.valueOf(piddata
																	.getIGyro_RP_KI());
													strGyro_RP_KD = String
															.valueOf(piddata
																	.getIGyro_RP_KD());
													strGyro_Y_KP = String
															.valueOf(piddata
																	.getIGyro_Y_KP());
													strGyro_Y_KI = String
															.valueOf(piddata
																	.getIGyro_Y_KI());
													strGyro_Y_KD = String
															.valueOf(piddata
																	.getIGyro_Y_KD());
													strProp_RP_KP = String
															.valueOf(piddata
																	.getIProp_RP_KP());
													strProp_RP_KI = String
															.valueOf(piddata
																	.getIProp_RP_KI());
													strProp_Y_KP = String
															.valueOf(piddata
																	.getIProp_Y_KP());
													strProp_Y_KI = String
															.valueOf(piddata
																	.getIProp_Y_KI());
													handler.sendEmptyMessage(4);
													break;
												}
											}
										}
									} else {

										/**
										 * // * 先获得前5个字节(2个包头1个ID),放入Data中,
										 * 并清空cIndexHeader //
										 **/
										if (bytes < 0) {
											bytes = (byte) (bytes + 256);
										} else {
											bytes = (byte) bytes;
										}
										/*
										 * 先获得前3个字节(2个包头1个ID),放入allData中,
										 * 并清空dataHeaderBytes,
										 */
										dataHeaderBytes[0] = dataHeaderBytes[1];
										dataHeaderBytes[1] = dataHeaderBytes[2];
										dataHeaderBytes[2] = dataHeaderBytes[3];
										dataHeaderBytes[3] = dataHeaderBytes[4];
										dataHeaderBytes[4] = bytes;

										if (dataHeaderBytes[0] == (byte) 0xB5
												&& dataHeaderBytes[1] == (byte) 0x5B) {
											for (int j = 0; j < 5; j++) {
												allData[j] = dataHeaderBytes[j];
											}
											for (int j = 0; j < 5; j++) {
												dataHeaderBytes[j] = 0;
											}
											if (allData[2] < 0) {
												dataPacketSize = allData[2] + 256;
											} else {
												dataPacketSize = allData[2];
											}
											if (dataPacketSize > 0) {
												dataIndex = 5;
												dataFlag = true;
											}
										}
									}

								}
							}
							count = 0;

						}

					} catch (IOException e) {
						Log.e(Tag, e.getMessage());
						Looper.prepare();
						Toast.makeText(SettingActivity.this, "接收数据异常",
								Toast.LENGTH_SHORT).show();
						Looper.loop();
					}
				}

			}.start();

		}

	};
	/** 向服务器发送消息 */
	private OnClickListener SendClientListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

		}
	};

	/** 离线地图管理 */
	private OnClickListener OfflinemapListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intentofflmap = new Intent(SettingActivity.this,
					OfflineMapActivity.class);
			startActivity(intentofflmap);
		}
	};

	/** 显示发送消息 */
	private OnClickListener Disp_sendListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

		}
	};

	/** 显示接收消息 */
	private OnClickListener Disp_recevierListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

		}
	};

	String blank = "\n";

	double Roll, Pitch, Yaw, RollRate, PichRate, YawRate;
	double AccX, AccY, AccZ;
	double GeoX, GeoY, GeoZ;
	double ReRoll, RePitch, ReYaw, ReThrottle;
	double SW1, SW2, SW3, SW4;
	int WayID, WayCount, WayIndex;
	double Motor_Front, Motor_Back, Motor_Left, Motor_Right, Motor_X, Motor_Y;
	double Lateral, Longitudinal, Updown;
	double Latitude, Longitude;// 经纬度
	double StarCount, Hight;
	double BaraAlt;
	double GpsDop;
	double Heading;
	double Voltage;
	double FlyStatus, SendFlag, SensorStatus, CommStatus, PhotoFlag;
	double XBEE;
	float fGyro_RP_KP, fGyro_RP_KI, fGyro_RP_KD;
	float fGyro_Y_KP, fGyro_Y_KI, fGyro_Y_KD;
	float fProp_RP_KP, fProp_RP_KI, fProp_Y_KP, fProp_Y_KI;

	/**
	 * 获取到数据传给MainActivity
	 */
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Roll = Double.parseDouble(strRoll);
				Pitch = Double.parseDouble(strPitch);
				Yaw = Double.parseDouble(strYaw);
				RollRate = Double.parseDouble(strRollRate);
				PichRate = Double.parseDouble(strPichRate);
				YawRate = Double.parseDouble(strYawRate);
				EventBus.getDefault().post(
						new IMU_EventBus(Roll, Pitch, Yaw, YawRate, PichRate,
								YawRate));

				// Acc_log.setText("X方向加速度为："+strAccX );
				AccX = Double.parseDouble(strAccX);
				AccY = Double.parseDouble(strAccY);
				AccZ = Double.parseDouble(strAccZ);
				EventBus.getDefault().post(
						new Accelerated_EventBus(AccX, AccY, AccZ));

				// Geo_log.setText("X方向地磁数据为："+strGeoX);
				GeoX = Double.parseDouble(strGeoX);
				GeoY = Double.parseDouble(strGeoY);
				GeoZ = Double.parseDouble(strGeoZ);
				EventBus.getDefault().post(
						new Geomagnetism_EventBus(GeoX, GeoY, GeoZ));

				// Re_log.setText("遥控器Roll量为："+strReRoll);
				ReRoll = Double.parseDouble(strReRoll);
				RePitch = Double.parseDouble(strRePitch);
				ReYaw = Double.parseDouble(strReYaw);
				ReThrottle = Double.parseDouble(strReThrottle);
				EventBus.getDefault()
						.post(new Remote_EventBus(ReRoll, RePitch, ReYaw,
								ReThrottle));

				// SW_log.setText("开关SW1为："+strSW1 );

				SW1 = Double.parseDouble(strSW1);
				SW2 = Double.parseDouble(strSW2);
				SW3 = Double.parseDouble(strSW3);
				SW4 = Double.parseDouble(strSW4);
				EventBus.getDefault().post(
						new StartClose_EventBus(SW1, SW2, SW3, SW4));

				// Motor_log.setText("Motor_Front为："+strMotor_Front );
				Motor_Front = Double.parseDouble(strMotor_Front);
				Motor_Back = Double.parseDouble(strMotor_Back);
				Motor_Left = Double.parseDouble(strMotor_Left);
				Motor_Right = Double.parseDouble(strMotor_Right);
				Motor_X = Double.parseDouble(strMotor_X);
				Motor_Y = Double.parseDouble(strMotor_Y);
				EventBus.getDefault().post(
						new Motor_EventBus(Motor_Front, Motor_Back, Motor_Left,
								Motor_Right, Motor_X, Motor_Y));

				// Speed_log.setText("横向速度为："+strLateral );
				Lateral = Double.parseDouble(strLateral);
				Longitudinal = Double.parseDouble(strLongitudinal);
				Updown = Double.parseDouble(strUpdown);
				EventBus.getDefault().post(
						new Speed_EventBus(Lateral, Longitudinal, Updown));

				// GPS_log.setText("纬度为："+strLatitude );
				Latitude = Double.parseDouble(strLatitude);
				Longitude = Double.parseDouble(strLongitude);
				StarCount = Double.parseDouble(strStarCount);
				Hight = Double.parseDouble(strHight);
				BaraAlt = Double.parseDouble(strBaraAlt);
				GpsDop = Double.parseDouble(strDop);
				Heading = Double.parseDouble(strHeading);
				EventBus.getDefault().post(
						new GPS_EventBus(Latitude, Longitude, StarCount, Hight,
								GpsDop, BaraAlt, Heading));

				// State_log.setText("飞行模式为："+strFlyStatus);
				FlyStatus = Double.parseDouble(strFlyStatus);
				Voltage = Double.parseDouble(strVoltage);
				// SendFlag = Double.parseDouble(strSendFlag);
				SensorStatus = Double.parseDouble(strSensorStatus);
				// CommStatus = Double.parseDouble(strCommStatus);
				// PhotoFlag = Double.parseDouble(strPhotoFlag);
				EventBus.getDefault().post(
						new Status_EventBus(FlyStatus, Voltage, SensorStatus));

				// XBEE_log.setText("接收一个255数据："+strXBEE +blank);
				XBEE = Double.parseDouble(strXBEE);
				EventBus.getDefault().post(new XBEE_EventBus(XBEE));
				break;
			case 2:
				WayID = Integer.parseInt(strWayID);
				WayCount = Integer.parseInt(strWayCount);
				WayIndex = Integer.parseInt(strWayIndex);
				EventBus.getDefault().post(
						new WayPoint_EventBus(WayID, WayCount, WayIndex));
			case 4:
				fGyro_RP_KP = Integer.parseInt(strGyro_RP_KP);
				fGyro_RP_KI = Integer.parseInt(strGyro_RP_KI);
				fGyro_RP_KD = Integer.parseInt(strGyro_RP_KD);
				fGyro_Y_KP = Integer.parseInt(strGyro_Y_KP);
				fGyro_Y_KI = Integer.parseInt(strGyro_Y_KI);
				fGyro_Y_KD = Integer.parseInt(strGyro_Y_KD);
				fProp_RP_KP = Integer.parseInt(strProp_RP_KP);
				fProp_RP_KI = Integer.parseInt(strProp_RP_KI);
				fProp_Y_KP = Integer.parseInt(strProp_Y_KP);
				fProp_Y_KI = Integer.parseInt(strProp_Y_KI);
				EventBus.getDefault().post(
						new PID_Eventbus(fGyro_RP_KP, fGyro_RP_KI, fGyro_RP_KD,
								fGyro_Y_KP, fGyro_Y_KI, fGyro_Y_KD,
								fProp_RP_KP, fProp_RP_KI, fProp_Y_KP,
								fProp_Y_KI));
				break;
			default:
				break;
			}
		};
	};

	public static void SendDefaultData(byte senddefaultdata) {
		byte TxData[] = new byte[7];

		TxData[0] = (byte) 0x3A;
		TxData[1] = (byte) 0xA3;
		TxData[2] = (byte) 0x07;
		TxData[3] = (byte) senddefaultdata;
		TxData[4] = (byte) 0x00;
		CalcBcc(TxData, 4);
		m_nsenddefaultdata = TxData.clone();
	}
    /// <summary>
    /// 自主起飞
    /// </summary>
    /// <param name="TakeOff_Flag"></param>
    public void wifi_SendAutoTakeOff(int TakeOff_Flag)
    {
        byte[] TxData = new byte[7];
        short SBcc = 0;
        TxData[0] = (byte)0x3A;
        TxData[1] = (byte)0xA3;
        TxData[2] = (byte)0x07;
        TxData[3] = (byte)0x02;
        TxData[4] = (byte) (TakeOff_Flag);
        for (int i = 2; i <= 4; i++)
        {
            SBcc += (short)((short)TxData[i] & 0x00FF);
        }
        TxData[5] = (byte)((SBcc >> 8) & 0x00FF);
        TxData[6] = (byte)(SBcc & 0x00FF);
     
        try {
			mPrintWriterClient.write(TxData);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
       
    }
    /// <summary>
    /// 自主着陆
    /// </summary>
    /// <param name="FlightLanding_Flag"></param>
    public void wifi_SendAutoLanding(int FlightLanding_Flag)
    {
        byte[] TxData = new byte[7];
        short SBcc = 0;
        TxData[0] = (byte)0x3A;
        TxData[1] = (byte)0xA3;
        TxData[2] = (byte)0x07;
        TxData[3] = (byte)0x03;
        TxData[4] = (byte)(FlightLanding_Flag);
        for (int i = 2; i <= 4; i++)
        {
            SBcc += (short)((short)TxData[i] & 0x00FF);
        }
        TxData[5] = (byte)((SBcc >> 8) & 0x00FF);
        TxData[6] = (byte)(SBcc & 0x00FF);
        
        	try {
				mPrintWriterClient.write(TxData);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
       
    
    }

   /// <summary>
    /// //一键返航
    /// </summary>
    public void wifi_SendOneKeyToReturn_Line(int FlightReturn_Flag)
    {
        byte[] TxData = new byte[7];
        short SBcc = 0;
        TxData[0] = (byte)0x3A;
        TxData[1] = (byte)0xA3;
        TxData[2] = (byte)0x07;
        TxData[3] = (byte)0x04;
        TxData[4] = (byte)(FlightReturn_Flag);
        for (int i = 2; i <= 4; i++)
        {
            SBcc += (short)((short)TxData[i] & 0x00FF);
        }
        TxData[5] = (byte)((SBcc >> 8) & 0x00FF);
        TxData[6] = (byte)(SBcc & 0x00FF);
     
       
        try {
			mPrintWriterClient.write(TxData);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
       
      
    }
	public static void CalcBcc(byte[] TxData, int nByte) { // 计算校验和

		short sBcc = 0;

		for (int i = 2; i <= nByte; i++) {
			if (TxData[i] < 0) {
				TxData[i] = (byte) (TxData[i] + 256);
				sBcc += (TxData[i] & 0x00FF);
			} else
				sBcc += (TxData[i] & 0x00FF);
		}

		TxData[nByte + 1] = (byte) ((sBcc >> 8) & 0x00FF);
		TxData[nByte + 2] = (byte) (sBcc & 0x00FF);

		return;
	}

	/**
	 * 键盘监听
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (getCurrentFocus() != null
					&& getCurrentFocus().getWindowToken() != null) {
				InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				manager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
		return super.onTouchEvent(event);
	}
}
