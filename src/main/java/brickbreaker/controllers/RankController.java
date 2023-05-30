package brickbreaker.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import brickbreaker.ResourceLoader;
import brickbreaker.model.rank.GameRank;
import brickbreaker.model.rank.Rank;

public class RankController {

    private List<Rank> ranks;

    public RankController() {
        ranks = new ArrayList<>();
    }

    public List<Rank> getListOfRanks() {
        return ResourceLoader.getInstance().getMapsNames()
                .stream()
                .map(e -> new GameRank(e))
                .collect(Collectors.toList());
    }

    public Rank getRank(final String name) {
        return ranks.stream()
                .filter(e -> e.getFileName().equals(name))
                .findFirst().orElseThrow();
    }
}
