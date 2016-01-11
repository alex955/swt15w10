package refugeeApp.model;

import org.hibernate.validator.constraints.Email;

/**
 * Created by Vincenz on 11.01.2016.
 */
public class ResetForm {

    @Email(message ="")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
