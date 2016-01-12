package refugeeApp.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import refugeeApp.model.Article;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.Category;
import refugeeApp.model.CategoryFirstTierObject;
import refugeeApp.model.CategoryRepo;
import refugeeApp.model.ChatConversation;
import refugeeApp.model.ChatConversationRepo;
import refugeeApp.model.ChatMessage;
import refugeeApp.model.ChatMessageRepo;
import refugeeApp.model.Language;
import refugeeApp.model.LanguageRepository;
import refugeeApp.model.Location;
import refugeeApp.model.User;
import refugeeApp.model.UserRepository;
import refugeeApp.utilities.CategoryMethods;
import refugeeApp.utilities.PossibleChatMessages;
import refugeeApp.utilities.StringInForm;

/**
 * The Class ChatController.
 */
@Controller
@PreAuthorize("isAuthenticated()")
public class ChatController {
	
	/** The category methods. */
	@Autowired private final CategoryMethods categoryMethods;
	
	/** The article repo. */
	@Autowired private final ArticleRepo articleRepo;
	
	/** The chat repo. */
	@Autowired private final ChatConversationRepo chatRepo;
    
    /** The user repository. */
    @Autowired private final UserRepository userRepository;
    
    /** The language repository. */
    @Autowired private final LanguageRepository languageRepository;
    
    /** The possible messages object. */
    //TODO - replace variable with e.g. repo for chat content
    private PossibleChatMessages possibleMessagesObject = new PossibleChatMessages();
    
    /** The msg repo. */
    //just for persistent/transaction(?) use so no to be saved variables are "flushed in transient state"
    @Autowired private final ChatMessageRepo msgRepo;
	
	/** The processed categories. */
	//Classvars
	protected LinkedList<CategoryFirstTierObject> processedCategories;

	/**
	 * autowired constructor, arguments are repos or object with static methods for categories.
	 *
	 * @param categories the categories
	 * @param categoryMethods the category methods
	 * @param articleRepo the article repo
	 * @param chatRepo the chat repo
	 * @param userRepository the user repository
	 * @param msgRepo the msg repo
	 * @param languageRepository the language repository
	 */
	@Autowired
	public ChatController(CategoryRepo categories, CategoryMethods categoryMethods, ArticleRepo articleRepo, ChatConversationRepo chatRepo, UserRepository userRepository, ChatMessageRepo msgRepo, LanguageRepository languageRepository) {
		this.categoryMethods = categoryMethods;
		this.articleRepo = articleRepo;
		this.chatRepo = chatRepo;
		this.userRepository = userRepository;
		this.msgRepo = msgRepo;
		this.languageRepository = languageRepository;
	} 
	
	/**
	 * Generates a overview for sent and gotten messages.
	 *
	 * @param model the model
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
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		
		return "chat/chatMain";
	}
	
	
	/**
	 * Lets a logged in user inspect a chat thread in which he either was the recipient or sender.
	 *
	 * @param id ID of the chat thread
	 * @param model the model
	 * @param userAccount UserAccount of the logged in user
	 * @return chat/chatThread.html template
	 */
	@RequestMapping("/chat/thread/{id}")
	public String inspectChat(@PathVariable("id") long id, Model model, @LoggedIn Optional<UserAccount> userAccount){
		if(userAccount.get() == null) return "login";
		//Todo error page
		if(chatRepo.findOne(id) == null) return "error";
		
		long currentUserId = this.userRepository.findByUserAccount(userAccount.get()).getId();
		
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		ChatConversation conversation = this.chatRepo.findOne(id);
		
		//search kind of article which is talked about
		String kind = "";
		if(articleRepo.findOne(conversation.getArticleId()) != null){
			kind = articleRepo.findOne(conversation.getArticleId()).getKind();
		}
		
		
		if(conversation.getFromId() != currentUserId && conversation.getToId() != currentUserId) return "error/notAuthenticated";
		
		boolean currentUserStartedConversation = false;
		if(conversation.getFromId() == currentUserId){
			currentUserStartedConversation = true;
		}
		
		model.addAttribute("conversation", conversation);
		model.addAttribute("currentUserStartedConversation", currentUserStartedConversation);
		
		Map<Integer, String> possibleAppendedChatMessages;
		//case: current user started conversation, has to ask new questions
		if(currentUserStartedConversation){
			possibleAppendedChatMessages = this.possibleMessagesObject.getPossibleMessagesFromStarter(kind);
		} else{
			//case: current user is owner of article which is talked about, has to answes questions
			possibleAppendedChatMessages = this.possibleMessagesObject.getPossibleAnswersToMessage(conversation.getLastQuestion());
		}
		model.addAttribute("possibleMessages", possibleAppendedChatMessages);
		
		
		//for menu
		List<ChatConversation> incoming = this.chatRepo.findByToId(this.userRepository.findByUserAccount(userAccount.get()).getId());
		List<ChatConversation> outgoing = this.chatRepo.findByFromId(this.userRepository.findByUserAccount(userAccount.get()).getId());
		
		model.addAttribute("incomingMessages", incoming);
		model.addAttribute("outgoingMessages", outgoing);
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		//for menu end
		
		model.addAttribute("stringInForm", new StringInForm());
		model.addAttribute("currentUserId", currentUserId);
		
		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);
		if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
			browserLanguage = "en";
		}
		Language language = languageRepository.findByBrowserLanguage(browserLanguage);
		final String deleteChat = language.getDeleteChat();
		model.addAttribute("deleteChatWarning", deleteChat);
		
		return "chat/chatThread";
	}
	
	/**
	 * Appends an already existent chat thread with a new message.
	 *
	 * @param id Chat Thread id
	 * @param model the model
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
		
		int messageBlockId = Integer.parseInt(newString.getContent());
		String messageBlockContent = this.possibleMessagesObject.getCertainTextBlock(messageBlockId).get(messageBlockId);
		newMessage.setMessage(messageBlockContent);
		newMessage.setContentId(messageBlockId);
		
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
		currentChat.setLastQuestion(messageBlockId);
		
		this.chatRepo.save(currentChat);
		
		return "redirect:/chat/thread/{id}";
	}
	

	
	
	/**
	 * creates a view for a new message about an article.
	 *
	 * @param id Article which the message is about
	 * @param model the model
	 * @param userAccount UserAccount of the logged in user
	 * @return chat/newMessage.html template
	 */
	@RequestMapping(value = "/chat/newConversation/{id}")
	public String newMessage(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount) {
		//TODO: error messages
		if(userAccount.get() == null) return "error";
		if(this.articleRepo.findOne(id) == null) return "error";
		
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("article", this.articleRepo.findOne(id));
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));

		model.addAttribute("stringInForm", new StringInForm());
		
		model.addAttribute("possibleMessages", possibleMessagesObject.getPossibleStartMessages(this.articleRepo.findOne(id).getKind()));
		
 		return "chat/newMessage";
	}
	
	/**
	 * processes the data of a new conversation, creates chat thread.
	 *
	 * @param id ID of the Article which is to be talked about in the chat
	 * @param model the model
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
		
		newConversation.setArticleId(id);
		
		newConversation.setFromUnread(false);
		newConversation.setTitle(currentArticle.getTitle() + " in " + currentArticle.getLocation() + ".");
		newConversation.setToUnread(true);
		
		ChatMessage newMessage = new ChatMessage();
		newMessage.setFromId(currentUser.getId());
		newMessage.setFromUserName(this.userRepository.findOne(currentUser.getId()).getUsername());
		newMessage.setToUserName(this.userRepository.findOne(currentArticle.getCreator().getId()).getUsername());
		
		int messageBlockId = Integer.parseInt(newString.getContent());
		String messageBlockContent = this.possibleMessagesObject.getCertainTextBlock(messageBlockId).get(messageBlockId);
		newMessage.setContentId(messageBlockId);
		
		newMessage.setMessage(messageBlockContent);
		newMessage.setFreeText(newString.getContent2());
		newMessage.setToId(currentArticle.getCreator().getId());
		
		this.msgRepo.save(newMessage);
	
		newConversation.addChatMessage(newMessage);
		newConversation.setLastQuestion(messageBlockId);
		
		
		this.chatRepo.save(newConversation);
		
		return "redirect:/chat";
	}
	
	/**
	 * deletes a certain chat conversation.
	 *
	 * @param id of the chat conversation
	 * @param userAccount Optional either containing null or user acc of logged in user
	 * @return redirect to /chat
	 */
	@RequestMapping(value = "/chat/deleteConversation/{id}")
	public String deleteChatConversation(@PathVariable Long id, @LoggedIn Optional<UserAccount> userAccount) {
		ChatConversation cv = this.chatRepo.findOne(id);
		if(cv == null || !userAccount.isPresent()) return "error";
		
		long currentUserId = userRepository.findByUserAccount(userAccount.get()).getId();
		if(cv.getFromId() == currentUserId || cv.getToId() == currentUserId){
			this.chatRepo.delete(id);
			return "redirect:/chat"; 
		}else{
			return "error";
		}
	}
	
	

}
