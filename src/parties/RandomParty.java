package parties;

import java.util.List;

public class RandomParty extends Party {
    public RandomParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public boolean runCampaign(List<Campaign> campaigns) {
        java.util.Random randomGenerator = new java.util.Random();
        int randomIndex = randomGenerator.nextInt(campaigns.size()); // Partia wybiera losowe działanie
        while (randomIndex >= 0) {
            Campaign campaign = campaigns.get(randomIndex);
            if (budget >= campaign.getCost()) {
                budget -= campaign.getCost();
                campaign.run();
                return true;
            }
            // Jeśli działanie jest zbyt drogie, partia wybiera tańsze aż do skutku lub końca działań
            randomIndex--;
        }
        return false;
    }
}
