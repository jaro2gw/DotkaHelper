package heroes;

/**
 * Created by Jaro on 2017-02-11.
 */
public enum Faction {
    Radiant, Dire;

    static public Faction getFaction(String faction) {
        return faction.equals("radiant") ? Radiant : Dire;
    }
}
