package com.example.EventBus;

public class CountDownEvent {
	private String mMsg;
	public CountDownEvent(String msg) {
		// TODO Auto-generated constructor stub
		mMsg = msg;
	}
	public String getMsg(){
		return mMsg;
	}
}
