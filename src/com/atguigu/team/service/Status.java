package com.atguigu.team.service;
/**
 * 表示员工的状态
 * */
//public class Status {
//	
//	private  final String NAME;
//	private Status(String name) {
//		this.NAME=name;
//	}
//	public static final Status FREE=new Status("FREE");
//	public static final Status BUSY=new Status("BUSY");
//	public static final Status VOCATION=new Status("VOCATION");
//	
//	public String toString() {
//		return NAME;
//	}
//	public String getName() {
//		return NAME;
//	}
//用enum关键字定义枚举类
public enum Status {
	
	FREE,
	BUSY,
	VOCATION;
	
}
