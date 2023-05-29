package brickbreaker.model;

import java.util.List;
import java.util.Optional;
import brickbreaker.ResourceLoader;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.user.User;

public abstract class AbstractGameModel implements GameModel {

    protected static final String NULL_WORLD_PLACEHOLDER = "DUNNO";

    protected final static Integer LENRANK = 10;
    private Rank rank;
    private User user;
    private List<String> mapList;

    public AbstractGameModel(final Rank r, final User u) {
        this.rank = r;
        this.user = u;
        this.mapList = ResourceLoader.getInstance().getMapsNames();
    }

    @Override
    public abstract Optional<Level> getNextMatch();

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public Rank getRank() {
        return this.rank;
    }
    
    @Override
    public void setRank(final Rank rankToSet) {
        this.rank = rankToSet;
    }

    @Override
    public String getNameMap(final Integer i) {
        return this.mapList.get(i);
    }

    @Override
    public List<String> getListMapLenght(){
        return this.mapList;
    }

}
