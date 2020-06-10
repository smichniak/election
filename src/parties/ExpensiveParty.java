package parties;

import java.util.List;

public class ExpensiveParty extends Party {

    public ExpensiveParty(String name, int budget) {
        super(name, budget);
    }

    @Override
    public boolean runCampaign(List<Campaign> campaigns) {
        // Sprawdzamy strategie od końca (od najdroższych)
        for (int i = campaigns.size() - 1; i >= 0; --i) {
            if (budget >= campaigns.get(i).getCost()) {
                budget -= campaigns.get(i).getCost();
                campaigns.get(i).run();
                return true;
            }
        }
        return false;
    }
}
