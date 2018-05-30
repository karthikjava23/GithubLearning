package com.hdfcbank.carloans;

public class FirstClass 
{
	int a,b,c;
	
public int addition(int a, int b)
{
	
 c=a+b;
// System.out.println("Addition of a & b in local is :"+ c );
 return c;
 
 
}

	public static void main(String[] args) 
	{
		//System.out.println("Hi !");
		
		FirstClass obj=new FirstClass();
		int x = obj.addition(100,200);
		System.out.println("Addition of a & b is c="+ x);
	
	
	}

}
