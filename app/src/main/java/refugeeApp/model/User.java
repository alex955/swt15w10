package refugeeApp.model;


import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;

import javax.persistence.*;



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

	/**
	 * Sets the validated.
	 *
	 * @param validated the new validated
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	/**
	 * Gets the hashcode.
	 *
	 * @return the hashcode
	 */
	public int getHashcode() {
		return hashcode;
	}

	/**
	 * Sets the hashcode.
	 *
	 * @param hashcode the new hashcode
	 */
	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}

	/**
	 * Instantiates a new user.
	 */
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

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the confirm pw.
     *
     * @return the confirm pw
     */
    public String getConfirmPW() {
        return confirmPW;
    }

    /**
     * Sets the confirm pw.
     *
     * @param confirmPW the new confirm pw
     */
    public void setConfirmPW(String confirmPW) {
        this.confirmPW = confirmPW;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the zip.
     *
     * @param zip the new zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the street name.
     *
     * @return the street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the street name.
     *
     * @param streetName the new street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Gets the address addition.
     *
     * @return the address addition
     */
    public String getAddressAddition() {
        return addressAddition;
    }

    /**
     * Sets the address addition.
     *
     * @param addressAddition the new address addition
     */
    public void setAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the language1.
     *
     * @return the language1
     */
    public String getLanguage1() {
        return language1;
    }

    /**
     * Sets the language1.
     *
     * @param language1 the new language1
     */
    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    /**
     * Gets the language2.
     *
     * @return the language2
     */
    public String getLanguage2() {
        return language2;
    }

    /**
     * Sets the language2.
     *
     * @param language2 the new language2
     */
    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    /**
     * Gets the language3.
     *
     * @return the language3
     */
    public String getLanguage3() {
        return language3;
    }

    /**
     * Sets the language3.
     *
     * @param language3 the new language3
     */
    public void setLanguage3(String language3) {
        this.language3 = language3;
    }

    /**
     * Gets the user account.
     *
     * @return the user account
     */
    public UserAccount getUserAccount() {
        return userAccount;
    }

    /**
     * Sets the user account.
     *
     * @param userAccount the new user account
     */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}