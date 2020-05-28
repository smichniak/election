public class Candidate {
    private String name;
    private String surname;
    private String party;
    private int district;
    private int position;
    private int[] traits;
    private int votes;

    public Candidate(String name, String surname, String party, int district, int position, int[] traits) {
        this.name = name;
        this.surname = surname;
        this.party = party;
        this.district = district;
        this.position = position;
        this.traits = traits;
        this.votes = 0;
    }

    public void vote() {
        votes++;
    }


}
