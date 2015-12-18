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
	private int[] possibleMessagesFromStarter = { 5, 7, 8, 9 };
	
	private Map<Integer, LinkedList<Integer>> possibleAnswersToMessage = new HashMap<Integer, LinkedList<Integer>>();
	
	private Map<Integer,String> possibleChatMessage = new HashMap<Integer, String>();
	
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
	
	public Map<Integer, String> getPossibleAnswersToMessage(int key) {
		Map<Integer, String> toReturn = new HashMap<Integer, String>();
		
		List<Integer> possibleAnswerKeys = this.possibleAnswersToMessage.get(key);
		
		for(Integer s : possibleAnswerKeys){
			toReturn.put(s, this.possibleChatMessage.get(s));
		}
		
		return toReturn;
	}
	
	public Map<Integer,String> getPossibleChatMessages() {
		return possibleChatMessage;
	}
	
	public Map<Integer, String> getCertainTextBlock(int id){
		Map<Integer, String> toReturn = new HashMap<Integer, String>();
		toReturn.put(id, this.possibleChatMessage.get(id));
		
		return toReturn;
	}

	public PossibleChatMessages(){
		this.possibleChatMessage.put(0, "Freitext, z.B. Fragen oder andere Anliegen");
		
		this.possibleChatMessage.put(1, "Hallo! Habe Interesse an dem Artikel! Ist er noch verfügbar?");
		this.possibleChatMessage.put(2, "Hallo! Habe Interesse an der Aktivität! Sind noch Plätze frei?");
		
		this.possibleChatMessage.put(3,"Ja");
		this.possibleChatMessage.put(4,"Nein");
		
		ArrayList<Integer> answersToOne = new ArrayList<Integer>();
		answersToOne.add(0);
		answersToOne.add(3);
		answersToOne.add(4);
		
		ArrayList<Integer> answersToTwo = new ArrayList<Integer>();
		answersToOne.add(0);
		answersToTwo.add(3);
		answersToTwo.add(4);
		
		this.possibleChatMessage.put(5,"Soll ich etwas zur Aktivität mitbringen?");
		this.possibleChatMessage.put(6,"Ja, du sollst bitte folgendes mitbringen: ");
		
		this.possibleChatMessage.put(7,"Kann ich den Artikel zu folgenden Zeiten abholen: ");
		
		
		this.possibleChatMessage.put(8,"Ich kann den Artikel nicht abholen, Entschuldigung.");
		this.possibleChatMessage.put(9,"Ich kann an der Aktivität nicht teilnehmen, Entschuldigung");
//		this.possibleChatMessage.put(10,"option7");
//		this.possibleChatMessage.put(11,"option8");
//		this.possibleChatMessage.put(12,"ja");
//		this.possibleChatMessage.put(13,"nein");
//		this.possibleChatMessage.put(14,"Hallo!");
//		this.possibleChatMessage.put(15,"Halodrieu");
		
	}
	
	public Map<Integer, String >getFreeTextAnswer(){
		return this.getCertainTextBlock(0);
	}
}
