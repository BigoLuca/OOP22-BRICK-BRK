package brickbreaker.model.factory;

public class RealMapName implements MapName {

    private String name;

    public RealMapName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isNull() {
        return false;
    }
    
}
