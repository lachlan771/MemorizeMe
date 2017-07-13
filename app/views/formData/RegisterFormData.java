package views.formData;
import play.api.data.Forms;

import controllers.Application;
import play.data.validation.ValidationError;
import scala.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lachlan on 23/06/2017.
 */
public class RegisterFormData {
    /** The submitted userName. */
    public String userName = "";
    /** The submitted password. */
    public String password = "";
    /** The submitted email. */
    public String email = "";
    /** The submitted fullname */
    public String fullName = "";
    /** The submitted gender. */
    public String gender = "";

    /** Required for form instantiation. */

    public RegisterFormData() {
    }

    /**
     * Validates Form<LoginFormData>.
     * Called automatically in the controller by bindFromRequest().
     * Checks to see that email and password are valid credentials.
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        //See if the email matches an already enetered user
        //if (!Application.isNewProfileValid(email, password,userName,fullName,gender)) {
           // errors.add(new ValidationError("email", "email already in user"));
            //errors.add(new ValidationError("password", ""));
       // }
        //if the name enetered is null
        //The valid function checks to see if the email entered matches another users; if it does it returns true, if a current profile doesnt have that email it returns false and that means a new one can be made
        if(Application.isNewPValid(email)){
            errors.add(new ValidationError("email",""));
        }

        return errors.isEmpty() ? null:errors;
    }
}
