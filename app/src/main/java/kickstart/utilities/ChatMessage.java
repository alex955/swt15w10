package kickstart.utilities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kickstart.model.ChatConversation;

@Entity
public class ChatMessage {
	private @Id @GeneratedValue long id;

	private String message;
	private String freeText;
	
	private long fromId, toId;
	
	public ChatMessage() {
		message = "";
		fromId = toId = 0;
	}
	
	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long from) {
		this.fromId = from;
	}

	public long getToId() {
		return toId;
	}

	public void setToId(long to) {
		this.toId = to;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

}
