package refugeeApp.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class ChatMessage.
 */
@Entity
public class ChatMessage {
	
	/**
	 * Instantiates a new chat message.
	 *
	 * @param message the message
	 * @param freeText the free text
	 * @param fromId the from id
	 * @param toId the to id
	 * @param fromUserName the from user name
	 * @param toUserName the to user name
	 */
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

	/** The id. */
	private @Id @GeneratedValue long id;

	/** The message. */
	private String message;
	
	/** The free text. */
	private String freeText;
	
	/** The time. */
	private LocalDateTime time;
	
	/** The to id. */
	private long fromId, toId;
	
	/** The to user name. */
	private String fromUserName, toUserName;
	
	/** The content id. */
	private int contentId;
	
	/**
	 * Instantiates a new chat message.
	 */
	public ChatMessage() {
		message = "";
		fromId = toId = 0;
		this.setTime(LocalDateTime.now());
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
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @param from the new from id
	 */
	public void setFromId(long from) {
		this.fromId = from;
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
	 * @param to the new to id
	 */
	public void setToId(long to) {
		this.toId = to;
	}

	/**
	 * Gets the free text.
	 *
	 * @return the free text
	 */
	public String getFreeText() {
		return freeText;
	}

	/**
	 * Sets the free text.
	 *
	 * @param freeText the new free text
	 */
	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
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
	 * Gets the content id.
	 *
	 * @return the content id
	 */
	public int getContentId() {
		return contentId;
	}

	/**
	 * Sets the content id.
	 *
	 * @param contentId the new content id
	 */
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

}
