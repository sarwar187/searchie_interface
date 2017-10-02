package com.sarwar.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Query {
	
	private String param;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}
