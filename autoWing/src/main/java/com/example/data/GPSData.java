package com.example.data;

import com.example.buff.GetIntData;
import com.example.buff.GetLongData;

/**GPS数据*/
public class GPSData {
	//因为经纬度需要8个字节，所以不能用int来存，需要用long型
	double m_nLatitude;      //纬度
	double m_nLongitude;     //经度
	double m_nStarCount;     //卫星数量
	double m_nHight;         //高度
	double m_nHeading;		  //航向
	double m_Bara_Alt;		  //气压计
	double m_nGPSDop;		  // DOP值
	
	
	public GPSData(byte[] byBuff, int nOffset){
//		m_nLatitude = GetLongData.getDouble(byBuff, nOffset + 0, 8);
//		m_nLongitude = GetLongData.getDouble(byBuff, nOffset + 8, 8);
//		m_nStarCount = GetIntData.getInt(byBuff, nOffset + 16, 2);
//		m_nHight = GetIntData.getInt(byBuff, nOffset + 18, 2);
		
		m_nLongitude= GetLongData.getDouble(byBuff, nOffset,8);
		m_nLatitude= GetLongData.getDouble(byBuff, nOffset+8,8);
		m_nHight = GetIntData.getInt(byBuff, nOffset + 16, 3);
		m_nHeading = GetIntData.getInt(byBuff, nOffset + 19, 2);
		m_nStarCount = GetIntData.getInt(byBuff, nOffset + 21, 2);
		m_nGPSDop = GetIntData.getInt(byBuff, nOffset + 23, 2);
		m_Bara_Alt = GetIntData.getInt(byBuff, nOffset + 25, 3);
	}    
	
	public double getLatitude(){
		return m_nLatitude;
	}
	
	public double getLongitude(){
		return m_nLongitude;
	}
	
	public double getStarCount(){
		return m_nStarCount;
	}
	
	public double getHight(){
		return m_nHight / 100.0;
	}
	public double getBaraAlt(){
		return m_Bara_Alt / 100.0;
	}
	public double getGpsDop(){
		return m_nGPSDop / 100.0;
	}
	public double getHeading(){
		return m_nHeading / 100.0;
	}
}
