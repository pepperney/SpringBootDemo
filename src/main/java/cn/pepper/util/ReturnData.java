package cn.pepper.util;

import java.io.Serializable;
import java.util.List;

public class ReturnData<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String msg;
	
	private Object data;
	
	private int count;
	
	private List<T> list;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
	
	
}
