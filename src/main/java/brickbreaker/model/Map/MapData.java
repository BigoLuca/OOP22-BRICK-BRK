package brickbreaker.model.Map;

import java.util.List;

public class MapData {
    private int index;
    private String landscape;
    private List<List<Integer>> map;
    private String difficulty;
    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public List<List<Integer>> getMap() {
        return map;
    }

    public void setMap(List<List<Integer>> map) {
        this.map = map;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
