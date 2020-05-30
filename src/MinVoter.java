public class MinVoter extends ComparingVoter {
    private int trait;

    public MinVoter(String name, String surname, District district, String partyName, int trait) {
        super(name, surname, district, partyName);
        this.trait = trait;
    }

    @Override
    protected int compare(Candidate candidate1, Candidate candidate2) {
        return candidate2.traitValue(trait) - candidate1.traitValue(trait);
    }
}
