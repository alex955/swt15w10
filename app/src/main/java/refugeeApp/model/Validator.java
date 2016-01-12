package refugeeApp.model;

import lombok.Data;
import refugeeApp.utilities.email.EmailUsage;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.*;
import java.util.Random;


@Entity
@Data
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
    @Enumerated(EnumType.ORDINAL)
    private EmailUsage usage;

    /**
     * Instantiates a new validator.
     *
     * @param user the user
     * @param usage the usage
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    public Validator(User user, EmailUsage usage) throws MessagingException {
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
}
