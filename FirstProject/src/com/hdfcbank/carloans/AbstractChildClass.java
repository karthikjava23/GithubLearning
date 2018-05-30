package com.hdfcbank.carloans;

public class AbstractChildClass extends FirstAbstract
{

	public static void main(String[] args) 
	{
		
		AbstractChildClass a=new AbstractChildClass();
		a.m1();
		a.m2();
	}

	@Override
	public void m2() 
	{
		
		System.out.println("Hi i am m2 overridden method in AbstractChild Class");
	}

}
