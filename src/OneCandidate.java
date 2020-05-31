import java.util.ArrayList;
import java.util.List;

public class OneCandidate extends Voter {
    private Candidate candidate;

    public OneCandidate(String name, String surname, District district, String partyName, int candidatePosition) {
        super(name, surname, district, partyName);
        List<Candidate> candidates = possibleCandidates();
        candidate =  candidates.get(candidatePosition - 1);
    }

    @Override
    protected List<Candidate> possibleCandidates() {
        return new ArrayList<>(List.of(candidate));
    }
}
