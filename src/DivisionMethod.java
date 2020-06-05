import java.util.*;

public abstract class DivisionMethod extends MandateDistribution {

    protected abstract int step();

    private int getMaxValue(Map<String, Integer> votes, Map<String, Integer> mandatesByParty) {
        int maxValue = 0;
        for (String partyName : votes.keySet()) {
            int currentValue = votes.get(partyName) / (step() * mandatesByParty.getOrDefault(partyName, 0) + 1);
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
        }
        return maxValue;

    }

    @Override
    public Map<String, Integer> mandates(Map<String, Integer> votes, int mandatesCount) {
        Map<String, Integer> mandatesByParty = new HashMap<>();
        List<String> sameValue = new ArrayList<>();
        int value = getMaxValue(votes, mandatesByParty);

        while (mandatesCount > 0) {
            for (String partyName : votes.keySet()) {
                int currentValue = votes.get(partyName) / (step() * mandatesByParty.getOrDefault(partyName, 0) + 1);
                if (currentValue == value) {
                    sameValue.add(partyName);
                }
            }
            Collections.shuffle(sameValue);
            while (sameValue.size() > 0 && mandatesCount > 0) {
                mandatesByParty.put(sameValue.get(0), mandatesByParty.getOrDefault(sameValue.get(0), 0) + 1);
                sameValue.remove(0);
                mandatesCount--;
            }
            value = getMaxValue(votes, mandatesByParty);
        }

        return mandatesByParty;
    }

}
