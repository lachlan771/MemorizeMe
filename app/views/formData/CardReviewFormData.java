package views.formData;

import controllers.LearnController;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lachlan on 30/06/2017.
 */
public class CardReviewFormData {
    public UUID cardID;
    public String deckID;
    public int responseQuality;
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

//        if (!LearnController.isNewDeckValid(name)) {
//            errors.add(new ValidationError("name", "error"));
//        }

        return (errors.size() > 0) ? errors : null;
    }

}
