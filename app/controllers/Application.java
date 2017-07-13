package controllers;
import play.data.*;
import akka.io.Tcp;
import play.mvc.Security;
import java.util.List;
import javax.inject.Inject;
import com.avaje.ebean.Model;
import models.Profile;
import play.mvc.Result;
import play.data.Form;
import com.avaje.ebean.Model;
import play.data.FormFactory;
import play.mvc.Controller;
//Views imports
import views.formData.RegisterFormData;
import views.html.welcomePage;
import views.formData.LoginFormData;
import views.html.login;
import views.html.register;
import views.html.profileWelcome;




import play.api.data.Forms;

import controllers.Application;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Lachlan on 23/06/2017.
 */
public class


Application extends Controller {
    @Inject
    FormFactory formFactory;
    //Giving the welcome page with the option to log i
    public Result welcomePage(){
        return ok(welcomePage.render());
    }

    //Functions used in logging in a user
    //https://bootsnipp.com/snippets/featured/custom-login-registration-amp-forgot-password
    public Result login(){
        Form<LoginFormData> formData = formFactory.form(LoginFormData.class);

        return ok(login.render("Login", false, null, formData));
    }
    public Result loginHelp(){
        return TODO;
    }
    //For deciding if the login  details are valid

    public static boolean isValid(String email, String password) {
        //If the email and string do no equal null
        int i =0;
        Profile profile = Profile.find.byId(email);
        return ((email != null)
                &&
                (password != null)
                &&
                (profile!=null)
                &&
                profile.password.equals(password));
    }
    public Result postLogin(){
        Form<LoginFormData> formData = formFactory.form(LoginFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Login credentials not valid.");
            return badRequest(login.render("Login", false, null, formData));
        }
        else {
            // email/password OK, so now we set the session variable and only go to authenticated pages.
            session().clear();
            session("email", formData.get().email);
            return redirect(routes.Application.profile(formData.get().email));
        }
    }

    //Functions for registering a new User
    public Result register(){
     Form<RegisterFormData> formData = formFactory.form(RegisterFormData.class);
        return ok(register.render("Register", false, null, formData));

    }

    public Result postRegister(){
        Form<RegisterFormData> formData = formFactory.form(RegisterFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Email is already in use");
            return badRequest(register.render("Register", false, null, formData));
        }
        else {
            // email/password OK, so now we set the session variable and only go to authenticated pages.
            session().clear();
            //Creating a new Profile item
            RegisterFormData data = formData.get();
            Profile profile = new Profile(data.email,data.userName,data.password,data.fullName);
            //add new profile to deck
            profile.save();
            session("email",data.email);
            return redirect(routes.Application.profile(formData.get().email));
        }
    }
    //Finding whether a new user is valid. ie there email isnt n use
    public static boolean isNewPValid(String email){

        Profile profile = Profile.find.byId(email);

        return ! (profile==null);


    }
    //Only available to a logged in user
    @Security.Authenticated(Secured.class)
    public Result profile(String name){
        //return TODO;
        return ok(profileWelcome.render("Welcome", Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx())));
    }
    @Security.Authenticated(Secured.class)
    public Result learn(){
        return redirect(routes.LearnController.learnDecks());
    }
    @Security.Authenticated(Secured.class)
    public Result generate(){
        return TODO;
    }
    @Security.Authenticated(Secured.class)
    public Result templates(){
        return TODO;
    }
    @Security.Authenticated(Secured.class)
    public Result logout(){
        session().clear();
        return redirect(routes.Application.welcomePage());
    }
}
