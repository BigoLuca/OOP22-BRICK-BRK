package brickbreaker.model.factory;

public class NullMapName implements MapName {

    public String getName() {
        return "";
    }

    public boolean isNull() {
        return true;
    }
}
