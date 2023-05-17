package brickbreaker.model;

import java.util.Iterator;
import java.util.List;

import brickbreaker.common.Mode;
import brickbreaker.model.factory.MapName;
import brickbreaker.model.factory.RealMapName;
import brickbreaker.model.factory.WorldFactory;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.world.World;

public class LevelsModel extends AbstractGameModel {

    private Iterator<String> mapNames;

    public LevelsModel(final List<String> names, final Mode m, final GameRank r) {
        super(m, r);
        mapNames = names.iterator();
    }

    @Override
    public boolean getNextMatch() {
        boolean next = mapNames.hasNext();

        if (next) {
            World old = this.getGameState().getWorld();
            MapName name = new RealMapName(mapNames.next());

            this.getGameState().setWorld(WorldFactory.getInstance().createFromWorld(name, old, false));
            this.getGameState().init();
        }

        return next;
    }
    
}
