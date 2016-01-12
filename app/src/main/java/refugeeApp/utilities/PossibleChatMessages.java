package refugeeApp.utilities;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The Class PossibleChatMessages.
 */
@Component
public class PossibleChatMessages {
	
	/** The start messages. */
	private int[] startMessages = { 1, 2 };
	
	/** The possible messages from starter. */
	private int[] possibleMessagesFromStarter = { 1,2,5,7,9,10, 11, 13, 0};
	
	/** The possible answers to message. */
	private Map<Integer, LinkedList<Integer>> possibleAnswersToMessage = new HashMap<Integer, LinkedList<Integer>>();
	
	/** The possible chat message. */
	private Map<Integer,String> possibleChatMessage = new HashMap<Integer, String>();
	
	/**
	 * Constructor, contains hardcoded chat flow.
	 */
	//wasn't supposed to be so heavily hardcoded,..
	public PossibleChatMessages() {
		this.possibleChatMessage.put(0, "textblock.freeChat");
		LinkedList<Integer> answersToZero = new LinkedList<Integer>();

		this.possibleChatMessage.put(1, "textblock.helloArticle");
		LinkedList<Integer> answersToOne = new LinkedList<Integer>();

		this.possibleChatMessage.put(2, "textblock.helloActivity");
		LinkedList<Integer> answersToTwo = new LinkedList<Integer>();

		this.possibleChatMessage.put(3, "textblock.yes");
		LinkedList<Integer> answersToThree = new LinkedList<Integer>();

		this.possibleChatMessage.put(4, "textblock.no");
		LinkedList<Integer> answersToFour = new LinkedList<Integer>();

		this.possibleChatMessage.put(5, "textblock.bringToActivityQuestion");
		LinkedList<Integer> answersToFive = new LinkedList<Integer>();

		this.possibleChatMessage.put(6, "textblock.bringToActivityAnswer");
		LinkedList<Integer> answersToSix = new LinkedList<Integer>();

		this.possibleChatMessage.put(7, "textblock.fetchArticleAtTimeQuestion");
		LinkedList<Integer> answersToSeven = new LinkedList<Integer>();

		this.possibleChatMessage.put(8, "textblock.fetchArticleAtTimeAnswer");
		LinkedList<Integer> answersToEight = new LinkedList<Integer>();

		this.possibleChatMessage.put(9, "textblock.cantFetchArticle");
		LinkedList<Integer> answersToNine = new LinkedList<Integer>();

		this.possibleChatMessage.put(10, "textblock.cantParticipateActivity");
		LinkedList<Integer> answersToTen = new LinkedList<Integer>();

		this.possibleChatMessage.put(11, "textblock.whichAdressIsArticle");
		LinkedList<Integer> answersToEleven = new LinkedList<Integer>();

		this.possibleChatMessage.put(12, "textblock.ArticleHasThisAdress");
		LinkedList<Integer> answersToTwelve = new LinkedList<Integer>();

		this.possibleChatMessage.put(13, "textblock.whereExactlyIsActivity");
		LinkedList<Integer> answersToThirteen = new LinkedList<Integer>();

		this.possibleChatMessage.put(14, "textblock.whereExactlyIsActivityAnswer");
		LinkedList<Integer> answersToFourteen = new LinkedList<Integer>();

		this.possibleChatMessage.put(15, "textblock.articleNotAvailableAnymore");
		LinkedList<Integer> answersToFifteen = new LinkedList<Integer>();

		this.possibleChatMessage.put(16, "textblock.activityWontTakePlace");
		LinkedList<Integer> answersToSixteen = new LinkedList<Integer>();

		answersToZero.add(0);
		possibleAnswersToMessage.put(0, answersToZero);


		answersToOne.add(3);
		answersToOne.add(4);
		answersToOne.add(0);
		possibleAnswersToMessage.put(1, answersToOne);


		answersToTwo.add(3);
		answersToTwo.add(4);
		answersToTwo.add(0);
		possibleAnswersToMessage.put(2, answersToTwo);

		answersToThree.add(0);
		possibleAnswersToMessage.put(3, answersToThree);

		answersToFour.add(0);
		possibleAnswersToMessage.put(4, answersToFour);

		answersToFive.add(4);
		answersToFive.add(6);
		answersToFive.add(0);
		possibleAnswersToMessage.put(5, answersToFive);

		answersToSix.add(0);
		possibleAnswersToMessage.put(6, answersToSix);


		answersToSeven.add(8);
		answersToSeven.add(0);
		answersToSeven.add(4);
		possibleAnswersToMessage.put(7, answersToSeven);

		answersToEight.add(0);
		possibleAnswersToMessage.put(8, answersToEight);

		answersToNine.add(0);
		possibleAnswersToMessage.put(9, answersToNine);

		answersToTen.add(0);
		possibleAnswersToMessage.put(10, answersToTen);

		answersToEleven.add(0);
		answersToEleven.add(12);
		answersToEleven.add(15);
		possibleAnswersToMessage.put(11, answersToEleven);

		answersToTwelve.add(0);
		possibleAnswersToMessage.put(12, answersToTwelve);

		answersToThirteen.add(0);
		answersToThirteen.add(14);
		answersToThirteen.add(16);
		possibleAnswersToMessage.put(13, answersToThirteen);

		answersToFourteen.add(0);
		possibleAnswersToMessage.put(14, answersToFourteen);

		answersToFifteen.add(0);
		possibleAnswersToMessage.put(15, answersToFifteen);

		answersToSixteen.add(0);
		possibleAnswersToMessage.put(16, answersToSixteen);


//		this.possibleChatMessage.put(10,"option7");
//		this.possibleChatMessage.put(11,"option8");
//		this.possibleChatMessage.put(12,"ja");
//		this.possibleChatMessage.put(13,"nein");
//		this.possibleChatMessage.put(14,"Hallo!");
//		this.possibleChatMessage.put(15,"Halodrieu");

	}
	
	/**
	 * Gets the possible start messages.
	 *
	 * @return Mapped (by int id) String values for possible messages with which a conversation can be started out of an article view
	 */
	public Map<Integer, String> getPossibleStartMessages() {
		Map<Integer, String> toReturn = new HashMap<Integer, String>();

		for (int startMessage : startMessages) {
			possibleChatMessage.entrySet().stream().filter(s -> s.getKey() == startMessage).forEach(s -> toReturn.put(startMessage, s.getValue()));
		}

		return toReturn;
	}
	
	/**
	 * Gets the possible messages from starter.
	 *
	 * @return Mapped (by int id) String values for possible messages with which a conversation can be appended by interested party, conversation already exists
	 */
	public Map<Integer, String> getPossibleMessagesFromStarter() {
		Map<Integer, String> toReturn = new HashMap<Integer, String>();

		for (int aPossibleMessagesFromStarter : possibleMessagesFromStarter) {
			possibleChatMessage.entrySet().stream().filter(s -> s.getKey() == aPossibleMessagesFromStarter).forEach(s -> toReturn.put(aPossibleMessagesFromStarter, s.getValue()));
		}

		return toReturn;
	}
	
	/**
	 * Gets the possible answers to message.
	 *
	 * @param key ID of message
	 * @return Mapped (by int id) String values for possible answers to a certain message
	 */
	public Map<Integer, String> getPossibleAnswersToMessage(int key) {
		Map<Integer, String> toReturn = new HashMap<Integer, String>();

		LinkedList<Integer> possibleAnswerKeys = this.possibleAnswersToMessage.get(key);

		for (int s : possibleAnswerKeys) {
			toReturn.put(s, this.possibleChatMessage.get(s));
		}

		return toReturn;
	}
	
	/**
	 * Gets the possible chat messages.
	 *
	 * @return all possible chat messages, all questions and answers
	 */
	public Map<Integer, String> getPossibleChatMessages() {
		return possibleChatMessage;
	}
	
	/**
	 * Gets a certain text block by id.
	 *
	 * @param id Textblock ID
	 * @return Map with one text block - mapped by it's id. Map needed for further computational magic.
	 */
	public Map<Integer, String> getCertainTextBlock(int id) {
		Map<Integer, String> toReturn = new HashMap<Integer, String>();
		toReturn.put(id, this.possibleChatMessage.get(id));

		return toReturn;
	}
	
	/**
	 * returns the "free chat" texblock.
	 *
	 * @return single text block for free chat, mapped by it's id ( = 0)
	 */
	public Map<Integer, String >getFreeTextAnswer(){
		return this.getCertainTextBlock(0);
	}
}
