package heroes;

/**
 * Created by Jaro on 2017-02-10.
 */
public enum Attribute {
    Strength, Agility, Intelligence;

    static public Attribute getAttribute(String atr) {
        switch (atr) {
            case "strength":
                return Strength;
            case "agility":
                return Agility;
            case "intelligence":
                return Intelligence;
        }
        return null;
    }
}
