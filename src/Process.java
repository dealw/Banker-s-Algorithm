package work;

public class Process {
	private String name;
	public Process(String name, int maxA, int maxB, int maxC, int allA, int allB, int allC) {
		super();
		this.name = name;
		this.maxA = maxA;
		this.maxB = maxB;
		this.maxC = maxC;
		this.allA = allA;
		this.allB = allB;
		this.allC = allC;
		initneed();
	}
	void initneed() {
		needA=maxA-allA;
		needB=maxB-allB;
		needC=maxC-allC;
	}
	private int needA;
	private int needB;
	private int needC;
	public int getNeedA() {
		return needA;
	}
	public void setNeedA(int needA) {
		this.needA = needA;
	}
	public int getNeedB() {
		return needB;
	}
	public void setNeedB(int needB) {
		this.needB = needB;
	}
	public int getNeedC() {
		return needC;
	}
	public void setNeedC(int needC) {
		this.needC = needC;
	}
	private int maxA;
	private int maxB;
	private int maxC;
	private int allA;
	private int allB;
	public int getAllA() {
		return allA;
	}
	public void setAllA(int allA) {
		this.allA = allA;
	}
	public int getAllB() {
		return allB;
	}
	public void setAllB(int allB) {
		this.allB = allB;
	}
	public int getAllC() {
		return allC;
	}
	public void setAllC(int allC) {
		this.allC = allC;
	}
	private int allC;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxA() {
		return maxA;
	}
	public void setMaxA(int maxA) {
		this.maxA = maxA;
	}
	public int getMaxB() {
		return maxB;
	}
	public void setMaxB(int maxB) {
		this.maxB = maxB;
	}
	public int getMaxC() {
		return maxC;
	}
	public void setMaxC(int maxC) {
		this.maxC = maxC;
	}
	
}
