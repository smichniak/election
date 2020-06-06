import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class District {
    private int districtNumber;
    private int numberOfVoters;
    private List<Voter> voters;
    private Map<Party, List<Candidate>> partyLists;
    private Map<Party, Integer> votes;
    private Map<Party, Integer> mandates;


    public District(int districtNumber, int numberOfVoters) {
        this.districtNumber = districtNumber;
        this.numberOfVoters = numberOfVoters;
        this.voters = new ArrayList<>();
        this.partyLists = new HashMap<>();
        this.votes = new HashMap<>();
    }

    public void addVoter(Voter voter) {
        voters.add(voter);
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public int getDistrictNumber() {
        return districtNumber;
    }

    public int getNumberOfVoters() {
        return numberOfVoters;
    }

    public void addCandidate(Party party, Candidate candidate) {
        partyLists.get(party).add(candidate);
    }

    public List<Candidate> getCandidates(Party party) {
        if (party == null) {
            List<Candidate> candidates = new ArrayList<>();
            partyLists.values().forEach(candidates::addAll);
            return candidates;
        } else {
            return partyLists.get(party);
        }
    }

    public void merge(District district) {
        for (Party party : district.partyLists.keySet()) {
            int candidates = partyLists.get(party).size();
            for (Candidate candidate : district.partyLists.get(party)) {
                candidate.setPosition(candidate.getPosition() + candidates);
                partyLists.get(party).add(candidate);
            }
        }
        voters.addAll(district.voters);
    }

    public void countVotes() {
        for (Party party : partyLists.keySet()) {
            for (Candidate candidate : partyLists.get(party)) {
                votes.put(party, votes.getOrDefault(party, 0) + candidate.getVotes());
            }
        }
    }

    public void distributeMandates(MandateDistribution method) {
        mandates = method.mandates(votes, voters.size() / 10);
    }


}
