package refugeeApp.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChatConversation {
	private @Id @GeneratedValue long id;
	
	private long fromId, toId;
	private String fromUserName, toUserName;
	
	private boolean fromUnread, toUnread;
	
	private int lastQuestion;
	
	public List<ChatMessage> getContent() {
		return content;
	}

	private int iterationCount;
	
	private String title;
	
	@ElementCollection
	private List<ChatMessage> content = new ArrayList<ChatMessage>();
	
	public ChatConversation(){
		this.iterationCount = 0;
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
	
	public void addChatMessage(ChatMessage arg){
		this.content.add(arg);
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

	public int getLastQuestion() {
		return lastQuestion;
	}

	public void setLastQuestion(int lastQuestion) {
		this.lastQuestion = lastQuestion;
	}
	
	
}
