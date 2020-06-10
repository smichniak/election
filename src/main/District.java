package main;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import parties.*;
import voters.*;

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
        // Liczbę wyborców w okręgu dostajemy na początku, ale może się ona zmienić przy scalaniu okręgów
        return Math.max(numberOfVoters, voters.size());
    }

    public List<Candidate> getPartyList(Party party) {
        return partyLists.get(party);
    }

    public int getMandates(Party party) {
        return mandates.get(party);
    }

    public void addParty(Party party) {
        partyLists.put(party, new ArrayList<>());
    }

    public void addCandidate(Party party, Candidate candidate) {
        partyLists.get(party).add(candidate);
    }

    public List<Candidate> getCandidates(Party party) {
        if (party == null) { // Null oznacza kandydatów z dowolnej partii
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
                // Przy scalaniu okręgów dopisujemy kandydatów z drugiego na koniec listy pierwszego okręgu
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
        // Mandatów rodzielamy 10 razy mniej niż jest głosujących
        mandates = method.mandates(votes, getNumberOfVoters() / 10);
    }


}
