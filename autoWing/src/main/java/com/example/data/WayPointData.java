package com.example.data;

import com.example.buff.GetIntData;


/**·������*/
public class WayPointData {
	int m_wayID;
	int m_wayCount;
	int m_wayIndex;
	
	public WayPointData(byte[] byBuff, int nOffset){
		m_wayID = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_wayCount = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_wayIndex = GetIntData.getInt(byBuff, nOffset + 4, 2);
	}
	
	public double getWayID(){
        return m_wayID ;
    }
	
	public double getWayCount(){
        return m_wayCount;
    }
	
	public double getWayIndex(){
        return m_wayIndex;
    }
}
