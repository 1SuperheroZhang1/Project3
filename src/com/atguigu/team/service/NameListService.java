package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;

import static com.atguigu.team.service.Data.*;
/**
 * 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 * */
public class NameListService {
	private Employee[] employees;

/**
 * 给employees及数组的元素进行初始化
 * */
	public NameListService() {
		//1.根据项目提供的Data类，构造相应大小的employees数组。
		//2.再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer、Architect
		//3.将对象存于数组中
		employees=new Employee[Data.EMPLOYEES.length];
		
		for(int i=0;i<employees.length;i++) {
			//1.获取员工的类型
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			//2.获取Employee的4个基本信息
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name=EMPLOYEES[i][2];
			int age= Integer.parseInt(EMPLOYEES[i][3]);
			double salary=Double.parseDouble(EMPLOYEES[i][4]);
			Equipment equipment;
			double bonus;
			int stock;
			
			switch(type) {
			case EMPLOYEE:
				employees[i]=new Employee(id,name,age,salary);
				break;
			case PROGRAMMER:
				equipment=createEquipment(i);
				employees[i]=new Programmer(id,name,age,salary,equipment);
				break;
			case DESIGNER:
				equipment=createEquipment(i);
				bonus=Double.parseDouble(EMPLOYEES[i][5]);
				employees[i]=new Designer(id,name,age,salary,equipment, bonus);
				break;
			case ARCHITECT:
				equipment=createEquipment(i);
				bonus=Double.parseDouble(EMPLOYEES[i][5]);
				stock=Integer.parseInt(EMPLOYEES[i][6]);
				employees[i]=new Architect(id,name,age,salary,equipment, bonus, stock);
				break;
			}
		}
	}
/**
 * 获取指定index位置上员工的设备
 * */
	private Equipment createEquipment(int index) {
	// TODO Auto-generated method stub
		int type = Integer.parseInt(EQUIPMENTS[index][0]);
		String modelOrName=EQUIPMENTS[index][1];
		switch(type) {
		case PC://21
			String display=EQUIPMENTS[index][2];
			return new PC(modelOrName,display);
		case NOTEBOOK://22
			double price=Double.parseDouble(EQUIPMENTS[index][2]);
			return new NoteBook(modelOrName,price);
		case PRINTER://23
			return new Printer(modelOrName,EQUIPMENTS[index][2]);
		}
		return null;
}
/**
 * 获取当前所有员工
 * */
	public Employee[] getAllEmployees() {
		return employees;
	}
/**
 * 
 * */
	public Employee getEmployee(int id) throws TeamException {
		for(int i=0;i<employees.length;i++) {
			if(employees[i].getId()==id) {
				return employees[i];
			}
		}
		throw new TeamException("找不到指定员工");
	}
	
}
