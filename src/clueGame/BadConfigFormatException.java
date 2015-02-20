package clueGame;

public class BadConfigFormatException extends Exception{
	
	private String message;
	
	public BadConfigFormatException() {}
	public BadConfigFormatException(String message) {
		super(message);
		this.message  = message;
	}
	public String toString() {
		return message;
	}
	
	

}
