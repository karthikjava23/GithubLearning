package com.hdfcbank.carloans;

public class Hdfc implements Rbi {

	public static void main(String[] args) 
	{
	 Hdfc h =new Hdfc();
	 h.withdrawl();

	}

	@Override
	public void withdrawl() 
	{
	
		System.out.println("Hi i am overriden withdrawl implemented method in HDFC");
	}

}

