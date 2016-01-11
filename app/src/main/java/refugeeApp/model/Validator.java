package refugeeApp.model;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.*;
import java.util.Random;


@Entity
public class Validator {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    /** The user. */
    @OneToOne
    private User user;

    /** The token. */
    private String token;
    
    /** The usage. */
    private int usage;

    /**
     * Instantiates a new validator.
     *
     * @param user the user
     * @param usage the usage
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    public Validator(User user, int usage) throws AddressException, MessagingException {
        super();
        this.user = user;
        this.token = generateToken(32);
        this.usage = usage;
    }

    /**
     * Instantiates a new validator.
     */
    public Validator() {
        super();
    }

    /**
     * Generate token.
     *
     * @param length the length
     * @return the string
     */
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

    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token the new token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the usage.
     *
     * @return the usage
     */
    public int getUsage() {
        return usage;
    }

    /**
     * Sets the usage.
     *
     * @param usage the new usage
     */
    public void setUsage(int usage) {
        this.usage = usage;
    }


}
