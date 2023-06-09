package brickbreaker.controllers.session;

import java.util.Optional;

import brickbreaker.common.Difficulty;

public class EndlessSession extends GenericSession {

    private Optional<Difficulty> od;

    public EndlessSession(final Optional<Difficulty> diff) {
        this.od = diff;
    }

    public Optional<Difficulty> getDifficulty() {
        return this.od;
    }
}
