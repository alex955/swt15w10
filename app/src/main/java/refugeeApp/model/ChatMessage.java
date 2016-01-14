package refugeeApp.model;

import lombok.Data;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class ChatMessage.
 */
@Data
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

}
