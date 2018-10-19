package com.wy.model.core;

public class ResultMapper {

	public static int SUCCESS = 1;
	public static int FAILURE = -1;

	private int ack_code = SUCCESS;
	private String message;
	private Object data;

	public int getAck_code() {
		return ack_code;
	}

	public void setAck_code(int ack_code) {
		this.ack_code = ack_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
