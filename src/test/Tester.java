package test;

import ciriutbreaker.Breaker;

public class Tester {

	public static void main(String a[]) {
		Breaker breaker = new Breaker();
		
		SaySomething2 ss2 = new SaySomething2();
		breaker.setCircuit(ss2);
		
		try {
			Object obj = breaker.circuitBreaker();
			System.out.println((String)obj);
		}catch(Exception e) {
			e.printStackTrace();
		}
		/*
		System.out.println("NEXT!! \n");
		
		SaySomething ss = new SaySomething();
		breaker.setCircuit(ss);
		
		try {
			Object obj = breaker.circuitBreaker();
			
			System.out.println((String)obj);
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
}