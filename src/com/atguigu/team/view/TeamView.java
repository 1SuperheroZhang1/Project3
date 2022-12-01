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
 * ��������ʾ
 * */
	public void enterMainMenu() {
		
		boolean loopFlag=true;
		char  menu=0;
		while(loopFlag) {
			if(menu!='1') {
				listAllEmployee();
			}
			System.out.print("1-�Ŷ��б� 2-����Ŷӳ�Ա 3-ɾ���Ŷӳ�Ա 4-�˳� ��ѡ��(1-4):");
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
				System.out.println("ȷ���Ƿ��˳�(Y/N)");
				char isExit = TSUtility.readConfirmSelection();
				if(isExit=='Y') {
					loopFlag=false;
				}
				break;
			
			}
		}
	}
/**
 * �Ա����ʽ�г���˾���г�Ա
 * */
	private void listAllEmployee() {
		System.out.println("-----------------------�����Ŷӵ������--------------------\n");
		Employee[] employees = listSvc.getAllEmployees();
		if(employees==null||employees.length==0) {
			System.out.println("��˾��û��Ա����Ϣ");
		}else {
			System.out.println("ID\t����\t����\t����\tְλ\t״̬\t����\t��Ʊ\t�����豸");
			for(int i=0;i<employees.length;i++) {
				System.out.println(employees[i]);
			}
		}
		System.out.println("----------------------------------------------------------");
	}

/**
 * ��ʾ�Ŷӳ�Ա�б����
 * */
	private void getTeam() {
		System.out.println("-----------------------�Ŷӳ�Ա�б�--------------------\n");
		Programmer[] team = teamSvc.getTeam();
		if(team==null||team.length==0) {
			System.out.println("�����Ŷ���û�г�Ա��");
		}else {
			System.out.println("TID/ID\t����\t����\t����\tְλ\t����\t��Ʊ\n");
			for(int i=0;i<team.length;i++) {
				System.out.println(team[i].getDetailsForTeam());
			}
		}
		System.out.println("-------------------------------------------------------");
	}
	
	private void addMember() {
		System.out.println("-----------------------��ӳ�Ա--------------------\n");
		System.out.print("������Ҫ��ӳ�Ա��ID:");
		int id = TSUtility.readInt();
		try {
			Employee employee = listSvc.getEmployee(id);
			teamSvc.addEmployee(employee);
			System.out.println("��ӳɹ�");
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			System.out.println("���ʧ�ܣ�ԭ��:"+e.getMessage());
		}
		//���س�������......
		TSUtility.readReturn();
		
	}
	
	private void deleteMember() {
		System.out.println("-----------------------ɾ����Ա--------------------\n");
		System.out.print("������Ҫɾ����Ա��TID:");
		int memberId = TSUtility.readInt();
		System.out.println("��ȷ���Ƿ�ɾ��(Y/N)");
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete=='N') {
			return;
		}else {
			try {
				teamSvc.removeEmployee(memberId);
				System.out.println("ɾ���ɹ�");
			} catch (TeamException e) {
				// TODO Auto-generated catch block
				System.out.println("ɾ��ʧ�ܣ�ԭ��:"+e.getMessage());
			}
			//���س�������......
			TSUtility.readReturn();
		}
		
	}
	
	public static void main(String[] args) {
		TeamView view=new TeamView();
		view.enterMainMenu();
	}
}
