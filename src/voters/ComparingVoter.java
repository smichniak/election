package voters;

import java.util.ArrayList;
import java.util.List;
import main.District;
import parties.Candidate;
import parties.Party;


public abstract class ComparingVoter extends Voter {
    public ComparingVoter(String name, String surname, District district, Party party) {
        super(name, surname, district, party);
    }

    // Zwraca wartość > 0, jeśli `candidate1` jest lepszy niż `candidate2`,
    // < 0 w przeciwnym przypadku, i 0 gdy są na równi
    protected abstract int compare(Candidate candidate1, Candidate candidate2);


    @Override
    protected List<Candidate> possibleCandidates() {
        List<Candidate> candidates = district.getCandidates(party);
        List<Candidate> bestCandidates = new ArrayList<>(List.of(candidates.get(0)));

        for (Candidate candidate : candidates) {
            int compareResult = compare(candidate, bestCandidates.get(0));
            if (compareResult > 0) {
                bestCandidates = new ArrayList<>(List.of(candidate));
            } else if (compareResult == 0) {
                bestCandidates.add(candidate);
            }
        }
        return bestCandidates;
    }
}
