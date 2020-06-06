import java.util.List;

public abstract class Party {
    private String name;
    protected int budget;
    private int mandates;

    public Party(String name, int budget) {
        this.name = name;
        this.budget = budget;
        this.mandates = 0;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addMandates(int mandates) {
        this.mandates += mandates;
    }

    public abstract boolean runCampaign(List<Campaign> campaigns);
}
