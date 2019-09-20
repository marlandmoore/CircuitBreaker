package test;

import ciriutbreaker.Circuit;
import ciriutbreaker.CiruitBreaker;
import ciriutbreaker.FailOver;

@CiruitBreaker
public class SaySomething2 {

	public String sayingSomething() {
		return "something";
	}
	
	public String sayingNothing() {
		return "nothing";
	}
	
	@FailOver
	public String fail() {
		System.out.println("failed");
		return "There was a failure";
	}
	
	@Circuit()
	public String action() {
		for(int i=1000;i>0; i--) {
			 int t = (int)(Math.random()*10);
			 if(i%t==0) {
				 System.out.println( sayingNothing());
			 }else {
				 System.out.println(sayingSomething());
			 }
		}
		return "I said something";
	}
	
	
}
