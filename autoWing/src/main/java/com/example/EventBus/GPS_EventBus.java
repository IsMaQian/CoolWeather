package com.example.EventBus;


public class GPS_EventBus {
	double m_nLatitude;      //纬度
	double m_nLongitude;     //经度
	double m_nStarCount;     //卫星数量
	double m_nHight;         //高度
	double m_nGpsDop;			//DOP值
	double m_nBaraAlt;		//气压计值
	double m_nHeading;		//航向角
	
	
	public GPS_EventBus(double m_nLatitude,double m_nLongitude,double m_nStarCount,double m_nHight,double m_nGpsDop,double m_nBaraAlt,double m_nHeading) {
		this.m_nLatitude = m_nLatitude;
		this.m_nLongitude = m_nLongitude;
		this.m_nStarCount = m_nStarCount;
		this.m_nHight = m_nHight;
		this.m_nGpsDop = m_nGpsDop;
		this.m_nBaraAlt = m_nGpsDop;
		this.m_nHeading = m_nGpsDop;
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
		return m_nHight;
	}
	public double getGpsDop(){
		return m_nGpsDop;
	}
	
	public double getBaraAlt(){
		return m_nBaraAlt;
	}
	
	public double getHeading(){
		return m_nHeading;
	}
	
}
