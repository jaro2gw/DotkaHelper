package heroes;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by Jaro on 2017-02-10.
 */
public class Hero implements Comparable {
    private String name, path;

    public String getPath() {
        return path;
    }

    private ArrayList<Categories> categories;
    private Attribute attribute;
    private Faction faction;
    private AttackType attackType;
    private ImageView imageView;

    public void setImageView(String path){
        this.path = path;
        imageView = new ImageView("DOTA_PICS/HERO_ICONS/" + path + "_hphover.png");
//        imageView = new ImageView("DOTA_PICS/PORTRAITS/" + path + "_full.png");
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Hero(String name) {
        this.name = name;
        categories = new ArrayList<>();
    }


    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }


    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }


    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategory(Categories category) {
        categories.add(category);
    }


    @Override
    public String toString() {
        return "Name = '" + name + '\'' +
                "\n\tFaction: " + faction +
                "\n\tAttack Type: " + attackType +
                "\n\tAttribute: " + attribute +
                "\n\tCategories = " + categories + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hero hero = (Hero) o;

        return name.equals(hero.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Hero)
            return name.compareTo(((Hero) o).getName());
        return 0;
    }
}
