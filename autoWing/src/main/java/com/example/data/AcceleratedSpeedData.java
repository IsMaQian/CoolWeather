package com.example.data;

import com.example.buff.GetIntData;

/**加速度数据*/
public class AcceleratedSpeedData {
	double m_aX;
	double m_aY;
	double m_aZ;
	
	public AcceleratedSpeedData(byte[] byBuff, int nOffset){
		m_aX = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_aY = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_aZ = GetIntData.getInt(byBuff, nOffset + 4, 2);
	}
	public double getX(){
        return m_aX/1000.0;
    }
	
	public double getY(){
        return m_aY/1000.0;
    }
	
	public double getZ(){
        return m_aZ/1000.0;
    }
}
