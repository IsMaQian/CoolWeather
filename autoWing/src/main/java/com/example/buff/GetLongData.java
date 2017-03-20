package com.example.buff;

public class GetLongData {
	public static double getDouble(byte[] byBuff, int nOffset, int nSize) {
	    long nRes = 0;
	    
	    byte[] btef = new byte[8];
	    for (int i = 0; i < 8; i++) {
	    	if (byBuff[i] < 0) {
	    		btef[i] = (byte)(byBuff[i+nOffset] +256);
			}else {
				btef[i] = (byte)byBuff[i+nOffset];
			}
		}
	    
	    nRes = btef[0];
	    nRes &= 0xff;
	    nRes |= ((long) btef[1] << 8);
	    nRes &= 0xffff;
	    nRes |= ((long) btef[2] << 16);
	    nRes &= 0xffffff;
	    nRes |= ((long) btef[3] << 24);
	    nRes &= 0xffffffffl;
	    nRes |= ((long) btef[4] << 32);
	    nRes &= 0xffffffffffl;
	    nRes |= ((long) btef[5] << 40);
	    nRes &= 0xffffffffffffl;
	    nRes |= ((long) btef[6] << 48);
	    nRes &= 0xffffffffffffffl;
	    nRes |= ((long) btef[7] << 56);
	    return Double.longBitsToDouble(nRes);
	}

}
