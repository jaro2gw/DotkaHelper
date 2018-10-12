package heroes;

/**
 * Created by Jaro on 2017-02-10.
 */
public enum Categories {
    Nuker, Jungler, Carry, Disabler, Support, Initiator, Durable, Pusher, Escape;

    static public Categories getCategory(String cat) {
        switch (cat) {
            case "nuker":
                return Nuker;
            case "jungler":
                return Jungler;
            case "carry":
                return Carry;
            case "disabler":
                return Disabler;
            case "support":
                return Support;
            case "initiator":
                return Initiator;
            case "durable":
                return Durable;
            case "pusher":
                return Pusher;
            case "escape":
                return Escape;
        }
        return null;
    }
}
