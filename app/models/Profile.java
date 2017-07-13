package models;

import com.avaje.ebean.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lachlan on 23/06/2017.
 */
@Entity
public class Profile extends Model {
    @Id
    public String email;

    public String userName;
    public String password;
    public String fullName;
    //The Decks of this person
    @OneToMany(mappedBy = "profile", cascade= CascadeType.ALL)
    public List<Deck> decks = new ArrayList<Deck>();


    public Profile(String email, String userName, String password,String fullName) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    public static Model.Finder<String,Profile> find = new Model.Finder<>(Profile.class);
}
