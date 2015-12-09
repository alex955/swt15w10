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
import kickstart.utilities.StringInForm;

@Controller
public class ChatController {
	
	@Autowired private final CategoryRepo categories;
	@Autowired private final CategoryMethods categoryMethods;
	@Autowired private final ArticleRepo articleRepo;
	@Autowired private final ChatConversationRepo chatRepo;
    @Autowired private final UserRepository userRepository;
    
    //do not use, just for persistene/transaction(?) use
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
	
	@RequestMapping(value = "/chat/inspectChat/append/{id}", method = RequestMethod.POST)
	public String appendConversation(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("chatMessage") StringInForm newString) {
		if(userAccount.get() == null) return "error";
		
		long currentUserId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		ChatConversation currentChat = this.chatRepo.findOne(id);
		
		if(currentChat.getFromId() != currentUserId && currentChat.getToId() != currentUserId) return "error/notAuthenticated";
		
		ChatMessage newMessage = new ChatMessage();
		newMessage.setFromId(currentUserId);
		newMessage.setMessage(newString.getContent());
		
		long chatPartnerId;
		if(currentChat.getFromId() == currentUserId) {
			chatPartnerId = currentChat.getToId();
		}
		else{
			chatPartnerId = currentChat.getFromId();
		}
		newMessage.setToId(chatPartnerId);
		
		this.msgRepo.save(newMessage);
		
		currentChat.addChatMessage(newMessage);
		
		this.chatRepo.save(currentChat);
		
		return "redirect:/chat/thread/{id}";
	}
	
	
	@RequestMapping(value = "/chat/newConversation/{id}")
	public String newMessage(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount) {
		if(userAccount.get() == null) return "error";
		
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("article", this.articleRepo.findOne(id));

		model.addAttribute("stringInForm", new StringInForm());
		
		return "chat/newMessage";
	}
	

	
	@RequestMapping(value = "/chat/processNewConversation/{id}", method = RequestMethod.POST)
	public String processNewMessage(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("chatMessage") StringInForm newString) {
		if(userAccount.get() == null) return "error";
		
		User currentUser = this.userRepository.findByUserAccount(userAccount.get());
		Article currentArticle = this.articleRepo.findOne(id);
		
		String messageContent = newString.getContent();
		
		ChatConversation newConversation = new ChatConversation();
		
		newConversation.setFromId(currentUser.getId());
		newConversation.setToId(currentArticle.getCreator().getId());
		
		System.out.println("generated new message from "+currentUser.getId() + "to " +  currentArticle.getCreator().getId());
		
		newConversation.setFromUnread(false);
		newConversation.setTitle(currentArticle.getTitle() + " in " + currentArticle.getLocation() + ".");
		newConversation.setToUnread(true);
		
		ChatMessage newMessage = new ChatMessage();
		newMessage.setFromId(currentUser.getId());
		newMessage.setMessage(messageContent);
		newMessage.setToId(currentArticle.getCreator().getId());
		
		this.msgRepo.save(newMessage);
	
		newConversation.addChatMessage(newMessage);
		
		
		
		this.chatRepo.save(newConversation);
		
		return "redirect:/chat";
	}
	
	
	
	
	
	

}
