package views.formData;

import controllers.LearnController;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burnish on 27/07/17.
 */
public class generateCardsTextForm {
    public String cardText;
    public List<ValidationError> validate() {
        java.util.List<play.data.validation.ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }
}
