import java.util.List;

public class GreedyParty extends Party {

    public GreedyParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public boolean runCampaign(List<Campaign> campaigns) {
        if (budget < campaigns.get(0).getCost()) {
            return false;
        } else {
            Campaign bestCampaign = campaigns.get(0);
            int bestChange = bestCampaign.possibleChange(this);
            for (Campaign campaign : campaigns) {
                int currentPossibleChange = campaign.possibleChange(this);
                if (currentPossibleChange > bestChange) {
                    bestCampaign = campaign;
                    bestChange = currentPossibleChange;
                }
            }
            budget -= bestCampaign.getCost();
            bestCampaign.run();
            return true;
        }
    }
}
