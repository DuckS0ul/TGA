package players.heuristics;

import core.AbstractGameState;
import core.CoreConstants;
import core.interfaces.IStateHeuristic;

public class Sushigo1Heuristic implements IStateHeuristic {

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
        int numPlayers = gs.getNPlayers();
        double maxScore = -Double.MAX_VALUE;

        for (int i = 0; i < numPlayers; i++) {
                double otherPlayerScore = gs.getGameScore(i);
                maxScore = Math.max(maxScore, otherPlayerScore);
        }

        double scoreDifference = playerScore - maxScore;

        return playerScore + scoreDifference;
    }
}



