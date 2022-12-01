package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

/**
 *  关于开发团队成员的管理、添加、删除等。
 * */
public class TeamService {
	private static int counter=1;//给memberId赋值使用
	private final int MAX_MEMBER=5;//开发团队最大人数
	private Programmer[] team=new Programmer[MAX_MEMBER];//保存开发团队成员
	private int total=0;//用来记录开发团队中实际的人数
	
	public TeamService() {
		super();
//		counter++;
	}
/**
 * 获取开发团队的所有成员
 * */
	public Programmer[] getTeam() {
		Programmer[] team=new Programmer[total];
		for(int i=0;i<team.length;i++) {
			team[i]=this.team[i];
		}
		return team;
		
	}
/**
 * 将指定的员工添加到开发团队当中
 * */	
	public void addEmployee(Employee e) throws TeamException{
//成员已满，无法添加
		if(total>=MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
//该成员不是开发人员，无法添加
		if(!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
//该员工已在本开发团队中
		if(isExist(e)) {
			throw new TeamException("该员工已在本开发团队中");
		}
		Programmer p=(Programmer)e;//一定不会出现ClassCastException
//该员工已是某团队成员
		//避免出现NullPointerException
//		if("BUSY".equals(p.getStatus().getName())){//if(p.getStatus().getName().equals("BUSY")) {
//			throw new TeamException("该员工已是某团队成员");
//		}else if("VOCATION".equals(p.getStatus().getName())) {//该员工正在休假，无法添加
//			throw new TeamException("该员工正在休假，无法添加");
//		}
		switch(p.getStatus()){//byte\short\char\int\String\枚举类对象
		case BUSY:
			throw new TeamException("该员工已是某团队成员");
		case VOCATION:
			throw new TeamException("该员工正在休假，无法添加");
		}
//该团队至多只能有一名架构师
//该团队至多只能有两名设计师
//该团队至多只能有三名程序员
		//获取团队中已有的架构师，设计师，程序员的人数
		int numOfArch=0,numOfDes=0,numOfPro=0;
		for(int i=0;i<total;i++) {
			if(team[i] instanceof Architect) {
				numOfArch++;
			}else if(team[i] instanceof Designer) {
				numOfDes++;
			}else if(team[i] instanceof Programmer) {
				numOfPro++;
			}
		}
		if(p instanceof Architect) { 
			if(numOfArch>=1) {
				throw new TeamException("该团队至多只能有一名架构师");
			}
		}else if(p instanceof Designer) {
			if(numOfDes>=2) {
				throw new TeamException("该团队至多只能有两名设计师");
			}
		}else {
			if(numOfPro>=3) {
				throw new TeamException("该团队至多只能有三名程序员");
			}
		}
		//将p(或e)添加到现有的team中
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
		team[total]=p;
		total++;
		//p的属性的赋值
		
	}
/**
 * 判断指定的员工是否已经存在于现有的开发团队中
 * */
	private boolean isExist(Employee e) {
	// TODO Auto-generated method stub
		for(int i=0;i<total;i++) {
			if(team[i].getId()==e.getId()) {
				return true;
			}
		}
	return false;
}
/**
 * 从团队中删除成员
 * */
	public void removeEmployee(int memberId) throws TeamException{
		int i=0;
		for(;i<total;i++) {
			if(team[i].getId()==memberId) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		
		//未找到指定memberId的员工，删除失败
		if(i==total) {
			throw new TeamException("找不到指定memberId的员工，删除失败");			
		}
		//后一个元素覆盖前一个元素，实现删除操作
		for(int j=i;j<total-1;j++) {
			team[j]=team[j+1];
		}
		//写法一:
//		team[total-1]=null;
//		total--;
		//写法二:
		team[--total]=null;
	}

	
	
}
