package refugeeApp.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * Created by Vincenz on 11.01.2016.
 */
@Data
public class NewPasswordForm {

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "")
    private String password;

    private String confirmPw;

    private String pwToken;

}
