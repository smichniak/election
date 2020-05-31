import java.util.List;

public class CheapParty extends Party {

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
