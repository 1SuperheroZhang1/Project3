package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

/**
 *  ���ڿ����Ŷӳ�Ա�Ĺ�����ӡ�ɾ���ȡ�
 * */
public class TeamService {
	private static int counter=1;//��memberId��ֵʹ��
	private final int MAX_MEMBER=5;//�����Ŷ��������
	private Programmer[] team=new Programmer[MAX_MEMBER];//���濪���Ŷӳ�Ա
	private int total=0;//������¼�����Ŷ���ʵ�ʵ�����
	
	public TeamService() {
		super();
//		counter++;
	}
/**
 * ��ȡ�����Ŷӵ����г�Ա
 * */
	public Programmer[] getTeam() {
		Programmer[] team=new Programmer[total];
		for(int i=0;i<team.length;i++) {
			team[i]=this.team[i];
		}
		return team;
		
	}
/**
 * ��ָ����Ա����ӵ������Ŷӵ���
 * */	
	public void addEmployee(Employee e) throws TeamException{
//��Ա�������޷����
		if(total>=MAX_MEMBER) {
			throw new TeamException("��Ա�������޷����");
		}
//�ó�Ա���ǿ�����Ա���޷����
		if(!(e instanceof Programmer)) {
			throw new TeamException("�ó�Ա���ǿ�����Ա���޷����");
		}
//��Ա�����ڱ������Ŷ���
		if(isExist(e)) {
			throw new TeamException("��Ա�����ڱ������Ŷ���");
		}
		Programmer p=(Programmer)e;//һ���������ClassCastException
//��Ա������ĳ�Ŷӳ�Ա
		//�������NullPointerException
//		if("BUSY".equals(p.getStatus().getName())){//if(p.getStatus().getName().equals("BUSY")) {
//			throw new TeamException("��Ա������ĳ�Ŷӳ�Ա");
//		}else if("VOCATION".equals(p.getStatus().getName())) {//��Ա�������ݼ٣��޷����
//			throw new TeamException("��Ա�������ݼ٣��޷����");
//		}
		switch(p.getStatus()){//byte\short\char\int\String\ö�������
		case BUSY:
			throw new TeamException("��Ա������ĳ�Ŷӳ�Ա");
		case VOCATION:
			throw new TeamException("��Ա�������ݼ٣��޷����");
		}
//���Ŷ�����ֻ����һ���ܹ�ʦ
//���Ŷ�����ֻ�����������ʦ
//���Ŷ�����ֻ������������Ա
		//��ȡ�Ŷ������еļܹ�ʦ�����ʦ������Ա������
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
				throw new TeamException("���Ŷ�����ֻ����һ���ܹ�ʦ");
			}
		}else if(p instanceof Designer) {
			if(numOfDes>=2) {
				throw new TeamException("���Ŷ�����ֻ�����������ʦ");
			}
		}else {
			if(numOfPro>=3) {
				throw new TeamException("���Ŷ�����ֻ������������Ա");
			}
		}
		//��p(��e)��ӵ����е�team��
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
		team[total]=p;
		total++;
		//p�����Եĸ�ֵ
		
	}
/**
 * �ж�ָ����Ա���Ƿ��Ѿ����������еĿ����Ŷ���
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
 * ���Ŷ���ɾ����Ա
 * */
	public void removeEmployee(int memberId) throws TeamException{
		int i=0;
		for(;i<total;i++) {
			if(team[i].getId()==memberId) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		
		//δ�ҵ�ָ��memberId��Ա����ɾ��ʧ��
		if(i==total) {
			throw new TeamException("�Ҳ���ָ��memberId��Ա����ɾ��ʧ��");			
		}
		//��һ��Ԫ�ظ���ǰһ��Ԫ�أ�ʵ��ɾ������
		for(int j=i;j<total-1;j++) {
			team[j]=team[j+1];
		}
		//д��һ:
//		team[total-1]=null;
//		total--;
		//д����:
		team[--total]=null;
	}

	
	
}
