package brickbreaker.model.rank;

import java.util.Map;

/**
 * Class representing the rank.
 * Implements the {@link Rank} interface.
 */
public class GameRank implements Rank {

    private Map<String, Integer> r;

    public GameRank(Map<String, Integer> r) {
        this.r = r;
    }
    
    @Override
    public Map<String, Integer> getRank() {
        return this.r;
    }

    @Override
    public void setRank(Map<String, Integer> r) {
        this.r = r;
    }

    @Override
    public boolean addUser(String user, Integer score) {
        boolean result = false;

        if (this.r.containsKey(user) && this.r.get(user) < score) {
            this.r.put(user, score);
            result = true;
        }

        if (this.r.size() > 10) {
            String key = this.r.keySet().stream().skip(this.r.size() - 1).findFirst().get();
            this.r.remove(key);
            result = false;
        }

        return result;
    }
}
