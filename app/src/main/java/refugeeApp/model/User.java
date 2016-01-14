package refugeeApp.model;


import lombok.Data;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;

import javax.persistence.*;


@Data
@Entity
public class User {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    
    /** The user account. */
    @OneToOne private UserAccount userAccount;
    
    /** The role. */
    private Role role;
    
    /** The last name. */
    private String lastName;
    
    /** The first name. */
    private String firstName;
    
    /** The username. */
    private String username;
    
    /** The username to lower case */
    private String usernameLowercase;
	
	/** The email. */
	private String email;
    
    /** The password. */
    private String password;
    
    /** The confirm pw. */
    private String confirmPW;
    
    /** The city. */
    private String city;
    
    /** The zip. */
    private String zip;
    
    /** The street name. */
    private String streetName;
    
    /** The address addition. */
    private String addressAddition;
    
    /** The country. */
    private String country;
    
    /** The language1. */
    private String language1;
    
    /** The language2. */
    private String language2;
    
    /** The language3. */
    private String language3;
    
    /** The latitude. */
    private double  latitude;
    
    /** The longitude. */
    private double longitude;    
    
    /** The validated. */
    private boolean validated;
    
    /** The hashcode. */
    private int hashcode;


    /**
     * Checks if is validated.
     *
     * @return true, if is validated
     */
    public boolean isValidated() {
		return validated;
	}

	public User() {}

    /**
     * Instantiates a new user.
     *
     * @param id the id
     * @param userAccount the user account
     * @param lastName the last name
     * @param firstName the first name
     * @param country the country
     * @param email the email
     * @param city the city
     * @param zip the zip
     * @param streetName the street name
     * @param addressAddition the address addition
     * @param language1 the language1
     * @param language2 the language2
     * @param language3 the language3
     */
    public User(long id, UserAccount userAccount, String lastName, String firstName, String country, String email, String city, String zip, String streetName, String addressAddition, String language1, String language2, String language3) {
        this.userAccount = userAccount;        
        this.username = userAccount.getUsername();       
        this.lastName = lastName;
        this.firstName = firstName;
        this.country = country;
        this.email = email;
        this.city = city;
        this.zip = zip;
        this.streetName = streetName;
        this.addressAddition = addressAddition;
        this.language1 = language1;
        this.language2 = language2;
        this.language3 = language3;        
        this.validated = false;
        this.hashcode = (lastName + firstName + Long.toString(id)).hashCode();   
        this.usernameLowercase = username.toLowerCase();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
		return "User [id=" + id + ", userAccount=" + userAccount + ", role=" + role + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", username=" + username + ", country=" + country + ", email=" + email
				+ ", password=" + password + ", confirmPW=" + confirmPW + ", city=" + city + ", zip=" + zip
				+ ", streetName=" + streetName + ", addressAddition=" + addressAddition
				+ ", language1=" + language1 + ", language2=" + language2 + ", language3=" + language3 + ", validated="
				+ validated + ", hashcode=" + hashcode + "]";
	}
}