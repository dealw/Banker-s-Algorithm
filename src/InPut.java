package work;

import java.util.Scanner;

/**
 * ���м��㷨���ý���������ÿ�����̶Ը���Դ������
 * @author Wenger.Lee
 *
 */
public class InPut {
private static int aNums;
private static int bNums;
private static int cNums;
private static Process arrs[];
public static void main(String[] args) {
	add();
	out:while(true) {
		Scanner sc=new Scanner(System.in);
	System.out.println("��ѡ����: 1.���³�ʼ������   2.������Դ   3.�鿴��ǰ��Դ����ͼ 4.�˳� ");
	int num = sc.nextInt();
	switch(num) {
	case 1:
		add();
		break;
	case 2:
         Application(arrs);
         break;
	case 3:
		show1(arrs);
		break;
	case 4:
		break out;
	}
	}
}
//Ϊ����������Դ 
private static void Application(Process[] arr) {
	// TODO Auto-generated method stub
	Scanner sc=new Scanner(System.in);
	System.out.println("������Ҫ������Դ�Ľ������͸�����Դ������:");
	String pname = sc.next();
	int appA = sc.nextInt();
	int appB = sc.nextInt();
	int appC = sc.nextInt();
	boolean find=false;
//	����������ȫʱ���ı��ֵ����ʱ�õı���
	Process pro=null;
	int faNums=0;
	int fbNums=0;
	int fcNums=0;
	int fAllA = 0;
	int fAllB=0;
	int fAllC=0;
	for(Process p:arr) {
		if(p.getName().equals(pname)) {
//			����������ȫʱ���ı��ֵ����ʱ�õı���
			 faNums=aNums;
			 fbNums=bNums;
			 fcNums=cNums;
			 fAllA=p.getAllA();
			 fAllB=p.getAllB();
			 fAllC=p.getAllC();
			 pro=p;
//			��Ҫ����Ľ�����������Դ
			p.setAllA(p.getAllA()+appA);
			p.setAllB(p.getAllB()+appB);
			p.setAllC(p.getAllC()+appC);
			aNums-=appA;
			bNums-=appB;
			cNums-=appC;
			p.initneed();
			find=true;
			break;
		}
	}
//	���ֲ�����
	if(!find) {
		System.out.println("�˽��̲��������飡");
		return ;
	}
//	show1(arr);
//	��������ֵ����ȫ���������
	boolean flag = secCheck(arr);
	if(!flag) {
		pro.setAllA(fAllA);
		pro.setAllB(fAllB);
		pro.setAllC(fAllC);
		aNums=faNums;
		bNums=fbNums;
		cNums=fcNums;
	}
	
}
// ���ӽ��̺�����
 public static void add() {
	 Scanner sc=new Scanner(System.in);
//	 System.out.println("�м�����Դ?");
//	 int sou = sc.nextInt();
	 System.out.println("�м������̲���?");
	 int pnum = sc.nextInt();
	 arrs = new Process[pnum];
	 int count=0;
//	 ѭ��������Ϣ����װ�ɶ����������
	 while(pnum>0) {
		 System.out.println("������������Ը���Դ��������������ѷ�����");
		 String pName = sc.next();
//		 while(sou>0) {
//			 int num = sc.nextInt();
//			 sou--;
//		 }
		 int aNum = sc.nextInt();
		 int bNum = sc.nextInt();
		 int cNum = sc.nextInt();
		 int allaNum = sc.nextInt();
		 int allbNum = sc.nextInt();
		 int allcNum = sc.nextInt();
//		 ���� ����
		 arrs[count]=new Process(pName,aNum,bNum,cNum,allaNum,allbNum,allcNum);
		 count++;
		 pnum--;
	 }
	 System.out.println("������Դ��ʼֵ:");
	 aNums = sc.nextInt();
	 bNums = sc.nextInt();
	 cNums = sc.nextInt();
	 show(arrs);
	 secCheck(arrs);
 }
// ����ʣ��ֵ
 public static void available(Process arr[]) {
	 int sumA=0;
	 int sumB=0;
	 int sumC=0;
	 for(Process p:arr) {
		 sumA+=p.getAllA();
		 sumB+=p.getAllB();
		 sumC+=p.getAllC();
	 }
	 aNums=aNums-sumA;
	 bNums=bNums-sumB;
	 cNums=cNums-sumC;
 }
// ��ȫ��У�鷽��
 public static boolean secCheck(Process arr[]) {
	 int leftA=-1;
	 int leftB=-1;
	 int leftC=-1;
	 int aawNums=aNums;
	 int bawNums=bNums;
	 int cawNums=cNums;
	 int count=0;
	 boolean flag=false;
//	 whi:while(true) {
		 for(int i=0;i<arr.length;i++) {
		 leftA=aawNums-arr[i].getNeedA();
		 leftB=bawNums-arr[i].getNeedB();
		 leftC=cawNums-arr[i].getNeedC();
		 if(leftA>=0&&leftB>=0&&leftC>=0) {
			 Process temp=arr[i];
			 arr[i] =arr[count];
			 arr[count]=temp;
			 count++;
			 i=count;
			 aawNums+=temp.getAllA();
			 bawNums+=temp.getAllB();
			 cawNums+=temp.getAllC();
		  }
		 }
		 if(count==arr.length-1) {
		 flag=true;
		 }
//		 break;
//	 }
		 if(count==0) {
			 count=-1;
		 }
		 showCheck(arr,count);
	
		 return flag;
 }
// ��ʾ���鰲ȫ��״̬
 public static void showCheck(Process arr[],int count) {
	 int awNums=aNums;
	 int bwNums=bNums;
	 int cwNums=cNums;	
	 String flag="";
	 if(count!=arr.length-1) {
		 System.out.println("--------------------------------------------------------------------------------");
		 System.out.println("  ������    Work(ABC)  Need(ABC)  Allocation(ABC)  Work+Allocation(ABC)   Finish");
		 for(Process p:arr) {
			 System.out.println("--------------------------------------------------------------------------------");
			 System.out.print("  "+p.getName()+"    "
			 +awNums+" "+bwNums+" "+cwNums+"      "
			 +p.getNeedA()+" "+p.getNeedB()+" "+p.getNeedC()+"        "
		     +p.getAllA()+" "+p.getAllB()+" "+p.getAllC()+"               "
		     +(awNums+p.getAllA())+" "+(bwNums+p.getAllB())+" "+(cwNums+p.getAllC())+"             "
		     );
			 if(awNums-p.getNeedA()>=0&&bwNums-p.getNeedB()>=0&&cwNums-p.getNeedC()>=0) {
				 flag="true";
			 }else {
				 flag="false";
			 }
			 System.out.println(flag);
			 awNums+=p.getAllA();
			 bwNums+=p.getAllB();
			 cwNums+=p.getAllC();
//			 counts--;
			 flag="false";
		 }
		 System.out.println("��ǰʱ�̰�ȫ״̬Ϊ:false"+"  ");
		 return;
	 }
	 System.out.println("--------------------------------------------------------------------------------");
	 System.out.println("  ������    Work(ABC)  Need(ABC)  Allocation(ABC)  Work+Allocation(ABC)  Finish");
	 for(Process p:arr) {
		 System.out.println("--------------------------------------------------------------------------------");
		 System.out.println("  "+p.getName()+"    "
		 +awNums+" "+bwNums+" "+cwNums+"      "
		 +p.getNeedA()+" "+p.getNeedB()+" "+p.getNeedC()+"        "
	     +p.getAllA()+" "+p.getAllB()+" "+p.getAllC()+"               "
	     +(awNums+p.getAllA())+" "+(bwNums+p.getAllB())+" "+(cwNums+p.getAllC())+"             "
	     +(count>=0?"true":"false"));
		 awNums+=p.getAllA();
		 bwNums+=p.getAllB();
		 cwNums+=p.getAllC();
		 count--;
	 }
	 
		 System.out.print("��ǰʱ�̰�ȫ״̬Ϊ:true"+"  ");
		 String str="";
		 for(Process p:arr) {
			 str+=p.getName()+" ";
		 }
		 System.out.println("����һ����ȫ����Ϊ:"+str);
		 
 }
// ��ʾ״̬
 public static void show(Process arr[]) {
	 available(arr);
	 System.out.println("-----------------------------------------------------------");
	 System.out.println(" ������    Max(ABC)  Allocation(ABC)  Need(ABC)  Available(ABC)");
	 for(Process p:arr) {
		 System.out.println("-----------------------------------------------------------");
		 System.out.println("  "+p.getName()+"    "+p.getMaxA()+" "+p.getMaxB()+" "+p.getMaxC()+"         "
	     +p.getAllA()+" "+p.getAllB()+" "+p.getAllC()+"         "
	     +p.getNeedA()+" "+p.getNeedB()+" "+p.getNeedC()+"      "
	     +aNums+" "+bNums+" "+cNums);
	 }
//	 System.out.println("��ǰʱ�̰�ȫ״̬Ϊ:"+secCheck(arr));
	 
 }
 public static void show1(Process arr[]) {
//	 available(arr);
	 System.out.println("-----------------------------------------------------------");
	 System.out.println(" ������    Max(ABC)  Allocation(ABC)  Need(ABC)  Available(ABC)");
	 for(Process p:arr) {
		 System.out.println("-----------------------------------------------------------");
		 System.out.println("  "+p.getName()+"    "+p.getMaxA()+" "+p.getMaxB()+" "+p.getMaxC()+"         "
	     +p.getAllA()+" "+p.getAllB()+" "+p.getAllC()+"         "
	     +p.getNeedA()+" "+p.getNeedB()+" "+p.getNeedC()+"      "
	     +aNums+" "+bNums+" "+cNums);
	 }
//	 System.out.println("��ǰʱ�̰�ȫ״̬Ϊ:"+secCheck(arr));
	 
 }
}
