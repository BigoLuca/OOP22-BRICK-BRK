package brickbreaker.controllers;

import java.util.Map;

import brickbreaker.ResourceLoader;

public class RankController {

    /**
     * Method to get the current rank.
     * @param fileName
     * @param level
     * @return a map of user names and their ranks
     */
    public Map<String, Integer> getRank(final String fileName, final Integer level) {
        return ResourceLoader.getInstance().getRank(fileName, level);
    }
}
