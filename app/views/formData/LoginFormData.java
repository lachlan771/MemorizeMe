package views.formData;
import play.data.validation.ValidationError;
import java.util.ArrayList;
import java.util.List;
import models.Profile;
import controllers.Application;
import play.api.data.Forms;

/**
 * Created by Lachlan on 23/06/2017.
 */
public class LoginFormData {
    /** The submitted email. */

    public String email = "";
    /** The submitted password. */
    public String password = "";

    /** Required for form instantiation. */

    public LoginFormData() {
    }

    /**
     * Validates Form<LoginFormData>.
     * Called automatically in the controller by bindFromRequest().
     * Checks to see that email and password are valid credentials.
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (!Application.isValid(email, password)) {
           errors.add(new ValidationError("email", "error"));
           errors.add(new ValidationError("password", ""));
        }

        return (errors.size() > 0) ? errors : null;
    }
}
