package work;

import java.util.Scanner;

/**
 * 银行家算法设置进程数量及每个进程对各资源的需求
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
	System.out.println("请选择功能: 1.重新初始化进程   2.申请资源   3.查看当前资源分配图 4.退出 ");
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
//为进程申请资源 
private static void Application(Process[] arr) {
	// TODO Auto-generated method stub
	Scanner sc=new Scanner(System.in);
	System.out.println("请输入要申请资源的进程名和各类资源申请数:");
	String pname = sc.next();
	int appA = sc.nextInt();
	int appB = sc.nextInt();
	int appC = sc.nextInt();
	boolean find=false;
//	声明若不安全时将改变的值回溯时用的变量
	Process pro=null;
	int faNums=0;
	int fbNums=0;
	int fcNums=0;
	int fAllA = 0;
	int fAllB=0;
	int fAllC=0;
	for(Process p:arr) {
		if(p.getName().equals(pname)) {
//			设置若不安全时将改变的值回溯时用的变量
			 faNums=aNums;
			 fbNums=bNums;
			 fcNums=cNums;
			 fAllA=p.getAllA();
			 fAllB=p.getAllB();
			 fAllC=p.getAllC();
			 pro=p;
//			给要申请的进程所申请资源
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
//	名字不存在
	if(!find) {
		System.out.println("此进程不存在请检查！");
		return ;
	}
//	show1(arr);
//	若申请后的值不安全则回溯申请
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
// 增加进程和需求
 public static void add() {
	 Scanner sc=new Scanner(System.in);
//	 System.out.println("有几类资源?");
//	 int sou = sc.nextInt();
	 System.out.println("有几个进程参与?");
	 int pnum = sc.nextInt();
	 arrs = new Process[pnum];
	 int count=0;
//	 循环输入信息并封装成对象存入数组
	 while(pnum>0) {
		 System.out.println("输入进程名、对各资源的最大需求数和已分配数");
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
//		 存入 数组
		 arrs[count]=new Process(pName,aNum,bNum,cNum,allaNum,allbNum,allcNum);
		 count++;
		 pnum--;
	 }
	 System.out.println("各类资源初始值:");
	 aNums = sc.nextInt();
	 bNums = sc.nextInt();
	 cNums = sc.nextInt();
	 show(arrs);
	 secCheck(arrs);
 }
// 计算剩余值
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
// 安全性校验方法
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
// 显示检验安全性状态
 public static void showCheck(Process arr[],int count) {
	 int awNums=aNums;
	 int bwNums=bNums;
	 int cwNums=cNums;	
	 String flag="";
	 if(count!=arr.length-1) {
		 System.out.println("--------------------------------------------------------------------------------");
		 System.out.println("  进程名    Work(ABC)  Need(ABC)  Allocation(ABC)  Work+Allocation(ABC)   Finish");
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
		 System.out.println("当前时刻安全状态为:false"+"  ");
		 return;
	 }
	 System.out.println("--------------------------------------------------------------------------------");
	 System.out.println("  进程名    Work(ABC)  Need(ABC)  Allocation(ABC)  Work+Allocation(ABC)  Finish");
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
	 
		 System.out.print("当前时刻安全状态为:true"+"  ");
		 String str="";
		 for(Process p:arr) {
			 str+=p.getName()+" ";
		 }
		 System.out.println("存在一个安全序列为:"+str);
		 
 }
// 显示状态
 public static void show(Process arr[]) {
	 available(arr);
	 System.out.println("-----------------------------------------------------------");
	 System.out.println(" 进程名    Max(ABC)  Allocation(ABC)  Need(ABC)  Available(ABC)");
	 for(Process p:arr) {
		 System.out.println("-----------------------------------------------------------");
		 System.out.println("  "+p.getName()+"    "+p.getMaxA()+" "+p.getMaxB()+" "+p.getMaxC()+"         "
	     +p.getAllA()+" "+p.getAllB()+" "+p.getAllC()+"         "
	     +p.getNeedA()+" "+p.getNeedB()+" "+p.getNeedC()+"      "
	     +aNums+" "+bNums+" "+cNums);
	 }
//	 System.out.println("当前时刻安全状态为:"+secCheck(arr));
	 
 }
 public static void show1(Process arr[]) {
//	 available(arr);
	 System.out.println("-----------------------------------------------------------");
	 System.out.println(" 进程名    Max(ABC)  Allocation(ABC)  Need(ABC)  Available(ABC)");
	 for(Process p:arr) {
		 System.out.println("-----------------------------------------------------------");
		 System.out.println("  "+p.getName()+"    "+p.getMaxA()+" "+p.getMaxB()+" "+p.getMaxC()+"         "
	     +p.getAllA()+" "+p.getAllB()+" "+p.getAllC()+"         "
	     +p.getNeedA()+" "+p.getNeedB()+" "+p.getNeedC()+"      "
	     +aNums+" "+bNums+" "+cNums);
	 }
//	 System.out.println("当前时刻安全状态为:"+secCheck(arr));
	 
 }
}
