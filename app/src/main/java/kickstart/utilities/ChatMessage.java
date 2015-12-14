package kickstart.utilities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kickstart.model.ChatConversation;

@Entity
public class ChatMessage {
	public ChatMessage(String message, String freeText, long fromId, long toId, String fromUserName,
			String toUserName) {
		this.message = message;
		this.freeText = freeText;
		this.fromId = fromId;
		this.toId = toId;
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
		
		this.setTime(LocalDateTime.now());
	}

	private @Id @GeneratedValue long id;

	private String message;
	private String freeText;
	
	private LocalDateTime time;
	
	private long fromId, toId;
	private String fromUserName, toUserName;
	
	public ChatMessage() {
		message = "";
		fromId = toId = 0;
		this.setTime(LocalDateTime.now());
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

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

}
