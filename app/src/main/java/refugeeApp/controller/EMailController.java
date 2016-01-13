package refugeeApp.controller;


import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import refugeeApp.model.*;
import refugeeApp.utilities.email.EmailUsage;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Properties;


/**
 * The Class EMailController.
 */
@Controller
public class EMailController {

	private static final InternetAddress emailSender;
	private static final String emailUsername = "gandalf324687992";
	private static final String emailPassword = "324687992";
	private static final javax.mail.Authenticator emailAuthenticator = new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(emailUsername, emailPassword);
		}
	};
	private static final Properties props = new Properties();
	/** The static repository. */
	private static LanguageRepository staticRepository;

	static {
		InternetAddress emailSender1;
		try {
			emailSender1 = new InternetAddress("gandalf324687992@gmail.com");
		} catch (AddressException e) {
			emailSender1 = null;
		}
		emailSender = emailSender1;
	}

	static {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	/**
	 * The user repository.
	 */
	private final UserRepository userRepository;
	/**
	 * The validator repository.
	 */
	private final ValidatorRepository validatorRepository;
	/**
	 * The user settings repository.
	 */
	private final UserSettingsRepository userSettingsRepository;
	/**
	 * The language repository.
	 */
	private final LanguageRepository languageRepository;
	/**
	 * The user account manager.
	 */
	private UserAccountManager userAccountManager;

	/**
	 * autowired constructor.
	 *
	 * @param userRepository the user repository
	 * @param validatorRepository the validator repository
	 * @param userSettingsRepository the user settings repository
	 * @param languageRepository the language repository
	 */
	@Autowired
	public EMailController(UserRepository userRepository, ValidatorRepository validatorRepository, UserSettingsRepository userSettingsRepository, LanguageRepository languageRepository, UserAccountManager userAccountManager) {

		this.validatorRepository = validatorRepository;
		this.userRepository = userRepository;
		this.userSettingsRepository = userSettingsRepository;
		this.languageRepository = languageRepository;
		this.userAccountManager = userAccountManager;
	}

	/**
	 * Sends E-Mail by using the MimeMessage Class with the smpt protocol.
	 *
	 * @param receiver the receiver
	 * @param token the token
	 * @param usage the usage
	 * @throws AddressException the address exception
	 * @throws MessagingException the messaging exception
	 */
	public static void sendEmail(String receiver, String token, EmailUsage usage) throws MessagingException {

		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);

		if (staticRepository.findByBrowserLanguage(browserLanguage) == null) {
			browserLanguage = "en";
		}

		Language language = staticRepository.findByBrowserLanguage(browserLanguage);

		Session session = Session.getInstance(props, emailAuthenticator);

		Message message = new MimeMessage(session);
		message.setFrom();
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));


		switch (usage) {
			case Registration: {
				message.setSubject(language.getRegistrationEmailTopic());
				message.setText(String.format(language.getRegistrationEmail(), token, token));
				break;
			}

			case Deactivation: {
				message.setSubject(language.getDeleteEmailTopic());
				message.setText(String.format(language.getDeleteEmail(), token, token));
				break;
			}

			case Edit: {
				message.setSubject(language.getChangeEmailTopic());
				message.setText(String.format(language.getChangeEmail(), token, token));
				break;
			}

			case PasswordReset: {
				message.setSubject(language.getResetPwTopic());
				message.setText(String.format(language.getResetPw(), token, token));
				break;
			}

		}
		Transport.send(message);
	}

	/**
	 * Inits the static Repository.
	 */
	@PostConstruct
	public void init() {
		staticRepository = languageRepository;
	}

	/**
	 * Searches for the given HashID in UserRepository and sets the validated flag to true if
	 * it is found.
	 *
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = "/validate")
	public String validation(@RequestParam String id) throws MessagingException {

		Validator validator = validatorRepository.findByToken(id);

		if (validator == null)
			return "redirect:/";

		else {
			User user = validator.getUser();

			switch (validator.getUsage()) {
				case Registration: {
					userAccountManager.enable(user.getUserAccount().getIdentifier());
					validatorRepository.delete(validator);
					return "redirect:/";
				}
				case Deactivation: {
					userAccountManager.disable(user.getUserAccount().getIdentifier());
					validatorRepository.delete(validator);
					return "redirect:/";
				}
				case Edit: {
					return "redirect:/changeemail?token=" + id;
				}
				case PasswordReset: {
					return "redirect:/resetpw?token=" + id;
				}

			}
		}
		return "redirect:/";
	}

	/**
	 * changes mail.
	 *
	 * @param userSettingsForm the user settings form
	 * @param token the token
	 * @return the string
	 */
	@RequestMapping(value = "/changeemail")
	public String changeEmail(@ModelAttribute("userSettingsForm") UserSettingsForm userSettingsForm, @RequestParam String token){

		if (validatorRepository.findByToken(token) == null){
			return "redirect:/";
		}

		User user = validatorRepository.findByToken(token).getUser();
		UserSettings userSettings = userSettingsRepository.findByUserId(user.getId());

		user.setEmail(userSettings.getNewEmail());

		userRepository.save(user);
		validatorRepository.delete(validatorRepository.findByToken(token));

		return "redirect:/usersettings";
	}

	@RequestMapping(value ="/resetpw")
	public String setPw(@ModelAttribute("NewPasswordForm") NewPasswordForm newPasswordForm, Model model, @RequestParam String token) {
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		model.addAttribute("newPasswordform", newPasswordForm);
		model.addAttribute("pwToken", token);

        if (validatorRepository.findByToken(token) == null){
            return "redirect:/";
        }

		newPasswordForm.setPwToken(token);

		return "resetpw";
	}

	@RequestMapping(value = "/resetpw", method = RequestMethod.POST)
	public String changePw(@ModelAttribute("NewPasswordForm") @Valid NewPasswordForm newPasswordForm, BindingResult result, Model model, ModelMap modelMap){

		model.addAttribute("newPasswordform", newPasswordForm);
		model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));
		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);
		if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
			browserLanguage = "en";
		}
		Language language = languageRepository.findByBrowserLanguage(browserLanguage);

        String token = newPasswordForm.getPwToken();

		if(result.hasFieldErrors("password")){
			final String passwordError = language.getPasswordError();
			modelMap.addAttribute("passwordError", passwordError);
			return "resetpw";
		}

		if(!newPasswordForm.getPassword().equals(newPasswordForm.getConfirmPw())){
			final String passwordConfirmError = language.getPasswordConfirmError();
			modelMap.addAttribute("passwordConfirmError", passwordConfirmError);
			return "resetpw";
		}

		if (validatorRepository.findByToken(token) == null){
			return "redirect:/";
		}

		User user = validatorRepository.findByToken(token).getUser();

		userAccountManager.changePassword(user.getUserAccount(), newPasswordForm.getPassword());
		userRepository.save(user);
		validatorRepository.delete(validatorRepository.findByToken(token));

		return "redirect:/search";
	}


}
