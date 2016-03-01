package ua.nure.bj.deckpack;

/**
 * This class implements card's properties
 * 
 * @author max
 *
 */

public class Card {
	CardValue cardVal;// the value of the card like Ace,King or Five
	Integer cardPoints;// points that match the card in BlackJack game
	Suites suit;// the suit if the card
	String visualisation;// picture
	Boolean isOpened;// whether player sees this card or not

	public Card(CardValue cardVal, Suites suit) {
		this.cardVal = cardVal;
		this.suit = suit;
		/**
		 * Setting points and visual attributes
		 */
		switch (cardVal) {
		case ACE:
			cardPoints = 1;
			visualisation = "A";
			break;
		case DEUS:
			cardPoints = 2;
			visualisation = "2";
			break;
		case THREE:
			cardPoints = 3;
			visualisation = "3";
			break;
		case FOUR:
			cardPoints = 4;
			visualisation = "4";
			break;
		case FIVE:
			cardPoints = 5;
			visualisation = "5";
			break;
		case SIX:
			cardPoints = 6;
			visualisation = "6";
			break;
		case SEVEN:
			cardPoints = 7;
			visualisation = "7";
			break;
		case EIGHT:
			cardPoints = 8;
			visualisation = "8";
			break;
		case NINE:
			cardPoints = 9;
			visualisation = "9";
			break;
		case TEN:
			cardPoints = 10;
			visualisation = "10";
			break;
		case JACK:
			cardPoints = 10;
			visualisation = "J";
			break;
		case QUEEN:
			cardPoints = 10;
			visualisation = "Q";
			break;
		case KING:
			cardPoints = 10;
			visualisation = "K";
			break;
		default:
			break;
		}
		switch (suit) {
		case CLUB:
			visualisation += "c";
			break;
		case SPADE:
			visualisation += "s";
			break;
		case DIAMOND:
			visualisation += "d";
			break;
		case HEART:
			visualisation += "h";
			break;
		default:
			break;
		}

	}

	/**
	 * This method returns visual display of the card
	 */
	public String getVisualization() {
		return visualisation;
	}

	/**
	 * This method returns points given for current card
	 */
	public int getcardPoints() {
		return cardPoints;
	}

}
