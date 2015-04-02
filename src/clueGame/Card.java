package clueGame;

public class Card {
	private String name;
	public enum cardType {PERSON, WEAPON, ROOM}
	private cardType cardtype;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card(String name, cardType cardtype) {
		super();
		this.name = name;
		this.cardtype = cardtype;
	}

	public Card() {
		// TODO Auto-generated constructor stub
	}

	public void setcardType(cardType c) {
		this.cardtype = c;
	}
	
	public cardType getcardType() {
		return cardtype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardtype == null) ? 0 : cardtype.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardtype != other.cardtype)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
