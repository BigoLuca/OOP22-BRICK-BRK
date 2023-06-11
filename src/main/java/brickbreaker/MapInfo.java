package brickbreaker;

import java.util.List;

import brickbreaker.common.Difficulty;
import brickbreaker.common.GameImages;

public class MapInfo {

    private List<Integer> map;
    private GameImages landscape;
    private Difficulty difficulty;
    private String name;
    private Integer index;

    public MapInfo(Integer i, List<Integer> b, Difficulty d, String name, String landscape) {
        this.index = i;
        this.map = b;
        this.difficulty = d;
        this.name = name;
        this.landscape = GameImages.valueOf(landscape);
    }

    public GameImages getLandscapeData() {
        return this.landscape;
    }

    public Integer getIndex() {
        return this.index;
    }
    
    public List<Integer> getBricksData() {
        return this.map;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public String getName() {
        return this.name;
    }
}
