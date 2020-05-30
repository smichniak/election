import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class District {
    private int districtNumber;
    private Map<String, List<Candidate>> partyLists;
    private Map<String, Integer> votes;

    public District(int districtNumber, Map<String, List<Candidate>> partyLists) {
        this.districtNumber = districtNumber;
        this.partyLists = partyLists;
    }

    private int getDistrictNumber() {
        return districtNumber;
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
    }

    public void countVotes() {
        for (String partyName : partyLists.keySet()) {
            for (Candidate candidate : partyLists.get(partyName)) {
                votes.put(partyName, votes.getOrDefault(partyName, 0) + candidate.getVotes());
            }
        }
    }


}
