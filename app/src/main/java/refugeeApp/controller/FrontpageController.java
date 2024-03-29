package refugeeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import refugeeApp.model.*;
import refugeeApp.utilities.CategoryMethods;
import refugeeApp.utilities.email.EmailUsage;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.Locale;


/**
 * The Class FrontpageController.
 */
@Controller
public class FrontpageController {

	private final ValidatorRepository validatorRepository;

	private final LanguageRepository languageRepository;

	private final UserRepository userRepository;
	private final CategoryMethods categoryMethods;
	
	/** The processed categories. */
	protected LinkedList<CategoryFirstTierObject> processedCategories; 
	
	/**
	 * Instantiates a new frontpage controller.
	 *
	 * @param categoryMethods the category methods
	 */
	@Autowired
	public FrontpageController(CategoryMethods categoryMethods, ValidatorRepository validatorRepository, LanguageRepository languageRepository, UserRepository userRepository){
		this.categoryMethods = categoryMethods;
		this.validatorRepository = validatorRepository;
		this.languageRepository = languageRepository;
		this.userRepository = userRepository;
	}
	
	/**
	 * returns the frontpage with categories.
	 *
	 * @param model the model
	 * @return frontpage template
	 */
	@RequestMapping({"/", "/frontpage"})
	public String frontPage(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("current_category",new Category("AlleKategorien", 0));
		model.addAttribute("current_ort",new Location());
		return "frontpage";
	}
	
	@RequestMapping({"/imprint"})
	public String imprint(Model model) {
		this.processedCategories = categoryMethods.getProcessedCategories();
		model.addAttribute("categories", this.processedCategories);
		model.addAttribute("current_category",new Category("AlleKategorien", 0));
		model.addAttribute("current_ort",new Location());
		return "imprint";
	}

	@RequestMapping(value ="/reset")
	public String firstView(@ModelAttribute("ResetForm") ResetForm resetForm, Model model) {
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		return ("reset");
	}

	@RequestMapping(value = "/reset",  method = RequestMethod.POST)
	public String resetPw(@ModelAttribute("ResetForm") @Valid ResetForm resetForm, BindingResult result, Model model, ModelMap modelMap) throws MessagingException {

		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));

		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);
		if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
			browserLanguage = "en";
		}
		Language language = languageRepository.findByBrowserLanguage(browserLanguage);
		if(result.hasFieldErrors("email")){
			final String emailError = language.getEmailError();
			modelMap.addAttribute("emailError", emailError);
			return "reset";
		}

		if(userRepository.findByEmail(resetForm.getEmail()) == null){
			final String noAccountError = language.getNoAccountError();
			modelMap.addAttribute("noAccountError", noAccountError);
			return "reset";
		}

		User user = userRepository.findByEmail(resetForm.getEmail());

		Validator validator = new Validator(user, EmailUsage.PasswordReset);
		validatorRepository.save(validator);

		final String resetConfirm = language.getResetConfirm();
		modelMap.addAttribute("resetConfirm", resetConfirm);

		EMailController.sendEmail(user.getEmail(), validator.getToken(), validator.getUsage());
		return "reset";
	}

//	@RequestMapping({"/testVars"})
//	public String testVars(Model model) {
//		Locale currentLocale = LocaleContextHolder.getLocale();
//		model.addAttribute("currentLocale", currentLocale);
//		
//		Locale[] allLocales = Locale.getAvailableLocales();
//		model.addAttribute("allLocales", allLocales);
//		
//		return "testVars";
//	}
	
	
}
