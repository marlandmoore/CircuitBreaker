package ciriutbreaker;

import java.lang.reflect.Method;

public class Breaker extends Thread {

	private Object circuit;
	private Method circuitMethod;
	private Method failOver;
	private Exception ex = null;
	private Object response = null;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void findCircuit() {
		Class c = circuit.getClass();
		if(c.isAnnotationPresent(CiruitBreaker.class)) {
			for(Method method: c.getDeclaredMethods()) {
				if(method.isAnnotationPresent(Circuit.class)) {
					circuitMethod = method;
				}else if(method.isAnnotationPresent(FailOver.class)) {
					failOver = method;
				}
			}
		}
	}
	
	public Object circuitBreaker() throws Exception {
		if(circuit == null) {
			throw new Exception("Missing @CircuitBreak");
		}
		
		//find circuit method in circuit break
		findCircuit();
		
		if(circuitMethod == null) {
			throw new Exception("Missing @Circuit method");
		}
		
		Thread thread = new Thread(this);
		thread.start();
		synchronized (thread) {
			thread.wait();
		}
		
		
		if(ex!=null) {
			if(failOver==null) {
				throw ex;
			}else{
				response = failOver.invoke(circuit);
			}
		}
		
		return response;
	}

	public void run() {
		try {
			synchronized (this) {
				response = circuitMethod.invoke(circuit);
				notify();
			}
			
		} catch (Exception e) {
			ex = e;
			return;
		}
	}
	
	public Object getCircuit() {
		return circuit;
	}

	public void setCircuit(Object circuit) {
		this.circuit = circuit;
	}
		
}