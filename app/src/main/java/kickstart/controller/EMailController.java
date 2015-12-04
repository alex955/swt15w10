package kickstart.controller;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kickstart.model.User;
import kickstart.model.UserRepository;


@Controller
public class EMailController extends CommonVariables{
	
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
	  public static void SendEmail(String reciever, int id) throws AddressException, MessagingException{

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
		              InternetAddress.parse(reciever));
		      message.setSubject("RegistrierungsID");
		      message.setText("Ihre ID lautet " + id +".\n\n" + "http://localhost:8080/validate?id=" + id);

		      Transport.send(message);
		     System.out.println("E-Mail gesendet an " + reciever);
		     System.out.println("Mit id"+ Long.toString(id));

	  }
	  
	    /**
	     * 
	     * Searches for the given HashID in UserRepository and sets the validated flag to true if 
	     * it is found.
	     * 
	     * @author Lukas Klose
	     */
	  @RequestMapping(value = "/validate")
	  public String validation(@RequestParam(value = "id", required = false) String id){
		  System.out.println("In Funktion");
		  int realID = 0;
		  try {
			  realID = Integer.parseInt(id);
		  }
		  catch(Exception e){
			  System.out.println("NaN");
		  }
		  User foundUser = userRepository.findByHashcode(realID);
		  
		  if(foundUser == null){
			  return "frontpage";
		  }
		  else{
			  foundUser.setValidated(true);
			  
			  userRepository.delete(foundUser.getId());
			  userRepository.save(foundUser);
			  
		  }
		  
		  System.out.println(id);
		  return "frontpage";
		  
		  
		  
		  
	  }
	  
}
