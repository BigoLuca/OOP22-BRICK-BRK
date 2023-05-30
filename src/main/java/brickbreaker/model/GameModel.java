package brickbreaker.model;

public interface GameModel {

    String getNameMap(Integer i);

    Integer getListMapLenght();

    Level getRandomLevel();

    Level getLevel(Integer level);

}
