public class WeightedVoter extends ComparingVoter {
    private int[] weights;

    public WeightedVoter(String name, String surname, District district, String partyName, int[] weights) {
        super(name, surname, district, partyName);
        this.weights = weights;
    }

    @Override
    protected int compare(Candidate candidate1, Candidate candidate2) {
        int weightedMean1 = 0;
        int weightedMean2 = 0;

        for (int i = 0; i < weights.length; ++i) {
            weightedMean1 += weights[i] * candidate1.traitValue(i);
            weightedMean2 += weights[i] * candidate2.traitValue(i);
        }

        return weightedMean1 - weightedMean2;
    }

}
