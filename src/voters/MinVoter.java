package voters;

import main.District;
import parties.Candidate;
import parties.Party;

public class MinVoter extends MaxVoter {
    public MinVoter(String name, String surname, District district, Party party, int trait) {
        super(name, surname, district, party, trait);
    }

    @Override
    protected int compare(Candidate candidate1, Candidate candidate2) {
        return -super.compare(candidate1, candidate2);
    }
}
