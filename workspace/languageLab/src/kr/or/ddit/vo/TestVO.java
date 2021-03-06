package kr.or.ddit.vo;

import java.io.Serializable;

public class TestVO implements Serializable{
	private int number;
	private double dblNumber;
	
	private transient byte[] testArray; // 직렬화 대상에서 제외하기(transient)

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getDblNumber() {
		return dblNumber;
	}
	public void setDblNumber(double dblNumber) {
		this.dblNumber = dblNumber;
	}
	public byte[] getTestArray() {
		return testArray;
	}
	public void setTestArray(byte[] testArray) {
		this.testArray = testArray;
	}

	
}
