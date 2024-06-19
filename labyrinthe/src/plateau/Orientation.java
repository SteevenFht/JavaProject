/**
 * An enum who give the orientation of wall
 * @author
 */
package plateau;

import java.util.jar.Attributes.Name;
import java.util.regex.Pattern;

public enum Orientation {
    West(2,  "ouest","o(uest)?|w(est)?"),
    North(3, "nord", "n(ord)?|north"),
    East(0,  "est",  "e(st)?|east"),
    South(1, "sud",  "s(ud)?|south");

    // indicate the index of the opposite value
    private final int opposite;
    private final String name;
    private final Pattern input;

    private Orientation(int opposite, String name, String regEx) {
        this.opposite = opposite;
        this.name = name;
        this.input = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
    }

    public Orientation getOpposite() {
        return Orientation.values()[this.opposite];// .values -> renvoir tableau de valeurs du type dans l'ordre de declaration
    }

    public String getName() {return this.name;}

    public Pattern getInput() {return this.input;}
}
