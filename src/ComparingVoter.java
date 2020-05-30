import java.util.ArrayList;
import java.util.List;


public abstract class ComparingVoter extends Voter {
    public ComparingVoter(String name, String surname, District district, String partyName) {
        super(name, surname, district, partyName);
    }

    protected abstract int compare(Candidate candidate1, Candidate candidate2);

    @Override
    protected List<Candidate> possibleCandidates() {
        List<Candidate> candidates = district.getCandidates(partyName);
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
