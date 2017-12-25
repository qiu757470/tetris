package org.great.client;

import java.io.Serializable;

public class TbEmp implements Serializable{

	private static final long serialVersionUID = 1L;
	private String e_no;
	private String e_pwd;
	
	public TbEmp() {
		super();
	}
	
	public TbEmp(String e_no, String e_pwd) {
		super();
		this.e_no = e_no;
		this.e_pwd = e_pwd;
	}

	public String getE_no() {
		return e_no;
	}
	public void setE_no(String e_no) {
		this.e_no = e_no;
	}
	public String getE_pwd() {
		return e_pwd;
	}
	public void setE_pwd(String e_pwd) {
		this.e_pwd = e_pwd;
	}
}
