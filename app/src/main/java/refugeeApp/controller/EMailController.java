package refugeeApp.controller;


import java.util.Locale;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import refugeeApp.model.*;


@Controller
public class EMailController {

	@Autowired
	private UserAccountManager userAccountManager;

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final ValidatorRepository validatorRepository;

	@Autowired
	private final UserSettingsRepository userSettingsRepository;

	@Autowired
	private final LanguageRepository languageRepository;

	private static LanguageRepository staticRepository;

	@PostConstruct
	public void init(){
		this.staticRepository = languageRepository;
	}

	/**
	 * autowired constructor
	 * @param userRepository
	 * @param validatorRepository
	 * @param userSettingsRepository
	 * @param languageRepository
	 */
	@Autowired
	public EMailController(UserRepository userRepository, ValidatorRepository validatorRepository, UserSettingsRepository userSettingsRepository, LanguageRepository languageRepository) {

		this.validatorRepository = validatorRepository;
		this.userRepository = userRepository;
		this.userSettingsRepository = userSettingsRepository;
		this.languageRepository = languageRepository;
	}


	/**
	 * Sends E-Mail by using the MimeMessage Class with the smpt protocol.
	 *
	 * @author Lukas Klose
	 */
	public static void sendEmail(String receiver, String token, int usage) throws AddressException, MessagingException {

		final String username = "gandalf324687992";
		final String password = "324687992";
		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);

		if(staticRepository.findByBrowserLanguage(browserLanguage) == null){
			browserLanguage = "en";
		}

		Language language = staticRepository.findByBrowserLanguage(browserLanguage);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("gandalf324687992@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));

          /*
          * Cases: 1 = Registrierung
          *        2 = Account deaktivieren
          *  	   3 = Email ändern
          */

		switch (usage) {
			case 1: {
				message.setSubject(language.getRegistrationEmailTopic());
				message.setText(String.format(language.getRegistrationEmail(), token, token));
				break;
			}

			case 2: {
				message.setSubject(language.getDeleteEmailTopic());
				message.setText(String.format(language.getDeleteEmail(), token, token));
				break;
			}

			case 3: {
				message.setSubject(language.getChangeEmailTopic());
				message.setText(String.format(language.getChangeEmail(), token, token));
				break;
			}
		}
		Transport.send(message);
	}

	/**
	 * Searches for the given HashID in UserRepository and sets the validated flag to true if
	 * it is found.
	 *
	 * @author Lukas Klose
	 */
	@RequestMapping(value = "/validate")
	public String validation(@RequestParam String id) {

		Validator validator = validatorRepository.findByToken(id);

		if (validator == null)
			return "redirect:/frontpage";

		else {
			User user = validator.getUser();

			switch (validator.getUsage()) {
				case 1: {
					userAccountManager.enable(user.getUserAccount().getIdentifier());
					validatorRepository.delete(validator);
					return "redirect:/";
				}
				case 2: {
					userAccountManager.disable(user.getUserAccount().getIdentifier());
					validatorRepository.delete(validator);
					return "redirect:/";
				}
				case 3: {
					return "redirect:/changeemail?token=" + id;
				}
			}
		}
		return "redirect:/";
	}

	/**
	 * changes mail
	 * @param userSettingsForm
	 * @param token
	 * @return
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

}
