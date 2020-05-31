import java.util.List;

public abstract class Party {
    private String name;
    protected int budget;
    private int mandates;

    @Override
    public String toString() {
        return name;
    }

    public abstract boolean runCampaign(List<Campaign> campaigns);
}
