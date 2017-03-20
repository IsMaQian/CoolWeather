package com.example.EventBus;

public class PID_Eventbus {
    float m_Gyro_RP_KP,m_Gyro_RP_KI,m_Gyro_RP_KD;
	float m_Gyro_Y_KP, m_Gyro_Y_KI, m_Gyro_Y_KD;
	float m_Prop_RP_KP,m_Prop_RP_KI,m_Prop_Y_KP,m_Prop_Y_KI;
	
	public  PID_Eventbus(float Gyro_RP_KP,float Gyro_RP_KI,float Gyro_RP_KD,
			float Gyro_Y_KP,float Gyro_Y_KI,float Gyro_Y_KD,
			float Prop_RP_KP,float Prop_RP_KI,float Prop_Y_KP, float Prop_Y_KI) {
		this.m_Gyro_RP_KP = Gyro_RP_KP;
		this.m_Gyro_RP_KI = Gyro_RP_KI;
		this.m_Gyro_RP_KD = Gyro_RP_KD;
		this.m_Gyro_Y_KP = Gyro_Y_KP;
		this.m_Gyro_Y_KI = Gyro_Y_KI;
		this.m_Gyro_Y_KD = Gyro_Y_KD;
		this.m_Prop_RP_KP = Prop_RP_KP;
		this.m_Prop_RP_KI = Prop_RP_KI;
		this.m_Prop_Y_KP = Prop_Y_KP;
		this.m_Prop_Y_KI = Prop_Y_KI;
	}
	
	public float getGyro_RP_KP(){
        return m_Gyro_RP_KP;
    }
	
	public float getGyro_RP_KI(){
		return m_Gyro_RP_KI;
	}
	
    public float getGyro_RP_KD(){
        return m_Gyro_RP_KD;
    }
    
    public float getGyro_Y_KP(){
        return m_Gyro_Y_KP;
    }
    
    public float getGyro_Y_KI(){
        return m_Gyro_Y_KI;
    }
    
    public float getGyro_Y_KD(){
        return m_Gyro_Y_KD;
    }
    public float getProp_RP_KP(){
        return m_Prop_RP_KP;
    }
    public float getProp_RP_KI(){
        return m_Prop_RP_KI;
    }
    public float getProp_Y_KP(){
        return m_Prop_Y_KP;
    }
    public float getProp_Y_KI(){
        return m_Prop_Y_KI;
    }
}
