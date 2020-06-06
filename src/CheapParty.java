import java.util.List;

public class CheapParty extends Party {

    public CheapParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public boolean runCampaign(List<Campaign> campaigns) {
        for (Campaign campaign : campaigns) {
            if (campaign.getCost() <= budget) {
                budget -= campaign.getCost();
                campaign.run();
                return true;
            }
        }
        return false;
    }
}
