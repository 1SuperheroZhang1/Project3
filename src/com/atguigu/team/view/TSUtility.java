package com.atguigu.team.view;

import java.util.Scanner;

/**
 * ��Ŀ���ṩ��TSUtility.java�࣬�����������ʵ�ּ��̷��ʡ�
 * */
public class TSUtility {
	public static Scanner sc=new Scanner(System.in);
	
/**
 * �÷�����ȡ���̣�����û�����'1'~'4'�������ַ����򷽷����ء�����ֵΪ�û������ַ���
 * 
 * */
	public static char readMenuSelection() {
		char c;
		for(;;) {
			String str=readKeyBoard(1,false);
			c=str.charAt(0);
			if(c!='1'&&c!='2'&&c!='3'&&c!='4') {
				System.out.println("ѡ���������������:");
			}else break;
		}
		return c;
	}
	/**
	 * �÷�����ʾ���ȴ���ֱ���û����س����󷵻ء�
	 * */
	public static void readReturn() {
		System.out.println("���س�������......");
		readKeyBoard(100,false);
	}
/**
 * �÷����Ӽ��̶�ȡһ�����Ȳ�����2λ����������������Ϊ�����ķ���ֵ��
 * */
	public static int readInt() {
		int n;
		for(;;) {
			String str=readKeyBoard(2,false);
			try {
				n=Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("���������������������");
			}
		}
		return n;
	}
/**
 * �Ӽ��̶�ȡ'Y'��'N'����������Ϊ�����ķ���ֵ��
 * */	
	public static char readConfirmSelection() {
		char c;
		for(;;) {
			String str=readKeyBoard(1,false).toUpperCase();
			c=str.charAt(0);
			if(c=='Y'||c=='N') {
				break;
			}else {
				System.out.println("ѡ���������������:");
			}
		}
		return c;
	}
	
	private static String readKeyBoard(int limit,boolean blockReturn) {
		String line="";
		while(sc.hasNextLine()) {
			line=sc.nextLine();
			if(line.length()==0) {
				if(blockReturn) {
					return line;
				}else {
					continue;
				}
			}
			
			if(line.length()<1||line.length()>limit) {
				System.out.println("���볤��(������"+limit+")��������������: ");
				continue;
			}
			break;
		}
		return line;
	}
}
