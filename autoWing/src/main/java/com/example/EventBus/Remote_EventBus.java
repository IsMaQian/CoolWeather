package com.example.EventBus;

public class Remote_EventBus {
	double m_rRoll;
	double m_rPitch;
	double m_rYaw;
	double m_rThrottle;
	
	public Remote_EventBus(double m_rRoll,double m_rPitch,double m_rYaw,double m_rThrottle) {
		this.m_rRoll = m_rRoll;
		this.m_rPitch = m_rPitch;
		this.m_rYaw = m_rYaw;
		this.m_rThrottle = m_rThrottle;
	}
	public double getRoll(){
        return m_rRoll;
    }
	
	public double getPitch(){
        return m_rPitch;
    }
	
	public double getYaw(){
        return m_rYaw;
    }
	
	public double getThrottle(){
        return m_rThrottle;
    }
}
