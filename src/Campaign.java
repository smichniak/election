public class Campaign {
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

    }

}
