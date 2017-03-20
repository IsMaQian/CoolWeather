package com.example.EventBus;

public class WayPoint_EventBus {
	int m_wayID;
	int m_wayCount;
	int m_wayIndex;
	
	
	public WayPoint_EventBus(int m_wayID,int m_wayCount,int m_wayIndex) {
		this.m_wayID = m_wayID;
		this.m_wayCount = m_wayCount;
		this.m_wayIndex = m_wayIndex;
	}
	public int getWayID(){
        return m_wayID ;
    }
	
	public int getWayCount(){
        return m_wayCount;
    }
	
	public int getWayIndex(){
        return m_wayIndex;
    }
}
