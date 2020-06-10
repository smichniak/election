package parties;


import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class HareNiemeyer extends MandateDistribution {
    @Override
    public Map<Party, Integer> mandates(Map<Party, Integer> votes, int mandatesCount) {
        int totalMandates = mandatesCount;
        Map<Party, Integer> mandatesByParty = new HashMap<>();
        Map<Party, Double> values = new HashMap<>();

        int totalVotes = 0;
        for (int voteCount : votes.values()) {
            totalVotes += voteCount;
        }

        for (Party party : votes.keySet()) {
            double value = (double) totalMandates * votes.get(party) / totalVotes;
            values.put(party, value - (int) value); // Zachowujemy części ułamkowe ilorazów do rozdzielenia pozostałych mandatów
            mandatesByParty.put(party, mandatesByParty.getOrDefault(party, 0) + (int) value);
            mandatesCount -= (int) value;
            party.addMandates((int) value);
        }
        List<Party> keys = new ArrayList<>(values.keySet());
        Collections.shuffle(keys); // Dla tych samych wartości mamy losowo wybrane partie
        while (mandatesCount > 0) {
            double maxValue = 0;
            Party maxParty = keys.get(0);
            for (Party party : keys) {
                if (values.get(party) > maxValue) {
                    maxValue = values.get(party);
                    maxParty = party;
                }
            }
            keys.remove(maxParty);
            mandatesByParty.put(maxParty, mandatesByParty.getOrDefault(maxParty, 0) + 1);
            mandatesCount--;
            maxParty.addMandates(1);
        }

        return mandatesByParty;
    }

    @Override
    public String toString() {
        return "Metoda Hare’a-Niemeyera";
    }
}
