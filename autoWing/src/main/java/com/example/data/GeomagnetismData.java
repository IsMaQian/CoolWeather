package com.example.data;

import com.example.buff.GetIntData;

/**�ش�����*/
public class GeomagnetismData {
	double m_gX;
	double m_gY;
	double m_gZ;
	
	public GeomagnetismData(byte[] byBuff, int nOffset){
		m_gX = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_gY = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_gZ = GetIntData.getInt(byBuff, nOffset + 4, 2);
	}
	
	public double getX(){
        return m_gX/1000.0;
    }
	
	public double getY(){
        return m_gY/1000.0;
    }
	
	public double getZ(){
        return m_gZ/1000.0;
    }
}
