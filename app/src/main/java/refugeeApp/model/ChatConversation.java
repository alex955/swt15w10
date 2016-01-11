package refugeeApp.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class ChatConversation.
 */
@Entity
public class ChatConversation {
	
	/** The id. */
	private @Id @GeneratedValue long id;
	
	/** The to id. */
	private long fromId, toId;
	
	/** The to user name. */
	private String fromUserName, toUserName;
	
	/** The to unread. */
	private boolean fromUnread, toUnread;
	
	/** The last question. */
	private int lastQuestion;
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public List<ChatMessage> getContent() {
		return content;
	}

	/** The iteration count. */
	private int iterationCount;
	
	/** The title. */
	private String title;
	
	/** The content. */
	@ElementCollection
	private List<ChatMessage> content = new ArrayList<ChatMessage>();
	
	/**
	 * Instantiates a new chat conversation.
	 */
	public ChatConversation(){
		this.iterationCount = 0;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Checks if is from unread.
	 *
	 * @return true, if is from unread
	 */
	public boolean isFromUnread() {
		return fromUnread;
	}

	/**
	 * Sets the from unread.
	 *
	 * @param fromUnread the new from unread
	 */
	public void setFromUnread(boolean fromUnread) {
		this.fromUnread = fromUnread;
	}

	/**
	 * Checks if is to unread.
	 *
	 * @return true, if is to unread
	 */
	public boolean isToUnread() {
		return toUnread;
	}

	/**
	 * Sets the to unread.
	 *
	 * @param toUnread the new to unread
	 */
	public void setToUnread(boolean toUnread) {
		this.toUnread = toUnread;
	}

	/**
	 * Gets the iteration.
	 *
	 * @return the iteration
	 */
	public int getIteration() {
		return iterationCount;
	}
	
	/**
	 * Increment iteration.
	 */
	public void incrementIteration(){
		this.iterationCount++;
	}

	/**
	 * Gets the from id.
	 *
	 * @return the from id
	 */
	public long getFromId() {
		return fromId;
	}

	/**
	 * Sets the from id.
	 *
	 * @param fromId the new from id
	 */
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	/**
	 * Gets the to id.
	 *
	 * @return the to id
	 */
	public long getToId() {
		return toId;
	}

	/**
	 * Sets the to id.
	 *
	 * @param toId the new to id
	 */
	public void setToId(long toId) {
		this.toId = toId;
	}
	
	/**
	 * Adds the chat message.
	 *
	 * @param arg the arg
	 */
	public void addChatMessage(ChatMessage arg){
		this.content.add(arg);
	}

	/**
	 * Gets the from user name.
	 *
	 * @return the from user name
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	/**
	 * Sets the from user name.
	 *
	 * @param fromUserName the new from user name
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * Gets the to user name.
	 *
	 * @return the to user name
	 */
	public String getToUserName() {
		return toUserName;
	}

	/**
	 * Sets the to user name.
	 *
	 * @param toUserName the new to user name
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * Gets the last question.
	 *
	 * @return the last question
	 */
	public int getLastQuestion() {
		return lastQuestion;
	}

	/**
	 * Sets the last question.
	 *
	 * @param lastQuestion the new last question
	 */
	public void setLastQuestion(int lastQuestion) {
		this.lastQuestion = lastQuestion;
	}
	
	
}
