package refugeeApp.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;

import refugeeApp.AbstractIntegrationTests;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.ChatConversation;
import refugeeApp.model.ChatConversationRepo;
import refugeeApp.model.ChatMessage;
import refugeeApp.model.ChatMessageRepo;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;

/**
 * The Class ChatTest.
 */
public class ChatTest extends AbstractIntegrationTests {

	/** The msg repo. */
	@Autowired ChatMessageRepo msgRepo;
	
	/** The chat repo. */
	@Autowired ChatConversationRepo chatRepo;
	
	/** The conv. */
	final ChatConversation conv = new ChatConversation();
	
	/** The message1standard. */
	final ChatMessage message1standard = new ChatMessage();
	
	/** The message2standard. */
	final ChatMessage message2standard = new ChatMessage();
	
	/** The message3explicit. */
	final ChatMessage message3explicit = new ChatMessage("message1", "freetext1", 1, 2, "myName", "adress");
	
	/** The message4explicit. */
	final ChatMessage message4explicit = new ChatMessage("message2", "freetext2", 2, 1, "adress", "myName");
    
    /**
     * Constructor test.
     */
    @Test
	public void constructorTest() {
		assertFalse("Error", message1standard == null);
		assertFalse("Error", message2standard == null);
		assertFalse("Error", message3explicit == null);
		assertFalse("Error", message4explicit == null);
		
		assertFalse("Error", conv == null);
	}
    
	/**
	 * Initialisation test.
	 */
	@Test
	public void initialisationTest() {
		assertEquals("Error", null, conv.getToUserName());
		assertEquals("Error", null, message1standard.getFreeText());
		assertEquals("Error", null, message2standard.getFreeText());
		assertEquals("Error", "freetext1", message3explicit.getFreeText());
		assertEquals("Error", "freetext2", message4explicit.getFreeText());
	}
	
	/**
	 * Chat conversation repo test.
	 */
	@Test
	public void ChatConversationRepoTest() {
		conv.setFromId(1);
		conv.setToId(2);
		
		this.chatRepo.save(conv);
		
		List<ChatConversation> result2 = this.chatRepo.findByFromId(1);
		List<ChatConversation> result3 = this.chatRepo.findByToId(2);
		
		assertEquals("Error", false, result2.equals(result3));
		
		this.chatRepo.delete(conv);
	}
	
	/**
	 * Adds the chat messages.
	 */
	@Test
	public void addChatMessages() {
		conv.addChatMessage(message3explicit);
		conv.addChatMessage(message4explicit);
		
		List<ChatMessage> convContent = conv.getContent();
		assertEquals("Error", true, convContent.contains(message3explicit));
		assertEquals("Error", true, convContent.contains(message4explicit));
	}
	
	
	
}
