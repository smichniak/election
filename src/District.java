import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class District {
    private int districtNumber;
    private List<Voter> voters;
    private Map<String, List<Candidate>> partyLists;
    private Map<String, Integer> votes;
    private Map<String, Integer> mandates;


    public District(int districtNumber, int numberOfVoters) {
        this.districtNumber = districtNumber;
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
        return voters.size();
    }

    public void addCandidate(String partyName, Candidate candidate) {
        partyLists.get(partyName).add(candidate);
    }

    public List<Candidate> getCandidates(String partyName) {
        if (partyName == null) {
            List<Candidate> candidates = new ArrayList<>();
            partyLists.values().forEach(candidates::addAll);
            return candidates;
        } else {
            return partyLists.get(partyName);
        }
    }

    public void merge(District district) {
        for (String partyName : district.partyLists.keySet()) {
            int candidates = partyLists.get(partyName).size();
            for (Candidate candidate : district.partyLists.get(partyName)) {
                candidate.setPosition(candidate.getPosition() + candidates);
                partyLists.get(partyName).add(candidate);
            }
        }
        voters.addAll(district.voters);
    }

    public void countVotes() {
        for (String partyName : partyLists.keySet()) {
            for (Candidate candidate : partyLists.get(partyName)) {
                votes.put(partyName, votes.getOrDefault(partyName, 0) + candidate.getVotes());
            }
        }
    }


}
