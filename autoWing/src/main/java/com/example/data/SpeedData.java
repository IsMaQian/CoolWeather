package com.example.data;

import com.example.buff.GetIntData;

/**�ٶ�����*/
public class SpeedData {
	double m_nLateral;         //�����ٶ�
	double m_nLongitudinal;    //�����ٶ�
	double m_nUpdown;           //�����ٶ�
	
	public SpeedData(byte[] byBuff, int nOffset){
		m_nLateral = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_nLongitudinal = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_nUpdown = GetIntData.getInt(byBuff, nOffset + 4, 2);
	}
	
	public double getLateral(){
		return m_nLateral / 100.0;
	}
	
	public double getLongitudinal(){
		return m_nLongitudinal / 100.0;
	}
	
	public double getUpdown(){
		return m_nUpdown / 100.0;
	}
}
