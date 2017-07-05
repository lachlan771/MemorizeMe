package views.formData.cardTypes;

import controllers.LearnController;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lachlan on 27/06/2017.
 */
public class StandardCardFormData {
    public String question;
    public String Answer;

    /**
     * Validates Form<LoginFormData>.
     * Called automatically in the controller by bindFromRequest().
     * Checks to see that email and password are valid credentials.
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        //Possible error checking

        return (errors.size() > 0) ? errors : null;
    }
}
