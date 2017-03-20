package com.example.EventBus;

public class Geomagnetism_EventBus {
	double m_gX;
	double m_gY;
	double m_gZ;
	
	public Geomagnetism_EventBus(double m_gX,double m_gY,double m_gZ) {
		this.m_gX = m_gX;
		this.m_gY = m_gY;
		this.m_gZ = m_gZ;
	}
	public double getX(){
		return m_gX;
	}
	
	public double getY(){
		return m_gY;
	}
	
	public double getZ(){
		return m_gZ;
	}
}
