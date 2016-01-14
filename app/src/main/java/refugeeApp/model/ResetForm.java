package refugeeApp.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

/**
 * Created by Vincenz on 11.01.2016.
 */
@Data
public class ResetForm {

    @Email(message ="")
    private String email;

}
