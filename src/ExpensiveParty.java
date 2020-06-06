import java.util.List;

public class ExpensiveParty extends Party {

    public ExpensiveParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public boolean runCampaign(List<Campaign> campaigns) {
        for (int i = campaigns.size() - 1; i >= 0; --i) {
            if (campaigns.get(i).getCost() <= budget) {
                budget -= campaigns.get(i).getCost();
                campaigns.get(i).run();
                return true;
            }
        }
        return false;
    }
}
