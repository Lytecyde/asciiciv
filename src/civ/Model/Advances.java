package civ.Model;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

import static civ.Model.Advance.*;

/**
 * Created by miku on 30/05/2017.
 */
public class Advances {


    public AdvanceType advanceType;
    //Advance advance;
    LinkedList<String> agriculture = new LinkedList<>();
    LinkedList<String> all = new LinkedList<>();//TODO: sort out into sublists
    LinkedList<String> construction = new LinkedList<>();
    LinkedList<String> maritime = new LinkedList<>();
    LinkedList<String> education = new LinkedList<>();
    LinkedList<String> spirituality = new LinkedList<>();
    LinkedList<String> economics = new LinkedList<>();
    LinkedList<String> culture = new LinkedList<>();
    LinkedList<String> science = new LinkedList<>();
    LinkedList<String> health = new LinkedList<>();
    LinkedList<String> warfare = new LinkedList<>();
    LinkedList<String> metascience = new LinkedList<>();
    public LinkedList<String> acquired = new LinkedList<>();



    public static Advance advance = new Advance();
    public Advances(){
        defineAdvances();
        firstAdvances();
    }

    private void firstAdvances() {
        new Advances(0);
        acquired.add(construction.getFirst());
        acquired.add(agriculture.getFirst());
    }
    public Advances(int n) {



    }

    private void defineAdvances() {
        construction.add("Mining");
        construction.add("Engineering");
        construction.add("Waterworks");
        construction.add("Roads");
        construction.add("Bridges");
        construction.add("Tunnels");

        agriculture.add("Irrigation");
        agriculture.add("Husbandry");
        agriculture.add("Fishfarming");
        agriculture.add("Almanach");
        agriculture.add("Fertilisers");
        agriculture.add("Hydroponics");


        maritime.add("Riverboat");
        maritime.add("Fishing");
        maritime.add("Cartography");
        maritime.add("Warships");
        maritime.add("Navigation");
        maritime.add("Seadrones");

        education.add("Writing");
        education.add("Philosophy");
        education.add("Academy");
        education.add("University");
        education.add("MOOC");
        education.add("AugmentedIntelligence");

        spirituality.add("Myths");
        spirituality.add("Rituals");
        spirituality.add("Ceremonies");
        spirituality.add("Priesthood");
        spirituality.add("Theocracy");
        spirituality.add("Transpersonality");

        economics.add("Bartering");
        economics.add("Trade");
        economics.add("Currency");
        economics.add("Banking");
        economics.add("Taxation");
        economics.add("Logistics");

        culture.add("Paintings");
        culture.add("Sculptures");
        culture.add("Architecture");
        culture.add("Theatre");
        culture.add("Film");
        culture.add("Games");

        science.add("Mathematics");
        science.add("Physics");
        science.add("Biology");
        science.add("Chemistry");
        science.add("Economics");
        science.add("Informatics");

        health.add("Antibiotics");
        health.add("Fermentation");
        health.add("Canning");
        health.add("Sanitation");
        health.add("Medicine");
        health.add("Surgery");

        warfare.add("Tactics");
        warfare.add("Strategy");
        warfare.add("Siegecraft");
        warfare.add("Artillery");
        warfare.add("Blitz");
        warfare.add("Electronic");

        metascience.add("PeerReview");
        metascience.add("Falsification");
        metascience.add("Paradigms");
        metascience.add("Experimentation");
        metascience.add("Observation");
        metascience.add("Theorisation");

        all.addAll(construction);
        all.addAll(maritime);
        all.addAll(education);
        all.addAll(economics);
        all.addAll(spirituality);
        all.addAll(economics);
        all.addAll(culture);
        all.addAll(science);
        all.addAll(health);
        all.addAll(warfare);
        all.addAll(metascience);
    }


}
