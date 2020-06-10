package voters;

import main.District;
import parties.Candidate;
import parties.Party;

public class MaxVoter extends ComparingVoter {
    private int trait;

    public MaxVoter(String name, String surname, District district, Party party, int trait) {
        super(name, surname, district, party);
        this.trait = trait-1;
    }

    @Override
    protected int compare(Candidate candidate1, Candidate candidate2) {
        return candidate1.traitValue(trait) - candidate2.traitValue(trait);
    }
}
