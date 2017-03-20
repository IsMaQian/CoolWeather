package com.example.EventBus;

public class Motor_EventBus {
	double m_Motor_Front;
	double m_Motor_Back;
	double m_Motor_Left;
	double m_Motor_Right;
	double m_Motor_X;
	double m_Motor_Y;
	
	
	public Motor_EventBus(double m_Motor_Front,double m_Motor_Back,double m_Motor_Left,
				double m_Motor_Right,double m_Motor_X,double m_Motor_Y) {
		this.m_Motor_Front = m_Motor_Front;
		this.m_Motor_Back = m_Motor_Back;
		this.m_Motor_Left = m_Motor_Left;
		this.m_Motor_Right = m_Motor_Right;
		this.m_Motor_X = m_Motor_X;
		this.m_Motor_Y = m_Motor_Y;
	}
	public double getMotor_Front(){
        return m_Motor_Front;
    }
	
	public double getMotor_Back(){
        return m_Motor_Back;
    }
	
	public double getMotor_Left(){
        return m_Motor_Left;
    }
	
	public double getMotor_Right(){
        return m_Motor_Right;
    }
	
	public double getMotor_X(){
        return m_Motor_X;
    }
	
	public double getMotor_Y(){
        return m_Motor_Y;
    }
}
