package voters;

import java.util.List;
import main.Person;
import main.District;
import parties.Candidate;
import parties.Party;

public class Voter extends Person {
    protected District district;
    private Candidate votedFor;
    protected Party party; // Partia `null` oznacza, że wyborca nie ma preferencji partyjnych

    public Voter(String name, String surname, District district, Party party) {
        super(name, surname);
        this.district = district;
        this.party = party;
    }

    // Zwraca listę potencjalnych kandydatów, z których wyborca wylosuje jednego
    protected List<Candidate> possibleCandidates() {
        return district.getCandidates(party);
    }

    public Candidate chooseCandidate() {
        List<Candidate> candidates = possibleCandidates();
        java.util.Random randomGenerator = new java.util.Random();
        int randomIndex = randomGenerator.nextInt(candidates.size());
        votedFor = candidates.get(randomIndex);
        return votedFor;
    }

    public Candidate getVotedFor() {
        return votedFor;
    }

    // Działania partii nie wpływają na wyborców, którzy nie ważą cech kandydatów
    public void changeWeights(int[] changeVector) {}

    // Potencjalna zmiania działań partii na wyborców bez wag wynosi 0
    public int possibleChange(int[] changeVector, Party party) {
        return 0;
    }
}