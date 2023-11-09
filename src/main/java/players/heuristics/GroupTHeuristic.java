package players.heuristics;

import core.AbstractGameState;
import core.interfaces.IStateHeuristic;

public class GroupTHeuristic implements IStateHeuristic {

    /**
     * Calculate the score difference between the player and the player with the highest score
     * and add it to the score returned by the heuristic
     *
     * @param gs       - game state to evaluate and score.
     * @param playerId - player id
     * @return
     */
    @Override
    public double evaluateState(AbstractGameState gs, int playerId) {
        double playerScore = gs.getGameScore(playerId);
        int nPlayers = gs.getNPlayers();
        double maxScore = 0;

        for (int i = 0; i < nPlayers; i++) {
                double playerIScore = gs.getGameScore(i);
                maxScore = Math.max(maxScore, playerIScore);
        }

        double difference = playerScore - maxScore;

        return playerScore + difference;
    }
}



