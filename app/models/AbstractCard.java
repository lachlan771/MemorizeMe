package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import views.formData.CardReviewFormData;

import javax.persistence.Id;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;


/**
 * Created by Lachlan on 26/06/2017.
 */
//Discovered the below @Entity etc.. rather than @MappedSuperClass from https://stackoverflow.com/questions/9873547/how-to-map-an-abstract-collection-with-jpa
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractCard extends Model {
    //The id of the car is an in

    @Id
    public UUID id;
    //The id of the deck that it belongs to

    public int responseQuality;
    public BigDecimal repNum;
    public BigDecimal eFactor;
    //Date of the card creation
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date creationDate;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date nextRep;

    public abstract List<String>  getFrames();

    //the Deck assignment
    @ManyToOne(cascade = CascadeType.ALL)
    public Deck deck;
    public static Model.Finder<UUID,AbstractCard> find = new Model.Finder<>(AbstractCard.class);
    //Determining if the card is up for review
    // updates card and applies the supermemo algorithim

    public boolean isUpForReview(){
        Date date = new Date();
        if(nextRep.compareTo(date)<0){
            return true;
        }
        return false;

    }
    //function for calculating when the cards next review date is
    private void setNextRep(){
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        //I(1)=1
        if(repNum.equals(new BigDecimal(1))){

            calendar.add(Calendar.DATE,1);
            nextRep=calendar.getTime();

        }
        else if(repNum.equals(new BigDecimal(2))){

            calendar.add(Calendar.DATE,6);
            nextRep=calendar.getTime();

        }
        else{
            BigDecimal daysTillNextRep =  (repNum .subtract(new BigDecimal(1)) ).multiply(eFactor);

            calendar.add(Calendar.DATE,(int)(Math.ceil(daysTillNextRep.floatValue())));
            nextRep=calendar.getTime();

        }
    }
    //Applying the supermemo algo to the card.https://www.supermemo.com/english/ol/sm2.htm
    public  void updateCard(CardReviewFormData reviewData){
    //6.If the quality response was lower than 3 then start repetitions for the item from the beginning without changing
    //the E-Factor (i.e. use intervals I(1), I(2) etc. as if the item was memorized anew)
        this.responseQuality =reviewData.responseQuality;
        if (responseQuality>4){
            //5.After each repetition modify the E-Factor of the recently repeated item according to the formula:


            //repNum = repNum.add(new BigDecimal(1));
            repNum = new BigDecimal(1);
            eFactor = eFactor.add(new BigDecimal(.1-(5-responseQuality)*(0.08+(5-responseQuality)*0.02)));
            //If EF is less than 1.3 then let EF be 1.3.
            if(eFactor.compareTo(new BigDecimal(1.3))<0){
                eFactor= new BigDecimal(1.3);
            }
            //Repeat items using the following intervals:I(1)=1,I(2)=2, for n>2:I(n)=I(n-1)*EF
            setNextRep();
            this.save();
            deck.update();

        }
    };
}
