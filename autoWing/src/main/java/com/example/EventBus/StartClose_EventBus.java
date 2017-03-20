package com.example.EventBus;

public class StartClose_EventBus {
	double m_SW1;
	double m_SW2;
	double m_SW3;
	double m_SW4;
	
	
	public StartClose_EventBus(double m_SW1,double m_SW2,double m_SW3,double m_SW4) {
		this.m_SW1 = m_SW1;
		this.m_SW2 = m_SW2;
		this.m_SW3 = m_SW3;
		this.m_SW4 = m_SW4;
	}
	public double getSW1(){
        return m_SW1;
    }
	
	public double getSW2(){
        return m_SW2;
    }
	
	public double getSW3(){
        return m_SW3;
    }
	
	public double getSW4(){
        return m_SW4;
    }
}
