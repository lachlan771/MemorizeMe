package models;

import com.avaje.ebean.Model;
//import com.avaje.ebean.PagedList;
import com.avaje.ebeaninternal.server.lib.util.Str;
import controllers.Secured;
import play.data.Form;
import play.mvc.Security;
import models.Profile;
import com.avaje.ebean.*;
import views.formData.CardFormData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lachlan on 25/06/2017.
 */
//@Security.Authenticated(Secured.class)
@Entity
public class Deck extends Model{
    //id of the string of the form "deckName"+"-"email" e.g french-test@gmail.com
    @Id
    public String id;

    public String name;

    public int totalCards;
    public int totalReviewCards;
    @ManyToOne(cascade = CascadeType.ALL)
    public Profile profile;

    @OneToMany(mappedBy = "deck", cascade= CascadeType.ALL)
    public List<AbstractCard> cards = new ArrayList<AbstractCard>();

    public Deck(String name, int totalCards, int totalReviewCards,Profile profile) {
        this.id = name+"-"+profile.email;
        this.name = name;
        this.totalCards = totalCards;
        this.totalReviewCards = totalReviewCards;
        this.profile=profile;


    }

    public Deck() {
    }

    public  PagedList<AbstractCard> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                AbstractCard.find.where().
                        eq("deck",this)
                        .orderBy(sortBy + " " + order)
                        .findPagedList(page, pageSize);
    }
    public static Model.Finder<String,Deck> find = new Model.Finder<>(Deck.class);

    public void cardFactory(CardFormData cardData,Deck deck) {
        //The type of card it is
        int frameNum = 2;
        switch (frameNum){
            //stamdard card case
            case 2: StandardCard card = new StandardCard(cardData.question,cardData.answer,deck);
                break;
            default:
                break;
        }
    }
    //Method that returns whether any in the cards are ready for review
    public boolean isUpForReview(){
        for(int i = 0; i<cards.size();i++){
            if(cards.get(i).isUpForReview()){
                return  true;
            }
        }
        return false;
    }
    //Gets a card that is up for review
    public UUID getReviewCard() {
        for (AbstractCard card : cards) {
            if (card.isUpForReview()) {
                return card.id;
            }

        }
        return null;
    }
    //Get the value for how many cards need to be reviewed
    public int getReviewAmount() {
        int  counter=0;
        for (AbstractCard card : cards) {
            if (card.isUpForReview()) {
                counter++;
            }

        }
        return counter;
    }
}
