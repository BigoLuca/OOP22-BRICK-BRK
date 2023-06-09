package brickbreaker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.MapInfo;
import brickbreaker.ResourceLoader;
import brickbreaker.common.GameImages;

public class LevelMenuController extends GenericController {

    private List<MapInfo> mapsInfo;

    public LevelMenuController() {
        super();
        this.mapsInfo = ResourceLoader.getInstance().getMapsInfo();
    }

    public List<GameImages> getLandscapes() {
        return this.mapsInfo.stream().map(item -> item.getLandscapeData()).collect(Collectors.toList());
    }

    public List<String> getMapsName() {
        return this.mapsInfo.stream().map(item -> item.getName()).collect(Collectors.toList());
    }
}
