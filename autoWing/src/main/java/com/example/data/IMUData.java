package com.example.data;
import com.example.buff.GetIntData;

/**IMUÊý¾Ý*/
public class IMUData {
	double m_iRoll;          //¹ö×ª½Ç
	double m_iPitch;         //¸©Ñö½Ç
	double m_iYaw;           //º½Ïò½Ç
	double m_iRollRate;      //ÍÓÂÝÒÇRollrate
	double m_iPichRate;      //ÍÓÂÝÒÇPitchrate
	double m_iYawRate;		  //ÍÓÂÝÒÇYawrate
    
    public IMUData(byte[] byBuff, int nOffset){
    	m_iRoll = GetIntData.getInt(byBuff, nOffset + 0, 2);
    	m_iPitch = GetIntData.getInt(byBuff, nOffset + 2, 2);
    	m_iYaw = GetIntData.getInt(byBuff, nOffset + 4, 2);
    	m_iRollRate = GetIntData.getInt(byBuff, nOffset + 6, 2);
    	m_iPichRate = GetIntData.getInt(byBuff, nOffset + 8, 2);
    	m_iYawRate = GetIntData.getInt(byBuff, nOffset + 10, 2);
    }
   
	public double getIRoll(){
        return m_iRoll/100.0;
    }
    public double getIPitch(){
        return m_iPitch/100.0;
    }
    public double getIYaw(){
        return m_iYaw/100.0;
    }
    public double getIRollRate(){
        return m_iRollRate/1000.0;
    }
    public double getIPitchRate(){
        return m_iPichRate/1000.0;
    }
    public double getIYawRate(){
        return m_iYawRate/1000.0;
    }
}
