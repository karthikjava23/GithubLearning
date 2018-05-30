package com.hdfcbank.carloans;

public class Icici implements Rbi {

	public static void main(String[] args) 
	{
	 Icici i =new Icici();
	 i.withdrawl();

	}

	@Override
	public void withdrawl() 
	{
	
		System.out.println("Hi i am overriden withdraw implemented method in Icici");
	}

}