package kickstart.controller;


import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import kickstart.model.Validator;
import kickstart.model.ValidatorRepository;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kickstart.model.User;
import kickstart.model.UserRepository;


@Controller
public class EMailController {

	@Autowired
	private UserAccountManager userAccountManager;
	
    @Autowired
    private final UserRepository userRepository;

	@Autowired
	private ValidatorRepository validatorRepository;

	@Autowired
    public EMailController(UserRepository userRepository, ValidatorRepository validatorRepository){
		this.validatorRepository = validatorRepository;
		this.userRepository = userRepository;
        
    }
	
    /**
     * Sends E-Mail by using the MimeMessage Class with the smpt protocol.
     * 
     * @author Lukas Klose
     */
	  public static void sendEmail(String receiver, String token, int usage) throws AddressException, MessagingException{

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
          *  TODO  3 = Email ändern
          */

          switch(usage) {

              case 1: {
                  message.setSubject("RefugeeApp: EMail-Verifizierung");
                  message.setText("Zum Registrieren Ihrer Email klicken Sie auf den Link.\n\n" + "http://localhost:8080/validate?id=" + token);
                  break;
              }

              case 2: {
                  message.setSubject("RefugeeApp: Account deaktivieren");
                  message.setText("Zum Deaktivieren Ihres Accounts klicken Sie auf den Link.\n\n" + "http://localhost:8080/validate?id=" + token);
              }

          }
          Transport.send(message);
      }
	    /**
	     * 
	     * Searches for the given HashID in UserRepository and sets the validated flag to true if 
	     * it is found.
	     * 
	     * @author Lukas Klose
	     */
	  @RequestMapping(value = "/validate")
	  public String validation(@RequestParam String id){

		  Validator validator = validatorRepository.findByToken(id);

		  if (validator == null)
			  return "frontpage";

		  else {
			switch(validator.getUsage()) {
				case 1:
					userAccountManager.enable(validator.getUser().getUserAccount().getIdentifier());
					break;
				case 2:
					userAccountManager.disable(validator.getUser().getUserAccount().getIdentifier());
					break;
			}
		  }
		  return "redirect:/";
	}


	  
}
