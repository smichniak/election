package parties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public abstract class DivisionMethod extends MandateDistribution {

    protected abstract int step();

    @Override
    public Map<Party, Integer> mandates(Map<Party, Integer> votes, int mandatesCount) {
        Map<Party, Integer> mandatesByParty = new HashMap<>();
        List<Party> parties = new ArrayList<>(votes.keySet());
        Collections.shuffle(parties); // W przypadku tych samych wartości ilorazów partia, która otrzyma mandat będzie wybrana losowo

        while (mandatesCount > 0) {
            float maxValue = 0;
            Party maxParty = parties.get(0);
            for (Party party : parties) { // Szukamy największego ilorazu wśród partii
                float currentValue = (float) votes.get(party) / (step() * mandatesByParty.getOrDefault(party, 0) + 1);
                if (currentValue > maxValue) {
                    maxValue = currentValue;
                    maxParty = party;
                }
            }
            mandatesByParty.put(maxParty, mandatesByParty.getOrDefault(maxParty, 0) + 1);
            maxParty.addMandates(1);
            mandatesCount--;

        }

        return mandatesByParty;
    }

}
