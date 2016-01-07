package refugeeApp.controller;


import java.util.Properties;
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
	public EMailController(UserRepository userRepository, ValidatorRepository validatorRepository, UserSettingsRepository userSettingsRepository) {

		this.validatorRepository = validatorRepository;
		this.userRepository = userRepository;
		this.userSettingsRepository = userSettingsRepository;
	}


	/**
	 * Sends E-Mail by using the MimeMessage Class with the smpt protocol.
	 *
	 * @author Lukas Klose
	 */
	public static void sendEmail(String receiver, String token, int usage) throws AddressException, MessagingException {

		final String username = "gandalf324687992";
		final String password = "324687992";


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
				message.setSubject("RefugeeApp: EMail-Verifizierung");
				message.setText("Zum Registrieren Ihres Accounts klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=" + token + "\n\n Lokal: localhost:8080/validate?id=" + token);
				break;
			}

			case 2: {
				message.setSubject("RefugeeApp: Account deaktivieren");
				message.setText("Zum Deaktivieren Ihres Accounts klicken Sie auf den Link.\n\n" +"Testserver: http://refugee-app.tk/swt15w10/validate?id=" + token + "\n\n Lokal: localhost:8080/validate?id=" + token);
				break;
			}

			case 3: {
				message.setSubject("RefugeeApp: EMail-Änderung");
				message.setText("Zum Ändern Ihrer Mailadresse klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=" + token + "\n\n Lokal: localhost:8080/validate?id=" + token);
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
