import java.util.*;

public abstract class DivisionMethod extends MandateDistribution {

    protected abstract int step();

    private int getMaxValue(Map<Party, Integer> votes, Map<Party, Integer> mandatesByParty) {
        int maxValue = 0;
        for (Party party : votes.keySet()) {
            int currentValue = votes.get(party) / (step() * mandatesByParty.getOrDefault(party, 0) + 1);
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
        }
        return maxValue;

    }

    @Override
    public Map<Party, Integer> mandates(Map<Party, Integer> votes, int mandatesCount) {
        Map<Party, Integer> mandatesByParty = new HashMap<>();
        List<Party> sameValue = new ArrayList<>();
        int value = getMaxValue(votes, mandatesByParty);

        while (mandatesCount > 0) {
            for (Party party : votes.keySet()) {
                int currentValue = votes.get(party) / (step() * mandatesByParty.getOrDefault(party, 0) + 1);
                if (currentValue == value) {
                    sameValue.add(party);
                }
            }
            Collections.shuffle(sameValue);
            while (sameValue.size() > 0 && mandatesCount > 0) {
                mandatesByParty.put(sameValue.get(0), mandatesByParty.getOrDefault(sameValue.get(0), 0) + 1);
                sameValue.remove(0);
                mandatesCount--;
                sameValue.get(0).addMandates(1);
            }
            value = getMaxValue(votes, mandatesByParty);
        }

        return mandatesByParty;
    }

}
