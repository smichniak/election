public class Candidate extends Person {
    private int position;
    private int[] traits;
    private int votes;

    public Candidate(String name, String surname, int position, int[] traits) {
        super(name, surname);
        this.position = position;
        this.traits = traits;
        this.votes = 0;
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }

    public int getPosition() {
        return position;
    }

    public int getVotes() {
        return votes;
    }

    public void vote() {
        votes++;
    }

    public int traitValue(int trait) {
        return traits[trait-1];
    }



}
