package com.blogAppApi.exception;

public class ResorceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String resorcename;
	String feildname;
	Integer feildvalue;
	public String getResorcename() {
		return resorcename;
	}
	public void setResorcename(String resorcename) {
		this.resorcename = resorcename;
	}
	public String getFeildname() {
		return feildname;
	}
	public void setFeildname(String feildname) {
		this.feildname = feildname;
	}
	public Integer getFeildvalue() {
		return feildvalue;
	}
	public void setFeildvalue(Integer feildvalue) {
		this.feildvalue = feildvalue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ResorceNotFoundException(String resorcename, String feildname, Integer feildvalue) {
		super(String.format("%s User not found %s :%s",resorcename,feildname,feildvalue ));
		this.resorcename = resorcename;
		this.feildname = feildname;
		this.feildvalue = feildvalue;
	}

}
