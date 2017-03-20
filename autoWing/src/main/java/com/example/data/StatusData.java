package com.example.data;

import com.example.buff.GetIntData;

/**状态数据*/
public class StatusData {
	double m_nFlyStatus;   //飞行模式
	double m_nVoltage;     //电压
	double m_nSendFlag;    //发送标志
	double m_nSensorStatus;//传感器状态
	double m_nCommStatus;  //通信状态
	double m_nPhotoFlag;   //拍照状态
	
	public StatusData(byte[] byBuff, int nOffset){
//		m_nFlyStatus = GetIntData.getInt(byBuff, nOffset + 0, 2);
//		m_nVoltage = GetIntData.getInt(byBuff, nOffset + 2, 2);
//		m_nSendFlag = GetIntData.getInt(byBuff, nOffset + 4, 2);
//		m_nSensorStatus = GetIntData.getInt(byBuff, nOffset + 6, 2);
//		m_nCommStatus = GetIntData.getInt(byBuff, nOffset + 8, 2);
//		m_nPhotoFlag = GetIntData.getInt(byBuff, nOffset + 10, 2);
		
		m_nVoltage = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_nFlyStatus = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_nSensorStatus = GetIntData.getInt(byBuff, nOffset + 4, 2);
		//m_nCommStatus = GetIntData.getInt(byBuff, nOffset + 6, 2);
	}
	
	public double getFlyStatus(){
		return m_nFlyStatus;
	}
	
	public double getVoltage(){
		return m_nVoltage / 100.0;
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
