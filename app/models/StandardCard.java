package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lachlan on 26/06/2017.
 */
//This card is the traditional flashcard with a question on one side and the answer on the other
@Entity
@DiscriminatorValue("StandardCard")
public class StandardCard extends AbstractCard {
    //the two frames that make up this class

    public String question;
    public String answer;
    public StandardCard(String question,String answer,Deck deck) {
        this.question=question;
        this.answer=answer;
        //"At the moment of introducing an item into a SuperMemo database, its E-Factor was assumed to equal 2.5."
        this.eFactor = new BigDecimal(2.5);
        this.responseQuality=0;
        this.creationDate = new Date();
        this.nextRep = new Date();
        this.deck=deck;
        this.save();

    }

    @Override
    public List<String> getFrames() {
        List<String> frames = new ArrayList<String>();
        frames.add(question);
        //formatting it so the new answer is under the question
        frames.add(question +" "+answer);
        return frames;
    }
    //after a card has been reviewed

}
