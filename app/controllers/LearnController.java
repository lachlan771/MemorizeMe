package controllers;

import com.avaje.ebean.Ebean;
import models.AbstractCard;
import models.Deck;
import models.Profile;
import models.StandardCard;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import views.formData.CardFormData;
import views.formData.CardReviewFormData;
import views.formData.DeckFormData;
import views.formData.cardTypes.StandardCardFormData;
import views.html.learner.*;
import play.mvc.Http.Context;

import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Lachlan on 25/06/2017.
 */
public class LearnController extends Controller {
    @Inject
    FormFactory formFactory;
    //Where the user will be directed when they click Learn option in profile toolbar
    @Security.Authenticated(Secured.class)
    public Result learnDecks(){
        return ok(learnerHome.render("Welcome", Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx())));
        }
    //Shows all the decks with editing options
    @Security.Authenticated(Secured.class)
    public Result index() {
        //Getting all te decks that the user owns

        return ok(deckIndex.render("Welcome", Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx()),Secured.getUserInfo(ctx()).decks));
    }
    //handles creating a new deck
    @Security.Authenticated(Secured.class)
    public Result createDeck(){
        Form<DeckFormData> formData = formFactory.form(DeckFormData.class);
        return ok(createDeck.render("Welcome", Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx()),formData));
    }
    //For editing the deck
    public Result editDeck(String name,int page, String sortBy, String order, String filter) {
        //Getting the deck from the list of decks
        String profilename = Secured.getUserInfo(ctx()).email;

        //Deck deck = Deck.find.byId((name+"-"+profilename));
        Deck deck = Deck.find.byId((name));

        Form<CardFormData> formData = formFactory.form(CardFormData.class).bindFromRequest();
        return ok(

                editDeck.render("Welcome", Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx()),
                        deck.page(page, 10, sortBy, order, filter), sortBy, order, filter,deck,formData)
        );
    }
    public Result learn(String deckName){
        return TODO;
    }
    //Methods that involve the creation of the deck
    @Security.Authenticated(Secured.class)
    public static boolean isNewDeckValid(String name){

        Deck deck = Deck.find.byId((name+"-"+Secured.getUserInfo(ctx()).email));


        return  (deck==null);
    }
    //The creation of the card in the EditLIst page
    @Security.Authenticated(Secured.class)
    public Result postCreateDeck(){
        Form<DeckFormData> formData = formFactory.form(DeckFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Deck name is already being used");
            return badRequest(createDeck.render("Create Deck",  Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx()), formData));
        }
        else {
            //The profile is gotten this way as the Secured.getUserInfo(ctc()) is a static method and static methods return transient object(arent saved)
            String profilename = Secured.getUserInfo(ctx()).email;
            Profile profile = Profile.find.byId(profilename);
            Deck deck = new Deck(formData.get().name,0,0,profile);
            //Saving the deck to database
            deck.save();
            //adding deck to decks
            profile.decks.add(deck);
            //Saving the profile
            profile.save();
           return redirect(routes.LearnController.index());
        }
    }
    //Methood that takes a cardform data and adds the card to the deck
    public Result postCardCreation(String name,int page, String sortBy, String order, String filter){
        Form<CardFormData> formData = formFactory.form(CardFormData.class).bindFromRequest();
        Deck deckID =Deck.find.byId(name);

        if (formData.hasErrors()) {


            flash("error", "Error creating card");
            return badRequest(editDeck.render("Welcome", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()),
                    deckID.page(page, 10, sortBy, order, filter), sortBy, order, filter, deckID, formData));
        }

        else {
            deckID.cardFactory(formData.get(),deckID);
            deckID.save();

            return redirect(routes.LearnController.editDeck( name, page,  sortBy,  order, filter));
        }

    }
    public Result postCardDeletion(){
        return TODO;
    }
    public Result postCardEdit(){return TODO;}
    //Methods involved with the actual learning of the cards
    public Result learnSettings(String Email){
        return TODO;
    }
    //Goal of this function is to find any deck that has cards up for review. It then sends this decks id to learnDeck
    public Result learnAll(){
        String profileID = Secured.getUserInfo(ctx()).email;
        Profile profile = Profile.find.byId(profileID);
        //Finding any deck that has cards that are ready to be learnt
        Date now = new Date();
        for(int i = 0; i<profile.decks.size();i++){
            if(profile.decks.get(i).isUpForReview()){
                Deck deck = profile.decks.get(i);
                return redirect(routes.LearnController.learnDeck(deck.id));
            }
        }

        //if no have any cards that can be reviewed
        return ok(learningFinished.render("Well Done",  Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx())));
    }
    //Finds a card in the deck and gives it to learn card
    public Result learnDeck(String deckID){
        Deck deck = Deck.find.byId(deckID);

        if(deck.getReviewCard()!=null){

            return redirect(routes.LearnController.learnCard(deckID,deck.getReviewCard()));
        }


        //Only occurs if there is an error
        return redirect(routes.LearnController.learnDecks());
    }
    public Result learnCard(String deckID, UUID cardID){
        AbstractCard card= AbstractCard.find.byId(cardID);

        Deck deck = Deck.find.byId(deckID);
        //Creating a reviewCardFormData object that has the values of deckID cand CardID already in it. Idea fromhttps://stackoverflow.com/questions/15788517/initial-values-in-form-in-play-2-0-4
        CardReviewFormData cardReviewFormData= new CardReviewFormData();
        cardReviewFormData.cardID =cardID;
        cardReviewFormData.deckID=deckID;
        Form<CardReviewFormData> formData = formFactory.form(CardReviewFormData.class).fill(cardReviewFormData);

        return  ok(learnCard.render("Welcome", Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx()),
                     deck, card.getFrames(),cardID,formData));
        }




    public Result  updateCard(){
        Form<CardReviewFormData> reviewData = formFactory.form(CardReviewFormData.class).bindFromRequest();

        if (reviewData.hasErrors()) {
            flash("error", "Deck name is already being used");
            return badRequest(learnerHome.render("Create Deck",  Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx())));
        }
        else {
           //Updating the card: giving the review data to the card in cardUpdate
            AbstractCard card = AbstractCard.find.byId(reviewData.get().cardID);
            card.updateCard(reviewData.get());

            //Sending back to learn card with the id of another card that is up for review
            Deck deck = Deck.find.byId(reviewData.get().deckID);

            if(deck.getReviewCard()!=null){
                return redirect(routes.LearnController.learnCard(deck.id,deck.getReviewCard()));
            }




        }
        return ok(learningFinished.render("Well Done",  Secured.isLoggedIn(ctx()),Secured.getUserInfo(ctx())));
    }
}
