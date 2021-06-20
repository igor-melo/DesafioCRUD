package br.com.pitang.desafio.exception;

public class SystemError extends Exception{
	
	public SystemError(String message) {
		super(message);
	}

	public SystemError(String message, Throwable cause) {
		super(message, cause);
	}
}
