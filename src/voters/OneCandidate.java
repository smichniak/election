package voters;

import java.util.ArrayList;
import java.util.List;
import main.District;
import parties.Candidate;
import parties.Party;

public class OneCandidate extends Voter {
    private Candidate candidate; // Jedyny kandydat, na którego wyborca będzie głosował

    public OneCandidate(String name, String surname, District district, Party party, int position) {
        super(name, surname, district, party);
        List<Candidate> candidates = district.getCandidates(party);
        candidate =  candidates.get(position - 1);
    }

    @Override
    protected List<Candidate> possibleCandidates() {
        return new ArrayList<>(List.of(candidate));
    }
}
