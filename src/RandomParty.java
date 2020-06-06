import java.util.List;

public class RandomParty extends Party {
    public RandomParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public boolean runCampaign(List<Campaign> campaigns) {
        java.util.Random randomGenerator = new java.util.Random();
        int randomIndex = randomGenerator.nextInt(campaigns.size());
        while (randomIndex >= 0) {
            Campaign campaign = campaigns.get(randomIndex);
            if (campaign.getCost() <= budget) {
                budget -= campaign.getCost();
                campaign.run();
                return true;
            }
            randomIndex--;
        }
        return false;
    }
}
