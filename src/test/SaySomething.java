package test;

import ciriutbreaker.Circuit;
import ciriutbreaker.CiruitBreaker;

@CiruitBreaker
public class SaySomething {

	public String sayingSomething() {
		return "something";
	}
	
	public String sayingNothing() {
		return "nothing";
	}
	
	@Circuit()
	public void action() {
		for(int i=1000;i>0; i--) {
			 int t = (int)(Math.random()*10);
			 if(t>0 && i%t==0) {
				 System.out.println( sayingNothing());
			 }else {
				 System.out.println(sayingSomething());
			 }
		}
		
	}
		
}