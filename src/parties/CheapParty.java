package parties;

import java.util.List;

public class CheapParty extends Party {

    public CheapParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public boolean runCampaign(List<Campaign> campaigns) {
        Campaign campaign = campaigns.get(0); // Wybieramy pierwszą (najtańszą) strategię
        if (budget >= campaign.getCost()) {
            budget -= campaign.getCost();
            campaign.run();
            return true;
        }

        return false;
    }
}
