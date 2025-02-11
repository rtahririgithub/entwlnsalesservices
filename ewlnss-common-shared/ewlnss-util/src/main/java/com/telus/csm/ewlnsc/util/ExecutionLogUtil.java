package com.telus.csm.ewlnsc.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.MDC;

public class ExecutionLogUtil {
	private static final int MAX_MESSAGE_LINELEN=40;
	private static final String NEW_LINE="\n";
	private Map<String, String> messageList = new LinkedHashMap<String,String>();
	private String operationName;
	private String txnId ;
	public ExecutionLogUtil(String operation,String txnId){
		this.operationName	=operation;
		this.txnId			= txnId;
	}
	public void add(String message,String key,String value){
		String existingVal= messageList.get(message);
		String newVal=new StringBuilder(key).append("=").append(value).toString();
		if(existingVal==null){
			messageList.put(message, newVal);
		}
		else{
			messageList.put(message, new StringBuilder(existingVal).append(",").append(newVal).toString());
		}
	}
	public void add(String message,String key,boolean value){
		add( message, key, Boolean.toString(value));
	}
	public void add(String message,String key,int value){
		add( message, key, Integer.toString(value));
	}
	public void add(String message,String key,Double value){
		add( message, key, value!=null?value.toString():null);
	}
	public void add(String message,String key,Set<String> value){
		add( message, key, value!=null?value.toString():null);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("operation_name:").append(operationName).append(NEW_LINE)
				.append("transaction_id:").append(txnId).append(NEW_LINE);
		if(messageList.keySet()!=null){
			for(Map.Entry<String, String> entry : messageList.entrySet()){
				sb.append(getLableLine(entry.getKey())).append(":").append(entry.getValue()).append(NEW_LINE);
			}
		}
		return sb.toString();
	}
	
	public String getLableLine(String txtLine){
		String textLine = txtLine;
		int pointer;
		StringBuilder multiLine= new StringBuilder();
		while(textLine.length()>40)		{
			pointer = textLine.substring(0,40).lastIndexOf(' ');
			if (pointer==-1){
				pointer=40;
			}
			multiLine.append(textLine.substring(0,pointer)).append(NEW_LINE);
			textLine = textLine.substring(pointer);
		}
		if(textLine.length()>0){
			multiLine.append(textLine);
		}
		int lastLineLen = multiLine.lastIndexOf(NEW_LINE)>-1
				? multiLine.substring(multiLine.lastIndexOf(NEW_LINE)).length()
						:multiLine.length();
		multiLine.append(leftPadding(lastLineLen));
		return multiLine.toString();
	}

	private static String leftPadding(int padLen) {
		StringBuilder padding = new StringBuilder();
		int i;
		for(i=0;i<MAX_MESSAGE_LINELEN-padLen;i++){
			padding.append(".");
		}
		return padding.toString();
	}
	
	public static void initThreadLog() {

		MDC.clear();
		
		Thread currentThread = Thread.currentThread();
		String threadState = currentThread.getName();
		int endIndex = threadState.indexOf(']');
		if (endIndex > 0 ) {
			threadState = threadState.substring(0, endIndex+1);
			MDC.put("threadState", threadState);
		}
		
		MDC.put("threadId", " Thread:" + currentThread.getId());

	}
}
