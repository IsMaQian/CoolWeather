package com.example.EventBus;

public class IMU_EventBus {
	
	double m_iRoll;          //¹ö×ª½Ç
	double m_iPitch;         //¸©Ñö½Ç
	double m_iYaw;           //º½Ïò½Ç
	double m_iRollRate;      //ÍÓÂÝÒÇRollrate
	double m_iPichRate;      //ÍÓÂÝÒÇPitchrate
	double m_iYawRate;		  //ÍÓÂÝÒÇYawrate
	
	public IMU_EventBus(double Roll,double Pitch,double Yaw,double RollRate,double PichRate,double YawRate) {
		this.m_iRoll = Roll;
		this.m_iPitch = Pitch;
		this.m_iYaw = Yaw;
		this.m_iRollRate = RollRate;
		this.m_iPichRate = PichRate;
		this.m_iYawRate = YawRate;
	}
	
	public double getIRoll(){
        return m_iRoll;
    }
	
	public double getIPitch(){
		return m_iPitch;
	}
	
    public double getIYaw(){
        return m_iYaw;
    }
    
    public double getIRollRate(){
        return m_iRollRate;
    }
    
    public double getIPitchRate(){
        return m_iPichRate;
    }
    
    public double getIYawRate(){
        return m_iYawRate;
    }
}
