package com.atguigu.team.view;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

public class TeamView {
	private NameListService listSvc=new NameListService();
	private TeamService teamSvc=new TeamService();
/**
 * 主界面显示
 * */
	public void enterMainMenu() {
		
		boolean loopFlag=true;
		char  menu=0;
		while(loopFlag) {
			if(menu!='1') {
				listAllEmployee();
			}
			System.out.print("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出 请选择(1-4):");
			menu = TSUtility.readMenuSelection();
			switch(menu) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.println("确认是否退出(Y/N)");
				char isExit = TSUtility.readConfirmSelection();
				if(isExit=='Y') {
					loopFlag=false;
				}
				break;
			
			}
		}
	}
/**
 * 以表格形式列出公司所有成员
 * */
	private void listAllEmployee() {
		System.out.println("-----------------------开发团队调度软件--------------------\n");
		Employee[] employees = listSvc.getAllEmployees();
		if(employees==null||employees.length==0) {
			System.out.println("公司中没有员工信息");
		}else {
			System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
			for(int i=0;i<employees.length;i++) {
				System.out.println(employees[i]);
			}
		}
		System.out.println("----------------------------------------------------------");
	}

/**
 * 显示团队成员列表操作
 * */
	private void getTeam() {
		System.out.println("-----------------------团队成员列表--------------------\n");
		Programmer[] team = teamSvc.getTeam();
		if(team==null||team.length==0) {
			System.out.println("开发团队中没有成员！");
		}else {
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
			for(int i=0;i<team.length;i++) {
				System.out.println(team[i].getDetailsForTeam());
			}
		}
		System.out.println("-------------------------------------------------------");
	}
	
	private void addMember() {
		System.out.println("-----------------------添加成员--------------------\n");
		System.out.print("请输入要添加成员的ID:");
		int id = TSUtility.readInt();
		try {
			Employee employee = listSvc.getEmployee(id);
			teamSvc.addEmployee(employee);
			System.out.println("添加成功");
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			System.out.println("添加失败，原因:"+e.getMessage());
		}
		//按回车键继续......
		TSUtility.readReturn();
		
	}
	
	private void deleteMember() {
		System.out.println("-----------------------删除成员--------------------\n");
		System.out.print("请输入要删除成员的TID:");
		int memberId = TSUtility.readInt();
		System.out.println("请确认是否删除(Y/N)");
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete=='N') {
			return;
		}else {
			try {
				teamSvc.removeEmployee(memberId);
				System.out.println("删除成功");
			} catch (TeamException e) {
				// TODO Auto-generated catch block
				System.out.println("删除失败，原因:"+e.getMessage());
			}
			//按回车键继续......
			TSUtility.readReturn();
		}
		
	}
	
	public static void main(String[] args) {
		TeamView view=new TeamView();
		view.enterMainMenu();
	}
}
