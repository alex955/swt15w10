package kickstart.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kickstart.model.Article;
import kickstart.model.ArticleRepo;
import kickstart.model.CategoryFirstTierObject;
import kickstart.model.CategoryRepo;
import kickstart.model.ChatConversation;
import kickstart.model.ChatConversationRepo;
import kickstart.model.ChatMessageRepo;
import kickstart.model.User;
import kickstart.model.UserRepository;
import kickstart.utilities.CategoryMethods;
import kickstart.utilities.ChatMessage;
import kickstart.utilities.PossibleChatMessages;
import kickstart.utilities.StringInForm;

@Controller
public class ChatController {
	
	@Autowired private final CategoryRepo categories;
	@Autowired private final CategoryMethods categoryMethods;
	@Autowired private final ArticleRepo articleRepo;
	@Autowired private final ChatConversationRepo chatRepo;
    @Autowired private final UserRepository userRepository;
    
    //TODO - replace variable with e.g. repo for chat content
    private List<String> possibleChatMessages = new PossibleChatMessages().getPossibleChatMessage();
    
    //just for persistent/transaction(?) use so no to be saved variables are "flushed in transient state"
    @Autowired private final ChatMessageRepo msgRepo;
	
	//Classvars
	protected LinkedList<CategoryFirstTierObject> processedCategories;

	@Autowired
	public ChatController(CategoryRepo categories, CategoryMethods categoryMethods, ArticleRepo articleRepo, ChatConversationRepo chatRepo, UserRepository userRepository, ChatMessageRepo msgRepo) {
		this.categories = categories;
		this.categoryMethods = categoryMethods;
		this.articleRepo = articleRepo;
		this.chatRepo = chatRepo;
		this.userRepository = userRepository;
		this.msgRepo = msgRepo;
	} 
	
	/**
	 * Generates a overview for sent and gotten messages
	 * @param model
	 * @param userAccount UserAccount of the logged in user
	 * @return chat/chatMain.html Template
	 */
	@RequestMapping("/chat")
	public String chat(Model model, @LoggedIn Optional<UserAccount> userAccount){
		if(userAccount.get() == null) return "login";
		
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		List<ChatConversation> incoming = this.chatRepo.findByToId(this.userRepository.findByUserAccount(userAccount.get()).getId());
		List<ChatConversation> outgoing = this.chatRepo.findByFromId(this.userRepository.findByUserAccount(userAccount.get()).getId());
		
		model.addAttribute("incomingMessages", incoming);
		model.addAttribute("outgoingMessages", outgoing);
		
		return "chat/chatMain";
	}
	
	
	/**
	 * Lets a logged in user inspect a chat thread in which he either was the recipient or sender
	 * @param id ID of the chat thread
	 * @param model
	 * @param userAccount UserAccount of the logged in user
	 * @return chat/chatThread.html template
	 */
	@RequestMapping("/chat/thread/{id}")
	public String inspectChat(@PathVariable("id") long id, Model model, @LoggedIn Optional<UserAccount> userAccount){
		if(userAccount.get() == null) return "login";
		
		long currentUserId = this.userRepository.findByUserAccount(userAccount.get()).getId();
	
		
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		ChatConversation conversation = this.chatRepo.findOne(id);
		
		if(conversation.getFromId() != currentUserId && conversation.getToId() != currentUserId) return "error/notAuthenticated";
		
		List<ChatMessage> actualConversation = conversation.getContent();
		
		boolean currentUserStartedConversation = false;
		if(!actualConversation.isEmpty()){
			if(actualConversation.get(0).getFromId() == currentUserId) currentUserStartedConversation = true;
		}
		
		model.addAttribute("conversation", conversation);
		model.addAttribute("currentUserStartedConversation", currentUserStartedConversation);
		
		model.addAttribute("possibleMessages", possibleChatMessages);
		
		
		//for menu
		List<ChatConversation> incoming = this.chatRepo.findByToId(this.userRepository.findByUserAccount(userAccount.get()).getId());
		List<ChatConversation> outgoing = this.chatRepo.findByFromId(this.userRepository.findByUserAccount(userAccount.get()).getId());
		
		model.addAttribute("incomingMessages", incoming);
		model.addAttribute("outgoingMessages", outgoing);
		//for menu end
		
		model.addAttribute("stringInForm", new StringInForm());
		model.addAttribute("currentUserId", currentUserId);
		
		return "chat/chatThread";
	}
	
	/**
	 * Appends a already existent chat thread with a new message
	 * @param id Chat Thread id
	 * @param model
	 * @param userAccount UserAccount of the logged in user
	 * @param newString Object which contains String values of the append form
	 * @return redirect to the appended chat thread
	 */
	@RequestMapping(value = "/chat/inspectChat/append/{id}", method = RequestMethod.POST)
	public String appendConversation(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("chatMessage") StringInForm newString) {
		if(userAccount.get() == null) return "error";
		
		long currentUserId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		ChatConversation currentChat = this.chatRepo.findOne(id);
		
		if(currentChat.getFromId() != currentUserId && currentChat.getToId() != currentUserId) return "error/notAuthenticated";
		
		ChatMessage newMessage = new ChatMessage();
		newMessage.setFromId(currentUserId);
		newMessage.setFromUserName(this.userRepository.findOne(currentUserId).getUsername());
		newMessage.setMessage(newString.getContent());
		newMessage.setFreeText(newString.getContent2());
		
		long chatPartnerId;
		String chatPartnerName;
		
		if(currentChat.getFromId() == currentUserId) {
			chatPartnerId = currentChat.getToId();
			chatPartnerName = this.userRepository.findOne(chatPartnerId).getUsername();
		}
		else{
			chatPartnerId = currentChat.getFromId();
			chatPartnerName = this.userRepository.findOne(chatPartnerId).getUsername();
		}
		newMessage.setToId(chatPartnerId);
		newMessage.setToUserName(chatPartnerName);
		
		this.msgRepo.save(newMessage);
		
		currentChat.addChatMessage(newMessage);
		
		this.chatRepo.save(currentChat);
		
		return "redirect:/chat/thread/{id}";
	}
	
	/**
	 * processes the data of a new conversation, creates chat thread
	 * @param id ID of the Article which is to be talked about in the chat
	 * @param model
	 * @param userAccount UserAccount of the logged in user
	 * @param newString Object which contains String values of form
	 * @return redirect to chat overview
	 */
	@RequestMapping(value = "/chat/processNewConversation/{id}", method = RequestMethod.POST)
	public String processNewMessage(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("chatMessage") StringInForm newString) {
		if(userAccount.get() == null) return "error";
		
		User currentUser = this.userRepository.findByUserAccount(userAccount.get());
		Article currentArticle = this.articleRepo.findOne(id);
		
		ChatConversation newConversation = new ChatConversation();
		
		newConversation.setFromId(currentUser.getId());
		newConversation.setFromUserName(this.userRepository.findOne(currentUser.getId()).getUsername());
		newConversation.setToId(currentArticle.getCreator().getId());
		newConversation.setToUserName(this.userRepository.findOne(currentArticle.getCreator().getId()).getUsername());
		
		newConversation.setFromUnread(false);
		newConversation.setTitle(currentArticle.getTitle() + " in " + currentArticle.getLocation() + ".");
		newConversation.setToUnread(true);
		
		ChatMessage newMessage = new ChatMessage();
		newMessage.setFromId(currentUser.getId());
		newMessage.setFromUserName(this.userRepository.findOne(currentUser.getId()).getUsername());
		newMessage.setToUserName(this.userRepository.findOne(currentArticle.getCreator().getId()).getUsername());
		newMessage.setMessage(newString.getContent());
		newMessage.setFreeText(newString.getContent2());
		newMessage.setToId(currentArticle.getCreator().getId());
		
		this.msgRepo.save(newMessage);
	
		newConversation.addChatMessage(newMessage);
		
		
		
		this.chatRepo.save(newConversation);
		
		return "redirect:/chat";
	}
	
	
	/**
	 * creates a view for a new message about an article
	 * @param id Article which the message is about
	 * @param model
	 * @param userAccount UserAccount of the logged in user
	 * @return chat/newMessage.html template
	 */
	@RequestMapping(value = "/chat/newConversation/{id}")
	public String newMessage(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount) {
		if(userAccount.get() == null) return "error";
		
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("article", this.articleRepo.findOne(id));

		model.addAttribute("stringInForm", new StringInForm());
		
		model.addAttribute("possibleMessages", possibleChatMessages);
		
		return "chat/newMessage";
	}
	
	

}
