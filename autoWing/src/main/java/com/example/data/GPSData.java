package com.example.data;

import com.example.buff.GetIntData;
import com.example.buff.GetLongData;

/**GPS����*/
public class GPSData {
	//��Ϊ��γ����Ҫ8���ֽڣ����Բ�����int���棬��Ҫ��long��
	double m_nLatitude;      //γ��
	double m_nLongitude;     //����
	double m_nStarCount;     //��������
	double m_nHight;         //�߶�
	double m_nHeading;		  //����
	double m_Bara_Alt;		  //��ѹ��
	double m_nGPSDop;		  // DOPֵ
	
	
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
