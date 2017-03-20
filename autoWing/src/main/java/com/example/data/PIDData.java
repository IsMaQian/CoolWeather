package com.example.data;

import com.example.buff.GetIntData;
public class PIDData {
	
    float m_Gyro_RP_KP,m_Gyro_RP_KI,m_Gyro_RP_KD;
	float m_Gyro_Y_KP, m_Gyro_Y_KI, m_Gyro_Y_KD;
	float m_Prop_RP_KP,m_Prop_RP_KI,m_Prop_Y_KP,m_Prop_Y_KI;
	int   Change_Pid_Send_Flag;
	 public PIDData(byte[] byBuff, int nOffset){
		 m_Gyro_RP_KP = GetIntData.getInt(byBuff, nOffset + 0, 2);
		 m_Gyro_RP_KI = GetIntData.getInt(byBuff, nOffset + 2, 2);
		 m_Gyro_RP_KD = GetIntData.getInt(byBuff, nOffset + 4, 2);
		 m_Gyro_Y_KP = GetIntData.getInt(byBuff, nOffset + 6, 2);
		 m_Gyro_Y_KI = GetIntData.getInt(byBuff, nOffset + 8, 2);
		 m_Gyro_Y_KD = GetIntData.getInt(byBuff, nOffset + 10, 2);
		 m_Prop_RP_KP = GetIntData.getInt(byBuff, nOffset + 12, 2);
		 m_Prop_RP_KI = GetIntData.getInt(byBuff, nOffset + 14, 2);
		 m_Prop_Y_KP = GetIntData.getInt(byBuff, nOffset + 16, 2);
		 m_Prop_Y_KI = GetIntData.getInt(byBuff, nOffset + 18, 2);
		// Change_Pid_Send_Flag = GetIntData.getInt(byBuff, nOffset + 20, 2);
		 
	 }	
		public double getIProp_RP_KI(){
	        return m_Prop_RP_KI/10.0;
	    }
		public double getIProp_RP_KP(){
	        return m_Prop_RP_KP/10.0;
	    }
		public double getIProp_Y_KI(){
	        return m_Prop_Y_KI/10.0;
	    }
		public double getIProp_Y_KP(){
	        return m_Prop_Y_KP/10.0;
	    }
		
		public double getIGyro_RP_KP(){
	        return m_Gyro_RP_KP/10.0;
	    }
		public double getIGyro_RP_KI(){
	        return m_Gyro_RP_KI/10.0;
	    }
		public double getIGyro_RP_KD(){
	        return m_Gyro_RP_KD/10.0;
	    }
		
		public double getIGyro_Y_KP(){
	        return m_Gyro_Y_KP/10.0;
	    }
		public double getIGyro_Y_KI(){
	        return m_Gyro_Y_KI/10.0;
	    }
		public double getIGyro_Y_KD(){
	        return m_Gyro_Y_KD/10.0;
	    }
		public double getIChange_Pid_Send_Flag(){
			return Change_Pid_Send_Flag;
		}
		
}