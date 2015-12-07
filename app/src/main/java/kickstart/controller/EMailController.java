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
	  public static void SendEmail(String receiver,  String token) throws AddressException, MessagingException{

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
		      message.setRecipients(Message.RecipientType.TO,
		              InternetAddress.parse(receiver));
		      message.setSubject("RegistrierungsID");
		      message.setText("Ihre ID lautet " + token +".\n\n" + "http://localhost:8080/validate?id=" + token);

		  	Transport.send(message);
		     System.out.println("E-Mail gesendet an " + receiver);
		     System.out.println("Mit id "+ token );

	  }
	  
	    /**
	     * 
	     * Searches for the given HashID in UserRepository and sets the validated flag to true if 
	     * it is found.
	     * 
	     * @author Lukas Klose
	     */
	  @RequestMapping(value = "/validate")
	  public String validation(@RequestParam String token){


		  Validator validator = validatorRepository.findByToken(token);

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
