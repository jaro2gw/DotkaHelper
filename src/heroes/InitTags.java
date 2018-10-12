package heroes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InitTags {
    private void initCat(List<Hero> heroes, String category) {
        Categories cat = Categories.getCategory(category);
        Scanner sc = new Scanner(
                new BufferedReader(
                        new InputStreamReader(
                                this.getClass().getResourceAsStream("/" + category + ".txt"))));
        Hero temp = new Hero("");
        while (sc.hasNext()) {
            temp.setName(sc.nextLine());
            try {
                heroes.get(heroes.indexOf(temp)).setCategory(cat);
            }
            catch (IndexOutOfBoundsException e) {
                System.err.println("Nie mozna nadac kategorii. " + temp.getName() + " nie znajduje sie na liscie " +
                        "bohaterow.");
            }
        }
    }

    private String format = "Cannot set %s. %s is not on a heroes list";

    private void initFaction(LinkedList<Hero> heroes, String faction) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream("/" + faction + ".txt"))));
        Hero temp = new Hero("");
        while (sc.hasNext()) {
            temp.setName(sc.nextLine());
            try { heroes.get(heroes.indexOf(temp)).setFaction(Faction.getFaction(faction)); }
            catch (IndexOutOfBoundsException e) { System.err.format(format, "faction", temp.getName()); }
        }
    }

    private void initAtr(LinkedList<Hero> heroes, String attribute) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream("/" + attribute + ".txt"))));
        Hero temp = new Hero("");
        while (sc.hasNext()) {
            temp.setName(sc.nextLine());
            try { heroes.get(heroes.indexOf(temp)).setAttribute(Attribute.getAttribute(attribute)); }
            catch (IndexOutOfBoundsException e) { System.err.format(format, "attribute", temp.getName()); }
        }
    }

    private void initAtkType(LinkedList<Hero> heroes, String atkType) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream("/" + atkType + ".txt"))));
        Hero temp = new Hero("");
        while (sc.hasNext()) {
            temp.setName(sc.nextLine());
            try { heroes.get(heroes.indexOf(temp)).setAttackType(AttackType.getAttackType(atkType)); }
            catch (IndexOutOfBoundsException e) { System.err.format(format, "attack type", temp.getName()); }
        }
    }

    public LinkedList<Hero> initHeroes() {
        LinkedList<Hero> heroes = new LinkedList<>();

        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream("/heroeslist.txt"))));
        while (scanner.hasNext()) heroes.add(new Hero(scanner.nextLine()));

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream("/iconNamesList.txt"))));
//        Collections.sort(heroes);
//        for (Hero hero : heroes) hero.setImageView(sc.nextLine());
        heroes.stream().sorted().forEachOrdered(hero -> hero.setImageView(sc.nextLine()));

        for (String atkType : new String[]{"melee", "ranged"}) initAtkType(heroes, atkType);

        for (String faction : new String[]{"radiant", "dire"}) initFaction(heroes, faction);

        for (String attribute : new String[]{"strength", "agility", "intelligence"}) initAtr(heroes, attribute);

        for (String cat : new String[]{"carry", "disabler", "durable", "escape", "initiator", "nuker", "pusher",
                "support"})
            initCat(heroes, cat);

        return heroes;
    }
}
