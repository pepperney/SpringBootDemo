package cn.pepper.model;

import java.sql.SQLException;

public class MyException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private Integer code;
	
	private String msg;
	
	public MyException(RuntimeException e){
		this.msg=e.getMessage();
	}
	public MyException(SQLException e){
		this.msg=e.getMessage();
	}
	public MyException(String message){
		super(message);
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String message) {
		this.msg = message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
