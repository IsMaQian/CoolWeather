package com.example.data;

import com.example.buff.GetIntData;

/**XBEEÍ¨¶Ï¼ì²âÊý¾Ý*/
public class XBEEData {
	double m_nXBEE;
	
	public XBEEData(byte[] byBuff, int nOffset){
		m_nXBEE = GetIntData.getInt(byBuff, nOffset + 0, 2);
	}
	
	public double getXBEE(){
        return m_nXBEE;
    }
}
