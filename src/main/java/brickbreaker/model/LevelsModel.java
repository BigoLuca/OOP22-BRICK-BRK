package brickbreaker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import brickbreaker.common.Mode;
import brickbreaker.model.factory.MapName;
import brickbreaker.model.factory.RealMapName;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.world.World;

public class LevelsModel extends AbstractGameModel {

    private final Iterator<Level> level;

    public LevelsModel(final List<String> names, final Rank r) {
        super(Mode.LEVEL, r);
        List<Level> levels = new ArrayList<Level>();
        for (String n : names) {
            levels.add(new Level(names.indexOf(n), n));
        }
        this.level = levels.iterator();
    }

    @Override
    public boolean getNextMatch() {

        if (level.hasNext()) {
            World old = this.getGameState().getWorld();
            Level l = level.next();

            MapName m = new RealMapName(l.getNameMap());
            this.getGameState().setWorld(WorldFactory.getInstance().createFromWorld(m, old, false));
            this.getGameState().init();
        } else {
            return false;
        }

        return true;
    }
    
}
