package voters;

import main.District;
import parties.Candidate;
import parties.Party;

public class WeightedVoter extends ComparingVoter {
    private int[] weights;

    public WeightedVoter(String name, String surname, District district, Party party, int[] weights) {
        super(name, surname, district, party);
        this.weights = weights;
    }

    // Suma ważona cech dantego kandydata
    private int candidateSum(Candidate candidate, int[] weights) {
        int weightedSum = 0;
        for (int i = 0; i < weights.length; ++i) {
            weightedSum += weights[i] * candidate.traitValue(i);
        }
        return weightedSum;
    }

    @Override
    protected int compare(Candidate candidate1, Candidate candidate2) {
        int weightedSum1 = candidateSum(candidate1, weights);
        int weightedSum2 = candidateSum(candidate2, weights);
        return weightedSum1 - weightedSum2;
    }

    @Override
    public void changeWeights(int[] changeVector) {
        for (int i = 0; i < changeVector.length; ++i) {
            int newWeights = weights[i] += changeVector[i];
            weights[i] = Math.max(-100, Math.min(100, newWeights));
        }
    }

    @Override
    public int possibleChange(int[] changeVector, Party party) {
        int sumChange = 0;

        int[] changedWeights = weights.clone();
        for (int i = 0; i < changeVector.length; ++i) {
            int newWeights = weights[i] += changeVector[i];
            changedWeights[i] = Math.max(-100, Math.min(100, newWeights));
        }
        for (Candidate candidate : district.getCandidates(party)) {
            // Zmiana sum ważonych dla każdego kandydata w danej partii
            sumChange += candidateSum(candidate, changedWeights) - candidateSum(candidate, weights);
        }
        return sumChange;
    }
}
