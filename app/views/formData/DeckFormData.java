package views.formData;

import controllers.LearnController;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lachlan on 25/06/2017.
 */
public class DeckFormData {
    public String name;

    /**
     * Validates Form<LoginFormData>.
     * Called automatically in the controller by bindFromRequest().
     * Checks to see that email and password are valid credentials.
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (!LearnController.isNewDeckValid(name)) {
            errors.add(new ValidationError("name", "error"));
        }

        return (errors.size() > 0) ? errors : null;
    }
}
