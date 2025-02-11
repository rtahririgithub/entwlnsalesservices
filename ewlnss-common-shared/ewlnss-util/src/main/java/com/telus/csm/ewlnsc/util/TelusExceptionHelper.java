package com.telus.csm.ewlnsc.util;

import java.util.List;

public class TelusExceptionHelper {
	
	
	public static String concatFaultInfo(List<String> list){
		
		StringBuffer strBuf = new StringBuffer();
		for (String str : list){
			strBuf.append(str);
			strBuf.append(" ");
		}
		
		return strBuf.toString();
	}
	
	public static String concatFaultInfo(String[] list){
        
        StringBuffer strBuf = new StringBuffer();
        for (String str : list){
            strBuf.append(str);
            strBuf.append(" ");
        }
        
        return strBuf.toString();
    }
}