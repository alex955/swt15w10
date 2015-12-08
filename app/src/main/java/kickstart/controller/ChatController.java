package kickstart.controller;

import java.util.LinkedList;
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
import kickstart.model.User;
import kickstart.model.UserRepository;
import kickstart.utilities.CategoryMethods;
import kickstart.utilities.StringInForm;

@Controller
public class ChatController {
	
	@Autowired private final CategoryRepo categories;
	@Autowired private final CategoryMethods categoryMethods;
	@Autowired private final ArticleRepo articleRepo;
	@Autowired private final ChatConversationRepo chatRepo;
    @Autowired private final UserRepository userRepository;
	
	//Classvars
	protected LinkedList<CategoryFirstTierObject> processedCategories;

	@Autowired
	public ChatController(CategoryRepo categories, CategoryMethods categoryMethods, ArticleRepo articleRepo, ChatConversationRepo chatRepo, UserRepository userRepository) {
		this.categories = categories;
		this.categoryMethods = categoryMethods;
		this.articleRepo = articleRepo;
		this.chatRepo = chatRepo;
		this.userRepository = userRepository;
	} 
	
	@RequestMapping("/chat")
	public String chat(Model model){
		//initiate categories
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		
		return "chat";
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
	
	@RequestMapping(value = "/chat/newConversation/{id}", method = RequestMethod.POST)
	public String processNewMessage(@PathVariable("id") long id,Model model, @LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("chatMessage") StringInForm newString) {
		if(userAccount.get() == null) return "error";
		
		User currentUser = this.userRepository.findByUserAccount(userAccount.get());
		Article currentArticle = this.articleRepo.findOne(id);
		
		String messageContent = newString.getContent();
		
		ChatConversation newConversation = new ChatConversation();
		
		newConversation.setFromId(currentUser.getId());
		newConversation.setToId(currentArticle.getCreator().getId());
		
		newConversation.setFromUnread(false);
		newConversation.setTitle(currentArticle.getTitle() + " in " + currentArticle.getLocation() + ".");
		newConversation.setToUnread(true);
		//newConversation.addMessage(messageContent);
		
		this.chatRepo.save(newConversation);
		
		return "chat";
	}
	
	
	
	

}
