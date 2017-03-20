package com.example.EventBus;

public class Speed_EventBus {
	double m_nLateral;         //横向速度
	double m_nLongitudinal;    //纵向速度
	double m_nUpdown;           //上下速度
	
	
	public Speed_EventBus(double m_nLateral,double m_nLongitudinal,double m_nUpdown) {
		this.m_nLateral = m_nLateral;
		this.m_nLongitudinal = m_nLongitudinal;
		this.m_nUpdown = m_nUpdown;
	}
	public double getLateral(){
		return m_nLateral;
	}
	
	public double getLongitudinal(){
		return m_nLongitudinal;
	}
	
	public double getUpdown(){
		return m_nUpdown;
	}
	
}
