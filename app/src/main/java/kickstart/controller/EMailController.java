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

import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kickstart.model.User;
import kickstart.model.UserRepository;


@Controller
public class EMailController extends CommonVariables{
	
	@Autowired
	private UserAccountManager userAccountManager;

	@Autowired
    public EMailController(UserRepository userRepository){
        //this.userAccountManager = userAccountManager;
        this.userRepository = userRepository;
        
    }
	
    /**
     * Sends E-Mail by using the MimeMessage Class with the smpt protocol.
     * 
     * @author Lukas Klose
     */
	  public static void SendEmail(String reciever, long l) throws AddressException, MessagingException{

		  final String username = "gandalf324687992";
		  final String password = "324687992";
		  String validationID = generateString(16);


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
		              InternetAddress.parse(reciever));
		      message.setSubject("RegistrierungsID");
		      message.setText("Ihre ID lautet " + validationID +".\n\n" + "http://localhost:8080/validate?id=" + validationID);

		  	Transport.send(message);
		     System.out.println("E-Mail gesendet an " + reciever);
		     System.out.println("Mit id"+ Long.toString(l));

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


		  int realID = 0;
		  try {
			  realID = Integer.parseInt(id);
		  }
		  catch(Exception e){
			  System.out.println("NaN");
		  }

		  User foundUser = userRepository.findOne(Long.parseLong(id));

		  if(foundUser == null){

			  return "frontpage";

		  }
		  else{
			  System.out.println(foundUser.toString());
			  foundUser.setValidated(true);
			  System.out.println(foundUser.getUserAccount());
			  userAccountManager.enable(foundUser.getUserAccount().getIdentifier());
			  System.out.println("enabled nach email= " + foundUser.getUserAccount().isEnabled());

			  
			  //userRepository.delete(foundUser.getId());
			  userRepository.save(foundUser);
			  
		  }
		  
		  System.out.println(id);
		  //return "frontpage";
		  
		  
		  return "redirect:/";
		  
		  
	  }

	public static String generateString(int length)
	{
		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rng = new Random();

		char[] text = new char[length];
		for (int i = 0; i < length; i++)
		{
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}
	  
}
