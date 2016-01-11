package refugeeApp.model;

import javax.validation.constraints.Pattern;

/**
 * Created by Vincenz on 11.01.2016.
 */
public class NewPasswordForm {

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "")
    private String password;

    private String confirmPw;

    private String pwToken;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPw() {
        return confirmPw;
    }

    public void setConfirmPw(String confirmPw) {
        this.confirmPw = confirmPw;
    }

    public String getPwToken() {
        return pwToken;
    }

    public void setPwToken(String pwToken) {
        this.pwToken = pwToken;
    }
}
