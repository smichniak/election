public class Campaign implements Comparable<Campaign> {
    private int[] traitChangeVector;
    private District district;
    private int cost;

    public Campaign(int[] traitChangeVector, District district) {
        this.traitChangeVector = traitChangeVector;
        this.district = district;
        cost = 0;
        for (int traitChange : traitChangeVector) {
            cost += Math.abs(traitChange);
        }
        cost *= district.getNumberOfVoters();
    }

    public int getCost() {
        return cost;
    }

    public void run() {
        for (Voter voter : district.getVoters()) {
            voter.changeWeights(traitChangeVector);
        }
    }

    public int possibleChange(Party party) {
        int change = 0;
        for (Voter voter : district.getVoters()) {
            change += voter.possibleChange(traitChangeVector, party);
        }
        return change;
    }


    @Override
    public int compareTo(Campaign campaign) {
        return this.cost - campaign.cost;
    }
}
