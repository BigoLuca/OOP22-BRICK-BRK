package brickbreaker.model;

import java.util.List;

import brickbreaker.ResourceLoader;
import brickbreaker.common.Mode;
import brickbreaker.model.rank.Rank;
import brickbreaker.model.user.User;

public abstract class AbstractGameModel implements GameModel {

    private Mode mode;
    private Rank rank;
    private User user;
    private List<String> mapList;

    public AbstractGameModel(final Mode m, final Rank r, final User u) {
        this.mode = m;
        this.rank = r;
        this.user = u;
        this.mapList = ResourceLoader.getInstance().getMapsNames();
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public Mode getMode() {
        return this.mode;
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
    public Integer getListMapLenght(){
        return this.mapList.size();
    }

}
