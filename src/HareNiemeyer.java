import java.util.*;

public class HareNiemeyer extends MandateDistribution {
    @Override
    public Map<String, Integer> mandates(Map<String, Integer> votes, int mandatesCount) {
        Map<String, Integer> mandatesByParty = new HashMap<>();
        Map<String, Double> values = new HashMap<>();

        int totalVotes = 0;
        for (int voteCount : votes.values()) {
            totalVotes += voteCount;
        }

        for (String partyName : votes.keySet()) {
            double value = (double) mandatesCount * votes.get(partyName) / totalVotes;
            values.put(partyName, value);
            mandatesByParty.put(partyName, mandatesByParty.getOrDefault(partyName, 0) + (int) value);
            mandatesCount -= (int) value;
        }
        List<String> keys = new ArrayList<>(values.keySet());
        Collections.shuffle(keys);
        while (mandatesCount > 0) {
            double maxValue = 0;
            String maxParty = keys.get(0);
            for (String partyName : keys) {
                if (values.get(partyName) > maxValue) {
                    maxValue = values.get(partyName);
                    maxParty = partyName;
                }
            }
            keys.remove(maxParty);
            mandatesByParty.put(maxParty, mandatesByParty.getOrDefault(maxParty, 0) + 1);
            mandatesCount--;
        }

        return mandatesByParty;
    }

    @Override
    public String toString() {
        return "Metoda Hare’a-Niemeyera";
    }
}
