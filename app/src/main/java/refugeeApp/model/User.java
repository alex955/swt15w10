package refugeeApp.model;


import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;

import javax.persistence.*;

/**
 * Created by Vincenz on 27.10.15.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private long id;

    @OneToOne
    private UserAccount userAccount;

    private Role role;
    private String lastName;
    private String firstName;
    private String username;
	private String email;
    private String password;
    private String confirmPW;
    private String city;
    private String zip;
    private String streetName;
    private String addressAddition;
    private String country;
    private String language1;
    private String language2;
    private String language3;
    private double  latitude;
    private double longitude;
   
    
    private boolean validated;
    private int hashcode;


    public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public int getHashcode() {
		return hashcode;
	}

	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}

	public User() {}

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

    @Override
	public String toString() {
		return "User [id=" + id + ", userAccount=" + userAccount + ", role=" + role + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", username=" + username + ", country=" + country + ", email=" + email
				+ ", password=" + password + ", confirmPW=" + confirmPW + ", city=" + city + ", zip=" + zip
				+ ", streetName=" + streetName + ", addressAddition=" + addressAddition
				+ ", language1=" + language1 + ", language2=" + language2 + ", language3=" + language3 + ", validated="
				+ validated + ", hashcode=" + hashcode + "]";
	}

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getConfirmPW() {
        return confirmPW;
    }

    public void setConfirmPW(String confirmPW) {
        this.confirmPW = confirmPW;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAddressAddition() {
        return addressAddition;
    }

    public void setAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public String getLanguage3() {
        return language3;
    }

    public void setLanguage3(String language3) {
        this.language3 = language3;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


}

