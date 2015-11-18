package kickstart.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Vincenz on 27.10.15.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private long id;
    private String role;
    private String lastName;
    private String firstName;
    private String username;
    private String email;
    private String password;
    private String confirmPW;
    private String city;
    private String zip;
    private String streetName;
    private long houseNumber;
    private String addressAddition;
    private String language1;
    private String language2;
    private String language3;


    public User() {}

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

    public User(long id, String role, String lastName, String firstName, String username, String email, String password, String confirmPW, String city, String zip, String streetName, long houseNumber,String addressAddition, String language1, String language2, String language3) {

        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPW = confirmPW;
        this.city = city;
        this.zip = zip;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.addressAddition = addressAddition;
        this.language1 = language1;
        this.language2 = language2;
        this.language3 = language3;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id='%d', role='%s', lastName='%s', firstName='%s', username='%s', email='%s', password='%s', confirmPW='%s', city='%s', zip='%s', streetName='%s', houseNumber='%d', addressAddition='%s', language1='%s', language2='%s', language3='%s' ]", id, role, lastName, firstName, username, email, password, confirmPW, city, zip, streetName, houseNumber, addressAddition, language1, language2, language3);

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    public long getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(long houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressAddition() {
        return addressAddition;
    }

    public void setAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
    }
}

