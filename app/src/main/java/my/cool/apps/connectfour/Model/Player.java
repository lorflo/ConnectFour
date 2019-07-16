package my.cool.apps.connectfour.Model;

/**
 * A player of the Connect Four game. Each player has a name.
 */
public class Player {

    /** Name of this player. */
    private final String name;
    private  final int color;

    /** Create a new player with the given name. */
    public Player(String name, int color) {

        this.name = name;
        this.color = color;
    }

    /** Returns the name of this player. */
    public String name() {
        return name;
    }
    public int color() { return color; }
}
