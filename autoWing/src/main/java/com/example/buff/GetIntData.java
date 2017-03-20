package com.example.buff;

public class GetIntData {
	///byBuff:�ֽ����飬Դ����
	///nOffset���ӵڼ����ֽڿ�ʼ��ȡInt
	///nSize:Ҫ��ȡ���ٸ��ֽڣ�һ����4���ֽ�
	public  static short getInt(byte[] byBuff, int nOffset, int nSize) {
	    short nRes = 0;
//	    for (int i = 0; i < nSize; i++) {
//			if (byBuff[i] < 0) {
//				byBuff[i] = (byte) (byBuff[i] + 256);
//			}
//		}
	    
	    if (nOffset + nSize > byBuff.length){
	    	return 0;
	    }
//	    nRes = (short) (((byBuff[nOffset+0] & 0x00FF))<<8);
//	    nRes = (short) (nRes | ((byBuff[nOffset+1] & 0x00FF)));
//	    nRes = ((nRes<<16) & 0x80000000);
	    //nRes = ((nRes & 0x8000)<<16)|((nRes & 0x7FFF));
	    for (int i = 0; i < nSize; i++){
	        nRes = (short) (nRes |(short)( (byBuff[i + nOffset] & 0x00FF)<< (8 * (nSize-i-1)) ));
	    }
	    return nRes;
	}

}
