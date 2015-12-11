package kickstart.utilities;

import java.util.LinkedList;
import java.util.List;

public class PossibleChatMessages {
	
	private List<String> possibleChatMessage = new LinkedList<String>();
	
	public List<String> getPossibleChatMessage() {
		return possibleChatMessage;
	}

	public void setPossibleChatMessage(List<String> possibleChatMessage) {
		this.possibleChatMessage = possibleChatMessage;
	}

	public PossibleChatMessages(){
		this.possibleChatMessage.add("Hallo! Habe Interesse an dem Artikel.");
		this.possibleChatMessage.add("optionX");
		this.possibleChatMessage.add("optionY");
		
		this.possibleChatMessage.add("option1");
		this.possibleChatMessage.add("option2");
		this.possibleChatMessage.add("option3");
		this.possibleChatMessage.add("option4");
		this.possibleChatMessage.add("option5");
		this.possibleChatMessage.add("option6");
		this.possibleChatMessage.add("option7");
		this.possibleChatMessage.add("option8");
		this.possibleChatMessage.add("ja");
		this.possibleChatMessage.add("nein");
		this.possibleChatMessage.add("Hallo!");
		this.possibleChatMessage.add("Halodrieu");
		
	}
}
