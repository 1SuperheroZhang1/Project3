package com.atguigu.team.domain;

public class PC implements Equipment{

	private String display;//display��ʾ��ʾ������
	private String model;//model��ʾ�������ͺ�
	
	
	
	public PC() {
		super();
	}



	public PC(String display, String model) {
		super();
		this.display = display;
		this.model = model;
	}



	public String getDisplay() {
		return display;
	}



	public void setDisplay(String display) {
		this.display = display;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return model+"(" + display + ")";
	}


}
