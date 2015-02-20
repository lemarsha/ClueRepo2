package clueGame;

public enum DoorDirection {
	
	UP('U'), DOWN('D'), LEFT('L'), RIGHT('R'), NONE('N');
	
	private char value;
	
	DoorDirection(char aValue) {
		value = aValue;
	}
	
	public char toChar() {
		return value;
	}
	
	

}
