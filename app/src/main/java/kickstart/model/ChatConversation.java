package kickstart.model;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kickstart.utilities.ChatMessage;

@Entity
public class ChatConversation {
	private @Id @GeneratedValue long id;
	
	private long fromId, toId;
	private boolean fromUnread, toUnread;
	
	private int iterationCount;
	
	private String title;
	
	@ElementCollection
	private List<ChatMessage> content;
	
	public ChatConversation(){
		this.iterationCount = 0;
		//this.content = new LinkedList<ChatMessage>();
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isFromUnread() {
		return fromUnread;
	}

	public void setFromUnread(boolean fromUnread) {
		this.fromUnread = fromUnread;
	}

	public boolean isToUnread() {
		return toUnread;
	}

	public void setToUnread(boolean toUnread) {
		this.toUnread = toUnread;
	}

	public int getIteration() {
		return iterationCount;
	}
	
	public void incrementIteration(){
		this.iterationCount++;
	}

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public long getToId() {
		return toId;
	}

	public void setToId(long toId) {
		this.toId = toId;
	}
	
	
}
