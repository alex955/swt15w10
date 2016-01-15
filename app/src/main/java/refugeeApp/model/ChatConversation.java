package refugeeApp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class ChatConversation.
 */
@Data
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
	
	private String languagesFrom, languagesTo;
	
	/** The last question. */
	private int lastQuestion;
	
	/**
	 * the article the conversation is about
	 */
	private long articleId;
	
	
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
	 * Checks if is from unread.
	 *
	 * @return true, if is from unread
	 */
	public boolean isFromUnread() {
		return fromUnread;
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
	 * Increment iteration.
	 */
	public void incrementIteration(){
		this.iterationCount++;
	}
	
	/**
	 * Adds the chat message.
	 *
	 * @param arg the arg
	 */
	public void addChatMessage(ChatMessage arg){
		this.content.add(arg);
	}
	
}
