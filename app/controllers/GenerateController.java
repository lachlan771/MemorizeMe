package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.formData.DeckFormData;
import views.formData.generateCardsTextForm;

/**
 * Created by burnish on 27/07/17.
 */
public class GenerateController extends Controller{
    FormFactory formFactory;
    public Result postGenerate(){

        Form<generateCardsTextForm> formData = formFactory.form(generateCardsTextForm.class);
        return redirect(routes.Application.generate());

    }
}
