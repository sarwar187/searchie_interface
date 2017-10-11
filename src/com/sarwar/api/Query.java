package com.sarwar.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Query {
	
	private String param;
	private String passcode;
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getPasscode() {
		// TODO Auto-generated method stub
		return passcode;
	}
	public void setPasscode(String passcode) {
		// TODO Auto-generated method stub
		this.passcode = passcode;
	}
	
	
	
}
