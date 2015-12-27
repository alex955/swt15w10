package kickstart.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PossibleChatMessages {
	
	private int[] startMessages = { 1, 2 };
	private int[] possibleMessagesFromStarter = { 1,2,5,7,9,10, 11, 13, 0};
	
	private Map<Integer, LinkedList<Integer>> possibleAnswersToMessage = new HashMap<Integer, LinkedList<Integer>>();
	
	private Map<Integer,String> possibleChatMessage = new HashMap<Integer, String>();
	
	/**
	 * @return Mapped (by int id) String values for possible messages with which a conversation can be started out of an article view
	 */
	public Map<Integer,String> getPossibleStartMessages() {
		Map<Integer,String> toReturn = new HashMap<Integer,String>();
		
		for(int i = 0; i < startMessages.length; i++){
			for(Map.Entry<Integer, String> s : possibleChatMessage.entrySet()){
				if(s.getKey() == startMessages[i]) {
					toReturn.put(startMessages[i], s.getValue());
				}
			}
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * @return Mapped (by int id) String values for possible messages with which a conversation can be appended by interested party, conversation already exists
	 */
	public Map<Integer, String> getPossibleMessagesFromStarter(){
		Map<Integer,String> toReturn = new HashMap<Integer,String>();
		
		for(int i = 0; i < possibleMessagesFromStarter.length; i++){
			for(Map.Entry<Integer, String> s : possibleChatMessage.entrySet()){
				if(s.getKey() == possibleMessagesFromStarter[i]) {
					toReturn.put(possibleMessagesFromStarter[i], s.getValue());
				}
			}
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * @param key ID of message
	 * @return Mapped (by int id) String values for possible answers to a certain message
	 */
	public Map<Integer, String> getPossibleAnswersToMessage(int key) {
		Map<Integer, String> toReturn = new HashMap<Integer, String>();
		
		LinkedList<Integer> possibleAnswerKeys = this.possibleAnswersToMessage.get(key);
		
		for(int s : possibleAnswerKeys){
			toReturn.put(s, this.possibleChatMessage.get(s));
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * @return all possible chat messages, all questions and answers
	 */
	public Map<Integer,String> getPossibleChatMessages() {
		return possibleChatMessage;
	}
	
	/**
	 * Gets a certain text block by id
	 * @param id Textblock ID
	 * @return Map with one text block - mapped by it's id. Map needed for further computational magic.
	 */
	public Map<Integer, String> getCertainTextBlock(int id){
		Map<Integer, String> toReturn = new HashMap<Integer, String>();
		toReturn.put(id, this.possibleChatMessage.get(id));
		
		return toReturn;
	}
	/**
	 * Constructor, contains hardcoded chat flow
	 */
	//wasn't supposed to be so heavily hardcoded,.. 
	public PossibleChatMessages(){
		this.possibleChatMessage.put(0, "Freitext/Chat, z.B. Fragen/Antworten oder andere Anliegen");
		LinkedList<Integer> answersToZero = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(1, "Hallo! Ich habe Interesse an dem Artikel! Ist er noch verfügbar?");
		LinkedList<Integer> answersToOne = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(2, "Hallo! Ich habe Interesse an der Aktivität! Sind noch Plätze frei?");
		LinkedList<Integer> answersToTwo = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(3,"Ja");
		LinkedList<Integer> answersToThree = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(4,"Nein");
		LinkedList<Integer> answersToFour = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(5,"Soll ich etwas zur Aktivität mitbringen?");
		LinkedList<Integer> answersToFive = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(6,"Ja, du sollst bitte folgendes mitbringen: ");
		LinkedList<Integer> answersToSix = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(7,"Kann ich den Artikel zu folgenden Zeiten abholen: ");
		LinkedList<Integer> answersToSeven = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(8,"Ja, du kannst ihn zu den genannten Zeiten abholen.");
		LinkedList<Integer> answersToEight = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(9,"Ich kann den Artikel doch nicht holen, Entschuldigung.");
		LinkedList<Integer> answersToNine = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(10,"Ich kann an der Aktivität trotzdem nicht teilnehmen, Entschuldigung.");
		LinkedList<Integer> answersToTen = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(11,"Bei welcher Adresse kann man den Artikel abholen?");
		LinkedList<Integer> answersToEleven = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(12,"Der Artikel kann bei folgender Adresse abgeholt werden:");
		LinkedList<Integer> answersToTwelve = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(13,"Wo genau findet die Aktivität statt?");
		LinkedList<Integer> answersToThirteen = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(14,"Die Aktivität findet bei folgender Adresse/bei folgendem Ort statt:");
		LinkedList<Integer> answersToFourteen = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(15 ,"Der Artikel ist leider nicht mehr verfügbar.");
		LinkedList<Integer> answersToFifteen = new LinkedList<Integer>();
		
		this.possibleChatMessage.put(16 ,"Die Aktivität ist leider abgesagt.");
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
	 * returns the "free chat" texblock
	 * @return single text block for free chat, mapped by it's id ( = 0)
	 */
	public Map<Integer, String >getFreeTextAnswer(){
		return this.getCertainTextBlock(0);
	}
}
