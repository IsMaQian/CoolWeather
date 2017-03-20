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
 * ����ҳ��
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

	String IPStr;// ����������ַ
	String Client_message;// ������Ϣ
	String Client_log;// ��ʾ������Ϣ
	String Receive_log;// ��ʾ������Ϣ
	String textlog;

	static int newIPSegment = 0;// �޷���

	/** IMU���� */
	private String strRoll;// ��ת��
	private String strPitch;// ������
	private String strYaw;// �����
	private String strRollRate;// ������Rollrate
	private String strPichRate;// ������Pitchrate
	private String strYawRate;// ������Yawrate

	/** ���ٶ����� */
	private String strAccX;
	private String strAccY;
	private String strAccZ;

	/** �ش����� */
	private String strGeoX;
	private String strGeoY;
	private String strGeoZ;

	/** ң�������� */
	private String strReRoll;
	private String strRePitch;
	private String strReYaw;
	private String strReThrottle;

	/** �������� */
	private String strSW1;
	private String strSW2;
	private String strSW3;
	private String strSW4;

	/** ·������ */
	private String strWayID;
	private String strWayCount;
	private String strWayIndex;

	/** ������� */
	private String strMotor_Front;
	private String strMotor_Back;
	private String strMotor_Left;
	private String strMotor_Right;
	private String strMotor_X;
	private String strMotor_Y;

	/** �ٶ����� */
	private String strLateral;
	private String strLongitudinal;
	private String strUpdown;

	/** GPS���� */
	private String strLatitude;
	private String strLongitude;
	private String strStarCount;
	private String strHight;
	static String strDop;
	static String strBaraAlt;
	static String strHeading;

	/** ״̬���� */
	private String strFlyStatus;
	private String strVoltage;
	private String strSendFlag;
	private String strSensorStatus;
	private String strCommStatus;
	private String strPhotoFlag;

	/** XBEEͨ�ϼ������ */
	private String strXBEE;

	/** PID���� */
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
	public int PID_Send_Flg = 0;// PID�������ͱ�־
	/** ������Ϣ */
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

	/** ������Ϣ */
	String sIP;
	String sPort;
	int port;
	int start = 0;

	/** ����������� */
	public static boolean isConnecting = false;
	private Socket mSocketClient = null;
	static BufferedOutputStream mPrintWriterClient = null;
	static BufferedInputStream mBufferedReaderClient = null;
	Thread mThreadClient;

	/**
	 * IP��ַ��ʷ����
	 */
	InputMethodManager manager;
	public static final String SEARCH_HISTORY = "search_history";
	private SearchAutoAdapter mSearchAutoAdapter;
	// private ListView mAutoList;
	private SharedPreferences IPsharep;
	/** ����������� */

	int bodylen = -1;
	SelectionKey sk;
	static final int MESSAGE_LENGTH_HEAD = 2;// �����

	packageType dataType;
	GetIntData getInt;

	/** IMU���� */
	IMUData IMU;
	/** ���ٶ����� */
	AcceleratedSpeedData accspeed;
	/** �ش����� */
	GeomagnetismData geoData;
	/** ң�������� */
	RemoteControlData remoteData;
	/** �������� */
	StartCloseData closeData;
	/** ·������ */
	WayPointData wayPointData;
	/** ������� */
	MotorData motorData;
	/** �ٶ����� */
	SpeedData speedData;
	/** GPS���� */
	GPSData gpsData;
	/** ״̬���� */
	StatusData statusData;
	/** XBEEͨ�ϼ������ */
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

	// ״̬��
	private void initWindow() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// ͸��������
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

		//IPStr = ed_IP.getText().toString(); // ��ȡд���IPֵ

		/**
		 * ��ӵļ�������
		 */
//		StartBtn.setOnClickListener(StartConnectListener);
//		SendBtn.setOnClickListener(SendClientListener);
//
//		offlinemap.setOnClickListener(OfflinemapListener);
//		Disp_recevierBtn.setOnClickListener(Disp_recevierListener);

	}

	public boolean isIPValid() {

		sIP = "192.168.2.1";// IPStr.substring(0, start);//��ȡIP��ַ
		sPort = "8000";// IPStr.substring(start + 1);//��ȡ���ں�
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
	 * ����������¼ public void saveSearchHistory() { String longhistory =
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
	 * ��������
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
			//StartBtn.setText("�ѶϿ�");
			handler.removeCallbacks(mThreadClient);
		} else {
			isConnecting = true;
			//StartBtn.setText("������");
			// ����һ��������������߳�
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
				//StartBtn.setText("�ѶϿ�");
				// StartBtn.setEnabled(false);
				// ed_IP.setEnabled(false);
				handler.removeCallbacks(mThreadClient);
			} else {
				isConnecting = true;

				//StartBtn.setText("������");
				// StartBtn.setEnabled(true);
				// ed_IP.setEnabled(true);
				// ����һ��������������߳�
				mThreadClient = new Thread(new StartRunnable());
				mThreadClient.start();

			}
		}
	};

	/**
	 * ��������Խ��߳�
	 * 
	 * @author android_wbin
	 */
	class StartRunnable implements Runnable {
		@Override
		public void run() {
			if (SettingActivity.this.isIPValid()) {
				try {
					// ���ӷ�����
					mSocketClient = new Socket(sIP, port); // portnum
					// ȡ�����롢�����
					mBufferedReaderClient = new BufferedInputStream(
							mSocketClient.getInputStream());
					mPrintWriterClient = new BufferedOutputStream(
							mSocketClient.getOutputStream());
					// handler.post(r)��r��Ҫִ�е�������롣��˼����˵r�Ĵ���ʵ������UI�߳�ִ�еġ�
					// ����д����UI�Ĵ��롣�������߳��ǲ��ܸ���UI�ģ�
					handler.post(receiver);
					System.out.println("<<<<<<<<<<<<<<<<<<<������IP");
					new Thread(new WriteData()).start();
					Looper.prepare();
					
//					Toast.makeText(null, "������IP",
//							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} catch (Exception e) {
					Log.e(Tag, e.toString() + e.getMessage());
					Looper.prepare();
//					Toast.makeText(null, "����IP�쳣",
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
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}
	}

	int clientCheckAnd;// �ͻ���У���
	int serverCheckAnd;// �����У���
	// 11������ ��Ӧÿ�����ݰ��ĳ���
	int dataPacketSize;
	int count = 0;
	// ���ܵ�������
	byte[] charBuffer = new byte[153];
	// ������±�
	int dataIndex;
	// ����ͷ��3���ֽ�
	byte[] dataHeaderBytes = new byte[5];
	// �ܵ�����
	byte[] allData = new byte[256];
	// ���ݴ�С
	int nSize = allData.length;
	byte[] m_byBuff;
	boolean dataFlag = false;

	/**
	 * ��ȡ�����յ��������� ת��
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
								// System.out.println(">>>>>>>>>>>>>>>>>>>>�ӵ�����");
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
											// ����õĸ�������ת������������ss����
											for (int x = 0; x < dataPacketSize; x++) {
												if (allData[x] < 0) {
													allData[x] = (byte) (allData[x] + 256);
												} else {
													allData[x] = (byte) allData[x];
												}
											}
											clientCheckAnd = 0;
											serverCheckAnd = 0;
											// ����У���
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
												// ȡID
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
													PID_Send_Flg = (allData[3] & 0x0000001f);// ����flag
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
										 * // * �Ȼ��ǰ5���ֽ�(2����ͷ1��ID),����Data��,
										 * �����cIndexHeader //
										 **/
										if (bytes < 0) {
											bytes = (byte) (bytes + 256);
										} else {
											bytes = (byte) bytes;
										}
										/*
										 * �Ȼ��ǰ3���ֽ�(2����ͷ1��ID),����allData��,
										 * �����dataHeaderBytes,
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
						Toast.makeText(SettingActivity.this, "���������쳣",
								Toast.LENGTH_SHORT).show();
						Looper.loop();
					}
				}

			}.start();

		}

	};
	/** �������������Ϣ */
	private OnClickListener SendClientListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

		}
	};

	/** ���ߵ�ͼ���� */
	private OnClickListener OfflinemapListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intentofflmap = new Intent(SettingActivity.this,
					OfflineMapActivity.class);
			startActivity(intentofflmap);
		}
	};

	/** ��ʾ������Ϣ */
	private OnClickListener Disp_sendListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

		}
	};

	/** ��ʾ������Ϣ */
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
	double Latitude, Longitude;// ��γ��
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
	 * ��ȡ�����ݴ���MainActivity
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

				// Acc_log.setText("X������ٶ�Ϊ��"+strAccX );
				AccX = Double.parseDouble(strAccX);
				AccY = Double.parseDouble(strAccY);
				AccZ = Double.parseDouble(strAccZ);
				EventBus.getDefault().post(
						new Accelerated_EventBus(AccX, AccY, AccZ));

				// Geo_log.setText("X����ش�����Ϊ��"+strGeoX);
				GeoX = Double.parseDouble(strGeoX);
				GeoY = Double.parseDouble(strGeoY);
				GeoZ = Double.parseDouble(strGeoZ);
				EventBus.getDefault().post(
						new Geomagnetism_EventBus(GeoX, GeoY, GeoZ));

				// Re_log.setText("ң����Roll��Ϊ��"+strReRoll);
				ReRoll = Double.parseDouble(strReRoll);
				RePitch = Double.parseDouble(strRePitch);
				ReYaw = Double.parseDouble(strReYaw);
				ReThrottle = Double.parseDouble(strReThrottle);
				EventBus.getDefault()
						.post(new Remote_EventBus(ReRoll, RePitch, ReYaw,
								ReThrottle));

				// SW_log.setText("����SW1Ϊ��"+strSW1 );

				SW1 = Double.parseDouble(strSW1);
				SW2 = Double.parseDouble(strSW2);
				SW3 = Double.parseDouble(strSW3);
				SW4 = Double.parseDouble(strSW4);
				EventBus.getDefault().post(
						new StartClose_EventBus(SW1, SW2, SW3, SW4));

				// Motor_log.setText("Motor_FrontΪ��"+strMotor_Front );
				Motor_Front = Double.parseDouble(strMotor_Front);
				Motor_Back = Double.parseDouble(strMotor_Back);
				Motor_Left = Double.parseDouble(strMotor_Left);
				Motor_Right = Double.parseDouble(strMotor_Right);
				Motor_X = Double.parseDouble(strMotor_X);
				Motor_Y = Double.parseDouble(strMotor_Y);
				EventBus.getDefault().post(
						new Motor_EventBus(Motor_Front, Motor_Back, Motor_Left,
								Motor_Right, Motor_X, Motor_Y));

				// Speed_log.setText("�����ٶ�Ϊ��"+strLateral );
				Lateral = Double.parseDouble(strLateral);
				Longitudinal = Double.parseDouble(strLongitudinal);
				Updown = Double.parseDouble(strUpdown);
				EventBus.getDefault().post(
						new Speed_EventBus(Lateral, Longitudinal, Updown));

				// GPS_log.setText("γ��Ϊ��"+strLatitude );
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

				// State_log.setText("����ģʽΪ��"+strFlyStatus);
				FlyStatus = Double.parseDouble(strFlyStatus);
				Voltage = Double.parseDouble(strVoltage);
				// SendFlag = Double.parseDouble(strSendFlag);
				SensorStatus = Double.parseDouble(strSensorStatus);
				// CommStatus = Double.parseDouble(strCommStatus);
				// PhotoFlag = Double.parseDouble(strPhotoFlag);
				EventBus.getDefault().post(
						new Status_EventBus(FlyStatus, Voltage, SensorStatus));

				// XBEE_log.setText("����һ��255���ݣ�"+strXBEE +blank);
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
    /// �������
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
       
    }
    /// <summary>
    /// ������½
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
       
    
    }

   /// <summary>
    /// //һ������
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
       
      
    }
	public static void CalcBcc(byte[] TxData, int nByte) { // ����У���

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
	 * ���̼���
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
