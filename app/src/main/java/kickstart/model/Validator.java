package kickstart.model;

import org.salespointframework.useraccount.UserAccountManager;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.*;
import java.util.Random;

/**
 * Created by Vincenz on 07.12.15.
 */
@Entity
public class Validator {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    
    @OneToOne
    private User user;
    
    private String token;
    private int usage;
    
    //private UserAccountManager userAccountManager;


    public Validator(User user, int usage) throws AddressException, MessagingException {
        super();
    	this.user = user;
        this.token = generateToken(32);
        this.usage = usage;
    }
    
    

    public Validator() {
		super();
	}



	public static String generateToken(int length)
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }


}
