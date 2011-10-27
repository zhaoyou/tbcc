package org.tbcc.util;

/**
 *  这是一个自定义的异常
 * @author Administrator
 *
 */
public class MyException extends Exception {
	
	private String error = ""  ;

	public MyException(){
		
	}
	
	public MyException(String error){
		this.error = error ;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public String toString(){
		return this.error ;
	}
	
}
