package heroes;

/**
 * Created by Jaro on 2017-02-11.
 */
public enum AttackType {
    Melee, Ranged;

    static public AttackType getAttackType(String at) {
        return at.equals("melee") ? Melee : Ranged;
    }
}
