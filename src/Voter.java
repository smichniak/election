import java.util.List;

public class Voter extends Person {
    protected District district;
    //TODO
    //May require to be initialized with null
    private Candidate votedFor;
    protected String partyName; //Null is any party

    public Voter(String name, String surname, District district, String partyName) {
        super(name, surname);
        this.district = district;
        this.partyName = partyName;
    }

    protected List<Candidate> possibleCandidates() {
        return district.getCandidates(partyName);
    }

    public void chooseCandidate() {
        List<Candidate> candidates = possibleCandidates();
        java.util.Random randomGenerator = new java.util.Random();
        int randomIndex = randomGenerator.nextInt(candidates.size());
        votedFor = candidates.get(randomIndex);
    }

    public Candidate getVotedFor() {
        return votedFor;
    }

}