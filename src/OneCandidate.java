import java.util.ArrayList;
import java.util.List;

public class OneCandidate extends Voter {
    private Candidate candidate;

    public OneCandidate(String name, String surname, District district, Party party, int position) {
        super(name, surname, district, party);
        List<Candidate> candidates = possibleCandidates();
        candidate =  candidates.get(position - 1);
    }

    @Override
    protected List<Candidate> possibleCandidates() {
        return new ArrayList<>(List.of(candidate));
    }
}
