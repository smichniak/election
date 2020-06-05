import java.util.List;

public abstract class Party {
    private String name;
    protected int budget;
    private int mandates;

    @Override
    public String toString() {
        return name;
    }

    public void addMandates(int mandates) {
        this.mandates += mandates;
    }

    public abstract boolean runCampaign(List<Campaign> campaigns);
}
