package players.heuristics;

import core.AbstractGameState;
import core.interfaces.IStateHeuristic;
import core.CoreConstants;
import games.sushigo.SGGameState;
import games.sushigo.SGParameters;
import games.sushigo.cards.SGCard;
import java.util.List;

import static games.sushigo.cards.SGCard.SGCardType.*;

public class SushigoHeuristic implements IStateHeuristic {

    @Override
    public double evaluateState(AbstractGameState gs, int playerId) {
        // 确保GameState是Sushi Go!的实例
        if (!(gs instanceof SGGameState)) {
            throw new IllegalArgumentException("GameState must be an instance of SGGameState");
        }
        SGGameState sgs = (SGGameState) gs;

        // 获取当前玩家的得分
        double score = sgs.getGameScore(playerId);

        // 简化的手牌潜在得分评估
        double potentialScore = evaluateHandPotential(sgs, playerId);

        // 返回总评分，包括潜在得分
        return score + potentialScore;
    }

    // 简化的手牌潜在得分评估
    private double evaluateHandPotential(SGGameState sgs, int playerId) {
        double potentialScore = 0.0;

        // 获取玩家手中的卡牌
        List<SGCard> hand = sgs.getPlayerHands().get(playerId).getComponents();

        // 遍历手中的卡牌，计算潜在得分
        for (SGCard card : hand) {
            switch (card.type) {
                case Tempura:
                    // Two Tempura cards score points
                    potentialScore += 5 / 2.0;
                    break;
                case Sashimi:
                    // Three Sashimi cards score points
                    potentialScore += 10 / 3.0;
                    break;
                case Dumpling:
                    // Dumplings score incrementally based on the number collected
                    potentialScore += 1; // Assume each Dumpling averages a certain score
                    break;
                case Maki:
                    // Assume each Maki roll averages a certain score
                    potentialScore += 1;
                    break;
                case SquidNigiri:
                case SalmonNigiri:
                case EggNigiri:
                    // Nigiri scores points based on the count
                    potentialScore += card.count; // Assume each Nigiri scores equal to its count
                    break;
                // Other card types can be handled similarly
            }
        }

        return potentialScore;
    }
}