import java.util.List;

public class Voter extends Person {
    protected District district;
    // TODO May require to be initialized with null
    private Candidate votedFor;
    protected Party party; //Null is any party

    public Voter(String name, String surname, District district, Party party) {
        super(name, surname);
        this.district = district;
        this.party = party;
    }

    protected List<Candidate> possibleCandidates() {
        return district.getCandidates(party);
    }

    public void chooseCandidate() {
        List<Candidate> candidates = possibleCandidates();
        java.util.Random randomGenerator = new java.util.Random();
        int randomIndex = randomGenerator.nextInt(candidates.size());
        votedFor = candidates.get(randomIndex);
        votedFor.vote();
    }

    public Candidate getVotedFor() {
        return votedFor;
    }

    public void changeWeights(int[] changeVector) {}

    public int possibleChange(int[] changeVector, Party party) {
        return 0;
    }
}