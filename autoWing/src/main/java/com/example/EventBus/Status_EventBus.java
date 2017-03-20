package com.example.EventBus;

public class Status_EventBus {
	double m_nFlyStatus;   //飞行模式
	double m_nVoltage;     //电压
	double m_nSendFlag;    //发送标志
	double m_nSensorStatus;//传感器状态
	double m_nCommStatus;  //通信状态
	double m_nPhotoFlag;   //拍照状态
	
	public Status_EventBus(double m_nFlyStatus,double m_nVoltage,
			double m_nSensorStatus) {
		this.m_nFlyStatus = m_nFlyStatus;
		this.m_nVoltage = m_nVoltage;
		this.m_nSensorStatus = m_nSensorStatus;
		
	
	}
//	public Status_EventBus(double m_nFlyStatus,double m_nVoltage,double m_nSendFlag,
//			double m_nSensorStatus,double m_nCommStatus,double m_nPhotoFlag) {
//		this.m_nFlyStatus = m_nFlyStatus;
//		this.m_nVoltage = m_nVoltage;
//		this.m_nSendFlag = m_nSendFlag;
//		this.m_nSensorStatus = m_nSensorStatus;
//		this.m_nCommStatus = m_nCommStatus;
//		this.m_nPhotoFlag = m_nPhotoFlag;
//	}
	public double getFlyStatus(){
		return m_nFlyStatus;
	}
	
	public double getVoltage(){
		return m_nVoltage;
	}
	
	public double getSendFlag(){
		return m_nSendFlag;
	}
	
	public double getSensorStatus(){
		return m_nSensorStatus;
	}
	
	public double getCommStatus(){
		return m_nCommStatus;
	}
	
	public double getPhotoFlag(){
		return m_nPhotoFlag;
	}
}
